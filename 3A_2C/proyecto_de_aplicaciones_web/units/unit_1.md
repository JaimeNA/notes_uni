# Unidad 1: Introduccion

HTTP/HTTPS es extremada mente sencillo y facil, por eso es adoptado masivamente. Es un protocolo 
que se conoce como **Request-Response**. Donde el cliente realiza una solicitud y el servidor 
envia una respuesta como consecuencia.

Las versiones mas nuevas como HTTP3 permiten hacer multiplexing y cosas server-side, pero en 
su base sigue los mismos principios. Busca maximizar la compatibilidad. HTTP usa TCP/IP, 
mientras que HTTP3 usa UDP/IP(mas rapido).

Por definicion, HTTP es **stateless**, no tiene forma de saber algo sobre un cliente. 
Por mas que la solicitud anterior haya sido del mismo cliente(trata a todos por igual).
Esto hace que sea muy facil escalar los servicios.

## Tipos de escalamiento

- **Vertical**: Mejoro el hardware. Mas facil, pero no deseado y llega un punto en el cual no 
se puede mejorar mas
- **Horizontal**: Agrego mas servidores y distribuyo la carga mediante un **load balancer**

La ventaja de stateless en el escalamiento horizontal es que no hay un servidor que tenga la 
informacion de un cliente y otro no. 

## Un poco de historia

En la Web 1.0 solo habia archivos que la gente hacia publicos para que otros puedan mirar, 
pero no se podia comentar salvo mandando un mail. 

Entonces, surge la Web 2.0 donde HTTP se empezo a quedar corto, ahora la Web era RW y no solo 
R-Only(aparecio Request-Response). Estaria bueno seguir autenticado una vez te logueas, 
aparece el header **cookie**. Entonces, se guarda el **session id** para utilizarlo 
en cada solicitud(asi se evita enviar usuario y password en cada request).
El protocolo sigue siendo stateless, pero la aplicacion no. De manera que ya no puedo 
escalar horizontalmente. Ahora, si un servidor recibe un request y no lo reconoce, debe 
rechazar el request. Hay dos formas de resolver esto:

- Cambiar la estrategia de round robin a **sticky session**, donde el load balancer recuerda a 
que servidor corresponde cada session id.
- Mantener round robin, pero usar un medio compartido para guardar las sesiones(un motor de 
base de datos)

Ahora el servidor no solo lee archivos, sino que tiene logica arbitraria unica a mi servicio.

Mas tarde, con Java se podian crear servidores web, pero tenian un problema de **performance** 
debido al **JVM**. Sin embargo, se continuo usando y es una de las maquinas virtuales mas 
eficientes que hay, de la cual se baso la maquina virtual de JavaScript.

Luego, esta el problema de las condiciones de carrera o deadlocks debido a los threads, en 
Java esto se soluciona simplemente: No tener estado compartido. Hay una base de datos de la 
cual todos los threads leen(el problema surge cuando se escribe, no cuando se lee).

Java originalmente penso que los servicios web iban a ser creados solo por empresas, de manera que 
solo se ofrecia en la **Enterprise Edition**. Estos servidores se conocian como 
**Application Servers**. Sin embargo, tambien surgieron los **Application Containers** por parte 
de la comunidad de Java para los usuarios con Java no enterprise(era open source).
Entonces, al ser una version mas vanilla, se empezo a dejar de lado la version enterprise debido 
a que esta se deviaba mucho de la version Vanilla de Java. Termino ganando la comunidad y hoy en 
dia hay una unica version de Java, **Java Standard Edition**.

Hay varios mecanismos que Java tiene para evolucionar:

- JSR (Java Specification Request)
- JLS (Java Language Specification)
- JEP (Java Enhancement Proposal)

Todas esas cosas se votas en un comite donde participan varias empresas y representantes de 
la comunidad open source. De manera, que cuando Oracle compra Java de Sun, este no queria dar 
tanta libertad al comite, por lo que rechazaba todas las propuestas haciendo que no se logre nada.
Esto se soluciono entre Java 7 y Java 8, recordar que Oracle tenia todos los derechos.

Mientras la comunidad no podria hacer nada contra Oracle, hubo un momento en el que existio un 
fork llamado **Jakarta**. Hasta que hace no mucho tiempo Oracle decidio hacer que Jakarta sea 
oficial, reemplazando **javax**(Java Extended).

> Tener cuidado, nosotros vamos a usar javax.

> Un .jar es basicamente un .zip con otro nombre, simplemente es convencion.

## Interfaz `servlet`

Es la interfaz que nuestra clase va a usar, lo cual evita que el servidor de la materia tenga 
que correr un .jar cada vez(Chequear esto, no estoy seguro si se referia a Servlet). 
Esto se logra utilizando **reflexion**.

``` Java
interface Servlet {
    doGet(HttpServletRequest, HttpServletResponse);
    doPost(...);
    doPut(...);
    doDelete(...);
    serve(...);
}
```





