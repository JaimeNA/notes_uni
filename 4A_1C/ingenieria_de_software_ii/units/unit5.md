# Unidad 5: Persistencia 

A la hora de elegir una solucion de persistencia, hay que considerar:

- Performance(accesos secuenciales o aleatorios)
- Transformacino de datos 
- Tolerancia a fallos

Por otro lado, hay 3 maneras de implementarlo:

- Archivos
- Prevalencia
- Bases de datos

## Archivos 

La mas sencilla, nuevamente pueden ser: 

- Planos(ej. CSV) 
- Estructurados(ej. XML)
- Archivos binarios(indexados o serializados) 

Los archivos planos son los mas sencillos de implementar y de leer a simple vista. 
CSV es de acnho variable pues es por filas y se pueden seguir agregando. El principal 
problema de estos archivos es que debido a su simplicidad tienen muchas restricciones. 
Por ejemplo, acceso aleatorio, solo se puede ir a un lugar empezando desde el principio. 
Son utiles cuando leo todo y escribo todo. 

Por otro lado, estan los archivos planos de ancho fijo, soluciona el problema de acceso 
aleatorio. Implica que el ancho es aleatorio es definido arbitrariamente, si tengo muy 
poco hay que poner padding y si tengo de mas hay que truncar. Sin embargo, restrictivo 
pues en el caso de tener que agregar un campo hay que cambiar todo. Una solucion que 
se suele ver es poner caracteres al final reservados para usos futuros. 

Serializacion transforma la informacion en un array de bytes, mientras los valores sean 
serializables. De manera que permite guardar valores en memoria. Sin embargo, trae 
problemas si cambia la manera en la que se serializa la informacion, generalmente se 
rompe. No se suele usar para persistencia, pero si para transmitir informacion entre 
programas. 

## Prevalencia 

Es un patron, los datos se mantienen en memoria en formato nativo, es llevar la 
serializacion al extremo. Las transacciones se registran, y se hacen **snapshot** a 
disco, permitiendo levantar de disco a memoria directamente. 

Implementaciones: 

- Prevayler(Java)
- Madeleine(Ruby)
- Bamboo(.net) 

En este caso, no sirve como mecanismo para expandir la capacidad de manipulacion de 
datos pues la memoria tiene un limite, y si quiero poner todo en memoria no se puede. 
Sin embargo, si solo quiero preservar estado, esta solucion es completa, flexible y 
de muy bajo costo. 

### Ventajas

- No hay transformación, los datos se guardan en el formato que los usa el programa.
- Alto grado de transparencia.
- La performance es muchísimo más alta.

### Desventajas

- La aplicación debe poder ser contenida en memoria.
-  Interoperabilidad.

## Bases de datos 

La forma en la que se guarda la informacion es un detalle de implementacion, no 
es nuestra responsabilidad. Permite acceso concurrente a la informacion, incluso por 
parte de distintos programas. 

Se estan desarrollando hace casi un siglo, lo cual significa que se sabe como fallan, 
son extremadamente predecibles y se sabe como trabajar con las distintas fallas. 
No se usa tener logica en la base de datos, hoy en dia los triggers ya son algo malo. 
Esto se debe a que con triggers se tendria que mantener dos logicas, la de la DB y la 
de la aplicacion, por lo tanto se tiene una DB tonta(los constraints como `NOT NULL`, 
etc. si se siguen usando). 

### Bases de datos relacionales transaccionales(OLTP) 

- Organiza datos en tablas(relacionales de filas y columnas) 
- Basada en teoria de algebra relacional y conjuntos 
- Posee tablas, contraints, keys, etc.
- Se debe normalizar
- Uso para la gestion de datos transaccionales 

### Bases de datos multidimensionales(OLAP) 

Estas se usa para analitica(reporting), casi nada de normalizacion. 

- Se almacenan grandes volumenes historicos 
- Se guarda desnormalizado(para no hacer joins)
- Se precalculan posibles agregaciones 
- Sin bases de datos **append-only**

Se tienen muy pocas tablas muy grandes. Simpre se populan a partir de una base de 
datos OLTP. Evitando joins aumenta la velocidad de obtencion de informacion. En general 
se actualiza pasando lo nuevo que haya en la base de datos OLTP periodicamente, 
usando un proceso batch llamado ETL(Extract, Transform & Load).

```
|-----------|           |------------|
| OLTP      |  -------> | OLAP       |
|-----------|   (ETL)   |------------|
```

> Se busca correr en momentos donde la carga del sistema sea poca

Generalmente las DB OLAP tienen un dia de delay, si se quiere algo mas real-time se 
debe utilizar otra tecnologia.

### Bases de datos de objetos 

No son tan populares. Guardan objetos complejos(no simples tablas). 
Lo mas comun es no usar esto y en cambio usar un ORM. 

## Mapeo objeto relacional(ORM) 

Mapea objetos a tablas y tablas a objetos. Por ejemplo, hibernate. Automatizan las 
operaciones mas comunes(CRUD). Facilitan la programacion, a costa de performance, y 
complejidad. 

## Impedancia objeto-relacion 

Viene de el detalle que el mundo de los objetos y el mundo relacional no sin 
isomorfos. Por tanto, se pierde informacion pasando de un lugar a otro. 

- Falta de herencia
- Identidad vs. Clave primaria 
- Inflexibilidad de las tablas 
- FKs vs. Relaciones bidireccionales 

## Escabilidad de bases de datos relacionales 

Las bases de datos relacionales pueden escalar: 

