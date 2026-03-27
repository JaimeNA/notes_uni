# Unidad 5: TLS/SSL

## SSL 

Se quiere encriptar datos desde y hacia el servidor, un Web Server en este caso 
usa SSL. HTTP no ofrecia nada de seguridad o identificacion. 

Se conoce como **Secure Sockets Layer**, va antes de la capa de transporte, es la 
capa que se encargara de encriptacion y seguridad. Se mete en el medio de un protocolo 
no seguro y el transporte. Es agnostico al protocolo que este encriptando, **HTTPS** 
no es un protocolo en si, es simplemente HTTP con esta capa extra.

### Claves asimetricas

No hay una unica clave que encripta y desencripta. Sino que hay una publica y una 
privada, un mensaje codificado con la clave publica solo se puede desencriptar con la 
clave privada. 

Entonces es facil, si quiero comunicar a y b, es cuestion de que a tenga la clave 
publica de b y viceversa.

![SSL](graphics/ssl.png)

La primera vez debe ser la persona actual a la que queremos enviar informacion 
encriptada, pues se transfiere en HTTP simple. Como nos aseguramos que el que 
este del otro lado sea la persona que creemos que es? Se encripta una passphrase con 
la clave publica, si el otro responde con la misma clave encriptada con mi clave 
publica. Si son iguales, entonces esta bien.

En el browser, al usar HTTPS, en vez de preguntarnos cada vez si confiamos en el 
servidor que esta del otro lado, se usan certificados emitidos por fuentes confiables 
que el browser usa para chequear si es valida la clave publica.

> Se llaman **entidades certificantes**

En HTTPS se utiliza SSL solamente en la parte inicial para transmitir una clave 
simetrica y usar esa durante la conexion. Esto se debe a que SSL es bastante 
pesado.

> Se crea una clave simetrica por cada sesion

### Tipos de SSL handshake

- Full handshake
- Session resumption

> Session resumption acelera el proceso

## TLS

**Transport Layer Security** es lo mismo que SSL, pero inicia la conexion segura 
de otra manera. Comienza con un `hello` inseguro y luego cambia a una conexion 
segura. No tiene la necesidad de tener un puerto especifico, se crea la conexion 
segura en el mismo puerto, no hace falta.

> En SSL hay un comando redirect para pasar a otro puerto donde se realizara la 
conexion segura.

## DNS over TLS(DoT)

Usa TCP y se usa para crear una conexion segura con DNS, esto evita tener 
una persona en el medio y ataques similares.

## DNS over HTTPS(DoH)

Es otra manera de tener una conexion mas segura a un servidor DNS, es menos 
eficiente.

## HTTPs y proxy HTTP

Los proxies HTTP se utilizan para cachar contenido, filtrar acceso y mejorar 
rendimiento. Cuando el trafico es HTTP, el proxy puede inspeccionar, modificar y 
almacenar las respuestas. 

Con HTTPS el trafico esta cifrado entre el cliente y el servidor, lo que impide 
que un proxy vea el contenido directamente. Entonces, como logran los proxies 
bloquear sitios o cachear contenido cifradp? Con MITM.

## Intercepcion HTTPS con MITM

Se conoce como **Man-in-the-middle**, es la tecnica que usan los proxies para 
inspeccionar HTTPS. Se coloca entre vos y el servidor, el proxy tendra una conexion 
HTTPS con vos y otra con el servidor, pasando los paquetes entre los dos y al 
mismo tiempo tener acceso a eso.

### Deteccion

- Verificar el certificado SSL en el navegador.
- Si el emisor no es una entidad oficial podria ser un MITM
- Se pueden usar herramientas como wireshark para detectarlo

### Limitaciones

- No funciona si el cliente no confia en el certificado del proxy
- Sitios con HSTS(HTTP Strict Transport Security) pueden rechazar la conexion 
con certificados no conffiables
- Algunas aplicaciones y navegadores alertan sobre certificados sospechosos

> Let's encrypt usa una query DNS para saber que dominios son validos, los dominios 
los saca de nuestra configuracion de nginx.
