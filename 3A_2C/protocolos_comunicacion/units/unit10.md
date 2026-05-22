# Unidad 10: SSH

### VPN 

Un tunel es una encapsulacion de un protocolo de la misma o menor capa, HTTP por IP no 
es un tunel. Un tunel es cuando protocolo lleva un paquete de igual o menos nivel.

Una de las razones para usar VPN es para administrar algun host desde otra red, no 
es la unica manera, pero es la mas segura. La gateway de la red del VPN sera 
0.0.0.0 pues esta en mi misma red. Va todo encriptado, de manera que nadie mas 
que este en el medio puede ver lo que se esta mandando salvo la IP destino y de 
origen. Si quiero que todo mi trafico vaya por el VPN hay que agregar otra entrada 
con el gateway de la otra red, aca entran en juego las prioridades de la tabla de 
ruteo.

| net                   | mask                  | gateway             | link  | prio |
| --------------------- | --------------------- | ------------------- | ----- | ---- |
| 192.168.1.3           | 255.255.255.255       | 0.0.0.0             | lo    |      | 
| 0.0.0.0               | 0.0.0.0               | 192.168.0.1         | eth0  | 20   |
| 192.168.1.0           | 255.255.255.0         | 0.0.0.0             | eth0  |      |
| 127.0.0.0             | 255.0.0.0             | 0.0.0.0             | eth0  |      | 
| 0.0.0.0               | 0.0.0.0               | 10.0.0.1            | vpn0  | 10   |
| 10.0.0.2              | 255.255.255.255       | 0.0.0.0             | lo    |      |
| 10.0.0.0              | 255.255.0.0           | 0.0.0.0             | vpn0  |      |

--- 

El protocolo SSH ofrece:

- Autenticacion
- Encriptacion
- Integridad(detecta si el mensaje fue alterado)

Cuando te conectas por primera vez te pide verificar si confias en el certificado del 
servidor porque no te puede garantizar que sea el servidor que buscas. No ocurre 
con HTTPS pues se usan certificados firmados que garantizan su autenticidad. 

`LoginGraceTime` indica cuanto tiempo espera a que el usuario se autentique antes de 
cortar la conexion. `StrictMode` se fija que el usuario no haya publicado su 
configuracion, se puede no permitir. 

SSH es un tunel pues puede llevar datos de otra aplicacion, aprovechando la 
conexion segura. Esto se conoce como **tunneling**. Se podria usar de la siguiente 
manera:

```
ssh -L8080:webserver:80 SSHserver
```

## Local port forwarding

Se puede habilitar para que no solo escuche a localhost, de manera que otras 
personas se pueden hacer pedidos de otros servicios y estos van a pasar por el 
servidor SSH.

## Remote port forwarding 

Misma idea, pero ahora el tunel pasivo esta en el lado del servidor SSH. Se usa 
cuando la aplicacion esta del lado del cliente. Sin embargo, esto no esta bueno a 
nivel seguridad, pues cualquiera se va a poder conectar a la aplicacion, solo 
se debe usar para casos extremos. 

## Dynamic Port Forwarding 

Solo hay que especificar el servidor SSH, solo funciona con un proxy especial. 
Tiene la ventaja que todos los servicios se van por el tunel y no solo uno 
especificado: 

```
ssh -D 9090 pampero.itba.edu.ar
```

Donde `9090` es el servidor proxy. Por ejemplo, abrir Google Chrome desde el proxy:

```
google-chrome --proxy-server='sock5://localhost:9090'
```

> Como AWS tiene un rango de IPs conocidas, hay sitios que detectan el tunel

Tiene sentido usar `PasswordAuthentication no` cuando se va a usar solo para tuneles, 
asi no hay que ingresar con password.

> Si no tengo usar una IP fija puedo usar pampero y conectarme por ssh. De esa manera 
puedo conectarme a AWS desde pampero y luego a desde mi casa a pampero.

## Bastions

Donde hay un hosts con acceso a internet y a una red interna, si pasa el trafico por 
ahi las claves no deben estar ahi por cuestiones de seguridad. Entonces, 
como saben los hosts en la red interna que el bastion tiene las claves 
privadas de el que se conecta desde afuera? Se usa algo llamado `ssh-agent`, el 
cual crea un tunel que se usa en el bastion, de manera que se pueda completar el 
challenge sin que la clave privada del host exterior salga de el.

Para crear esto:
```
eval $ (ssh-agent)
```

## Notas practicas

- No hay manera de protegerse de un man-in-the-middle en la primer conexion, 
tenemos que confiar que el que esta del otro lado es quien dice ser.
- `TCPKeepAlive`, desde el lado del cliente, permite mantener la conexion 
con vida.
- Root login debe estar con `no`, `prohibit-password` es poco seguro.
- La principal diferencia con VPN es la capa en la que actua, por ejemplo, 
SSH es a nivel de aplicacio:wqn y VPN a nivel de red.
- Nmap escanea lento sin sudo porque solo puede usar `connect` y tiene que esperar 
a que se termine el handshake. `-sS` es mas rapido.
- `--top-ports` indica la cantidad maxima de puertos que va a escanear por host.
- `-O` te deja ver el port(sigo hablando de nmap).

