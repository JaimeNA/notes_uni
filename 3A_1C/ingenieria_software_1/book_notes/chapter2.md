# Capitulo 2: Modelos del proceso

Se define proceso del software como una estructura para las actividades, 
acciones y tareas que se requieren a fin de construir software de alta calidad.

## Modelo general de proceso

Dentros de las actividades sobrilla se realizan las actividades estructurales, y el conjunto de actividades sobrilla forman la estructura 
del proceso. Sin embargo, falta mencionar el flujo, escribe la manera en que están organizadas las actividades estructurales y las acciones y
tareas que ocurren dentro de cada una con respecto de la secuencia y el tiempo. 

Hay varias maneras de realizar el flujo:

- Un **flujo de proceso lineal** realiza las cico actividades estructurales en secuencia.
- Un **flujo de proceso evolutivo** realiza las actividades en forma circular.
- Un **flujo de proceso paralelo** ejecuta una o mas actividades en paralelo.

![Tipos de flujo](graphics/tipos_flujo.png)

## Definicion: Actividad estructural

¿qué acciones son apropiadas para una actividad estructural, dados la naturaleza del problema por resolver,
las características de las personas que hacen el tra bajo y los participantes que patrocinan el proyecto?

 **Por ejemplo**: Para un proyecto sencillo:

1. Hacer contacto con el participante por vía telefónica.
2. Analizar los requerimientos y tomar notas.
3. Organizar las notas por escrito en una formulación breve de los requerimientos.
4. Enviar correo electrónico al participante para que revise y apruebe.

## Identificacion de un conjunto de tareas

Debe escogerse el conjunto de tareas que se adapte mejor a las necesidades del proyecto y a las características del equipo.
Esto implica que una acción de la ingeniería de software puede adaptarse a las necesidades específicas del proyecto de software y 
a las características del equipo del proyecto.

## Patrones del proceso

Cada equipo se enfrenta a problemas conforme avanza el desarrollo, un **patrón del proceso** describe un problema relacionado con 
el proceso que se encuentra durante el trabajo de ingeniería de software, identifica el ambiente en el que surge el problema y 
sugiere una o más soluciones para el mismo.

Amber propuso un formato para describir un patron de proceso:

- Nombre del patron
- Fuerzas
- Tipo(patron de etapa, patron de tarea o patron de fase)
- Contexto inicial
- Problema
- Solucion
- Contexto resultante
- Patrones relacionados
- Usos y ejemplos conocidos

Dan un mecanismo efectivo para enfrentar problemas asociados con cualquier proceso del software. 
Los patrones permiten desarrollar una descripción jerárquica del proceso, que comienza en un nivel alto de abstracción (un patrón de fase).

## Evaluacion y mejora del proyecto

La existencia de un proceso no garantiza que el software se entregue a tiempo o que cumpla con lo pedido. Para evitar esto, 
el proceso en si puede evaluarse para garantizar que por lo menos cumpla con ciertos criterios que se haya demostrado que son 
escenciales para el exito.

Algunos enfoques para la evaluacion y mejora son:

- **Metodo de evaluacion del estandar CMMI para el proceso de mejora(SCAMPI)**: Modelo de cinco fases para evaluar el proceso.
- **Evaluacion basada en CMM para la mejora de proceso interno(CBA IPI)**: Tecnica de diagnostico para evaluar madurez relativa.
- **SPICE(ISO/IEC 15504)**: Estandar, define requerimientos para evaluar el proceso. Ayuda a tener una evaluacion objetiva.
- **ISO9001**: Estandar generico que se enfoca en mejorar la calidad de los productos, directamenta aplicable al desarrollo de software.

> **Nota**: CMMI significa Capability Maturity Model Integration. SEI CMMI y SEI CMM son tecnicas de evaluacion.

## Modelos del proceso prescriptivo

Propuestos para poner orden, an resultado ser muy utiles, pero aplicado al software el producto que genera sigue estando "al borde del caos".
A continuacion se mustran algunos de estos modelos:

- Modelo de la cascada
- Modelo de proceso incremental
- Modelo de proceso evolutivo(Involucra el modelo espiral)
- Modelos concurrentes

El reto principal es establecer un balance apropiado entre estos parametros criticos del proyecto y el producto, y la satisfaccion del cliente(el que tendra la palabra final con respecto a la calidad).

