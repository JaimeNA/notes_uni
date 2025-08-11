# Capitulo 24: Conceptos de administracion de proyecto

Si un proyecto no se cumple a tiempo o contraria a sus usuarios, esto se debe generalmente a una mala administracion. 
En este capitulo se desarrolla como se puede llegar a una administracion efectiva.

## El espectro administrativo

El trabajo de la ingenieria de software es una empresa intensamente humana, fracasar en una comunicacion comprensiva 
durante las primeras etapas de un proyecto arriesga a construir una solucion para el problema equivocado.

La administracion efectiva se enfoca en(De mayor a menor prioridad):

1. **Personal**: El factor humano es tan importante que existe **People-CMM**(Modelo de madurez de capacidades del personal). 
Define areas practicas clave: plantilla, comunicación y coordinación, ambiente de trabajo, desempeño administrativo, capacitación, 
compensación, análisis y desarrollo de competencias, desarrollo profesional, desarrollo de grupo de trabajo y desarrollo de equipo/cultura, 
entre otros.

2. **Producto**: Antes de planear se debe establecer objetivos(metas globales) y el ambito del producto(datos, funciones, comportamientos), 
considerarse soluciones alternativas e identifiar restricciones.
Todos los participantes deben reunirse para definir estas cosas, esta actividad comienza como parte de la ingenieria del sistema y continua
con la ingenieria de requerimientos.

3. **Proceso**: Proporciona el marco conceptual, un pequeno numero de actividades se esta area se aplica a todos los proyectos de software,
sin importar el tamano o complejidad. Algunos conjuntos de tareas(hitos, tareas, produtos operativos) permiten que las actividades del marco 
conceptual se adapten al proyecto. Mientras que las actividades sombrilla recubren el modelo de proceso(independientes de cualquier actividad del marco).

4. **Proyecto**: Se planean y controlan debido a que es la unica forma de manejar su complejidad(aun asi, son dificiles). 
Para evitar el fracaso, se debe evitar un conjunto de advertencias, entender los factores de exito cruciales y desarrollar un enfoque de sentido comun.

## El personal

El factor mas importante, segun administradores de grandes companas, es el personal, 
pero con frecuencia el personal se da por hecho dentro de las empresas.

### Los participantes

Los participantes de un proyecto pueden organizarse dentro de las siguientes areas:

- **Gerentes ejecutivos**: Definen temas empresariales.
- **Gerentes de proyecto(tecnicos)**: Planifican, motivan, organizan y controlan a los profesionales.
- **Profesionales8**: Aportan habilidades tecnicas.
- **Clientes**: Especifican requerimientos.
- **Usuarios finales**: Interactuan una vez se libera el software.

Es responsabilidad del lider que las personas esten organizadas de manera que se maximicen sus habilidades.

### Lideres de equipo

Administrar implica trato con gente, debido a eso los profesionales competentes tienen pobre desempeno como lideres de equipo, 
pero generalmente uno no elije el papel de lider, sino que se topa con el. 

Se sugiere el modelo **MOI** de liderazgo:

- Motivacion(alentar al personal)
- Organizacion
- Ideas o innovacion(alentar a crear)

Por otro lado, un gerente eficaz debe tener los siguientes rasgos:

- Resolucion de problemas
- Indentidad administrativa
- Logro
- Influencia y construccion del equipo

### Equipo de software

La mejor estructura del equipo depende del estilo administrativo, del numero de personas y de sus niveles de habilidad, 
asi como la complejidad del problema. Hay siete factores que deben considerarse cuando se plantee la estructura:

- Dificultad del problema que se va a resolver.
- “Tamaño” del programa resultante en líneas de código o puntos de función.
- Tiempo que el equipo permanecerá unido (vida del equipo).
- Grado en el que puede dividirse en módulos el problema.
- Calidad y confiabilidad requeridas por el sistema que se va a construir.
- Rigidez de la fecha de entrega.
- Grado de sociabilidad (comunicación) requerido para el proyecto.

