# Unidad 3: Modelo de representacion/implementacion: Modelo relacional

Veremos el siguiente nivel de abstraccion, el de **implementacion**(logico). En la unidad anterior vimos el conceptual y el fisico lo vamos a ver muy por arriba.

## Un poco de historia

El modelo(relacional) fue creado por Codd, investigador de IBM, en 1970. Ya existian otros modelos como las jerarquicas,
pero se manejaba por medio de punteros y se navegaba de puntero a puntero buscando informacion, almacenada en disco. Tambien estaba el modelo de red, tipo grafo, pero similar.

Estos modelos anteriores se usaban mucho debido a su rapidez, lo cual era sumamente importante debido a la capacidad de las computadoras del momento.
A pesar de que funcionaban, Codd proyecto para adelante y se dio cuenta que a largo place iba a haber problemas frente a cambios en la estructura. 
De manera que, antes de la distribucion masiva de computadoras, penso en un modelo que se prepare para eso(estaba en IBM, conocia la tendencia).

El modelo se basaba en conjuntos(sustentado por teoria matematica), pero no era tan rapido como con punteros el uso de conjuntos, se dejo de lado la idea. 
Mas adelante, Codd propone el modelado grafico(entidad-relacion) y eso atrae atencion porque la representacion grafica permitio que sea mas facil de entender(antes era muy matematica).
Poco despues surge la primer base de datos relacional, rapidamente reemplazando al jerarquico y al de red, siendo usado hasta dia de hoy por la mayoria de las empresas.

## Modelo relacional

Representa todo el mini-universo por medio de relaciones en el sentido matematico(tuplas), no tiene tantos elementos como el entidad-relacion(entidad debil, fuerte, etc.).

### Atributo

Tiene un **Dominio**. Indica valores atomicos posibles que puede tomar cierto atributo(String, integer, char). Pero se agrega un valor especial llamado `NULL`, **three value logic**. 
Significa que(alguno de estos): No aplica; Es aplicable, pero desconocido; Ni siquiera se sabe si es aplicable.

**Ejemplo**: Para representar una casa, puede ser lote, piso, numero, etc. Entonces, se coloca `NULL`, representando tres cosas al mismo tiempo.

### Esquema

$$
    R(A_1. A_2, \text{...}, A_n)
$$

Esta dado por su nombre $R$ y la lista de atributos $A_1$, ..., $A_n$ donde cada $A_i$ es el nombre del rol que juego algun dominio en dicho esquema.
Varios atributos pueden tener igual dominio, pero cada uno tiene un rol diferente(nombre).

El esquema cambia con menos frecuencia que los datos, podria no cambiar nunca.

> **Nota**: En en el libro esta muy bien explicado

### Relacion 

Una relacion $r$ es un subconjunto del producto cartesiano de los $N$ dominios que definen $R$.

Dominios $D_1, D_2, \text{...}, D_n \Rightarrow r \subseteq D_1 \times D_2 \times \text{...} \times D_n$

**Nota**: Es imposible que una relacion tenga tuplas repetidas.

Cual es la maxima cantidad de elementos que puede tener una relacion(teniendo en cuenta que no se repiten)? 
Seria el producto cardinal de cada dominio, pero puede tener restricciones que lo hagan menor. Si no hay restricciones:

$$
    |D_1| \cdot |D_2| \cdot \text{...} \cdot |D_n|
$$

Normalmente nunca llegan al tope. 

Por otro lado, las relaciones pueden verse como fotos instantaneas del mini-universo. Tiene dos partes:

- **Intension**: Encabezado con nombre de cada atributo interviniente.
- **Extension**: Cada fila o tupla muestra una coleccion de valores relacionados.

## Relacional vs Entidad/Relacion

- El modelo entidad/relacion tiene conjunto de entidades, mientras que el relacional tiene un esquema de relaciones.
- El modelo entidad/relacion tiene conjunto de relaciones, mientras que el relacional tiene un esquema de relaciones.
- El modelo entidad/relacion tiene entidades, mientras que el relacional tiene un conjunto de tuplas en la tabla.
- El modelo entidad/relacion tiene relaciones, mientras que el relacional tiene un conjunto de tuplas en la tabla.
- El modelo entidad/relacion tiene una entidad, mientras que el relacional tiene una tupla de una tabla.
- El modelo entidad/relacion tiene una relacion, mientras que el relacional tiene una tupla de una tabla.

> **Nota**: La relacion en la relacional esta de forma implicita.


## Notacion para el modelo relacional

Esto solo se usa para definiciones y demostraciones.

