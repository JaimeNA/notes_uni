# Unidad 7: Capa de red

Los protocolos de red corren en varios host: PC, routers, servidores, etc.
El objetivo de la capa de red es transladar paquetes de un host a otro, que pueden 
estar en distintas redes. Los routers manejan una tabla de ruteo para enviar los 
paquetes(en la capa de red) de un lado a otro. La capa de red podria ofrecer:

- Entrega garantizada
- Demora minima garantizada
- Orientada a conexion
- Entrega en orden
- etc.

Hubo muchas tecnologias que ofrecian todo eso, surgio de cuando se utilizaban las redes 
telefonicas el concepto de **Virtual Circuits Networks**. Era orientado a la conexion 
y enviaba todo en orden, pero era mas complejo y encima el primero en llegar se 
copaba todo el ancho de banda hasta que terminara. Entonces, tampoco servia que los 
protocolos de transporte se ocuparan de eso, de manera que era mas simple dejar que 
la capa de transporte se encargue de eso.

Internet es simplemente un tipo de red, mas especificamente **Datagram Network**, 
un paquete puede tomar un camino y el proximo otro sin problema, pueden llegar 
desordenados y luego los protocolos de transportes tienen que ocuparse de ordenarlos.

## Direcciones en tabla de ruteo

No hace falta escanear toda la IP, basta con leer la primer parte hasta que sepamos a 
donde tiene que ir, el prefijo es lo que se conoce como mascara de red. 
Manda el paquete a la interfaz que mas matchee la direccion. 

![Subnets](graphics/subnets.png)

Hay distintos tipos de redes:

- **Loopback**: 127...
- **Privadas**: 
    - 10.., 
    - 172.16.0.0 a 172.31.255.255
    - 192.168...
- **Link local**: 170.254.0.0 a 169.254.255.255

Ejemplo tabla de red:

|   Destination | Mask           |   Gateway     | Interface |
| ------------- | -------------- | ------------- | --------- |
| 20.0.0.0      | 255.255.255.0  | 20.0.0.1      |   eth0    |

En el super, el carrito es el enlace, la interfaz seria la caja para que el 
paquete sea enviado a nuestra casa.

> En el parcial poner todas las entradas en orden, de mayor a menor.

**No** hay casos especiales, todos pasan por la misma tabla.

## Subredes(VLSM)

Dada la red 192.168.80.0/24 se desea particionar en 4 redes:

- Guest - 14 hosts
- Robots - 58 hosts
- Servers - 29 hosts
- Workers - 120 hosts

Buscamos la cantidad de bits minima que necesitamos:

- 4
- 6
- 5
- 7

Arrancamos con la red que mas hosts tiene, entonces arrancamos con workers, 
mascara de red de workers:

```
11111111.11111111.11111111.10000000

255.255.255.128
```

Hay dos opciones, elegimos una para la red workers: 

- **Network address**: 192.168.80.0
- **Usable host range**: 192.168.80.1 - 192.168.80.126 
- **Broadcast address**: 192.178.80.127

Ahora, con los robots: 

```
11111111.11111111.11111111.11000000

255.255.255.192
```

Ahora hay cuatro opciones, pero las primeras dos ya estan ocupadas por workers.

- **Network address**: 192.168.80.128
- **Usable host range**: 192.168.80.129 - 192.168.80.158 
- **Broadcast address**: 192.178.80.159

> Pude haber agarrado cualquiera de las demas, esta es solo una manera de resolverlo, en 
el examen mostrar todas las posibilidades.

Y asi sucesivamente con los que quedan.

### Motivos para crear subredes

Puede haber razones topologicas:

- Host muy distantes
- Interconectar distintas capas fisicas
- Filtrar trafico entre redes

O puede ser por razones organizativas:

- Simplificar la administracion de la red
- Mapear la estructura de la organizacion
- Aislar el trafico

## Superred(CIDR)

Mediante ruters, hay varios motivos para usarlas:

- Optimizacion de enrutamiento
- Escalabilidad
- Mejor uso del espacio de direcciones
- Reduccion de latencia

> Es gracias a que la IANA empezo a segmentar los RIR, las direcciones IP cobraron 
un orden jerarquico.

## Paquete IPv4(datagrama)

`Hlen` no son 4 bits, sino que se debe multiplicar por 4, esto obliga a poner un 
relleno para que el length sea multiplo de 4.

## Asignacion de direcciones IP

Como podemos asignar una direccion IP a una computadora? Puede ser:

- **Estatico**: Se asigna de forma manual
- **Dinamico**: Puede ser Local-Link(El dispositivo se asigna una IP a si mismo) o 
algun que otro protocolo(que se fueron deprecando): RARP, BOOTP y DHCP.

