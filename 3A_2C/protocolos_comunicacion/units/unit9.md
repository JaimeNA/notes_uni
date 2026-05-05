# Unidad 9: Capa de enlace

Cumple con un servicio basico, mover paquetes desde un nodo al nodo adyacente sobre un 
enlace de comunicacion. Tiene varias propiedades:

- Encapsulamiento
- Acceso al medio
- Comunicacion confiable
- Control de flujo
- Deteccion de errores
- Correccion de errores
- Half-duplex o full-suplex

Existen dos tipos de comunicaciones a nivel enlace: 

- Broadcast(LAN)
- Point to point(WAN)

## Point to point

Los protocoloes de nivel de enlace de este tipo son mas simples que para enlaces 
broadcast:

- Generalmente usados para encapsular datagramas IP
- No se necesita control de acceso al medio

### Protocolos

- SLIP(simple)
- PPP(sucesor de SLIP)

## Broadcast

Es un bus, un canal compartido. Tiene posibilidad de interferencia, lo que 
significa que puede haber colisiones(dos signals al mismo tiempo). Entonces, 
debe existir una norma de como de comparte el canal, la coordinacion se hace 
usando el mismo canal de datos. 

### Protocolos 

Son varios protocolos de acceso simple que pueden ser: 

- Protocolos de acceso aleatorio(ALOHA, CSMA, CSMA/CD, CSMA/CA)
- Protocolos de acceso controlado(Reservation, Polling, Token passing)
- Protocolos de acceso canalizado(FDMA, TDMA, CDMA)

Los protocolos de acceso canalizado se pueden dividir for frecuencia, por tiempo o por 
codigo. Los de acceso controlado como token ring usan un timeout para recuperar el 
token si un host se queda trabado. Los de acceso canalizado son los mas interesantes, 
cuando un nodo quiere transmitir: transmite. Si dos nodos transmiten al mismo tiempo 
hay una colision, el protocolo a usar debe definir: 

- Como detectar colisiones
- Como actuar frente a las colisiones

Este ultimo tiene en juego el tiempo de propagacion de los electrones, pueden 
ocurrir colisiones si uno transmite, pero no escucho otro que habia empezado a 
trasmitir. CSMA usa un tiempo aleatorio de reintento, cada vez prueba uno 
mas grande hasta que pueda transmitir. Una variacion es CSMA/CD(Collision detection), 
cuando uno detecta colision, hace un broadcast a toda la red anunciando una colision. 

![Protocolos de acceso aleatorio](graphics/broadcast.png)

> Las de acceso canalizado usan codigos ortogonales o transformadas de fourier.

## Ethernet 

### Topologia bus

Surgio de manera muy rustica, era muy economico y se basaba en un cable coaxil. 
Todos los host estaban en la misma linea, conectados a un hub. Cada host se puede 
conectar. 

### Topologia estrella

Cada computadora se conoce a un switch, cada host se puede conectar y desconectar sin 
afectar al resto de la red, se basan en el MEC address y se deben evitar ciclos.

## MAC address 

Seis octetos, tiene varios bits para indicar si es Unicast o Multicast, y si es 
Universally administered addresses o no.

## Preambulo

Son 7 octetos, usados para sincronizar la transmision.

## ARP

La direccion MAC es conocida, pero no siempre lo sabe el otro host, 
entonces se crea un paquete preguntando quien tiene tal IP y sera respondido con 
la IP y la MAC address. Si el otro host esta en otra red, hay una direccion de 
la gateway, entonces, obtiene la MAC del gateway. 

Similarmente a ICMP(aunque es entre red y transporte), no es de 
red(no conecta entre hosts) ni de enlace(no transmite datos de un host a otro).

Se pueden poner dos maquinas con la misma MAC address con IP distintas, una puede 
ser dinamica y la otra estatica. Lo que haga con el paquete depende, de fowarding y 
otros.

> Este protocolo se utiliza de distintas formas, por ejemplo, RARP

Si hay dos redes y una de ellas es oculta, entonces, la mac de respuesta es la 
del router.

### ARP spoofing

ARP no contempla autenticacion, cualquiera puede responder un ARP request para 
relacionar un IP con un MAC, lo que permite distintos tipos de ataque: 

- Man-in-the-middle
- Denial-of-service
- Session hijacking 

> Algunos programas para experimentar: Arpspoof, Arpoison, Cain & Abel, Ettercap

Hay varias maneras de mitigar esto, pero la mas sencilla es pasar a IPv6, que usa la 
MAC en la misma IP address para identificar los host.

## Notas practica

- En CSMA-CD se definen restricciones sobre el size maximo de la trama y el largo 
del cable(trama el doblde del minimo). Entonces no va a haber colisiones no 
detectables(esto puede ocurrir si una maquina termina y luego ocurre la colision).
- Los routers ayudan a separar las redes en dominios de colision(redes broadcast).
- Un switch es como un router(en caso de ser un switch de capa 3), pero con mas 
bocas. **No** se puede tener un loop de switched, sino ocurre una tormenta de broadcast. 
- Hay protocolos que permiten que haya loops de switchs.
- IP sirve para conectar distintas redes, cosa que no se podia con ethernet.
- Trama es capa de enlace, paquete es IP.
- Con ARP, cuando mandas broadcast, todos pasan a tener tu IP y MAC.
- En IPv6 no hay ARP.
