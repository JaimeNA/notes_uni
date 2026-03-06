# Unidad 2: HTTP

Antes, para obtener archivos se utilizaba un protocolo llamado **FTP**. En base a las referencias se debia acceder a otro sitio FTP y 
descargar el/los textos deseados. Surge la necesidad de aplicaciones basadas en **hipertextos**, para no tener que hacer todo manualmente, 
de ahi surge en HTTP.

## HTTP 0.9(1991)

La primer version estaba basado en texto de recuperacion de informacion con un mecanismo request-response. Era contenido estatico 
y no mantiene el estado. Orientado a la **linea de comando**. Mas adelante se dieron cuenta que tenia potencial comercial, para 
que sirva para algo mas que papers cientificos.

> TCP es de texto o binario? Es binario, mientras que HTTP es de texto.

## HTTP 1.0(1996)

Ahora necesita ser **browser friendly**, entonces se agregan comandos como `GET`, `POST`, `HEAD`. Ademas, puede transmitir otras cosas 
que no sean hipertexto.

## HTTP 1.1(1999)

Aparecen cabeceras en las peticiones, se agregaron los metodos `PUT`, `DELETE`, `TRACE`, `OPTIONS`. Negociacion de contenido, soporte de cache, 
conexiones persistentes. Ahora cada recurso se identifica con una **URI**(Uniform Resource Identifier)

## HTTP/2

Pasa a ser un protocolo binario, tiene compresion de headers, multiplexacion de recursos, server push, etc. Es mucho mas rapido 
que HTTP 1.1.

> Comparacion: http://www.http2demo.io/

Porque alguien usaria HTTP 1.1? Algun cliente que no soporte HTTP/2 o porque no hay soporte para la tecnologia que tenemos que usar.

## HTTP/3

Usa el protocolo **QUIC**(por ahi no lleguemos a verlo) como **transporte**, tiene menor latencia, multiplexacion mejorada, etc.
QUIC es un protocolo de transporte pensado para usar on HTTP pues pide muchos recursos. Desde el punto de vista de la funcionalidad, 
es de transporte, pero se podria considerar de aplicacion. 

> HTTP/3 no usa TCP, usa QUIC

## Recursos

Bloque de informacion identificado por su URI, puede ser un archivo(fisico) o generado por un programa(abstracto). La URI puede ser 
un URL o un URN. Con URN el archivo puede cambiar de lugar y el servidor se va a encargar de buscarlo, mientras que con URL se pierde.

Que diferencia hay entre una URL y una URI? URI es la clase abstracta para identificar recursos de forma univoca, mientras que URL y URN son 
clases concreta.

> Los protocolos son publicos y se identifican con RFC(1630, 2396, 2718, 3305, 3986).

## URI(RFC 3986 3.3)

```
<scheme>://<authority><path>?<query>
```

El path termina con el primer `?` o `#` o si no hay mas caracteros. Puede ser relativo o absolutos. 
Si representa una aplicacion puede recibir parametros. El schema identifica el protocolo y la mayoria de los browser 
lo agrega por defecto. Pueden ser usadas para acceder recursos, ya sea por medio de URL o URN.

### URL

```
<scheme>://<>user>:<password>@<host>:<port>/<path>?<query>
```

Identifica un recurso por su ubicacion.

### URN

Identifica un recurso por su nombre, no implica que el recurso exista o como acceder a el.

Por ejemplo:

```
urn:isbn:0132856204
```

---

> Escribimos `www.clarin.com` y el browser se encarga de detectar que es HTTP. Luego el browser averigua la IP(dsp lo vemos) y se conecta mediante 
TCP al servidor.

En caso(por ejemplo) de AWS, no es factible asignarle una IP publica a cada cliente, lo que se hace es tener todas en el mismo servidor(misma IP publica) y 
se diferencias las paginas por la URI, entonces, a veces puede no funcionar solicitar una pagina web escribiendo directamente la IP.

Por otro lado, los headers en un request estan delimitados por un espacio(`\n\n`), esto aprovecha que es texto, sino no se podria hacer. 
Mientras que para saber cuando termina el cuerpo se indica en el header con `content-length`. 

> Esto funciona perfecto si es estatico, pero surgen problemas si es dinamico.

## Request menos utilizados

- `PUT`
- `TRACE`
- `OPTION`
- `DELETE`

## Respuestas

