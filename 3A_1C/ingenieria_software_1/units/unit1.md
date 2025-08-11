# Introduccion

 Nosotros no buscamos la solucion optima teorica, sino buscamos que podemos hacer con nuestras limitacion de tiempo y economica. La ingenieria de software tiene dos dimensiones importantes, tiempo y escala(cuantas personas necesitamos para desarrollar una solucion y como se van a mantener los equipos). 

 **Definicion interesante**(suena mejor en ingles): Desarrollo entre multiples personas de programas de multiples definiciones. *Multi-person development of multi-version software.*
 Es decir, debe ir evolucionando, nunca se queda como esta ni se descarta y se empieza de nuevo.

# Unidad 1: Ciclos de vida

## Que es

 Proceso sistematico y estandarizado por el cual un producto de software es desarrollado y mantenido a lo largo del tiempo.

Define un marco común para los procesos del ciclo de vida de los programas informáticos, 
con una terminología bien definida, a la que pueda remitirse la industria del software. Contiene procesos, 
actividades y tareas aplicables durante la adquisición, el suministro, el desarrollo, el funcionamiento, el mantenimiento o la eliminación de sistemas, productos y servicios informáticos. 
Estos procesos del ciclo de vida se llevan a cabo mediante la participación de los interesados, con el objetivo final de lograr la satisfacción del cliente”.

## Por que

Sirve para contruir **software de alta calidad**, ese el el unico motivo. Esto tambien implica:

- **Predecir la etapa del ciclo de desarrollo**: Licencias, oficinas, personal, equipos, telefonos etc. Todo lo que necesitemos.
- Minimizar errores 
- Minimizar costos
- **Reutilizable y facilmente mantenible**: Al aplicar determinados mecanismos y tecnologias vamos a poder hacer el software mas reutilizable y mantenible, entonces, menos costoso.
- Cumplir con los rquisitos del cliente
- Escalabilidad

![Representacion de la calidad de un software](graphics/calidad.png)

## Modelos de ciclos de vida

Todos los modelos tienen sus pros y sus contras, de manera que dependera de la naturaleza del projecto cual sera el modelo a utilizar.

### Cascada

La finalizacion de una etapa implica en inicio de la siguiente. Las etapas son:

1. Analysis
2. Requirements specification
3. Design
4. Development
5. Testing and integration
6. Implementation/Deployment

Si me doy cuenta que, por ejemplo, durante la etapa de **testing** nos damos cuenta que hay que introducir un nuevo requerimiento, va a haber que volver a empezar(volver al analisis).

Ademas, lo primero de software que vera el cliente sera en la etapa final, lo cual puede ser meses o anos despues. Esto introduce el problema de que el cliente puede no estar satisfecho con el resultado final.

### Incremental

Plantea resolver el problema principal del modelo cascada, trata de mostrar software funcionando lo antes posible.

Primero se define el sitema copleto y se lo divide en funcionalidades, luego, cada incrmento sigue todas las fases del ciclo de vida (análisis -> diseño -> codificación -> pruebas -> entrega).

Se entrega al usuario **funcionalidad completa por partes**.

**Por ejemplo**: Sobre Gmail: Google Meet, Google Classroom, Google Jamboard.

### Evolutivo

Busca recoger **feedback** rapidamente, para poder mejorar la implementacion. Con cada ciclo se entregan diferentes versiones. Como funciona:

- Se define un conjunto minimo de requerimientos
- Se desarrolla una primera version o prototipo
- Se entrega al usuario y se recoge feedback
- Se planifican mejoras y se desarrolla una nueva version
- El ciclo se repite hasta alcanzar el producto final

Hay dos tipos de prototipos, el desechable(Tener cuidado con las expectativas del cliente, puede ser que no le guste que este *atado con alambre*) y el que es simplemente una version del programa, en la cual se puede ir iterando.

Modelo de desarrollo que , a diferencia del de Prototipos, busca reemplazar el viejo sistema con uno nuevo que tendría la propiedad de satisfacer los nuevos requerimientos lo más rápido posible. 
El desarrollo evolutivo asume que los requerimientos están sujetos a cambios continuos y que la estrategia para enfrentar aquello pasa por un reflejo, también continuo de aquellos cambios.

**Por ejemplo**: Windows, Office, Ubuntu.

![Modelo evolutivo](graphics/evolutivo.png)

> **Nota**: La pricipal falla de los proyectos informaticos es la falta de **comunicacion**. Esto es reponsabilidad del Ingeniero de Software, el prototipado busca resolver parte de esta comunicacion.

### Prototipado

La idea es poder mostrar como pensas que puede ser el sistema. 
Modelo que pone énfasis en la etapa de Especificación de Requerimientos a través de la construcción de prototipos que aproximan al usuario a la idea final del sistema, 
con objeto de poder clarificar los requerimientos.

Sirve para poder descubrir la funcinalidad completa que quiere el cliente. Esto se debe a que el cliente generalmente no sabe muy bien lo que quiere, hay que validad lo mas posible si estamos desarrollando lo que quiere el cliente.

