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

Se manifiestan en uno de los modos siguientes:

- **Entidades externas** (por ejemplo, otros sistemas, dispositivos y personas) que producen o
consumen la información que usará un sistema basado en computadora.
- **Cosa**s (reportes, pantallas, cartas, señales, etc.) que forman parte del dominio de infor-
mación para el problema.
- **Ocurrencias o eventos** (como una transferencia de propiedad o la ejecución de una serie
de movimientos de un robot) que suceden dentro del contexto de la operación del
sistema.
- **Roles** (gerente, ingeniero, vendedor, etc.) que desempeñan las personas que interactúan
con el sistema.
- **Unidades organizacionales** (división, grupo, equipo, etc.) que son relevantes para una
aplicación.
- **Lugares** (piso de manufactura o plataforma de carga) que establecen el contexto del
problema y la función general del sistema.
- **Estructuras** (sensores, vehículos de cuatro ruedas, computadoras, etc.) que definen una
clase de objetos o clases relacionadas de éstos.

Esta es una de las muchas propuestas. Es importante darse cuenta de lo que no son las clases u objetos, 
una clase nunca debe tener un "nombre de procedimiento imperativo".

### Especificacion de atributos

Describen una clase que fue seleccionada para incluirse en el modelo de requerimientos. 

### Definicion de las operaciones

Definen el comportamiento de un objeto, se dividen en cuatro categorias:

- Operaciones quemanipulan datos en cierta manera (por ejemplo, los agregan, eliminan, editan, seleccionan, etc.)
- Operaciones que realizan un cálculo.
- Operaciones que preguntan sobre el estado de un objeto.
- Operaciones que vigilan un objeto en cuanto a la ocurrencia de un evento de control.

### Modelado clase-responsabilidad-colaborador(CRC)

Proporciona una manera sencilla de identificación y organización de las clases que son relevantes para los requerimientos de un sistema o producto.

Las clases mencionadas anteriormente se pueden categorizar de las siguientes maneras:

- Clases de entidad, se extraen directamente del enunciado del problema.
- Clases de frontera, para crear la interfaz.
- Clases de controlador, administran una unidad de trabajo de principio a fin.

Se sugieren los siguientes lineamientos para asignar responsabilidades:

- La inteligencia del sistema debe estar distribuida entre las clases para enfrentar mejor las necesidades del problema.
- Cada responsabilidad debe enunciarse del modo más general posible.
- La información y el comportamiento relacionado con ella deben residir dentro de la misma clase.
- La información sobre una cosa debe localizarse con una sola clase, y no distribuirse a través de muchas.
- Cuando sea apropiado, las responsabilidades deben compartirse entre clases relacionadas.

Una de las maneras en la que una clase cumple sus responsabilidades es colaborando.
Las colaboraciones se identifican determinando si una clase puede cumplir cada responsabilidad. Si no es así,
entonces necesita interactuar con otra clase. Ésa es una colaboración.

Cuando se desarrolla un modelo CRC completo, los participantes lo revisan de la siguiente manera:

1. Se da a todos los participantes que intervienen en la revisión (del modelo CRC) un subconjunto del modelo de tarjetas índice CRC. 
Deben separarse aquellas que colaboran(de modo que ningún revisor deba tener dos tarjetas que colaboren).
2. Todos los escenarios de casos de uso (y los diagramas correspondientes) deben organizarse en dos categorías.
3. El líder de la revisión lee el caso de uso en forma deliberada. Cuando llega a un objeto con nombre, 
entrega una ficha a la persona que tenga la tarjeta índice de la clase correspondi
4. Cuando se pasa la ficha, se pide al poseedor de la tarjeta Sensor que describa las responsabilidades anotadas en la tarjeta. 
El grupo determina si una (o más) de las responsabilidades satisfacen el requerimiento del caso de uso.
5. Si las responsabilidades y colaboraciones anotadas en las tarjetas índice no se acomodan al caso de uso, éstas se modifican. 
Lo anterior tal vez incluya la definición de nuevas clases (y las tarjetas CRC índice correspondientes) o 
la especificación en las tarjetas existentes de responsabilidades o colaboraciones nuevas o revisadas.

### Asociaciones y dependencias

En muchos casos, dos clases de análisis se relacionan de cierto modo con otra, en forma muy
parecida a como dos objetos de datos se relacionan entre sí.

### Paquete de analisis

na parte importante del modelado del análisis es la categorización. Es decir, se clasifican dis-
tintos elementos del modelo de análisis (por ejemplo, casos de uso y clases de análisis) de manera que se agrupen en un paquete 
llamado **paquete de análisis** al que se da un nombre representativo.

## TL:DR

El objetivo del modelado de los requerimientos es crear varias representaciones que describan
lo que necesita el cliente, establecer una base para generar un diseño de software y definir un
conjunto de requerimientos que puedan ser validados una vez construido el software. El modelo
de requerimientos cruza la brecha entre la representación del sistema que describe el sistema
en su conjunto y la funcionalidad del negocio, y un diseño de software que describe la arquitectura de la aplicación del software,
la interfaz de usuario y la estructura de componentes.

Los modelos basados en el escenario ilustran los requerimientos del software desde el punto
de vista del usuario. El modelado basado en clases utiliza información obtenida de los elementos del modelado
basado en el escenario y en datos, para identificar las clases de análisis


