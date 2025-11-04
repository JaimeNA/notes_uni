# Unidad 8: SQL avanzado

SQL es muy declarativo, se pueden hacer muchas cosas con una sola sentencia, 
pero hay muchas cosas que no tiene(iteradores, variables).
Es preferible usar SQL, pero cuando hay algo que no se pueda hacer conviene usar otro lenguaje.

Cuando se mezclan muchos lenguajes es muy dificil realizar migraciones:

- Dificil de migrar
- Los programadores usan sus lenguajes predilectos

Entonces, el ANSI agrego sentencias procedurales para que usen dentro del DBMS lo que deseaban: "PSM".
Ocurrio que cuando ANSI especifico los estandares, muchos DBMS no lo obedecieron pues ya tenian su 
version hecha(ANSI tarda mucho en sacar estandares). Por lo que mucho de lo que pide ANSI esta 
implementado por muy pocos DBMS.

> Esto suele pasar con ANSI, nunca propone antes, sino que se une a las ideas que ya existen.

## Persistent Stored Module(PSM)

Codigo compilado y almacenado en la base de datos que podra ser ejecutado si se tiene permiso de 
ejecucion. Tiene ventajas edicionales:

- Permite factorizar codigo
- Permite reusar codigo
- Se almacena en la base de datos(evita trafico entre cliente y servidor ya que no esta del lado del 
cliente).

El ANSI diferencia entre **funcion** y **procedimiento**. Los procedimientos difieren de functions 
pues no pueden invocarse desde SELECT ni FROM ni WHERE pues acepta cambiar sus parametros, 
es decir, sus parametros pueden entrar vacios y volver con otro valor(IN/OUT/INOUT).

## PostgreSQL

No respeto nada de ANSI, pues se adelanto bastante y cuando vino ANSI ya estaba hecho todo y 
los programadores ya se habian adaptado.

Solo maneja `FUNCTION`, si retorna void podria considerarse como procedimiento.
Los parametros pueden ser IN/OUT/INOUT, pero solo se pasan por valor, nunca vuelve un parametro 
modificado. Entonces, los INOUT o OUT se manejan devolviendo un parametro nuevo en la salida.

- **Un solo `OUT` o `INOUT`**: Genera un `RETURNS` con eso, en este caso no tenemos que poner 
un `RETURNS` explicito.
- **Mas de uno**: Genera un `RETURNS RECORD`(tipo compuesto, como struct de C)
- **Si no hay**: Obligatoriamente tengo que declarar un `RETURNS` de lo que quiero devolver. 
Por ahi habria que decolver un `VOID` explicitamente

Permite "no cablear y parametrizar:

- Variables
- Parametros formales

Muy util para debuggear son los **bloques anonimos**(especifico de PstgreSQL y de Oracle). 
Entonces, se puede utilizar para probar PSMs con los `raise notice...`, un analogo a `printf`.

## Como se manejan los errores

Esto es tipico de una DB, no se puede comparar con C o Java. ANSI considera que hay dos 
tipos de errores:

- **Condicion**: Esperables o no graves
- **Excepcion**: Graves

Los errores se manipulan en una zona llamada SQLCA, ahi ocurre la comunicacion entre DBMS y aplicacion. Se usan las variables SQLCODE y SQLSTATE, su combinacion puede indicar distintos estados.

**Porque los separa el ANSI?** 

Si se llega a producir una **condicion** en el codigo y no hay declarado 
ningun manejador, la base de datos entiende que no era nada grave y continua como si nada.
Caso contrario, cede el control para que se maneje la excepcion.

Si se produce una **excepcion** y no hay manejador, termina el PSM y retorna al invocador(sigue en 
casada). Si no hay manejador, el motor no sigue de largo y termina **mal**.

Sin embargo, PostgreSQL no diferencia entre condiciones y excepciones, trata como excepciones.
Si no hay manejador, el DBMS siempre va a terminal anormalmente(mal).

> Para aprovechar las excepciones ya definidas, hay que usar los numero que no estan en uso.

## Algoritmo para manejar excepciones

Muy importante, no podemos meternos dentro de un motor de bases de datos, hay que conocerlo bien.

1. Deshace todas las sentencias SQL que hubiera realizado el(hace rollback).
2. Se fija si hay manejador de excepcion para ella en dicho bloque y procede con alguna 
de las siguientes 3 alternativas, segun el caso:

    - Si lo hay, entonces le transifere el control y la ejecucion sigue normal a partir de alli.
    - Si no lo hay y el bloque esta anidado en otro bloque, relanza la excepcion en el padre.
    - Si no lo hay y no esta anidado, relanza la excepcion y termina abnormalmente.

> Se aconseja no relanzar las excepciones hacia afuera, cada bloque debe ser responsable de 
intentar solucionar los errores que en el se produzcan.

## Cursor

Una forma sintactica para referenciar una tupla por vez cuando se tiene una coleccion.
Hay dos tipos:

- Cursor implicito
- Cursor explicito

### Implicito

Cuando el `LENGTH(RESULTSET) <= 1`. Se usan en funciones de agregacion siempre que no haya 
`GROUP BY` y cuando se usa `WHERE` con `PRIMARY KEY`.

Si se intenta de usar con mas de una tupla, PostgrSQL devuelve la primera que aparece y no lanza error(ORACLE si).

### Explicito

En cualquier otro caso, incluido el anterior. No hay manejador de exception NOT FOUND, 
sino que es una variable que hay que chequear como el errno de C. Es importante chequear la 
variable luego del `FETCH`.

Este tipo de cursor tambien puede modificar tuplas.

``` SQL
EXIT WHEN NOT FOUND
```

---

PostgreSQL tiene algunos atributos asociados a los cursores:

- `%FOUND`
- `%NOTFOUND`
- `%ROW_COUNT`

> Hay que tenes cuidado y no abusar de PSM, a veces se puede hacer de manera mas simple.

## Codigo activo

Ahora exite la posibilidad de poner **codigo activo**. Antes el modelo relacion tenia varias 
restricciones:

- Todo atributo atomico
- Toda relacion debe tener clave
- ...

Por otro lado, SQL2 tambien tenia restricciones.

### Restricciones de dominio

Son tipo de atributo, valor default. Se puede agregar un `CHECK` dinamico o estatico.

**Que pasaria si tengo `DEFAULT 100` y `CHECK(sueldo > 500)`?**

Nada, solo falla en insercion o modificacion.

Para evitar errores PostgreSQL solo permite hacer `CHECK`s estaticos.
ddfjlsdkfgjieei


Debe haber solo una `PRIMARY KEY` y el resto debe ser `UNIQUE`, aunque podrian ser todas 
`UNIQUE NOT NULL`. Por lo tanto, hay un motivo por el cual muchos programadores prefieren tener 
un `PRIMERY KEY`. Esto es porque es mas sencillo para usar como clave foranea, ya que nos evita 
escribir `ON DELETE...`.

### Restricciones avanzadas

1. **Check de tabla**: Se chequea cierta condicion que no versa sobre un solo atributo.
2. **Assertion**: Garantiza consistencia en **todo momento** y la primer aparicion de codigo 
activo sin contar las claves foraneas. Son mas potentes que `FOREIGN KEY`.

> PostgreSQL no posee Assertions. Las reemplazan por Triggers.

## Triggers

Mas complicado en sintaxis que los Assertion, pero es mas potente.
Hay que pensarlo asi: si algo falla, se deshace todo lo asociado al trigger.

Un elemento activo esta del lado del motor, no tiene ida y vuelta. Permite que ciertas acciones
sean ejecutadas automaticamente por el DBMS ante cierto evento(insert, delete, update, etc.).

Basicamente, un evento transfiere el control al codigo del trigger, luego hay un antes y un despues 
del evento en el cual se puede ejecutar el codigo de trigger en alguno de los dos. Tambien se 
podria chequear una condicion opcional(se podria hacer un if) que acciona un codigo o no. 
Tambien se puede especificar como se va a ejecutar el codigo. Entonces, para un trigger se puede 
especificar: 

- Timing
- Condicion
- `FOR EACH` o una sola vez(default).

> Ante un error se deshace toda la ejecucion del trigger, es lo mismo que con las acciones.

En la version de PostgreSQL se tomo la opcion `INSTEAD OF` para las vistas, se reemplaza la vista 
por lo que yo diga.

Tambien, a diferencia de ANSI, el codigo no va embebido, si o si hay que invocar a un 
**Trigger PSM**. No es un PSM cualquiera, tambien tiene que retornar un Trigger(depende de lo 
que retorne, como termina todo).

### Trigger PSM

No tiene argumentos, tiene variables que le llegan por entorno:

- Old
- New
- TN_NARGS y TN_ARGV[]

Como debe retornar?

#### row-level

Timing before o instead of:

- **Normal**: Devolver <> NULL.
- **Anormal**: Todo rollback, lanzar excepcion.
- **Normal, pero salteando operaciones**: Esta fea la forma, pero no esta mal. Querian que no falle 
como lo hace ANSI. Deja que el usuario haga lo que quiera, pero logeando la informacion. No 
va a ocurrir, pero al usuario no le salta la excepcion(si se quiere aumentar sueldo, por ejemplo).

Timing after:

- **Normal**: Devolver algo
- **Anormal**: Todo rollback, lanzar excepcion

#### Statement-level(no exite instead of)

- **Normal**: Devolver algo.
- **Anormal**: Todo rollback y lanza excepcion.

### Restricciones adiciones

- Before o ofter solo puede ser usada con tablas.
- WHen no puede tener subqueries ni invocar PSM.
- No se puede incluir explicitamente commit(guardar la informacion) o rollback.
- No se deben lanzar triggers recursivos(cascading triggers).