![Modelo prototipado](graphics/prototipado.png)

### Espiral

El mas completo, usa todos los anteriores:

- Prototipado
- Evolutivo
- Iterativo
- Incremental

![Modelo espiral](graphics/espiral.png)

Una cosa muy importante que contempla este modelo es el analisis de riesgos, ademas de su gestion dentro de cada iteracion. Trata las falencias de los modelos anteriores.

## Proceso unificado

De una manera es la madre de todos estos projectos. UP es un framework de proceso detallado, define aproximadamente 50 opcionales artefactors(non-software work products), tales como:

- Vision, Lista de riesgos, etc
- En RUP estan organizados dentro de las diciplinas, y los encontramos como templates(la documentacion)
- La regla a seguir con respecto a la creacion de estos documentos es "Menos es mejor"
- Esta unica adaptacion es llamada **Development Case** del projecto

Esto esta basado en projectos anteriores, tomando las practicas que funcionaron. No hace falta usar todos los artefactos en nuestros projectos. No quieren que agamos documentacion por documentacion, sino que tenga un *target*.

### Esfuerzo

![Actividad por diciplinas RUP](graphics/rup_diciplinas.png)

Los recursos y el staff son variables.

### Mejores practicas

- **Iteraciones TimeBoxed**: Recomendadas entre 2 y 6 semanas, como hackatlon.
- **Programando los elementos de alto riesgo, y alto valor**: Incentivar a la reutilización de componentes
- **Verificación de calidad continuada**: Uso de técnicas de integración y testeo continuo, como XP
- **Modelado visual**: Esquemas en una pizarra durante 1 hora. Esquemas basados en UML
- **Administración de requerimientos**: Encontrar (req. Funcionales a través de workshops). Organizar (según prioridades, atributos, riesgos, …) y seguir los requerimientos (según su estado – asignado, en curso, ….)
- **Administración del cambio**: A través de la administración de la config. y del control de versión. El uso de un protocolo de solicitud de cambios. Releases al final de cada iteración

### Errores comunes

- **Iteraciones**: Demasiado largas(se pierde el enfoque). Cada iteración no finaliza con un release en producción
- **El objetivo de la fase elaboración**: Crear un prototipo desechable
- **Planeamiento**: Predictivo no adaptativo. Diferir el testeo mayor hasta cerca de finalizar el proyecto
- **Documentación**: Hacer cantidades de diagramas de UML. Realizar un “Development Case” demasiado complejo, con muchos work products. El Software Architecture Document (SAD) finalizado antes de terminar la elaboración

# Extreme Programming

El desarrollo de software presenta varios problemas:

- Entregas tardias y/o incompletas
- Cancelacion de proyectos
- Sistema desactualizado
- Alta tasa de errores
- Reglas de negocio sin entender
- Cambios de requerimientos
- Desercion de programadores
- Ambientes de alto grado de incertidumbre

## Agile manifeso

Para solucionar esto se propone las siguientes reglas:

- **Individuals and interactions** over processes and tools
- **Working software** over comprehensive documentation
- **Customer collaboration** over contract negotiation
- **Responding to change** over following a plan

> **Nota**: Habia anotado mas cosas, pero se perdio. Basicamente era un desarrollo de cada punto, pero se puede deducir.

## Extreme programming

- Metodología liviana, eficiente de bajo riesgo, flexible, predecible y científica.
- Orientada a equipos pequeños o medianos.
- Para desarrollar software teniendo como premisa que los requisitos cambian rápidamente.
- Especialmente diseñadas para ambientes de gran incertidumbre.

### Valores

![Valores de XP](graphics/valores_xp.png)

### Practicas

#### Juego de planificacion

Determinar rapidamente el aclance de la proxima release combinando las prioridades del negocio y las limitaciones tecnicas. Planificar como maximo dos releases por adelantado.
Como la realidad supera la planificacion, actualizar el plan.

#### Releases pequenas

Poner en produccion el sistema mas pequeno que funcione, luego agregar funcionalidad iterativamente. 
Cada release debe tener una unidad de coherencia.

#### Metaforas

Guiar a todo el grupo de desarrollo con una historia simple de como debe funcionar el sistema. Ayudar a comprender los elementos basicos del sistema, debe ser refinada a medida que avanza el proyecto.
Es una vista desde *10k metros* del sistema.

#### Diseno simple

El sistema debe ser lo mas simple posible, remover toda complejidad. El diseño correcto
 1. Debe pasar todos los testeos
 2. No tiene lógica duplicada
 3. Muestra la intención del programador
 4. Tiene la menor cantidad posible de métodos y clases

El futuro es incierto, entonces no diseñar de más para una futura funcionalidad

#### Testing

Los testeos unitarios deber ser parte del codigo. Los testteos de funcionalidades ...

#### Refactoring

Implica llevar el programa a la forma mas simple una vez que se introducen cambios, favorece el agregado de nuevos cambios. Si existe codigoduplicado debe refactorizarse.