> BOOTP era muy tedioso porque manualmente se tenia que configurar la IP de cada 
host.

### DHCP

Dynamic Host Configuration Protocol, esta casi deprecado, se basa en UDP, por lo 
que transmite la informacion en binario. Lo que hace la computadora es enviar a la 
red un request pidiendo una direccion IP, si hay un servidor DHCP le va a responder 
con una IP asignada.

**De parcial**: Que protocolo tiene un puerto de origen cisco? DHCP

Son 4 paquetes(DORA):

- DHCP DISCOVER
- DHCP OFFER
- DHCP REQUEST
- DHCP ACK

Se usa el mismo **transaction id** durante la conversacion.

Si tenemos varias redes con un router y un servidor DHCP, entonces el router pasa a 
ser un DHCP relay. Cuando se llega al 50% del tiempo, la computadora pide renovar 
su IP con un **renew request**, el cual es respondido con un ACK del servidor. 
Ese era el tiempo T1(50%), si el servidor no responde, hay un tiempo T2 en el que 
la computadora vuelve a hacer DCHP DISCOVER. 

Que ocurre si habia un servidor que tenia una IP asignada? Va a cambiar su IP y habra 
que avisar al servicor DNS del cambio. Hay un cambio adicional en el DHCP REQUEST, 
el codigo 81, que le pide al servidor DHCP que mande un DNS UPDATE al servidor 
DNS. 

## Network Address Translation(NAT)

(Ya lo anote en la unidad 6)

> **Que header modifica SNAT de el paquete IP?** Modifica la direccion de origen. 

### DNAT(Destination NAT)

Se cambia no solo de IP publica a privada, sino que se cambia el puerto tambien. 
Esto hay que configurarlo en el firewall manualmente o se hace de manera automatica 
mediante protocolos. Ademas, hay una tercera opcion, se puede lograr una comunicacion 
entre dos dispositivos mediante el uso de un "Relay". En los routers de cada extremo 
se usa solo SNAT, pero entre medio se conectan al servidor de, por ejemplo, 
whatsapp, el cual se encargara de enviar la informacion al destino. Esto no esta tan 
bueno pues gasta recursos de servidor entre medio, entonces se suele usar piping para 
eso, establece una comunicacion point to point.

Existe UPnP(Universal Plug and Play), basicamente, si una aplicacion necesita 
port fowarding simplemente usa este protocolo en vez de necesitar que un 
administrador lo haga manualmente.

> **Que necesita una interfaz para empezar a navegar?** Direccion IP, mascara de 
red, direccion de gateway y direcciones de servidores DNS. 

Holepunching se usa en cosas como llamadas de WhatsApp, para que en vez de tener 
que pasar por el servidor, los telefonos obtienen la IP de cada uno mediante 
el servidor y utilizan SNAT para comunicarse entre si directamente. El primer 
paquete de uno a otro se descarta pues no hay conexion todavia, pero se logro 
generar una entrada en el router. El que esta del otro lado tiene que hacer lo 
mismo para crear las entradas y se establece la conexion.

## ICMP

Esta encapsulado dentro de IP, es una discusion teoria si esta en capa de red o de 
protocolo. Tiene seccion de datos para hacer echo requests, asi que se podria 
en teoria userlo para tranferir informacion, pero no conviene pues se bloquea(en 
ese caso es mejor usar DNS). Si mandamos un ping a loopback se esta probando todo 
lo que sea el funcionamiento interno del host. Si mandamos un ping a una computadora 
dentro de la red se esta probando que todo este bien conectado.

![Diagnostico de red](graphics/ping.png)

## IPv6

- Tienen un encabezado fijo(no hace falta padding)
- No permite fragmentacion(Era muy cumplicado)
- Estructura jerarquica de direcciones
- Permite la configuracion automatica
- Todos los dispositivos pueden tener una direccion exclusiva
- IPSec incorporado en el design(Antes era opcional)

### Notacion abreviada

Hay reglas para poder escribir todo mucho mas rapido.
Muchos ceros juntos --> `::`(solo una vez, sino no sabes cuantos faltan)
Pero si hay mas de un grupo de ceros, entonces hay dos maneras de representarlos.

### Tipos

- **Unique local address**: IP privadas, no pueden ser ruteadas a internet
    - **Local link address**: No pueden atravezar los router
- **Global address**: Internet

### Clases

- `000`: Unspecified, Loopback, Embedded IPv4 addresses
- `001`: Global Unicast Addresses
- `010 - 110`:Reserved by IETG for future use
- `111`: Link-local, Unique-local, Multicast addesses