Paradigmas organizacionales sugeridos: 

- **Paradigma cerrado**: Jerarquia de autoriadad, trabajan bien cuando producen software similar a esfuerzos anteriores, menos innovadores.
- **Paradigma aleatorio**: Estructura amplia, depende de la iniciativa individual. Se destaca cuando se necesita innovar.
- **Paradigma abierto**: Intenta de tener algunos de los controles del paradigma cerrado, pero tambien mucha innovacion. 
Se trabaja de manera colaboradora y es adecuado para problemas complejos, no tiene tanto desempeno como otros equipoc.
- **Paradigma sincrono**: Compartimentalizacion natural de un problema, los miembros trabajan en trozos con poca comunicacion entre ellos.

Para lograr un equipo de alto rendimiento:

- Los miembros del equipo deben tenerse confianza entre sí.
- La distribución de habilidades debe ser adecuada para el problema.
- Es posible que tenga que excluirse del equipo a los inconformes si debe mantenerse la cohesión del equipo.

Sin importar la organizacion, todo gerente de proyecto debe ayudar a crear un equipo que muestre cohesion. Un equipo** cuajando** es un equipo donde los
miembros estan muy fuertemente unido, trabajan muy bien juntos y el producto es mayor que la suma de sus partes. 
Los miembros de un equipo cuajando son mas productivos y motivados que el promedio.

Lamentablemente, no todos los equipos cuajan, muchos sufren toxicidad. Factores que fomentan toxicidad:

- Atmosfera de trabajo frenetico
- Alta frustracion
- Proceso fragmentado o pobremente fraccionado
- Definicion poco clara de roles
- Continua y repetida exposicion al fracaso

Un equipo de software puede evitar la frustración si se le da tanta responsabilidad para la toma de decisiones como sea posible.
nalmente, la clave para evitar una atmósfera de fracaso radica en establecer técnicas basadas en equipo para retroalimentarse y resolver problemas.

Ademas, un equipo de software con frecuencia batalla con los diferentes rasgos humanos de sus miembros. Algunos son extrovertidos; otros, introvertidos.
Algunas personas reúnen información de manera intuitiva y separan los conceptos abarcadores de los hechos dispares.
Otras procesan la información de manera lineal, y reúnen y organizan detalles minúsculos de los datos proporcionados.
Es importante observar que el reconocimiento de las diferencias humanas es el primer paso hacia la creación de equipos que cuajen.

### Equipos agiles 

Es un equipo enormemente motivado y preparado para trabajar exitosamente. Son equipos **autoarganizados**, no necesariamente mantiene una sola estructura,
sino que usa elementos de los paradigmas aleatorio, abierto y sincronico.

Con base en la información obtenida durante dichas reuniones, el equipo adapta su enfoque para lograr un incremento de trabajo. 
Conforme transcurre cada día, la autoorganización y la colaboración continuas mueven al equipo hacia un incremento de software completo.

### Conflictos de coordinacio y comunicacion

El software moderno presenta tres caracteristicas principales: escala, incertidumbre e interoperabilidad. Para lidiar con esto, 
deben implementarse metodos efectivos. Mediante mecanismos que ayuden con la comunicacion formal e informal.
Los miembros de un equipo de software comparten ideas sobre una base *ad hoc*,
piden ayuda cuando surgen problemas e interactúan unos con otros diariamente.

## El producto

Se presenta un dilema al comienzo: se necesitan estimaciones y un plan, pero no hay informacion disponible. Un analisis de los requerimientos tarda semanas
o meses en completarse, y estos pueden cambiar con regularidad. Por lo tanto, el gerente debe examinar el producto, 
para luego establecer y acotar el ambito del producto.

### Ambito del software

La primera actividad en la administracion de un proyecto, responde las siguientes preguntas:

- Contexto 
- Objetivos de informacion(Datos de entrada y de salida)
- Funcion y desempeno

