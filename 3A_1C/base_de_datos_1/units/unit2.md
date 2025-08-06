# Unidad 2: Modelo conceptual, modelo de entidad-relacion

Las bases de datos son un pedazo de sistemas de informacion que manejan cualquier organizacion con el objetivo de tomar decisiones y registrar sus procesos.

Tan complejo es poder manejar volumen de informacion hetereogenea que cada vez es mayor la tecnologia emergente que se va incorporando a las empresas con el fin de permitir que la misma sea mas organizada.

La informacion puede usarse en la medida en que se encuentre **pre-procesada**.

## Modelo de Entidad/Relacion y su extencion

Este es el primer nivel que vamos a ver(mencionados en la unidad 1). Con extencion se refiere a dos cosas del mundo de los objetos. 

### pasos de un buen disenador

1. Reunir los requisitos del sistema
2. Crear esquema conceptual
3. Implementar la base de datos con algun DBMS(comercial o no)
4. Diseno fisico de la base de datos(indices)
5. Carga de datos y examinacion de funcionamiento

---

Sirve para modelar el mini-universo a representar, maneja tres nociones basicas:

- Conjunto de entidades
- Conjunto de relaciones
- Atributos

### Entidades(entity type)

Una **entidad** es cualquier cosa del mundo real(concreta o obtracta) que posee existencia independiente. 
Debe poder saber si son la misma o no, entonces el cliente debe poder explicar que separa una entidad de otra(cuales atributos diferencian una entidad concreta de otra).
Siempre hay que preguntar pues las reglas del negocio son arbitrarios y no se puede asumir nada.

Entonces, un **conjunto de entidades** es la coleccion de todas las entidadess que poseen los mismos atributos(no hablamos de sus valores).

```
Entidad -> Extension
```

```
Conjunto de entidades -> Esquema(intension)
```

Como minimo debe haber un atributo **identificatorio/clave/discriminatorio**. Si hay mas de uno, hay que marcarlos a los dos como claves.

Los que no son claves, se denominan **descriptores**.

### Relaciones(Relationship type)

Una **relacion** es una asociacion entre entidades particulares. Mientras que un **conjunto de relaciones** es la coleccion de todas las relaciones del mismo tipo.

Formalmente, Un conjunto de relaciones $R$ es un subconjunto del producto cartesiano de conjuntos de entidades $E_1 \dot E_2 \dot$ ... $\dot E_n$ con $n \ge 2$

#### Caracteristicas

- **Grado o Aridad**: Cantidad de conjuntos de entidades participantes.
- **Rol**: Funcion que desempena una entidad en una relacion(generalmente no se rotula).

#### Restricciones

- **Recursividad**: Esto hace obligatorio rotular el rol. El mismo tipo de entidad participa **mas de una vez** en el conjunto de relaciones con **distintos roles**. Se consideran unarias, por ejemplo, es jefe.
- **Cardinalidad**: Numero de relaciones en que una entidad(concreta) puede participar, no confundir con aridad. Indica el tope superior, puede ser: 1:1(uno a uno), 1:N, N:1, M:N.

#### Participacion o existencia

Especifica si la existencia de na entidad depende o no de su participacion con otra entidad en una relacion.

Un conjunto de entidades A participa **totalmente** en un cojnunto de relaciones R si todas sus entidades tienen que participar por lo menos de una relacion. Si no fuera asi, sera participacion **parcial**. 

### Atributos

Cada propiedad que describe una entidad se denomina **atributo**. Una entidad en perticular toma valores para cada una de sus atributos.

Cada atributo posee un rango de valores posible, se conoce como el dominio. 

Los conjuntos de relaciones tambien pueden poseer atributos.
Pero hay que tener cuidado, si el conjunto de relaciones es M:N, entonces, los atributos deben ser especificados como parte del conjunto de relaciones. 
Es un conjunto de relaciones con cardinalidad 1:N o 1:1 los atributos pueden migrarse hacia alguno de los conjuntos de entidades participantes.

> **Nota**: **No** van claves en la relacion.

#### Clasificacion

- Simple - Compuesto(sueldo es simple, direccion es compuesto pues es calle, numero, dpto).
- Univaluado(unico valor) - Multivaluado(por ejemplo, un auto, tiene mas de un color al mismo tiempo).
- Almacenado - Derivado(antiguedad puede derivarse de fecha_ingreso)

#### Claves

Una entidad es **fuerte** si arma su clave a partir de sus atributos.
Una entidad es **debil** si es parte del conjunto de entidades que tiene su clave formada por atributos propios mas atributos de otros conjuntos. Parte la tiene una entidad con la que se relacionea.

Si es debil, **automaticamente** tiene participacion total.

## Modelo ER extendido agrega jerarquias

El modelo basico puede extenderse permitiendo representar mejor el mini-universo en cuestion. La extension permite expresar que un conjunto de entidades es subclase de otro co njunto de estidades debido a que posee mayor especializacion. Por ejemplo, *empleado* es superclase de los conjuntos *efectivo*, *contratado*, *free_lacne*.

Importante, cuando se verifica especializacion encontramos que el conjunto de entidades subclase hereda todos los atributos y participa de todas las relaciones de la superclase(yo que es un caso especial).

### Tipos de jerarquias

#### Generalizacion

Si se tiene un conjunto de entidades y se lo divide en subclases disjuntas. 

El diagrama que representa esto se lo hace representando a cada una de las subclases y uniendolas a la superclases por una doble flecha, via un hexagono que posea el atributo que produjo la separacion.

#### Es-un

Si sólo se especifica especialización, pero falla que al unir todas las subclases se obtenga la superclase, 
o se tiene que las subclases no son necesariamente disjuntas. El diagrama que representa esto es similar al anterior pero sin el hexágono del medio.



