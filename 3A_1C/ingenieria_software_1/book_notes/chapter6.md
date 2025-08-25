# Capitulo 6: Modelado de los requerimientos: Escenarios, informacion y clases de analisis

El modelo de requerimientos, o conjunto de modelos, es la primera representacion tecnica de un sistema.

## Analisis de los requerimientos

Da como resultado la especificacion de las caracteristicas operativas del software, permite al profesional construir sobre los
requerimientos basicos establecidos durante el proceso de ingenieria de requermientos.

Esto da como resultado uno o mas tipos de modelo:

- Modelos basados en el escenario.
- Modelos de datos.
- Modelos orientados a clases.
- Modelos orientados al flujo.
- Modelos de comportamiento.

El modelo brinda al desarrollador y al cliente medios para evaluar la calidad del software final. 
A continuacion se centrara en el **modelado basado en escenarios**, tecnica cada ves mas popular.

![Modelo de requerimientos como punte](graphics/modelo_req.png)

### Objetivos y filosofia general

Durante el modelado se enfoca en el *que* y no en el *como*, El modelo de requerimiento debe lograr:

- Describir lo que quiere el cliente.
- Establecer una base para la creacion de un diseno de software.
- Definir un conjunto de requerimientos que puedan validarse una vez construido el software.

Ademas, todos los elementos del modelo pueden rastrearse directamente hasta las partes del modelo de diseno, 
diseno y analisis no estan completamente divididas(se mezclan un poco).

### Reglas practicas del analisis

Se sugiere que un cierto numero de reglas practicas utiles cuando se crea un modelo:

- El modelo debe centrarse que los requerimientos visibles(no irse mucho a los detalles).
- Cada elemento del modelo debe agregarse al entendimiento general de los requermientos.
- Hay que retrasar las consideraciones de la infraestructura y otros modelos no funcionales hasta llegar a la etapa del diseno.
- Debe minimizarse el acoplamiento a traves del sistema.
- Es seguro que el modelo agrega valor para todos los participantes.
- Mantener el modelo tan sencillo como se pueda. 

### Analisis del dominio

Como ya se dijo, es frecuente que los patrones de analisis se repitan en muchas aplicaciones dentro del mismo negocio. 
Si se definen y se clasifica de manera que puedan reconocerse, la creacion del modelo sera mas expedita, y mejora el tiempo para llegar al 
mercado(reduciendo costos).

Se podria considerar que el analisis de dominio es una actividad sombrilla para el proceso de software, es decir, no esta conectada
con ningun proyecto en particular.

### Enfoques del modelado de requerimientos

- **Analisis estructurado**: Los datos y los procesos que los transforman son entidades separadas.
- **Analisis orientado a objetos**: Se centra en la definicion de las clases y en la manera en la que colaboran uno con el otro para cumplir los 
requerimientos. Usa UML.

Solo deben usarse elementos de modelado que agreguen valor al modelo.

## Modelado basado en escenarios

La experiencia del usuario es la principal prioridad, el modelado con UML comenza con la creacion de escenarios en forma de casos de uso.

### Creacion de un caso preliminar de uso

Se trata de un contrato para el comportamiento.

Para saber sobre que escribir hay que usar los dos primeras tareas de ingeniaria de requerimientos: **concepcion** e **idagacion**. 
Para:

- Identificar participantes
- Definir alcance
- Especificar objetivos
- Especificar prioridades
- Delinear requerimientos
- Describir objetos que seran manipulados por el sistema

Se utiliza:

- Reuniones para recabar requerimientos
- DEC
- Otros mecanismos

> **Nota**: Se denominan escenarios primerios aquellos que fluyen de manera lineal, sin caminos alternativos.

### Mejora de un caso de uso preliminar

Se evalua cada paso del **escenario primario**, planteando:

- El actor puede emprender otra accion en este punto?
- Es posible que el actor encuentre alguna condicion de error en este punto?
- Es posible que el actor encuentre otro comportamiento?(por ejemplo, provocado por algo fuera del control del actor)

Se recomienda el uso de una sesion de "lluvia de ideas" para obtener un conjunto complejo de excepciones, luego deben plantearse:

- Existen casos en los que ocurra alguna funcion de validacion durante este caso de uso?
- Hay casos en los que una funcion(o actor) de soporte falle en responder de manera apropiada?
- El mal desempeno del sistema da como resultado acciones inesperadas o impropias?

Luego, se debe razonar una excepcion usando los siguientes criterios:

- Una excepcion debe describirse dentro del caso de uso si el software la detecta
- El software debe manejarla una vez sea detectada.

## Modelos UML que proporcionan el caso de uso

Muchas veces un modelo basado en texto es muy poco util, entonces, se utilizan modelos UML graficos.
Hay varios tipos de diagramas:

- Diagrama de actividades
- Diagramas canal

## Concepto de modelado de datos

Si el software debe trabajar con datos, el equipo de software muy probablemente haga un **modelo de datos** como parte del modelado de requerimientos.
Para eso, se utilizan los diagramas **entidad-relacion**. 

(Interseccion con Base de Datos 1, ver apuntes esa materia)

## Modelado basado en clases

Representa: 

- Los objetos que manipularan el sistema
- Las operaciones que se aplicaran a los objetos
- Las relaciones entre objetos 
- Colaboraciones que ttienen lugar entre clases

Los elementos de este tipo de modelo son:

- Clases
- Objetos
- Atributos
- Paquetes

### Identificacion de las clases de analisis