> **Nota**: Descripcion de estos modelos estan en los apuntes de clase.

## Modelo de procesos especializado

Comparten algunaas caracteristicas con los modelos tradicionales, pero tienden a aplicarse cuando se tiene un enfoque muy especifico.

### Desarrollo basado en componentes

Incarpora muchas de las caracteristicas del modelo espiral, es de naturaleza evolutiva y demanda enfoque iterativo. 
Su principal caracteristica es que **contruye aplicaciones a partir de fragmentos de software prefabricados**. 

Incorpora las siguientes etapas:

1. Se investiga y evaluan productos ya disponibles.
2. Se consideran aspectos de integracion.
3. Se disena una arquitectura que reciba los coomponentes.
4. Se integran los componentes a la arquitectura.
5. Se efectuan pruebas para asegurar la funcionalidad adecuada.

Este modelo lleva a la reutilizacion del software, reduciendo el tiempo y costo del desarrollo.

### Modelo de metodos formales

Tiene un enfoque matematico, desarrollando un sistema por medio de una notacion matematica. 
Lo ambiguo o incorrecto se corrije con mucha mas facilidad mediante el analisis matematico. Aunque no es el modelo mas seguido,
promueve software libre de defectos. Ademas, permite construir software de muy alta calidad en terminos de seguridad o donde los errores 
son muy costosos.

Sin embargo, tiene sus desvantajas:

- El desarrollo consume mucho tiempo y es caro.
- Se requiere mucha capacitacion del personal.
- Es dificil de implementar con clientes que no tienen la complejidad tecnica.

> **Nota**: Existe una variante de este enfoque que se denomina *ingenieria de software de quirofano*.

---

### Desarrollo orientado a aspectos

Sin importar el proceso elejido, estos tienen varias caracteristicas en comun. A medida que se hacen mas sotisficados los proyectos, 
el desarrollo de software presenta **preocupaciones globales**. Afectan multiples funciones, caracteristicas e informacion del sistema.

Por lo tanto, el DSOA es un paradigma nuevo que se enfoque en definir, especificar y construir aspectos, estos pueden ser de:

- Persistencia(almacenamiento)
- Seguridad
- Transacciones(control de concurrencia, por ejemplo)

## El proceso unificado

La tendencia en el software es hacia sistemas mas grandes y complejos, principalmente a que las computadoras son cada ves mas potentes.
El proceso unificado es un intento de obtener mejores caracteristicas de los modelos tradicionales, pero que a su vez implemente los 
mejores principios del desarrollo **agil**. Sugiere flujo iterativo e incremental, lo que da la sensacion evolutiva que resulta esencial en el desarrollo.

### Fases del proceso unificado 

Estas faces involucran las **actividades del proceso** mencionadas anteriormente, mostrando como pueden usarse para describir cualquier modelo.

![Fases del proceso unificado](graphics/proceso_unificado.png)

- **Fase de concepcion**: Comunicacion con cliente y planeacion.
- **Fase de elaboracion**: Comunicacion y modelado del modelo general de proceso. Es comun que al finalizar se hagan modificaciones al plan.
- **Fase de construccion**: Actividad de construccion. Desarrolla y adquiere componentes del software(por ejemplo, el lanzamiento). Pruebas unitarias, integracion, etc.
- **Fase de transicion**: Ultimas etavas de la actividad de construccion y primera parte de despliegue general(entrega y feedback). Se da a los usuarios el software de prueba. Se genera informacion de apoyo(manuales, por ejemplo).
- **Fase de produccion**: Coincide con la actividad de despliegue del proceso general. Se reportan defectos y solicitudes.

El **flujo de trabajo** de la ingenieria de softare esta distribuido en todas las fases del PU. Sin embargo, no toda tarea mencionada 
es necesariamente realizada en todos los proyectos de software(se adapta al equipo).

> **Nota**: Las fases no ocurren en secuencia, sino de forma escalonada. 

## Modelos del proceso personal y del equipo

El proceso esta cerca de las personas que haran el trabajo, si el modelo se realiza a nival corporativo, sera efiscaz solo si acepta una adaptacion significativa. 
Idealmente, el proceso debe ajustarse del mejor modo a los requerimientos y cumbrir, al mismo tiempo, las amplias necesidades del equipo y de la 
organizacion. Alternativamente, el equipo puede ser el que se encargue de realizar el proceso, enfocandoce mas en el equipo y solo cubrir las necesidades 
generales de la organizacion. 

