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

### DHCP

Dynamic Host Configuration Protocol, esta casi deprecado, se basa en UDP, por lo 
que transmite la informacion en binario. Lo que hace la computadora es enviar a la 
red un request pidiendo una direccion IP, si hay un servidor DHCP le va a responder 
con una IP asignada.

**De parcial**: Que protocolo tiene un puerto de origen cisco? DHCP

Son 4 paquetes:

- DHCP DISCOVER
- DHCP OFFER
- DHCP REQUEST
- DHCP ACK

Se usa el mismo **transaction id** durante la conversacion.

Si tenemos varias redes con un router y un servidor DHCP, entonces el router pasa a 
ser un DHCP relay. Cuando se llega al 50% del tiempo, la computadora pide renovar 
su IP con un **renew request**, el cual es respondido con un ACK del servidor. 
Ese era el tiempo T1, si el servidor no responde, hay un tiempo T2 en el que la 
computadora vuelve a hacer DCHP DISCOVER. 

Que ocurre si habia un servidor que tenia una IP asignada? Va a cambiar su IP y habra 
que avisar al servicor DNS del cambio. Hay un cambio adicional en el DHCP REQUEST, 
el codigo 81, que le pide al servidor DHCP que mande un DNS UPDATE al servidor 
DNS. 

## Network Address Translation(NAT)

(Ya lo anote en la unidad 6)

### DNAT(Destination NAT)

Se cambia no solo de IP publica a privada, sino que se cambia el puerto tambien. 
Esto hay que configurarlo en el firewall manualmente o se hace de manera automatica 
mediante protocolos. Ademas, hay una tercera opcion, se puede lograr una comunicacion 
entre dos dispositivos mediante el uso de un "Relay". En los routers de cada extremo 
se usa solo SNAT, pero entre medio se conectan al servidor de, por ejemplo, 
whatsapp, el cual se encargara de enviar la informacion al destino. Esto no esta tan 
bueno pues gasta recursos de servidor entre medio, entonces se suele usar piping para 
eso, establece una comunicacion point to point.

