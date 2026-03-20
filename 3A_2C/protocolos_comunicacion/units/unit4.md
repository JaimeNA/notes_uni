# Unidad 4: Correo electronico

Cuando empezo no se considero que iba a ser usado por personas 
individuales, entonces no habia ningun tipo de seguridad.

Antes, en los ambientes Unix un usuario podia mandarle un mensaje a otro de 
forma asincronica. Cada usuario tenia una carpeta donde se le dejaban los mensajes 
, es decir, estaba pensado a los usuarios de un mismo sistema.

Luego, para permitir mandar mails a personas que esten en otros dominios, se creo un 
protocolo. Asi surgio el Simple Mail Transfer Protocol(SMTP) en 1982. Se encarga 
de dejar en la carpeta de mail de cada usuario el mail enviado a dicho domino.

Se usa el registro `MX` de los servidores DNS para resolver los request de mails.
SMTP usa TCP, la tranferencia es directa y los mensajes son en US-ASCII. 
Tiene tres fases:

- Handshaking
- Transferencia de mensajes
- Cierre

## Envio de datos binarios

Si no soporta otra cosa que sea US-ASCII(de 7 bits), como se envian binarios 
por ejemplo? Se codifica antes para que sea un mail valido, luego se decodifica.
Se debe codificar como si fuera texto con `\n` en cada linea u otros. 

Simil a HTTP, devuelve codigos para indicar el estado de la transferencia, por ejemplo, 
`250` es que todo estuvo OK.

> Es un protocolo de texto. Se puede usar netcat para crear una conexion y ver esto.

> Aca si da lo mismo si uso la IP o el host, a diferencia de HTTP.

## Como mandar mail con netcar

Primero vemos cual es el servidor de mail del dominio al que queremos contactar:

```
dig MX <dominio>
```

Luego, creamos una conexion con netcar:

```
netcat <Servidor MX> 25
```

Una vez tengamos una conexion asegurada:

```
EHLO
MAIL FROM <mail>
RCPT TO <mail>
DATA
SUBJECT <subject>
FROM <mail>
\n
<Contenido del mail>
.
EXIT
```

Importante terminar con un `.`. Es interesante que puedo poner cualquier mail y 
puedo usar el mail de otro como el que envia. Sin embargo, estos mails suelen 
terminar en spam pues les falta un monton de informacion.

Si lo pongo al mail del itba me lo va a rechazar porque el servidor de google(que es 
el que usa el itba) hace una verificacion extra, esta es validar la IP de origen.

> Las cadenas de mails se logran mediante un id que tiene cada mail.

Los `MX` reciben mail, pero hay otros registros que guardan los servidores de mails 
autorizados. Esto es lo que hacen la mayoria de los servidores para verificar los mails.

Si es algo que no se puede enviar(`TO` invalido), el campo `FROM` va vacio para evitar 
un ciclo infinito, que lo que ocurriria si este es invalido.

## Protocolos de entraga final al usuario

- POP3
- IMAP

IMAP evita perder todos los mails pues se guardan en el servidor siempre, caso 
contrario si yo pierdo o se rompe mi disco duro, voy a perder todos los mails.
Hoy en dia practicamente no se usa IMAP, es porque hoy en dia generalmente se 
usa una aplicacion web para ver los mails(y los mails estan en el servidor). 
En todo caso la aplicacion web usaria IMAP. Esta practica se conoce como webmail.

![Webmail](graphics/webmail.png)

## Encoding: Base64

Es un metodo de codificacion simple:

- Cada grupo de 3 bytes es codificado como 4 bytes, cada uno conteniendo solo 6 bits 
de datos.
- Estos son enviados como ASCII de 7 bits.

## SPAM

Surge de un sketch de Monty Python.

Como evitarlo: 

- Allow list y deny list
- Grey listing(porque antes eran white y black list)

Se clasifican con **filtros bayesianos**. Gray list es que es dudoso, 
no le acepta ni rechaza la conexion, sino que dice que intente mas tarde.
Entonces, si es de SPAM y sigue volviendo a mandar mails entonces para a la 
lista negra. Una persona haria lo logico de volver a intentar mas tarde.

## Tecnicas para autenticar mails

- SPF(Sender Policy Framework)
- DKIM(DomainKey Identified Mail)

### SPF

El servidor DNS de un dominio tiene registros para indicar cuales son los servidores 
autorizados para mandar mails de ese dominio.

### DKIM

El servidor que envia el mail verifica que el usuario realmente es el de su dominio, 
se le agrega un sello para garantizar que no fue alterado.



