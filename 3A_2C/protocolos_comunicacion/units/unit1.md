# Unidad 1: Introduccion

Las redes que vamos a ver es un conjunto interconectado de hosts, cada host es un equipo autonomo y no son necesariamente computadoras(pensar en IoT).
El host envia datos en paquetes, de L bits de longitud. 

Hay un problema, por ejemplo cuando contratamos un ISP, ya que nos venden la velocidad entre nuestra casa al ISP, pero no garantiza la misma velocidad a otros servicios.
Ademas, si todos se conectan al mismo ISP, aparece un cuello de botella, haciendo que todo sea mas lento.

![Packet switching: Demoras](graphics/packet_switching_demoras.png)
![Packet switching: Perdidas](graphics/packet_switching_perdidas.png)

Antes, tampoco habia estandares, entonces sistemas de diferentes empresas podian no ser compatible.

## Modelo OSI - TCP/IP

Es un modelo de 7 capas, usando el protocolo TCP/IP, el cual se utilizo para estandarizar todo esto.

![Modelo OSI - TCP/IP](graphics/modelo_osi.png)

A la capa de red no le importa el motivo, solo le importa hacer llegar un paquete de una computadora a la otra. 

> Un protocolo es un conjunto de reglas que ambas puntas tienen que seguir

Estos protocolos pueden ser:

- **Confiables**: Corfima si la informacion fue recibida, utiliza acuse de recibo, reenvia informacion de ser necesario e informa al nivel superior si no se pudo enviar
- **No confiable**: No puede asegurar si el destinatario recibio o no la informacion enviable

### Capa de enlaces

Tiene varios objetivos:

- Vincular hosts contiguos
- Controlar el acceso al medio fisico
- Detectar  y/o correjir errores basicos
- Encapsular los paquetes en tramas

Por ejemplo, una topologia tipo **bus**.
Para evitar colisiones, surgieron dos aparatos:

- **Hub**: Menos inteligente, trabaja en la capa fisica
- **Switch**: Mas inteligente, trabaja en la capa de enlace. Asocia las MAC de las vocas(va aprendiendo).

### Capa de ruteo

Hay que distribuir las IP alrededor del mundo, entonces, se dividio en zonas para que cada una realiza la ditribucion. No se pueden conectar dos host que 
usan enlaces diferentes, para ello se necesita un router. El router de nuestras casas tiene 2 **interfaces**, pues hay una interfaz por red(WAN y LAN). 
El router domiciliario es mucho mas que un simple router, pues internamente tiene un switch para el LAN que luego va a una de las interfaces. Sin embargo, 
a nivel capa de IP, todo esto es una misma red con la excepcion de WAN(red externa). Se podria decir que el switch tambien tiene 2 interfaces mas pues tiene 2.4GHz y 
5.8GHz, entonces... tiene 6 interfaces el router? **No**, pues en un switch se llaman vocas(capa enlace), las interfaces son de la capa IP. 

En conclusion, vamos a considerar que un router tiene dos interfacez, todo esto a nivel logico y de seguridad.

### Capa de transporte: Puestos

El numero de puerto identifica a los procesos dentro de hosts. 

### Capa de aplicacion: soporte a aplicaciones

Aca aparecen los servicios mas conocidos: HTTP, POP3, SMTP, DNS, SSH, etc.

> Comando que vamos a usar en todas las practicas: `tail -f /var/log/syslog` (-f es de follow)

## Serializacion

- Se puede transmitir numeros entre computadoras? Si y no, dependera como codifica el 
numero(little endian o big endian) una computadora y como lo decodifica la otra. 
Para solucionar esto, en los protocolos de comunicacion se transmite todo en big endian.
La mayoria de las computadoras hoy en dia usan little endian.
- Cuando tenemos que calcular la longitud de un string codificado en unicode, y tiene 
caracteres especiales, surgen problemas pues los caracteres podrian ocupas mas de un 
byte.


