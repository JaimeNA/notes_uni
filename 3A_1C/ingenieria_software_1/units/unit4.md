# Planificacion y estimacion

## Estimacion

El cliente desea una estimacion de costo y tiempo. La idea es plantear cuales son las mejores practicas y como 
estimar el trabajo que llevara hacer algo. Esta estimacion lleva un montons de cosas asociadas. 

### Costos

Un factor importante para estimar el tiempo:

- **Fijos**: Espacio fisico, hardware, muebles, etc. Todo lo que implica una oficina.
- **Variables**: RRHH (dias-hombre).

### Esfuerzo

Sirve para dimensionar en el tiempo cuando se podra entregar una solucion al problema. Se presentan varios problemas, como las estimaciones 
imprecisas que son causadas por:

- Pedidos frecuentes de cambios por parte de los clientes.
- Tareas pasadas por alto.
- Falta de compresion del usuario de sus propias necesidades.
- Analisis insuficiente.
- Falta de gestion del proyecto.
- Falta de metodo para estimar.

Se presentan varias tecnicas de estimacion de esfuerzo:

- **Juicio de expertos**: En base a la experiencia.
- **Metodos algoritmicos**: En base a un metodo cientifico. Todos intentan a partir de experiencias pasadas crear un modelo matematico que pueda
predecir una estimacion de tiempo.
- **Aprendizaje automatico**: En base a informacion(data mining, redes neuronales, etc.).

La estimacion dependera del **stack**, no es lo mismo si se usa Rust(mas backend) o React(mas frontend). 
La idea es hacerlo tarea a tarea, las tareas se obtienen a traves de desarmar el alcance. 

## Planificacion

Principalmente hay que conocer RRHH, recursos humanos, para estimar esfuerzo y costo. Se necesita conocer la cantidad de **gent**e que trabaja en el proyecto, las **tareas** que realizaran y las capacidades y experiencia que debe tener.

No todas las tareas pueden o deben ser realizadas por la misma persona, se debe tener en cuenta la capacidad, interes, experiencia, etc. Algunos roles:

- Projecto manager
- Product owner
- Scrum master
- Arquitecto
- Etc.

Ejemplo, definir el modulo de gestion de usuario, los roles, permisos: Cuanto tiempo estimarias que podrias llevar a cabo esa tarea?

Respuesta: Primero, son pocos datos, podria ser una semana, pero con tan pocos datos es mas seguro que ninguna estimacion esta bien. 
Hay que empezar por plantearse cual es la tecnologia, si tengo que integrar algo, tengo que empezar de cero, etc. Depende mucho del proyecto, 
puede pasar de semanas a meses.

### Tareas claves 

- Analisis de requerimientos
- Diseno del sistema
- Diseno del programa
- Codificacion
- Pruebas
- Entrenamiento
- Mantenimiento

# User Stories

## User Story Mapping

Tecnica visual para **organizar y priorizar** historias de usuario, permite ver la **experiencia completa del usuario** como un flujo.
Ayuda a construir un **producto de manera incremental**. Es una herramienta de alineacion organizacional.

> **Nota**: Es basicamente un top-down, solo le cambian el nombre a la tecnica para que sea mas llamativo.

### Para que

- Comprender la visiaon global del producto.
- Facilitar la colaboracion del equipo(negocios, diseno, devs.).
- Prioriza el backlog de forma visual y consensuada.
- Definir entregas en MVP y releases.
- Identificar funcionalidades faltantes o redundantes(duplicadas) de forma visual.

### Paso a paso

Basicamente un top-down:

1. Definir la meta/propósito del producto (visión).
2. Mapear actividades del usuario (ej. “comprar un pasaje”).
3. Desglosar en tareas/acciones (ej. “buscar vuelo”, “pagar”).
4. Escribir historias de usuario debajo de cada acción.
5. Ordenar horizontalmente (flujo del usuario).
6. Priorizar verticalmente (qué entra en el MVP).

### Buenas practicas

- Hacerlo en equipo.
- Mantener historias cortas y claras.
- Priorizar con criterio de negocio + valor al usuario.
- Revisar y ajustar el mapa en cada iteracion.

# Presentaciones eficientes

Es preferible terminar antes que terminar tarde, es decir, es mucho peor terminar tarde.
 
## Errores

- No mirar al frente
- Leer desde el celular
- Hablar entre los integrantes
- No acentuar al hablar
- Manos en el bolsillo
- Apoyarse en la pared
- Mucho texto
- Contexto

# Containers

Los contenedores son procesos, aislados del resto de los procesos. La imagen es **build time** y el contenedor es **run time**.

## Como funcionan

Tiene dos procesos principales:

- Namespaces
- Cgroups

### Namespaces

El entorno aislado. El contenedor no ve hacia afuera, pero desde afuera se puede observar el contenedor.

El **filesystem** se puede manerjar con chroot o pivot_root, conviene pivot_root porque no puede volver para atras y tomar la referencia del resto del filesystem.

Por otro lado, la **network** de cada contenedor es una red virtual dentro de la computadora, es decir, a cada contenedor se le asigna una interfaz de refl

### Cgroups

Restricciones del sistema operativo, limite de CPU, memoria, etc. Limitaciones que se imponen a los procesos. 

## VMs vs. Containers

Vntajas de contenedores:

- Estandarizacion
- Aislacion
- Velocidad
- Flexibilidad

Basicamente, los VM emulan todo el sistema operativo, mientras que los contenedores no. Sin embargo, hay contenedores que son sistemas operativos y son practicamente VMs,.

# Microservicios

Cada servicio se separa en su propio servicio, que a su vez son contenedores(pueden estar corriendo en computadoras distintas). De manera que es mucho mas rebusto que usar monolito. 