- **Esquemas de relacion**: R, S, T.
- **Atributos de un esquema de relacion**: A, B, C, A1, A2, ..., An.
- **Instancias de los esquemas**: r, s.
- **Valores de los atributos**: a, b, c, a1, a2, ..., an.
- **Filas o tuplas**: t, u, v, w(o con subindices).
- **Conjunto de atributos**: X, Y, Z o ABC en vez de {A, B, C}.
- **Restriccion de una tupla a un conjunto de atributos**: t\[X\] (o cualquier letra permitida). X seria el atributo que me interesa.

## Restricciones del modelo relacional

Todas las restricciones hay que representarlas lo **antes posible**, es decir, si se puede representarlas en E/R, sino mantenerlas en el relacional. Son una de las principales ventajas de este modelo.

### Restricciones de domino

Todo atributo debe ser atomico

### Restricciones de clave

No puede haber tuplas repetidas en una relacion(garantizado por teoria conjuntista). Pero puede haber **menos** atributos que garantices la unicidad de las tuplas. Definicion formal de clave:

> Existe un subconjunto de atributos X dal esquema de relacion R, tal que toda instancia r verifica que si t1 y t2 son tuplas de dicha instancia y t1 $\ne$ t2 entonces t1\[X\] $\ne$ t2\[X\]. 
X es una **superclave** en este caso.

Mas economicamente, conviene tener una **clave candidata**, es decir, es superclave y minima respecto de la propiedad de ser superclave(si se remueve alguno de sus atributos deja de serlo).
Es decir, aquella que me permite discriminar las entidades, pero minimal. Hace que sea mas barato verificar que un elemento no este repetido para la base de datos.

### Restriccion de integridad de entidad

Ningun atributo que forme la clave puede ser `NULL`. Pues `NULL` es ambiguo, entonces no se puede usar para discriminar. Todos los motores que implementan este modelo siguen esta restriccion.

### Restriccion de integridad referencial

Dependencia de inclusion, se especifica entre dos esquema de relaciones y se usa para garantizar consistencia entre ambas: **Clave Foranea(Foreign key)**. Cumple con:

- Atributos de X tienen el mismo dominio que la clave candidate.
- Se verifica que siendo r una relacion de R y s una relacion de S, entonces para toda tupla t de r existe una tupla u de s tal que t\[X\] = u\[Y\] o bien t\[X\] es `NULL`.

(completar)

> **Nota**: Definicion formal en apuntes.

> **Nota**: Las proximas tres las vamos a ver mas adelante, despues del primer parcial

### Restriccion de dependencias funcionales

###...

###...

## Lenguajes del DBMS

- DDL: De definicion de datos, debe perimitirme crear....


## Operacion de insercion

Se usa para gregar una tupla a una relacion, se debe proveer los valores de los atributos. 
El usuario facilmente puede violar alguna restriccion(de dominio, clave, integridad, y integridad referencial).

**Ejemplo**: Podria violar una restriccion de clave si quiere insertar una clave que ya existe(repetida).

**Ejemplo**: Podria violar restriccion de integridad referencial si se inserta una tupla cuya clave foranea refiere a una tupla que no existe en la relacion 
referida(si se aceptan `NULL` no habria porblema).

## Operacion de borrado 

Se usa para eliminar tuplas(pueden ser varias) en una relacion. Se debe proveer solo valores de los atributos que permiten identificar.
Solo podria dar problemas en restriccion de integridad referencial, pero no en restriccion de dominio, clave, o integridad de entidad.

Que deberia hacer el DML si el usuario viola las restricciones:

- `ON DELETE NO ACTION`: Rechaza la eliminacion.
- `ON DELETE SET`: Permite borrado, pero modifica las claves foraneas que referencias las tuplas que se quiere borrar. 
Ponerles `NULL`, un valor default o pedirle al usuario que lo indique. La que vamos a usar.
- `ON DELETE CASCADE`: Borrar en cascada(recursivamente) todas las tuplas de las distintas relaciones que violen esta restriccion.

## Operacion de modificacion

Se usa para cambiar los valores de ciertas tuplas(pueden ser varias) en una relacion. Se debe proveer los valores de los atributos que permiten identificarlas y los valores que se quieren cambiar.

> **Nota**: Para que lo pensemos(esta en apuntes de campus).

## Pasaje de entidad/relacion a relacional

### Pasos para un buen disenador

1. Reunir requerimientos del sistema
2. Crear un esquema conceptual
3. Implementar la base con algun DBMS comercial
4. Hacer diseno fisico de la base de datos(indices)
5. Implementar, cargar los datos necesarios y examinar el funcionamiento del sistema

### Mapeo de entidades fuertes

En el caso de tener compuesto(los multivaluados se dejan para mas adelante):

