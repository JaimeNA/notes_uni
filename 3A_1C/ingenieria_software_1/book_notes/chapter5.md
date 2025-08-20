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

### Indagacion de los productos de trabajo