#### Pair programming

Una de las personas escribe el codigo y piensa en la mejor forma de hacerlos, la otra persona piensa de forma estrategica(Es correcta la solucion? Hace falta definir otro testeo? Hay forma de simplificarlo?).
La pareja debe ir rotando para fomentar el conocimiento del sistema.

#### Compartir autoria

Cualquiera que ve la oportunidad de agregarle valor a un codigo debe hacerlo, no hay una persona que sea duena de un bloque de codigo. Mejora los tiempos de correccion de bugs, se ve favorecida por la programacion de a pares.

#### Integracion continua

El codigo debe ser integrado y testeado a intervalos regulares. Economicamente conviene tener una maquina dedicada a esta tarea, cualquier persona que agregue cambios a la release debe cerciorarse que el 100% de los testeos sean exitosos.

#### 40hs semanales

Trabajar más de esta cantidad de horas degrada la capacidad creativa del equipo. Esto repercute directamente en la cantidad de bugs presentes, se permite que una semana se trabaje más horas. 
Si esta situación se presenta en dos semanas consecutivas entonces el problemano puede solucionarse trabajando más horas

#### Cliente on-site

El cliente debe estar con el equipo, disponible para responder cualquier duda que pueda surgir. Por cliente se entiende alguien que va a utilizar el sistema cuando el mismo se encuentre en producción.
Mientras no es consultado, el cliente puede seguir realizando su trabajo.

#### Estandares de codificacion

Se deben definir para facilitar la práctica de la autoría compartida de código. Deben tener como premisa hacer el mínimo trabajo posible
Debe enfatizar la comunicación. El equipo debe adoptarlo de común acuerdo.

### Lifecycle

![XP lifecyle](graphics/lifecycle_xp.png)

### Resuelve problemas de software

Prevee cuando se va a hacer algo mal, siempre corrijiendo. 

> **Nota**: Hay mas en el apunte.

### Agile benefits

- Better product quality
- Customer satisfaction
- More control on projects
- Risk reduction
- Faster ROI
- Faster time to market
- Higher productivity

> **Nota**: XP es solo usado por el 1% de los programas de desarrollo.

# Metodologias - Scrum

La mas usada en la actualidad, framework ágil para construir, entregar y mantener productos complejos. Diseñado especialmente para equipos pequeños, altamente
cohesionados. Todo el trabajo sucede en iteraciones cortas llamadas sprints. Es un estandar muy puro, generalmente se usa con alteraciones.

Hoy en dia esta metodologia se usa en otras areas que no son de software. Un sprint dura entre una semana y un mes, la catedra usa 15 dias.

> **Nota**: En la practica nos van a pedir usar Scrum.

## Waterfall VS Agile(Scrum)

![Waterfall VS Agile(Scrum)](graphics/agile_vs_scrum.png)

Con agile se busca siempre tener una version funcional que ataca el problema(incluso si no es la version final), mientras el cliente da feedback, se reinicia el ciclo.
Se planifica el sprint y se lo vuelve a presentar al cliente. Esto no se puede con waterfall, con waterfall se crea software no mantenible y se debe saber cual es el resultado final de antemano(puede ocurrir que no es lo que el cliente quiera).

Agile permite mostrar el proceso y lograr una cohecion entre lo que el cliente quiere y lo que le das.

## Scrum roles

No hay gerarquia vertical(en la vresion pura) no hay jefe, sino que son todos iguales.

- **Product owner**: Se preocupa por los intereses del cliente.
- **Scrum master**: Se encarga de llevar adelante todas las ceremonias del scrum y documentar que se esta haciendo.
- **Scrum team members**: Desarrolladores
- **Stakeholders**: Personas externas al equipo itneresados en el producto.

> **Nota**: El manifesto indica que no deben ser mas de 10 personas por equipo, si son mas se debe dividir en dos equipos distintos.

## Ceremonies

1. **Sprint planning**: To craft a sprint goal and plan to achieve it.
2. **Daily scrum**: To review team progress on sprint goal and adjust the plan to get there.
3. **Sprint review**: To review de work completed in the sprint with stakeholders.
4. **Sprint retrospective**: To rewview the product development process and identify areas for continuos improvements

## Artifacts

Elementos uttilizados en el scrum para llevar adelante el projecto, en el backlog se lleva registro del proceso. Es una tarea que los desarrolladores van a tener que implementar en el codigo.

![Scrum artifacts](graphics/scrum_artifacts.png)

Cuando estan en el spring backlog, se estima el user story en base al esfuerzo que requiere realizar esa tarea. Se mide en story points, si el sprint empieza con 22, la idea es terminar en 0 el sprint.
Si no llega a 0, ocurre un **carry over**, se lo pasa al siguiente spring y se debe analizar porque paso. Lo ideal es que el grafico tienda a una funcion lineal, el grafico representa story points en relacion al tiempo.

> **Nota**: Vamos a tener que hacer este tipo de graficos en nuestros trabajos.