1. Ir directamente a lo que son las claves atomicas, los compuestos se descomponen y se toman los valores, ignorando la compuesta.
2. Si el compuesto era clave, entonces sus valores pasan a formar la clave.

### Mapeo de entidades debiles

Se colocan los atributos de la entidad y luego los atributos clave de la entidad con la cual se relaciona. 
Luego, la clave sera la clave debil mas los atributos clave agregador. Si tiene clave foranea, esta sera las claves agregadas. 
La foranea no pueden ser `NULL` pues son parte de la clave a nivel relacional y sera `ON DELETE CASCADE`.

### Mapeo de atributos multivaluados

En una tabla aparte se coloca el multivaliado y las claves de la entidad. Donde la clave de la tabla serian todos los atributos. 
Sera `NOT NULL` y `ON DELETE CASCADE`. 

### Mapeo de relaciones

#### Binaria M:N

1. Crear un esquema de relacion para representar la relacion. 
2. Agregarle los atributos atomicos o sus partes atomicas de los compuestos de la misma. 
3. Incluir claves foraneas para que referencien...

Es imposible de embeber en alguna de las dos participantes, si o si hay que construir una relacion aparte que consiste de las claves(seran claves) y 
un atributo extra que es el de la relacion(en caso de tener). 

Debido a las claves foraneas(las claves de una entidad son una y las claves de la otra son otra), entonces ambas seran `NOT NULL` y `ON DELETE CASCADE`.
Si alguna entidad tubiera participacion total, no se podra representar con `ON DELETE CASCADE`(foreign key). Se necesita programacion avanzada que veremos mas adelante para resolver este caso.

Podria con esta tecnica representarse relaciones 1:N o 1:1? Si, pero es mas practico embeberlo en las relaciones existentes, evitar agregar mas tablas cuando sea posible por motivos de eficiencia.

#### Binarias 1:N

No muy frecuente de encontrar, casos:

- Ninguna de las dos tiene participacion total, entonces hay que pensar en donde embebo la relacion. No hay opcion, se debe embeber en la entidad que sea N. 
Agregando a la tabla la clave de la que sea 1, sin olvidar el atributo de la relacion. Las claves de 1 obviamente son claves foraneas, 
pero pueden ser `NULL` pues la cardinalidad de la relacion es una cota superior.

- Alguna tiene participacio total, arranca igual, pero si la entidad con N tiene relacion total, entonces la clave foranea debe ser `NOT NULL` y `ON DELETE CASCADE`. 
Si la entidad con 1, tiene perticipacion total, se requiere programacion avanzada.

#### Binaria 1:1

Si ninguna tiene participacion total, podria embeberlo de cualquiera de las dos, es indistinto(FK `NULL`, `ON DELETE SELECT`?). Si se da el caso de participacion total, entonces necesito que la que 
tiene participacion total se borra si la otra tambien si borra(no tiene participacion total). Por lo tanto, FK es `NOT NULL` y `ON DELETE CASCADE`, caso contrario, 
se requiere de programacion avanzada(limite inferior).

Si ambas entidades tienen participacion total, debo poder garantizar que se borre en cascada, entonces ambas claves son FK es `NOT NULL` y `ON DELETE CASCADE`.

Finalmente, depende del programador, si realmente estan tan acopladas, conviene unirlas en una unica tabla, pues si borro una tengo que borrar la otra. Las nuevas claves seran 
las claves de las entidades, pero puede ser una sola la clave, no hay motivo para extender la clave.

#### Ternaria M:N:P

No puede embeberse en ningun lado, debe ir a una relacion aparte, las unicas que pueden embeberse son las unarias y las binarias(menos M:N). Si o si, 
la nueva relacion va a tener las claves de todas las entidades, asi se mapean todas, pero difieren en la clave. 

Como clave tiene la conbinacion, deja de tarea las FK.

#### Ternaria 1:M:N

**Razonamiento**: Cuando es ternaria, cuando yo completo dejo un lado de libertad(en este caso significa combinar de a dos). 
Veo que no tiene sentido que la combinacion de todas las claves no puede ser clave, 
pues una clave de entidad 1 como mucho aparece una vez en esa combinacion. Por lo tanto, se deja afuera la clave de la entidad con 1 y la clave termina siendo las claves restantes combinadas.

#### Ternaria M:1:1

Similarmente, las claves seran las de la entidad con M y las de una entidad con 1.

#### Ternaria 1:1:1

Ahora, la clave sera la combinacion de las claves de dos entidad con 1, se puede elegir(dejar un **grado de libertad**).

### Mapeo de Jerarquia generalizacion




