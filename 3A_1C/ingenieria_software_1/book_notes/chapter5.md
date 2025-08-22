# Capitulo 5: Comprension de los requerimientos

Una de las tareas mas dificiles que enfrenta un ingeniero de software; tiende un punte para el diseno y la construccion.

## Tareas

Incluye siete tareas dificiles:

- Concepcion
- Indagacion
- Elaboracion
- Negociacion
- Especificacion
- Validacion
- Administracion

Algunas de estas tareas ocurren en paralelo y todas se adaptan a las necesidades del proyecto.

### Concepcion

Una conversacio casual es todo lo que se necesita para iniciar un proyecto de software, pero en general, comienzan cuando se identifica una necesidad
de negocio o se descubre un nuevo mercado o servicio potencial.

En esta parte se establece:

- Entedimiento basico del problema
- Personas que quieren la solucion
- Naturaleza de la solucion que se desea
- Eficacia de comunicacion
- Colaboracion preliminares entres otros participantes y el equipo del software

### Indagacion

Parece simple, pero no lo es. No es simplemente preguntar los objetivos para el sistema, sino que surge un cierto numero de problemas:

- Problemas de alcance
- Problemas de entendimiento
- Problemas de volatilidad(requerimientos cambian con el tiempo)

Para superarlos, se debe enfocarse en la obtencion de requerimientos en forma organizada.

### Elaboracion

A partir de la informacion obtenida en las dos estapas anteriores, se desarrolla un modelo refinado de los requerimientos que identifique
factores clave del software pedido.

Esta motivada por la creacion y mejora de escenarios de usuario que describan como interactua el usuario final con el sistema.

### Negociacion

No es raro que el cliente pida mas de lo que puede lograrse, o que propongan requerimientos conflictivos. Estos conflictos se
resulven mediante una negociacion, pidiendo que se ordenen los requerimientos por prioridad y luego se analice los conflictos. 
Luego de elimniar, modificar o combinar requerimientos, cada parte logra un grado de satisfaccion.

### Especificacion

Tiene distinto significado para distitas personas en el ambito del software, pero una especificacion suele ser:

- Documento escrito
- Conjunto de modelos graficos
- Modelo matematico formal
- Conjunto de escenarios de uso
- Prototipo
- Cualquier combinacion de los anteriores.

Algunos sugieren utilizar una plantilla estandar para mantener consistencia, pero es necesario poder ser flexible. Para sistemas simples, 
puede ser suficiente tener solo escenarios de uso.

### Validacion

Busca garantizar que los requerimientos no tengan ambiguedades, inconsistencias, omisiones o errores. Sobre todo que se presenten 
comforme a los estandares establecidos. El mecanismo principal para lograr esto es la **revision tecnica**. 

### Administracion de los requerimientos

Como los requerimientos suelen cambiar, esta tarea se enfoca en ayudar al equipo del proyecto a identificar, 
controlar y dar seguimiento a los requerimiento y a sus cambios en cualquier momento.

## Establecer las bases

Idealmente los participantes y los ingenieros trabajan juntos, pero no siempre ocurre, quiza solo haya una idea vaga de lo que se requiere 
y los clientes no esten cerca para consultarlos.

Para que se entiendan bien los requerimientos y el proyecto comience bien encamidado, hay varias etapas requeridas para establecer estas bases:

1. **Identificacion de los participantes**: Cualquier persona que se beneficie en forma directa o indirecta del sistema en desarrollo.
2. **Reconocer los multiples puntos de vista**: Cada participante tiene una vision diferente del sistema.
3. **Trabajar hacia la colaboracion**: Identificar tareas de interes comun y las de conflicto y resolver conflictos usando puntos de prioridad
(como una especie de votacion).
4. **Hacer las primeras preguntas**: Las preguntar realizadas en la concepcion deben estar **libres de contexto**, 
se usan para identificar a los participantes y *romper el hielo* para iniciar la comunicacion.

## Indagacion de los requerimientos

Tambien conocida como **recabacion de los requerimientos** combina elementos de la solucion de problemas, elaboracion, negociacion y especificacion.

### Recabacion en forma colaborativa

Para ello, se proponen los siguientes lineamientos basicos:

- Tanto ingenieros como participantes dirigen o intervienen en las reuniones
- Se establecen reglas para la preparacion y participacion
- Se sugiere una agenda con suficiente formalidad para cubrir todos los puntos importantes, pero con suficiente informalidad para promover el libre flujo de ideas.
- Un cliente/desarrollador/participante externo (facilitador) controla la reunion
- Se utiliza un *mecanismo de definicion*

La meta es identificar el problema, proponer elementos de la solucion, negociar distintos enfoques y especificar un conjunto 
preliminar de requerimientos de la solucion.

### Despliegue de la funcion de calidad

Tambien conocida como DFC es una tecnica de administracion de la calidad que traduce las necesidades del cliente en requerimiento tecnicos para el 
software. Se enfoca en entender que es valioso para el cliente, tiene tres tipos de requerimientos:

- **Requerimientos normales**: Si estan presentes, el cliente queda satisfecho.
- **Requerimientos esperados**: Implicitos, pero importantes para el cliente. Por ejemplo, operacion general correcta y confiable.
- **Requerimientos emocionantes**: Mas alla de las espectativas, muy satisfactorias si estan presentes. 

Basicamente, en base a los datos obtenidos, se crea una tabla de requerimientos, conocida como **tabla de la voz del cliente** para luego extraer 
los requerimientos esperados y percibir los emocionantes.

