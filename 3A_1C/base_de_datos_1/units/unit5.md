# Unidad 5: Languaje de consulta SQL

Structures Query Language, creado por un gropu de IBM en 1974. Llamado originalmente SEQUEL, pero no habia convenciones ni reglas, 
esto se introdujo en SQL 1. 

Sintacticamente se parece al calculo relacional, en particular al de tuplas.

SQL se ecnarga de todo destro de una DBMS, del diseno interno, vista y manipulacion. El concepto de portabilidad tambien aplica a bases de datos.

> **Nota**: Para el primer parcial va a entrar casi todo SQL 2. 

## Lenguaje de definicion de datos(DDL)

Involucra tipos, dominios y tablas.

### Tipos

Hay que elegir el tipo de dato correcto para un atributo.

- Se restringen los valores.
- Se limitan las operaciones a realizar.
- Influye en el espacio que ocupara la tabla.

Esto garantiza la integridad semantica y permite representar las restricciones de dominio del modelo relacional.

SQL2 ofrece:

- `CHAR/CHARACTER(tam)`
- `VARCHAR/CHARVARYING/CHARACTERVARYING(max)` Estos pueden introducir **hops** dentro de la base, haciendo mas lenta la lectura(en la memoria no importa).
- `INTEGER/INT`
- `SMALLINT`
- `FLOAT`
- `REAL`
- `DOUBLE PRECISION`
- `NUMBER/DECIMAL/DEC`
- `DATE` yyyy-mm-dd
- `TIME` hh:mm:ss   (lo menos compatible de SQL2)
- `TIMESTAMP` fecha y hora(como minimo 5 lugares para la reaccion de segundo
- `INTERVAL` permite sumar o restar algunos de los tipos anteriores

### Dominios

Permite crear un dominio *a priori*, parecido al typedef de C.

``` SQL2
CREATE DOMAIN nombre built_in [valor_default][restriccion]:
```

Sin embargo, no es muy compatible, asi que no lo vamos a usar.

### Creacion de tablas persistentes

- Identificarla con nombre
- Indicar sus columnas
- Marcar sus claves
- Eventualmente, indicar claves foraneas y restricciones

SQL(a diferencia de un lenguaje verdaderamente relacional), permite que las tablas no tengan claves definidas, 
y por lo tanto permiten que las tuplas esten repetidas, en vez de sets tenemos BAGS.

### Destruccion de tablas

``` SQL2
DROP TABLE nombre_tabla [CASCADE|RESTRICT];
```

### Cambio de esquema de tablas

- Agregar atributos
- Elimniar atributos
- Cambiar la definicion de un atributo
- Agregar restricciones
- Eliminar restricciones

Por ejemplo:

``` SQL2
ALTER TABLE nombre tabla
    ALTER nombre_atributo DROP DEFAULT
```

## Lenguaje de manipulacion de datos(DML)

### Insercion de tuplas

Inserta una tupla por vez, mas tarde veremos insercion masiva.

``` SQL2
INSERT INTO nombre_tabla [(lista_atributos)]
    values(lista_valores);
```

La lista se atributos solo se puede omitir si ingreso todos los atributos en la lista de valores.

> **Nota**: Lo que esta entre corchetes es opcional.

### Borrado de tuplas 

Se eliminan tuplas ya ingresadas, la condicion puede ocasionar cierta accion por parte del DBMS (borrado en cascada).

``` SQL2
DELETE FROM nombre_tabla [WHERE condicion];
```

No se puede usar `telefono = null`, sino se usa `is null`.

### Actualizado de tuplas

Modifica tuplas ya existentes.

## Lenguaje de manipulacion de datos (DML)

Usado para recuperar tuplas. Recordad que SQL no respeta todas las restricciones del modelo relacional.

- Elementos repetidos
- Tablas sin clave primaria

En general SQL no elimina repeticiones al buscar una tupla.

- No pierde tiempo.
- Resulta util cuando se usan funciones de agregacion.
- Es horrible cuando se ven reportes con filas repetidas.

SQL solo elimina repetidos cuando se usan operaciones conjuntistas:

- `UNION`
- `INTERSECT`
- `EXCEPT`

Explicitamente `ALL` mantiene duplicaciones, si se especifica.

Sintaxis:

``` SQL2
SELECT [DISTINCT]
atrib1 [AS otroNombre1],
....
atribN [AS otroNombreN]

FROM lista_de_tablas
[WHERE condicion]
[GROUP BY lista_atributos_que_agrupan]
[HAVING condicion_para el grupo]
[ORDER BY lista_atributos]
```

### Ambiguedades y soluciones

- Usar en el `FROM` mas de una tabla con el mismo nombre de atributo(usar tabla.atribRepetido).
- Usar en el `FROM` la misma tabla mas de una vez(usar alias).

### Expresiones en la clausura `FROM`

#### Producto cartesioano 

``` SQL2
r CROSS JOIN s

(r CROSS JOIN s) AS 
    nueva_relacion(lista_atributos_renombrada)

r , s
```

#### Junta natural

Las tuplas que no matchean no aparecen en el resultado.

``` SQL2
r NATUYRAL JOIN s

(r NATURAL JOIN s) AS
    nueva_relacion(lista_atributos_renombrada)
```

#### Tetha Join

Es la junta default del SQL.

``` SQL2
r [INNER] JOIN s ON condicion

(r [INNER] JOIN s ON condicion) AS
    nueva_relacion(lista_atributos_renombrada)
```

#### Semijunta natural o outer join

``` SQL2
r LEFT OUTER JOIN s ON condicion

(...) AS ...
```

Tambien esta `RIGHT` y `FULL`.

### Consultas anidadas

Pueden participar del `FROM`, `WHERE` y `HAVING`.

### Expresiones en la clausura `WHERE`

Expresiones que se evaluen con `TRUE` o `FALSE`.

#### Operadores logicos

- `AND`
- `NOT`
- `OR`

#### Operadores relacionales

- \<
- \<=
- \>
- \>=
- \<\> (diferente)

#### Operador `LIKE` para strings

Con comodin % matchea con cualquier substring, con _ matchea con cualquier caracter.

#### Operadores `IN`, `=ANY`, `=SOME`

`IN` determina que un elemento esta dentro de una lista.

PostgreSQL no permite `=ANY` y `=SOME` con listas fijas.

#### Operadores `EXISTS` y `NOT EXISTS`

Los vistos en logica.

> **Nota**: Los query nunca van a ser no seguro, pues no existe la negacion. El prompt siempre es positivo.

> **Nota**:  `SET datestyle to DMY;`