- `1XX`: Informacion
- `2XX`: Exito
- `3XX`: Redireccion
- `4XX`: Error en cliente
- `5XX`: Error en servidor

## Tipos de informacion

HTTP solo transmite texto, utiliza **MIME** para describir contenido multimedia, cada objeto es etiquetado por el web server.

- `text/html`
- `text/plain`
- `video/mp4`
- `image/jpeg`
- `application/pdf`
- etc.

## `GET` vs `POST`

La idea es que el `GET` sea idempotente. Puede ser cacheado, el browser los mantiene en el historial, pueden ser **bookmarked**, 
tienen logitud acotada y solo son para recibir datos.

Mientras que el `POST` nunca es cacheado, no se mantienen en el historial del browser, no pueden ser bookmarked, no tienen 
longitud de datos maxima.

## Headers HTTP

Como se menciono anteriormente, finaliza conuna linea en blanco.

```
<nombre>: <valor asociado>
```

### Tipos

- Generales
- De solicitudes
- De respuestas
- De contenido

> Generalmente se envian en ese orden.

## Headers generales

- **Cache-control**: Directivas para cache
- **Connection**: Se definen opciones de conexión
- **Date**: Fecha de creación del mensaje
- **Transfer-Encoding**: Indica el encoding de transferencia 
- **Via**: Muestra la lista de intermediarios por los que pasó el mensaje.

## Headers de solicitud

- **Accept**: Tipo de contenido aceptado por el cliente
- **Accept-Charset**: Charset (ISO-xxxx, UTF-8) aceptado por el cliente  
- **Accept-Encoding**: Encoding (gzip, compress) aceptado por el cliente
- **Expect**: Comportamiento esperado del server frente al request. 
- **From**: Email del usuario de la aplicación que generó el request.
- **Host**: Servidor y puerto destino del request.
- **If-Modified-Since**: Condiciona al request a la fecha indicada. 
- **Referer**: URL del documento que generó el request 
- **User-Agent**: Aplicación que generó el request 
- **Upgrade**: solicita que use otro protocolo
- **Range**: solicita un rango (en bytes) del recurso 

> Referer esta mal escrito(va con *rr*, fue un error y quedo asi :/.

## Headers de respuesta

- **Age**: Estimación en segundos del tiempo que fue generada la respuesta en el server. 
- **Connection**: close, keep-alive
- **Location**: URI a redireccionar
- **Retry-After**: Tiempo de delay para reintento
- **Server**: Descripción del software del server. 
- **Authorization**: indica que el recurso necesita autorización

## Headers de contenido

- **Allow**: Métodos aplicables al recurso. 
- **Content-Encoding** 
- **Content-Length**
- **Content-Location** 
- **Content-MD5** 
- **Content-Type** 
- **Expires**: Fecha de expiración del recurso. 
- **Last-Modified**: Fecha de modificación del recurso 

## Proxy servers

**Foward proxy**, es algo que esta en el medio, pueded ser para molestar tambien. Actua como intermediario entre una plicacion cliente y un web server. Puede ser: 

- **Explicito**: Lo configuramos nosotros, sabemos de su existencia.
- **Transparente**: A traves de un firewall, no nos damos cuenta que esta.

![Proxy server](graphics/proxy.png)

Tiene varias finalidades:

- Controlar el acceso a determinados sitios o paginas
- Almacenar en cache recientes consultas, haciendo que sean mas rapida pues usamos la red local

> Sin embargo, el cache puede causar contenido desactualizado, por lo que el que genera el recurso decide cuanto tiempo dura el cache.
Setea el parametro `max-age`.

## Proxy reverso

El proxy directo esta del lado del cliente, mientras que el reverso, **reverse proxy** esta del lado del servidor. 
Redirecciona el trafico internamente a distintos servidores, por ejemplo, **NGINX**.

![Reverse proxy server](graphics/reverse_proxy.png)

> En el cache del browser/proxy esta `last-modified`, `max-age`, etc.

## Cookies

La idea es que cuando pida un recurso, en el header esten las cookies. Entonces, cuando el servidor me manda cookies
(`set-cookie` res header), a ese servidor en particular, cuando pida un recurso le voy a mandar las cookies(`cookie` req header).

Es un texto de informacion enviado por el servidor y almacenado en el servidor, se usa para mantener un estado entre 
el cliente y el servidor. Tiene dos tipos de persistencia:

- Session cookie
- Persistent cookie

Tambien hay **third-party** cookie y **secure** cookie.