### Escenarios de uso

Entender como emplearan los uusuarios finales dichas funciones y caracteristicas. Estos escenarios de llaman **casos de uso**.

### Indagacion(Analisis) de los productos de trabajo

Los productos de trabajo son generados a partir de la indagacion de los requerimienots, estos varian en funcion al tamano de proyecto.
Generalmente, los productos de trabajo incluyen los siguientes:

- Enunciado de la necesidad y su factibilidad.
- Enunciado acotado del alcance del sistema o producto.
- Lista de clientes, usuarios y otros participantes que intervienen en la indagacion de los requerimientos.
- Una descripcion del ambiente tecnico del sistema.
- Una lista de requerimientos(de preferencia organizados por funcion) y las restricciones del dominio que aplican a cada uno.
- Un conjunto de escenarios de uso que dan perspectiva al uso del sitema o producto en diferentes condiciones de operacion.
- Cualesquiera prototipos desarrollados para definir requerimientos.

Cada uno de estos es revisado por todas las personas que participan en la indagacion de los requerimientos. Esto es una actividad evolutiva.

## Desarrollo casos de uso

Un caso de uso narra una historia estilizada sobre como interactua un usuario final con el sistema en un contexto especifico.
Ilustra el software desde el punto de vista del usuario final. Tiene los siguientes pasos:

Primero, definir un conjunto de **actores**(distintas personas o dispositivos). Hay actores principales(trabajan con el software de forma directa)
y secundarios(dan apoyo al sistema).

Luego, desarrollar casos de uso, se sigiere que un caso de uso responda las siguientes preguntas:

- ¿Quién es el actor principal y quién(es) el(los) secundario(s)?
- ¿Cuáles son los objetivos de los actores?
- ¿Qué precondiciones deben existir antes de comenzar la historia?
- ¿Qué tareas o funciones principales son realizadas por el actor?
- ¿Qué excepciones deben considerarse al describir la historia?
- ¿Cuáles variaciones son posibles en la interacción del actor?
- ¿Qué información del sistema adquiere, produce o cambia el actor?
- ¿Tendrá que informar el actor al sistema acerca de cambios en el ambiente externo?
- ¿Qué información desea obtener el actor del sistema?
- ¿Quiere el actor ser informado sobre cambios inesperados?

> **Nota**: Actor y usuario final no necesariamente son lo mismo.

## Elaboracion del modelo de los requerimientos

El objetivo del modelo de analisis es describir dominios de informacion, funcion y comportamiento que se requieren. 
A medida que el modelo evoluciona, hay elementos que dan un fundamento solido(no cambian) y otros son volatiles, es decir, 
lo que indica que todavia no se entienden bien los requerimientos del sistema.  Para construirlo se requieren:

- Elementos del modelo de requerimiento, algunos elementos comunes son:
    - Basados en el escenario.
    - Basados en clases.
    - De comportamiento.
    - Orientados al flujo 
- Patrones de analisis

![Diagrama UNL para indagar requerimientos](graphics/indagar_req.png)

## Requerimientos de las negociaciones

Generalmente, para llegar a un acuerdo con respecto a los requerimientos, se tiene que entrar en negociaciones con uno o varios
participantes. Las negociaciones tienen como objetivo desarrollar un plan del proyecto que satisfalga a todos. Buscan un resiltado *win-win*.
Se definen un conjunto de actividades de negociacion al principio de cada proceso de software: 

- Identificación de los participantes clave del sistema o subsistema.
- Determinación de las “condiciones para ganar” de los participantes.
- Negociación de las condiciones para ganar de los participantes a fin de reconciliarlas en
un conjunto de condiciones ganar-ganar para todos los que intervienen (incluso el
equipo de software).

## Validacion de los requerimientos


La revision del modelo de requerimientos para detectar inconsistencias o errores aborda las preguntas siguientes:

- ¿Es coherente cada requerimiento con los objetivos generales del sistema o producto?
- ¿Se han especificado todos los requerimientos en el nivel apropiado de abstracción? Es
decir, ¿algunos de ellos tienen un nivel de detalle técnico que resulta inapropiado en
esta etapa?
- El requerimiento, ¿es realmente necesario o representa una característica agregada que
tal vez no sea esencial para el objetivo del sistema?
- ¿Cada requerimiento está acotado y no es ambiguo?
- ¿Tiene atribución cada requerimiento? Es decir, ¿hay una fuente (por lo general una individual y específica) clara para cada requerimiento?
- ¿Hay requerimientos en conflicto con otros?
¿Cada requerimiento es asequible en el ambiente técnico que albergará el sistema o
producto?
- Una vez implementado cada requerimiento, ¿puede someterse a prueba?
- El modelo de requerimientos, ¿refleja de manera apropiada la información, la función y
el comportamiento del sistema que se va a construir?
- ¿Se ha “particionado” el modelo de requerimientos en forma que exponga información
cada vez más detallada sobre el sistema?
- ¿Se ha usado el patrón de requerimientos para simplificar el modelo de éstos? ¿Se han
validado todos los patrones de manera apropiada? ¿Son consistentes todos los patrones
con los requerimientos del cliente?

Estas deben plantearse y responderse para garantizar que el modelo refleja las necesidades del participante y provee un fundamento solido.

## TL;DR

Las tareas de ingenieria de requerimientos se realizan para establecer un fundamento solido, los miembros del equipo llevan a cabo
siete funciones de ingenieria de requerimientos y al final se negocia la prioridad de cada requerimientos, 
verificandoce que se haya entendido bien al cliente.


