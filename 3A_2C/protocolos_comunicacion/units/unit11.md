# Unidad 11: Socket bloqueantes

En un servidor TCP hay dos tipos de sockets:

- Pasivo
- Activo

En UDP solo hay uno pues no esta la necesidad de mantener un estado. Las aplicaciones de 
red tienen:

- Comunicacion entre procesos(En un mismo host o en diferentes hosts)
- **Proceso cliente**: Inicia la conexion
- **Proceso servidor**: Espera conexiones
- Se debe poder identificar los procesos
- Cada host se identifica por IP
- Muchos procesos corrienddo en un host
- Para identificar procesos se usa el numero de puerto

Si estan en diferentes hosts, hay que identificar ambos host. Para TCP cada uno 
envia y recibe, no hay distincion entre cliente y servidor. El puerto del 
cliente es elegido al azar, mientras que para el servidor se tiene que seguir 
convenciones.

El protocolo a nivel de aplicacion debe definir: 

- Tipos de mensajes
- Sintaxis de los mensajes
- Semantica de los mensajes
- Reaccion frente a errores

> Es mas facil programar un protocolo que es binario pues no hay que parsear como 
se tendria que hacer con texto plano

El estandar TCP/IP sugiere que las API contemplen las siguientes operaciones basicas:

- Reservar recursos locales para la comunicacion
- Especificar endpoints origen y destino
- Iniciar conexion(desde cliente)
- Enviar datagrama(desde cliente)
- Esperar una conexion(desde servidor)
- Enviar y recibir datos
- Poder determinar cuando arriba informacion
- Generar datos urgentes
- Manejar arribo de datos urgentes

Algunas API:

- socket API(Berkeley)
- Windows Sockets
- Transport Layer Interface TLI 

> Nosotros vamos a usar socket API

## Berkeley

Disenado en los 80 para UNIX, utiliza system calls ya existentes cuando sea posible. 
Vamos a tener que especificar que el socket es para TCP/IP y que tipo de servicio 
requerimos(orientado a conexion o no). El socket del proceso debe estar escuchando 
antes de conectarme, no funciona levantar a demanda.

La API de sockets agrega una nueva abstracción para las comunicaciones en red, el 
socket. Al igual que con los archivos, cada socket es identificado por un handle y 
la información relacionada es almacenada en la tabla de file descriptors.

> Como es un stream va a parecer que leo y escribo de un archivo infinito

### Sockets

- El servidor debe estar en ejecución ANTES de que el cliente intente conectarse
- El servidor debe tener un socket (como una «puerta») abierto, por el cual recibir 
y enviar mensajes (segmentos)
- Los clientes necesitan un socket
- Los clientes necesitan conocer IP y número de puerto del socket del servidor

Un mismo proceso puede escuchar en ambos protocolos al mismo tiempo para el mismo 
puerto, como DNS(TCP, UDP).

### Creacion de un socket

```
int socket(int domain, int type, int protocol);
```

El dominio puede ser:

- AF_INET
- AF_INET6
- AF_UNIX
- AF_UNSPEC

El tipo puede usar:

- SOCK_DGRAM(UDP)
- SOCK_RAW(No agrega ningun encabezado, responsabilidad mia)
- SOCK_SEQPACKET
- SOCK_STREAM

El protocolo puede ser:

- IPPROTO_IP
- IPPROTO_IPV6 	
- IPPROTO_ICMP
- IPPROTO_RAW
- IPPROTO_TCP
- IPPROTO_UDP
- IPPROTO_SCTP

Aunque generalmente se deja en cero para que se seleccione el protocolo por defecto 
en base a domain y type.

> Puede fallar por falta de sockets o simplemente por parametros invalidos.

![Ejemplo TCP](graphics/socket_api.png)

En el ejemplo TCP, el `connect` se bloquea hasta que reciba el handshake, el 
servidor no se bloquea porque es mas importante que sea concurrente.

> Si se quiere bloquear ciertas IP desde la aplicacion(sin firewall) hay que hacerlo 
despues del `accept`.

### Socket I/O

La comunicacion en un socket es bidireccional. Se puede deshabilidar I/O con 
shutdown:

```
int shutdown(int sockfd, int how);
```

Posibles valores para how: 

- SHUT_RD
- SHUT_WR
- SHUT_RDWR(El socket sigue, pero no lo voy a usar mas)

El SHUT significa que se manda un FIN.

> Si quiero que el otro no envie datos que no voy a poder leer pongo un window de 0

### Formato de direccioens

Una direccion identifica un **socket endpoint**. El formato es especifico para cada 
familia, y se castean a un formato generico.

### Resolucion de nombres

Un cliente debe especificar la dirección de un servidor utilizando la estructura 
`sockaddr_in`. ¿Qué sucede si el programa cliente conoce sólo el FQDN del servidor?

- `inet_addr`: Convierte de notación con puntos (“200.132.2.15”) a decimal.
- `gethostbyname`: Dado un string que contiene el FQDN de un host retorna una 
  estructura que contiene –entre otras cosas-  el IP del host en forma decimal.
- `getaddrinfo`: Más completa que la anterior.

`getaddrinfo` es la que vamos a usar pues permite especificar numero de servicio y 
algunas cosas mas.

> Se puede usar tanto en el servidor como en el cliente.

> Notar que el socket pasivo no lo cerramos nunca, va a estar vivo mientras lo este 
el servidor.

---

Va a ser facil hacer un denial-of-service, creando el socket desde el cliente 
repetidamente, y la conexion no termina hasta que el cliente lo diga, simple.

## Diseno de servidores

### Algoritmo para servidor iterativo no orientado a conexion

1. Crear un socket y ligarlo (bind) a un puerto
2. Leer un datagrama request de un cliente
3. Enviar datagrama/s como respuesta
4. Volver al punto 2

Un datagrama es un mensaje auto-suficiente

### Algoritmo para servidor iterativo orientado a conexion

1. Crear un socket y ligarlo (bind) a un puerto
2. Aceptar un pedido de conexión a través del socket
3. Obtener un nuevo socket para la conexión
4. Leer un request del cliente
5. Enviar una respuesta
6. Si el cliente no finalizó, volver al punto 4
7. Cerrar el socket creado en punto 3
8. Volver al punto 2

## Debug de procesos

Podemos usar comandos para depurar procesos, por ejemplo:

- `truss` en Solaris y FreeBSD
- `strace` en GNU Linux
- `dtruss` es OSX

> En UDP hay que poner un timeout pues no podemos garantizar una respuesta.

## Servidores concurrentes vs iterativos 

Al concurrente no se le pueden hacer un denial-of-service tan facilmente como al 
iterativo. Un servidor TCP no puede ser iterativo pues la conexion se mantiene, de 
manera que se acumulan recursos mucho mas rapido que con UDP. 

## `select()`

> Muy importante

Todos los ejemplo vistos manejan I/O en un solo canal, un server puede manerjar 
varios canales. Para lo que vamos a usar `fork()` es bastante pesado. `select` es la 
alternativa que vamos a usar, recibe una lista de descriptores y se bloquea hasta que 
alguno este listo para I/O. 

> En la presentacion hay un ejemplo de un libro de como usar bien el `select()`.

> `htons` Host To Network Source, porque generalmente se transmite en big endian y 
nuestras computadoras estan en little endian.

> `sendto` se puede bloquear, pero es muy raro. 

## `pselect()` 

Mejor que `select`.

## `poll` y `epoll`

En vez de tener bits de entrada y salida, `epoll` tiene un file descriptor para 
registrar los sockets/file descriptors que quiero que me atienda. 
Dicen que va a reemplazar `select`.