No debe tener ambiguedades ni ser incomprensible. Debe acotar.

### Descomposicion del problema

Division o elaboracion del problema, se encuentra dentro del analisis de requerimientos del software. 
No se hacen intentos por descomponer el problema, sino que se descompone: 

- La funcionalidad y el contenido que deben entregarse
- El proceso que se usara par aentregarlo

## El proceso

El problema es seleccionar el modelo de proceso(capitulo 2) que sea adecuado para el software. Se debe elejir el modelo que sea adecuado para:

- Clientes que solicitaron el producto y el personal que hara el trabajo
- Caracteristicas del producto
- Entorno del proyecto donde trabaja el equipo

Una vez seleccionado, el equipo define un plan de proyecto preeliminar. Luego comienza la descomposicion, 
creando un plan completo que refleje las tareas requeridas para completar las actividades del marco conceptual.

### Fusion de producto y proceso

La planificacion comienza con esta fusion. Cada función que se va a someter a ingeniería por parte del equipo debe pasar a 
través del conjunto de actividades de marco conceptual que defina la organización de software.

### Descomposicion del proceso

Un equipo de software debe tener un grado significativo de flexibilidad al elegir el modelo de
proceso de software que es mejor para el proyecto y las tareas que pueblen el modelo.

Una vez elegido el modelo, el marco conceptual se adapta a el. Funcionara para los modelos lineales, iterativos, incrementales, 
evolutivos e incluso para modelos concurrentes o de ensamble. El marco conceptual es invariante y sirve como base.

Sin embargo, las tareas si varian, la descomposicion comienza cuando el gerente cuestiona como van a lograr alguna actividad del marco conceptual.

## El proyecto

Para administrar un proyecto de software exitoso, se debe comprender qué puede salir mal, de modo que los problemas puedan evitarse. 
Hay 10 indicadores de que un proyecto esta en peligro:

1. El personal del software no entiende las necesidades del cliente.
2. El ámbito del producto está pobremente definido.
3. Los cambios se gestionan pobremente.
4. Cambia la tecnología elegida.
5. Las necesidades empresariales cambian [o están mal definidas].
6. Las fechas límite son irreales.
7. Los usuarios son resistentes.
8. Pérdida de patrocinio [o nunca obtenido adecuadamente].
9. El equipo del proyecto carece de personal con habilidades adecuadas.
10. Los gerentes [y profesionales] evitan mejores prácticas y lecciones aprendidas.

> **Nota**: Se suele referirse a la regla 90-90, donde el ultimo 10% es el 90% del esfuerzo.

Para evitar estos problemas, se sugiere el siguiente enfoque:

- Comenzar con el pie derecho, trabajar muy duro para entender el problema.
- Mantener la contidad de movimiento, minima rotacion de personal y el administrador ejecutivo debe permanecer fuera del camino del equipo.
- Siga la pista al progreso, para asegurar calidad se rastrea el progreso.
- Tome decisiones inteligentes, deben mantenerse simples.
- Realice un analisis postmortem, extrear lecciones aprendidas.

## Principio $\text{W}^5\text{HH}$

Se trata de una serie de preguntas que conducen a una definicion de caracteristticas clave y al plan de proyecto resultante:

- Why
- What
- When
- Who
- Where
- How
- How much

Es plicable sin importar el tipo de proyecto. 

## Practicas cruciales

Practicas utilizadas por proyectos y organizaciones enormemente exitosas:

- Administracion del proyecto basada en metrica
- Rastreo de defecto contra metas de calidad
- Administracion consciente del personal

## TL;DR

La administración de proyectos de software es una actividad sombrilla dentro de la ingeniería
de software. Comienza antes de iniciar cualquier actividad técnica y continúa a lo largo del
modelado, construcción y despliegue del software de cómputo.

En general, las revisiones técnicas y la comunicación informal persona a persona tienen más valor para los profesionales. 
El elemento esencial en todos los proyectos de software es el personal.