- Verticalmente 
- Horizontalmente 

El escalamiento horizontal, puede hacerse de dos formas distintas: 

- Primario - Secundario 
- Primario - Primario 

> En general, nadie quiere escalar verticalmente, es exponencialmente caro y retorno 
  marginal.

### Primario - Secundario 

Es el sistema mas comun, el primario se encarga de modificar los datos, mientras 
que los secundarios se encargan de proveer informacion, son read-only. Tener 
una mayor escala de operaciones de lectura soluciona la mayoria de los casos pues 
las operaciones de lectura suelen ser ordenes de magnitud de diferencia con las 
escrituras. Ademas de mejor performance, ofrece tolerancia a fallas, pues si falla 
un secundario hay mas, si falla el primario, los nodos secundarios se ponen de acuerdo y 
un secundario pasa a ser primario. 

### Primario - Primario 

Full-duplex, se pueden escalar tanto las lecturas como las escrituras, pero no es 
gratis. En este caso todos los nodos deben permanecer sincronizados. A medida que se 
escala horizontalmente se debe sincronizar aun mas instancias y cada transaccion se 
debe complir en todos los nodos antes de marcarla como exitosa. 

> Uno de los pocos motores de base de datos que lo soporta es Oracle 

## Teorema CAP 

Para todo sistema de almacenamiento de datos, solo se pueden elegir dos de 
las tres propiedades:

- Consistency(Al clients see current data regardless of updates or delete)
- Availability(The system continues to operate as expected even with node failures)
- Partition tolerance(The system continues to operate as expected despite network or 
  message failures) 

La base de datos relacion una vez que empieza a diverger no se puede volver a juntar, 
hay que elegir una nueva fuente de verdad y tirar la otra. Es decir, son 
C y A. A continuacion algunas practicas comunes para soportar el aumento continuo de 
la contidad de lecturas y escrituras en las bases de datos. 

### Sharding 

Teniendo tantos datos que ni el indice de una tabla entra en memoria, performance 
ya no es ni considerada. Entonces, se divide la base de datos en N copias 
estructurales iguals y cada una tiene informacion disociada. Ahora pasa a ser 
responsabilidad de la aplicacion garantizar la consistencia de los datos, esto se 
logro con: 

- Two phase commit(mas de una modificacion en paralelo, si las db commitean, listo)
- Retry until success
- Last write wins 
- etc. 

### Tablas como columnas 

Por cada columna se creaba una nueva tabla, luego, cuando habia que buscar informacion 
se hacia un join con todas las tablas. Esto es parte del origen de las DB no 
relacionales. 

### JSOM blobs 

Soluciom schema-less encima de MySQL, guardando la informacion como JSON. Permitia 
hace busquedas sin tener que buscar lo que habia dentro de cada JSON mmediante 
**index tables** y **entity tables**.

---

Comienzan entonces a crearse bases de datos dedicadas que **productizan** estos 
mecanismos y se los abstraen al cliente. Y como todos tenian su propia manera 
de implementar esto, se las empezo a llamar NoSQL. Hoy en dia esta mal, no son 
NoSQL sino que son **bases de datos no relacionales**. 

## Bases de datos no relacionales 

Surgen en pare para suplir las limitaciones del modelo relacional. 

- Escabilidad
- Distribuidas **por diseno** 
- Enormes volumenes de datos
- Esquemas flexibles 
- Pueden o no implementar ACID 

### Columnares 

Los datos son guardados ordenados por columan en lugar de por registro, mucho mas 
rapidas. Mas facil calcular pproyecciones relativas a una o pocas columnas, pero es 
mas dificil realizar escrituras o consultas de muchas columnas. 

### Clave/Valor 

La información se guarda organizado como un par clave/valor. 

- Tiene una alta flexibilidad, no es necesario modificar estructuras para agregar 
  atributos.
- No es necesario lidiar con tipos de datos
- Es complicado hacer búsquedas por más de un campo (AND y OR)

### Documentos 

Guardan cualquier "documento" arbitrario. Es mas facil guardar estructuras que varian. 
Sin embargo, no es tan fácil hacer consultas complejas (proyecciones, por ejemplo).

> Lograr un buen diseno es lo mas dificil con estas bases de datos, es facil sin 
  querer terminar con un esquema relacional.

Estas bases implementan **sharding**, pero con escabilidad y (cierta) tolerancia a 
fallos. Convierten las consultas en funciones programadas de forma funcional. 
Usan funciones **map-reduce**, consiste en procesar los datos de a grupos, 
proviene del paradigma funcional, provee escalabilidad y performance al distribuir el 
computo. Dificulta algunas operaciones como proyecciones. La parte reduce compagina 
las respuestas, podria ser tan simple como contar la cantidad de elementos. 

Detalle interesante, no hay indices, va y procesa todos los elementos, el unico motivo 
por el cual tiene performance es porque el procesamiento se realiza de forma distribuida. 
Por fuerza bruta, basicamente. 

## Ahora bien, como eligo?

Cada opcion difiere mucho de las otras, en terminos de tradeoffs y usos. La gran 
diferencia va a venir de entender los patrones de acceso que va a haber. A la hora 
de decidir, lo que importa es el orden de magnitud. 

> Si usan una DB NoSQL, tengan un buen motivo, que la escala lo pida 

Usar no relacional si se espera un gran volumen de trafico, luego, el tipo a elegir 
dependera de dos grandes preguntas: 

- Patrones de acceso 
- Que necesita, CA, CP, AP