Ventajas:

- Autonomia de equipos
- Escalabilidad independiente
- Despliegue independientes
- Resiliencia
- Poliglotismo tecnologico

Desventajas:

- Mayyor complejidad operativa
- Coordinacion y versiones
- Pruebas mas dificiles
- Dificultad para debuggear
- Consistencia de datos
- Consto inicial e infraestructura

## Cuando elegirlos

No sirvern para todo, se deben elegir si:

- Debe aportar valor al negocio.
- Los equipos deben estar preparados, la infraestructura es mas dificil, el monitoreo, logging y debuggin es mas complicado, y se debe tener necesidad de automatizacion y diciplina tecnica.

> Las microservicios son un medio, no un fin.

## K8S

Kubernetes:

- Despliega contenedores en los servidores que tenga disponibles.
- Escala automaticamente dependiendo de la demanda.
- Mantiene la salud de los contenedores.
- Balancea la carga entre instancias.
- Tiene una gestion declarativa, trata de cumplir tus pedidos.

## Planificacion

Necesitamos transmitir ideas de un sistema de software de forma directa y sencilla. 

### Modelo

Una representacion simplificada de un sistema que nos ayuda a comprender o visualizar un aspecto del mismo.

Reduce el esfuerzo intelectual requerido para copmrender el contenido representado.

#### Caracteristicas

- **Mapeo**: Representacion de algo natural o artifical que a su vez puede ser un modelo en si mismo.
- **Reduccion**: Un modelo no captura todos los atributos del original sino solo los relevantes.
- **Pragmatismo**: Esta orientado a algo util, si no se indica la funcionalidad no sirve. 

#### Propiedades

- Abstraccion
- Comprensibilidad
- Precision
- Poder de prediccion
- Economia de recursos

### Aplicaciones de modelos

- Modelos como bossquejos(sketches)
- Modelos como planos(blueprints)
- Modelos como programas o especificaciones ejecutables

Podemos pensar en los modelos como ubicados en un gradiente: Dependiendo del sistema que estamos construyendo, 
vamos a queres ir mas o menos en detalle.

### Los modelos en software

Dada la importancia que tiene todo esto uno pensaria que la practica modelada esta muy extendida.

Sin embargo, la mayoria de los modelos que nos encontramos en el mundo laboral no cumple con estos criterios.

Hoy en dia el modelado esta comenzando a resurgir.

### UML

#### Diagrama caso de uso

Que se esta describiendo(el sistema), quien interactua con el sistema(los actores), y que pueden hacer los actores(los casos de uso).

Estos diagramas pueden incluir multiplicidad, es decir, dentro de un caso de uso pueden participar mas actories. 

Tambien establecen relaciones entre casos y relaciones entre actores. Hay que tener cuidado, podes decir que se necesitan dos actores para realizar una tarea o que alguno de los dos actores puede realizar la tarea.

#### Errores a evitas
- Los diagramas de caso de uso no modelan procesos o flujos de trabajo.
- Los actores no son parte del sistema, deben ir afuera de las lineas de contorno del mismo.
- Casos de uso que apuntan al mismo objetivo se pueden agrupar en uno solo. Por ejemplo, crear/editar/ eliminar cursos -> gestionar cursos.
- Los distintos pasos de un mismo caso de uno no representand casos separados.


#### Diagramas de clase

Similares a los vistos en OOP, son una herramienta importante para simplificar los requerimientos.

Lo unico obligatorio es el nombre de la clase, luego se pueden agregar atributos y operaciones.

Estos diagramas permiten tener asociaciones binarias, con multiplicidad. Tambien hay agregacion compartida, herencia, etc. 

#### Maquina de estados

Permite modelar los posibles estados de un sistema o de un objeto en cuestion. 
Basado en el concepto de automatas finitos(que que se ve en teoria de automatas).

#### Diagrama de secuencia

Mientras que los de maquina de estado modelan el comportamiento intre-objetos, el diagrama de secuencia modela interacciones entre objetos.
Permite seguir una traza de alguna interaccion a lo largo del tiempo.

#### Diagrama de actividad

Tipico diagrama de flujo.

### Herramientas para hacer diagramas

#### Basadas en texto

- Mermaid
- PlantUML
- D2
- SequenceDIagram
- Structurizr

#### Basadas en interfaces graficas

- Draw.io
- Excalidraw
- Lucidchart

### Sugerencias

- Evitar detalles innecesarios.
- Evitar cruces de lineas.
- Es mejor hacer distintos cortes transversales al sistema que itentar poner todo en un mismo diagrama.
- Cada diagrama debe tener un titulo.
- Las herramientas basadas en texto tienen una curva de aprendizaje mas alta, pero suelen ser mas eficientes y mejoran la colabotacion a la larga.

## C4 architecture

Creado por Simon Brown entre 2006 y 2011, es una tecnica de notacion grafica ajustada para modelar la arquitectura de sistemas de software. 
Antes cada equipo(incluso dentro de la misma empresa) tenia una version diferente para modelar.

El modelo toma una metodologia top-down se llama C4 porque tiene los siguientes niveles:

1. **Context**: Muestra alcance del sistema y su relacion con los usuarios y otros sistemas.
2. **Containers**: Descomponen un sistema en contenedores interrelacionados. Un contenedor es un subsistema ejecutable e implementable.
3. **Components**: Descomponen los contenedores en componentes interrelacionados y relacionan los componentes con otros contenedoresu otros sistemas.
4. **Classes**: Proporcionan detalles adicionales sobre el diseno de los elementos arquitectonicos que se pueden asignar al codigo.

De mayor a menor nivel. Dividiendo en cajas mas chicas. 
