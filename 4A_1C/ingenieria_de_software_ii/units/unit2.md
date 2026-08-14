# Clase 2: Construccion de Arquitecturas de Software

De los requerimientos funcionales y de los atributos de calidad se debe producir una 
arquitectura candidata. Es una forma de disenio llamada Attribute Driven Design.
Una vez se tiene una arquitectura candidata se lleva a cabo un proceso de iteracion 
para perfeccionarla. 

Con ADD se tiene como entrada General scenarios que se utilizan para formar 
Requirements. Luego se llega a una arquitectura. 

## ADD 

0. Se da un orden de prioridad a los atributos de calidad, pues mejorar una 
atenta en contra de otra. Esto implica la creacion de un criterio para saber cual 
priorizar. 
1. Determinar como va a ser la arquitectura, conceptualizarla. Usuarios, sistemas 
externos, donde va a ir la informacion, etc. Version mas inocente y minimalista que 
cumpla con los requerimientos funcionales. 
2. Tomar el primer atributo y proponer escenarios que los pongan a prueba, esto 
permite identificar donde se rompe, los riesgos. Estos escenarios se conocen como los 
**drivers de arquitectura**. Hay riesgos que se pueden determinar como aceptables, 
aceptando que pueden pasar cosas, entonces hay que determinar como solucionar el fallo.
3. Si se cambia algo hay que re-plantearse cada escenario, volver al paso 2. 
Entonces, cuantos mas escenarios hay, mas se tarda en volver a analizar.
4. Fin, se obtiene una arquitectura candidata, encenarios, riesgos asumidos, trade-offs 
asumidos y supuestos asumidos. Sirven para la verificacion post implementacion, para 
que lo verifique un stakeholder y simplemente como documentacion. 

> Los primeros escenarios del paso 2 son los mas probables

Por mas que haya riesgos que se asumen, hay que documentarlos y crear un plan de 
contingencia.

## Estilos arquitectonicos 

Es lo que se busca al conceptualizar el problema, son patrones de alto nivel que 
describen la organizacion general de un sistema en pos de ciertos atributos de 
calidad. Seguir un estilo ya probado provee consistencia y previsibilidad. 

## Dataflow 

Estilo arquitectonico, externosl objetivo es procesamiento de datos, no necesariamente 
un sistema interactivo. Hay varios: 

### Batch Secuencial 

Los datos se transmiten entre programas como un todo, en general mediante archivo. 
El procesamiente ocurre offline, no hay nada sincronico y los procesos no saben de 
la existencia de los demas. Se acumula informacion y en un momento dado se procesa. 

Pero, si falla, se tiene que revertir todo y abortar el proceso o por ahi puede 
guardar el punto de procesamiento y resumir luego. Hay que documentar que hara en 
caso de un error. 

Este estilo tiene como ventaja procesos independientes y permiten el reprocesamiento 
sin perdida de informacion. Sin embargo, no permite procesamiento real-time. En todo 
los casos documentar como manejar fallas. 

### Pipes & Filters 

En este caso se tiene procesamiento en tiempo real, se tienen procesos chicos donde 
mediante pipes la salida de uno es la entrada del siguiente. Es un stream de datos, 
es decir, no se necesita de un fin para poder emitir la salida. Son procesos 
extremada mente flexible y se utiliza mucho en los OS. Pero bueno, si alguno de los 
procesos falla, cualquier dato que este en transito se pierde. Entonces, tiene una 
baja tolerancia a errores en el procesamiente. Es el pipeline clasico de arqui. 

### Hierarchical Layers 

Cada capa provee servicios a la capa superior y es cliente de la capa inferior, el 
problema es encontrar el numero perfecto de capas. Las capas pueden ser logicas o 
fisicas. Divide un problema complejo en abstracciones, mejoras en una capa impactan 
en todo el sistema y suponen reusabilidad y facil intercambio de implementaciones. 

