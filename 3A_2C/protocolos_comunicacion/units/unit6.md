# Unidad 6: Protocolos de transporte
 
 > DNS puede usar TCP y UDP. 

 Proveen la comunicacion logica entre dos procesos que corren en distintos hosts. 
 Se ejecutan en las **puntas finales**, hay varios:

 - UDP
 - TCP
 - SCTP(No se usa tanto)

Ninguno, UDP y TCP, ofrecen ni un minimo de demora o latencia, 
ni minimo de ancho de banda.

Se implementan con sockets, cada proceso tiene un socket. Estan pensados como file 
descriptors, entonces para hablar con otro host se usa un socket. Es mas, 
usan la misma tabla que los file descriptors(pues usa los mismos system calls).

## Multiplexacion 

(en la presentacion de campus hay mas data)

Gran parte de los puertos estan definidos tanto para TCP como para UDP, aunque 
algunas aplicaciones utilicen solo uno de ellos.

> Si no somos root no podemos abrir puertos menores a 255(reservados para aplicaciones 
publicas).

## Demultiplexacion sin conexion

Se usa para saber para que proceso/socket es lo que llego.

- Crear socket con numero de puerto.
- El socket se identifica por el par <IP destino, Puerto destino>
- Cuando el host recibe un segmento UDP -> Dirige el segmento al socket que atiende
- Datagramas con distinto IP origen son atendidos por el mismo socket

## UDP 

UDP transporta datos de manera no confiable entre hosts.

- No orientado a conexión
- No confiable
- No ofrece verificación de software para la entrega de segmentos
- No reensambla los mensajes entrantes
- No utiliza acuses de recibo (ACK)
- No proporciona control de flujo

## Demultiplexacion orientado a conexion

Seria TCP. Cada socket identificado por:

- IP origen
- Puerto origen
- IP destino
- Puerto destino

Host que recibe usa los 4 valores para dirigir el segmento al proceso que corresponde
Conexiones simultáneas: c/u con su socket

## TCP

> Si una maquina tiene dos procesos intentando de conectarse al mismo servidor lo 
que ocurre es que cambia el puerto en el servidor, manteniendo el puerto de origen.

### Repaso conexion confiable

Si el protocolo de red es confiable

- No se alteran bits
- No se pierden paquetes
- Los paquetes llegan en orden

Entonces, la lógica a implementar es muy simple

> IP no es confiable.

--- 

Si hay errores se complica, pues hay que avisar que llego y que no llego, entonces 
los algoritmos para **Sender** y **Receiver** no son tan simples. Pero se puede 
recuperar con `ACK` y con `NAK`, pero se pueden perder esos tambien! Entonces, se 
manda otro `ACK`, pero el otro podria si haberlo recibido, es por eso que todo 
paquete debe estar identificado.

Hay algunos controles que se pueden implementar:

- **Stop & wait**: Es trivial, manda `ACK` y se bloquea, pone una alarma en caso 
de que no llegue y vuelve a mandar `ACK`. Es facil de programar.
- **Pipelining**: Se envian los paquetes y al final se manda un `ACK` diciendo 
hasta donde recibio. Si se recibio todo termino. Un poco mas complicado, pero mucho 
mas eficiente. Cada envio tiene una alarma de un tiempo, si no llega el `ACK` hay 
un error.

TCP no los numera 1, 2, 3... sino que usa bytes, es a nivel de desplazamiento de bytes 
que esta enviando. El primero no empieza en 0. Se coloca en el campo `Sequence number` 
del header.

El flag `URG` indica que si hay algun paquete urgente, que los mande primero. Esto no 
se usa mucho. El flag `RST` corta la conexion de una manera abrupta. En cambio, 
`FIN` indica que una punta no tiene nada mas que decir, pero se queda escuchando 
por si la otra punta todavia tiene algo que decir(mas largo que `RST`).
Por otro lado, `CRC` es como un checksum, paridad.

TCP ofrece un circuito virtual entre aplicaciones de usuario final. 

- Orientado a conexión
- Confiable
- Divide los mensajes salientes en segmentos
- Reensambla los mensajes en el host destino
- Vuelve a enviar lo que no se ha recibido
- Control de flujo (rfc 7323)
- Control de congestión (rfc 5681)

El cliente es el que inicia la conexion, es la unica distincion que usa TCP.

> En el tercer `ACK` el cliente ya puede enviar datos.

Si esta la conexion y no tengo nada para mandar, de ambos lados se mandan datos 
**keep-alive**. Son paquetes `ACK` vacios para mantener la conexion.

Si se manda el `ACK 120` y se envia informacion `seq=180`, este se guarda en el buffer. 
Luego, se puede recibir el `seq=120` esperado. Si se vuelve a mandar el `180`, se ignora.

> El `FIN` lo puede mandar cualquiera de los dos.

### Control de flujo

Si uno manda paquetes y se llena el buffer porque el otro esta ocupado con otra cosa, 
no puede pisar el buffer porque ya mando el `ACK`. Tampoco se pueden tirar los 
paquetes porque estaria mal. Lo que se usa es el size del buffer que se envio en uno 
de los paquetes(en el campo `window`), entonces no se envian mas datos que puedan 
entrar en el buffer del que va a recibirlos.

El problema es que `window` solo puede especificar hasta un buffer de 64KB, lo cual 
es inaceptable hoy en dia con las velocidades de internet actuales. Lo que se 
usa son los `options` para negociar y especificar que `window` indica el size 
con un multiplicador. Es una de las aplicaciones de `options`.

> Gracias a esto se evito armar un TCP nuevo y migrar todo.

### Control de congestion

Puede ocurrir que haya un cuello de botella en algun punto de la red, entonces, 
se pierden paquetes. En teoria, la capa de arriba no deberia encargarse de la capa 
de abajo(en este caso, la capa de red). Sin embargo, se puede tener informacion de la 
capa inferior. TCP se puede dar cuenta cuando hay congestion, como hoy en dia es poco 
probable que se pierda un paquete por errores de red, la causa mas probable es 
que hay congestion(y por eso llega el timeout). Entonces, una vez detectado eso, 
se mandan los paquetes mas lento, como todos hacen esto se libera la congestion. 
Esto se conoce como **AIMD**(Additive Increase Multiplicative Decrease).

> La idea es que nadie se haga el vivo, suele ser el caso, pero si ocurre hay forma 
de detectar si se siguen mandando paquetes a alta velocidad.

Como se decide un valor de `timeout` razonable? Se usa cuando tardo en ir y volver el 
handshake, de ahi tengo una cota inferior de cuando tardara, uso eso como 
`timeout`. 

> Como decidir el `timeout` no aparece en el `RFC` pues depende de la implementacion.

## NAPT (aka NAT)

### SNAT

Hasta mediados de los 90s para usar internet cada host necesitaba tener una 
IP publica. Pero rapidamente IPv4 se quedo sin IPs y IPv6 tardara decadas en 
terminar de implementar globalmente. 

Por lo tanto, surgio NAT, de manera que si en una red local se hace un pedido al 
internet, el router va a registrar la IP local y un puerto. Entonces, cuando sale 
el pedido al internet es con la IP publica y el puerto asignado. 

> Sale de la red interna a la externa.

### DNAT

**Port fowarding**, configura NAT para cambiar el puerto de destino. Esto permite 
implementar balance de carga, teniendo n servidores con una unica IP publica.

> Viene de la red externa a la interna.

## Firewall

Un firewal opera en el nivel mas bajo de red:

- Evita acceso no autorizado a la Intranet
- Permite definir que trafico entra y sale de nuestra red

> Lo vamos a ver mas adelante