### Tipos(de otra forma)

- Unicast
- Multicast
- Anycast

Anycast es para el caso donde hay varios hosts con la misma IP publica, permite 
conectarse al mas cercano que haya(pensando globalmente).

### Interface ID

Son 64 bits, hay dos formas de determinarlo:

- **EUI**: Se basa en la direccion MAC
- **EFC 7217**: Genera direcciones de forma aleatoria, pero estables

La segunda opcion busca evitar que te puedan trackear, basicamente se podria 
determinar en que redes estas pues siempre vas a tener la misma direccion.

---

**Flow Label** ayuda al router con el ruteo, indica la ruta que hay que hacer, haciendo 
que lleguen mas ordenados y aliviendo la carga de TCP.

### Ipv$-Mapped IPv6

Se puede mapear una IPv4 mediante IPv6, para que se pueda usar una direccion similar.

### NAT64

Por si solas, las redes IPv6 no son compatibles con IPv4, para ello existe 
**NAT64** y **DNS*64*. 

### Tunneling

Encapsular un protocolo en otro de **igual o mayor nivel**.

> **Que pasa si al querer comunicarse entre dos IPv6, hay una red IPv4 en el medio?** 
Se encapsula en IPv4, entonces habra dos headers IP mientras viaja en IPv4 y luego 
vuelve a ser IPv6.

En general se usa UDP si hay que encapsular pues tiene lo minimo necesario, hacer 
de wrapper. 

## Seguridad

En una empresa normal(IT) siempre hay que garantizar confiabilidad, lo menos 
importante es que esta andando el servicio. Caso contrario, si estamos en una 
empresa que se centra en el proceso pasa lo opuesto(hay procesos que no se 
pueden frenar). Sin embargo, en este ultimo caso no importa tanto si, por ejemplo, 
alguien se entera a que temperatura funciona un horno.

![Seguridad](graphics/ipsec.png)

Entonces, en IPv4 tambien hay IPSec, pero es opcional. Tiene dos variantesL 

- Modo transporte(entre dos hosts)
- Encapsulamiento(mas seguro, mas autenticacion y mas de un header)
- Modo tunel(VPN)

## Firewalls

Antes solo se filtraba por IP y puerto, luego se empezo a implementar a nivel 
de circuitos(por si hacian como si ya tenian una conexion TCP establecida), 
luego subieron a nivel de aplicacion para validar la sintaxis de la conversacion, y 
finalmente se hace un filtrado dinamico, unieron circuit level con packet filter.

### IP Tables

Despues de routear decide si aplicar NAT, por ejemplo, si va de una interfaz interna 
a otra interna, no hace falta cambiar la IP(si va de LAN a wifi). 

![IP tables 1](graphics/ip_tables_1.png)
![IP tables 2](graphics/ip_tables_2.png)

Hay algunos comandos basicos: 

- `MASQUERADE`: Aplicar la IP publica como origen cuando sale para afuera.
- `SNAT`: Similar a `MASQUERADE` se puede ser lo mismo o tambien cosas extra como 
cambiar el puerto.

Solamente aplica esto al que inicia la conexion, no a todos los paquetes(chequear, 
pregunta importante).


## Notas practica

- Para que sirve la capa de red?
- En vez de ser una conexion directa, se parte la informacion 
en paquetes, permitiendo que haya mas de una persona en la linea.
- En internet, si se corta un cable los paquetes se routean por otro lado, no 
se corta el servicio, igual si el cable esta saturado.
- Si UDP no responde, ICMP es el que se encarga de responder.
- En `route` usar `-n` porque no se va a entender nada sino.
- La capa de red no se encarga de ordenar los paquetes, se encarga transporte.
- Hay dos tipos de broadcast, uno a nivel red y otro a nivel enlace, la 
diferencia es como le llega a cada uno. Si haces un broadcast de capa 3, tiene 
que ser tambien de capa 2.
- Si tenes dos servidores DHCP en la misma red, los dos van a responder al 
DISCOVER y se va a elegir la respuesta que tenga mas puntos a favor, es decir, 
depende de la implementacion. Pero basicamente podes dejar a todos sin internet 
si agarran el DHCP malo.
- Si el DHCP en una red es centralizado y hay muchos routers en el medio, se pone 
un **Relay agent**(puede o no ser el router, una compu). El agent hace de servidor 
DHCP, responde los DISCOVER, pero consultando antes al servidor DHCP.
- Los routers no propagan los broadcast.
- En el discover la mac tambien va con todos 1s: ff:ff:ff:ff:ff:ff