Sin embargo, no todos los sistemas se pueden estructurar asi, hay un posible deterioro 
de performance con muchas capas(peor si son fisicas, por ejemplo, saltos entre 
servidores) y los cambios suelen tener que replicarse en todas las capas.

## Sistemas distribuidos 

Como se descubren miembros de la red y como se comunican. 

### Broker

Coordina comunicacion entre clientes y servidores, busca resolver el problema de 
encontrar un servicio y el de como comunicarse con el servicio. Los hace operando como 
un proxy, los servidores exponen servicio a partir del broker. Facilita el discovery 
de servicios, los servicios pueden no estar expuestos en forma directa a la red, pero el 
broker es un Single Point Of Failure(SPOF) stateful. Si falla se cae todo. 

### Publish-Subcribe 

Analogo al patron **Observer** en un contexto distribuido. Un componente 
**publisher** notifica a muchos consumidores **subscribers** suscritos a el. 
Hay un sistema en el medio que se encarga de hacer el broadcast a los 
subscribers, se conoce como el **topic**(topico). Este se encargara de hacer la 
multiplexacion. Ademas, estan completamente desacoplados, el sistema de origen no 
sabe de la existencia de los receptores y viceversa. 

Entonces, los publishers y subscribers no se conocen mutuamente, solo se conocen 
los topics. Es posible agregar y quitar suscriptores en forma transparence. La 
desventaja que tiene es que requiere consideraciones especiales respecto de la perdida 
de eventos, segun que solucion elijo puede o no haber perdida de mensajes. 

> Un webhook es muy parecido, hay implementaciones de topicos que funcionan como 
webhooks. Pero difieren en como lo implemmentas.  

### Forwarder-Receiver 

Resuelve exclusivamente los problemas de comunicacion, se abstraen por completo y 
resuelven solos los problemas. Permite consumir servicios remotos como si fuesen 
metodos locales. Corba y Java RMI son implementaciones. Entonces, simplifica el 
uso al ser transparente, pero se esconde la complejida, por lo que se desconoce 
si tiene mucho overhead, si hay algun problema oculto, etc. 

### Client-Dispatcher-Server 

Resuelve el problema de encontrar a los servicios, pero la conexion va a ser directa. 
Permite conexiones cuando el **server** no tiene una direccion fija. Por ejemplo, 
bit torrent, aplicaciones de mensajeria / videoconferencia. 

## Sistemas interactivos 

Sistemas que esperan a un input, para poder reflejar el cambio en pantalla. Clasico, 
MVC, PAC, MVP, VIPER, etc. Conceptualmente, todas estas opciones hacen lo mismo, lo que 
cambia son las abstracciones y responsabilidades. El objetivo es separar la logica de 
negocio de las vistas de presentacion. 

### MVC 

El mas popular, divide la aplicacion en Modelos, Vistas y Controladores. 
La desventaja principal es que hay un **trickle effect** entre componentes cuando 
cambia el modelo de datos. 

## Sistemas basados en eventos 

Permite crear sistemas distribuidos con bajo acoplamiento. El sistema se divide en 
componentes completamente agnosticos uno de otros. Conceptualmente existen tres 
componentes: 

- Event producer 
- Channel 
- Event consumer 

Notar que es conceptual pues un componente puede ser producer y consumer al mismo 
tiempo. Estilos aplicables: 

- Single Event Processing 
- Event Stream Processing 
- Complex Event Processing  
- Online Event Processing

Complex event: Si un usuario hace un login y cambia password y va a hacer una 
compra a un articulo muy caro, son eventos normales, pero si pasan las 
tres en secuencia es un indicador de posible fraude. Este tipo de analisis es lo 
que permite hacer este estilo.  

Tiene ventajas y desventajas(ver apuntes).

--- 

Existen nun monton mas de estilos, investigar a voluntad. 

> Libro para investigar patrones: Patterns of Entreprise Application architecture.



###