### Proceso personal del software(PPS)

Se enfoca en la medicion personal tanto del producto del trabajo que se genera como de su calidad, 
responsabiliza al profesional acerca de la planeacion del proyecto(por ejemplo, programacion de actividades) y delega 
en el practicante la calidad de los productos. Define cinco actividades estructurales:

- **Planeacion**: Aisla requerimientos y desarrolla estimaciones(numero de defectos, recursos, etc.). Se identifican las tareas de desarrollo y se crea un programa.
- **Diseno de alto nivel**: Especificaciones externas para cada componente, si hay incertidumbre se elaboran prototipos.
- **Revision de diseno de alto nivel**: Se aplican metodos de verificacion formal para descubrir errores. Se miden las tareas y resultados.
- **Desarrollo**: Mejora y revisa el diseno, se genera el codigo, se revisa y prueba. Se miden las tareas y trabajos de importancia.
- **Post mortem**: Se determina la eficacia del proceso por medio de mediciones(nuevas o recolectadas). Se mejora la eficacia.

Se centra en detectar errores lo antes posible, no a sido adoptado con amplitud en la industria debido a que es culturamente dificil realizar el 
nivel requerido de mediciones.

### Proceso del equipo de software(PES)

El objetivo de este es construir un equipo **autodirigido** para el proyecto, que se organice para producir software de alta calidad. 
Tiene los siguientes objetivos:

- Formar equipos autodirigidos.
- Mostrar a los gerentes como dirigir y motivar a sus equipos.
- Acelerar la mejora del proceso de software(CMM).
- Brindar a las organizacion una guia para la mejora.
- Facilitar la ensenanza universitaria de amplitudes de equipo.

Un equipo autodirigido tiene una comprension consistente de sus metas y objetivos, debe haber colaboracion interna y comunicacion externa. 
Define las siguientes actividades estructurales(similar a PPS):

- Inicio del proyecto
- Diseno de alto nivel
- Implementacion
- Integracion y pruebas
- Post mortem

Utiliza amplia variedad de **scripts**, los cuales definen formatos y estandares que guian a los miembros del equipo. Reconoce que 
los mejores equipos de software son los autodirigidos, los miembros son los responsables de seguir con los objetivos y adaptarse en caso de ser necesario.

## Tecnologia del proceso

El equipo debe adaptar uno o mas de los modelos de proceso mencionados, para ello existen **herramientas de tecnologia del proceso**. 
Una vez creado un proceso aceptable, se emplean otras herramientas de tecnologia para asignar, vigilas e incluso controlas todas las actividades. 
Tambien puede ser usada para coordinar el empleo de otras herramientas que sean apropiadas para una tarea en particular. Margaret Davis hace comentarios sobre la dualidad del producto y del proceso:

> En tanto que la tendencia natural de un péndulo es alcanzar el estado de reposo en el punto medio
entre dos extremos, la atención de la comunidad del software cambia constantemente porque se aplica
una nueva fuerza al fallar la última oscilación. Estos vaivenes son dañinos en sí mismos porque
confunden al profesional promedio del software al cambiar en forma radical lo que significa hacer 
el trabajo bien. Los cambios periódicos no resuelven “el problema” porque están predestinados a 
fallar toda vez que el producto y el proceso son tratados como si fueran una dicotomía en lugar 
de una dualidad.

> Obtenemos sentimientos de satisfacción por la reutilización de nuestros productos, 
ya sea que lo hagamos nosotros u otras personas.

Como profesional creativo, hay que ser capaz de obtener tanta satisfaccion del proceso como del 
producto final. La dualidad planteada es un elemento importante para hacer que personas creativas 
se involucren.

## TL;DR

Un modelo general del proceso incluye un conjunto de actividades, cada uno de los modelos puede
describirse por un flujo distinto. Los patrones del proceso pueden utilizarse para resolver problemas
comunes que surgen durante el proceso. Se dividen en 3 grandes grupos:

- Modelos de proceso prescriptivo
- Modelos de proceso secuencial
- Modelos de proceso icnremental
