# Unidad 9: Programacion embebida - C

(Completar, clase 18/11/25 de 13hs a 13:45hs)

## Manejo de errores

### Variable indicator

Variable compartida entre el DBMS y la aplicacion cliente que se asocia a cada variable host que 
se sospeche pueda acarrear un problema.

Si esta en 0 entonces la variable host esta OK, si es -1 entonces hubo un NULL y no hay que 
usarla.

## Cursor explicito

Como se podria hacer en programacion embebida en C? como `SELECT` no es embebible se 
debe utilizar `SELECT` con `INTO`.

Muy util usar:

``` SQL
EXEC SQL WHENEVER NOT FOUND DO BREAK;
```

## Cosas a tener en cuenta

- Si hay que recuperar informacion ordenada pedirlo en el cursor: NUNCA ORDENAR EN EL CLIENTE.

Esto se debe a que el servidor tiene mucha capacidad de computo, no es el caso del cliente.

- Las variables compartidas(`host variables`) deben ser declaradas en funcion del tipo y 
size que tienen en la base de datos.

Podria ocurrir que el servidor este en 64 bits mientras que el cliente tenga 32 bits. No hay 
que asumir el size de los tipos pues sino ocurrira truncamiento.

> Todo motor de base de datos indica como cambia el size de los tipos dependiendo del entorno, 
hay que prestar atencion que variables y columnas se usaron.

## Claves ficticias

Evitan usar los bytes que podria usar una clave foranea, es una clave que ocupa el size de un 
entero donde se trata de un id. Esto es especialmente util si la clave foranea es un 
arreglo de caracteres de 150 bytes.

Sin embargo, este tipo de desiciones no las realiza un programador, sino que se encarga el 
administrador de la base de datos pues se encarga de evaluar la informacion que se esta guardando.

Entonces, no es comun que un programador lo haga. Por otro lado, es un tradeoff, espacio v.s. 
performance.

Ahora, como definimos el identificador unico, como puede saber una tupla a que otra tupla se 
esta refiriendo. Obviamente no le vamos a pedir al usuario el numero. Una mala estrategia seria 
buscar el maximo id y agregarle 1, pues las bases de datos manejan concurrencia y habria 
condiciones de carrera.

### Buena tecnica

Utilizar secuencia o valor autonumerico ofrecidos por los DBMS(todos los motores de base de datos 
ofrecen esto). PostgreSQL tiene todas las opciones, vamos a ver solo una: `SEQUENCE`.

Esto tiene un problema, si se borra una tupla con un ID quedaran huecos.

Otra opcion es usar `SERIAL`.

Todas estas opcionas garantizan un valor automatico, no repetido.

---

Pero, como se refencia desde otra tabla? La estrategia corresponde a buscar el numero con un 
`SELECT`. 

> Es muy importante conocer las tecnicas: `INSERT RETURNING INTO`(evita realizar un `SELECT`).

### Dos tablas se autoreferencian

Para la insercion hay que indicar que no **valide** la restriccion hasta realizar **commit**, y 
por supuesto siempre insertar conjuntamente en ambas tablas.

> Creacion de indices(Arbol B) sin necesidad de unidad debe ser considerada con cuidado.

## Transacciones y programacion embebida

Una transaccion es un conjunto de sentencias, en vez de ejecutarse sueltas se deben ejecutar como 
un todo. Un buen ejemplo es la transferencia bancaria, debe ocurrir todo: Actualizar saldo en cuenta 
A, actualizar saldo en cuenta B.

En dicho caso, se realiza atomicamente utilizando un `COMMIT` y de deshace con un `ROLLBACK`.

> Una vez indicado el commit o rollback no es posible arrepentirse ya que se da comienzo a 
otra nueva transaccion.

Tienen dos cosas asociadas:

1. Garantizar consistencia de informacion
2. Permitir manejar concurrencia

> Todo o nada

> Los DBMS permiten trabajar con transacciones serializables que se comportan como si fueran 
seriales.

El problema de hacerlo serial es que no es interactivo, hay que esperar a que todo termine antes 
de empezar con otra cosa. Lo que hay que hacer es ir cambiando el nivel de aislamiento de las 
transacciones para permitir intercalar las trasacciones.

Otro tipo de transacciones es la serializable, donde el motor analiza lo que piden y las intercala 
hasta cierto nivel.

SQL2 y SQL3 agregaron la posibilidad de marcar una transaccion como `READ ONLY` o `READ WRITE`
(default) con `SET TRANSACTION`. Ademas, se puede especificar el nivel de insolacion:

- `READ UNCOMMITED`
- `READ COMMITED`
- `REPEATEABLE READ`
- `SERIALIZABLE`

### Problemas que soluciona la aislacion

- **Dirty read**: T1 actualiza un valor, T2 lo lee y luego T1 hace rollback. 
Quedando dirty read al read de T2.
- **Nonrepeatable read**: T2 lee un valor de un atributo, T1 lo actualiza/borra con commit y T2 
vuelve a leerlo, obteniendo un valor diferente.
- **Phantoms**: Similar al anterior, pero en vez de update es insert. T2 lee tuplas y T2 
inserta una nueva tupla con commit.

Nunca ocurrira alguno de estos programas si se es estricto y la base de datos es serializable.
Por eso es el default, pero si se necesita performance se podria relajar las restricciones.

> **Nunca** comenzar una transaccion cuando se abre una ventana de dialogo. Realizar 
transacciones atomicas.




