 # Unidad 3: Resolucion de nombres

Lo que hace HTTP, primero se crea una conexion mediante TCP, luego se pueden 
mandar los request. Esta conexion se hace a partir de la IP, entonces 
como se obtienen las IP a partir del domain? Hay dos formas, una usa DNS.

## Resolucion de nombres - DNS

Proceso de traducir un nombre de dominio a una direccion IP. 

Un **dominio** es una coleccion de computadores que pueden ser accedidas usando un nombre 
en comun. Un **nombre de dominio** 
hace referencia al nombre de multiples hosts que son referenciados colectivamente

> Las computadoras de un mismo dominio no tienen que estar en el mismo lugar. 
No tienen nada que ver.

No todos pueden encargarse de la resolucion de nombre, pero si se pueden tener 
subdominios. Por ejemplo, solo una organizacion da los .com, pero despues yo puedo 
tener mis propios subdominios como subdominio.ejemplo.com.

No siempre se tiene que acceder a un DNS, podrian estar localmente en `/etc/hosts`, 
si se encuentra, se usa eso. Ahi se encuentra `localhost`, pues no conviene que 
tenga una regla especial, es mejor que se comporte como todos los demas. 
Tambien sirve si queremos bloquear alguna pagina:

```
127.0.0.1 chatgpt.com
```

> En examen pueden haber preguntas de que codigos se devuelve en cierta situacion, 
por ejemplo, si mapeo mi pagina web a clarin.com en mi computadora, que pasa 
cuando el servidor ve que el host del request no es el suyo? Devuelve algun 4XX.

En caso de FQDN, se optiene su numero de IP del DNS o de `/etc/resolv.conf`. Se 
pueden especificar: 

- `nameserver <direcciones>`
- `domain <nombre>`
- `search <dominios>`
- `sortlist <red\[mascara\]>`
- `option <opcion>`

Los `nameserver` tienen que ya estar especificados por su IP, son los servidores 
DNS. Cuanto duran las resoluciones en la cache? Depende del **timeout** que te 
devuelva el servidor DNS junto a la IP.

Linux tiene las utilidades `dig` y `host` para mostrar como la computadora, a 
partir de un nombre de dominio, encontro la IP(reverse DNS) o al revez.

Si un dia alguna pagina web cambia de IP(cambio de proveedor de internet, cambio 
de locacion, etc). Entonces, si me quiero conectar, mi computadora va a intentar 
de acceder a la vieja IP, algo similar va a pasar en el cache de todos los DNS. 
Entonces, va a haber que notificar a `a.riu.edu.ar` pues es el root server que 
maneja `edu.ar`, no hace falta notificar al de `ar`.

## Como se consiguen los nombres

Es un sistema jerarquico y distribucion para la resolucion de nombres de 
dominio en direcciones IP(y viceversa). Se lee al revez, empieza la jerarquia mas alta 
por la derecha. Es **jerarquico y distribuido** porque delega a los de abajo.

> El nombre **completo** en realidad es un `.`, es el top-level domain.

Entonces, hay distintos tipos de servidores DNS:

- **Recursivos**: Realizan la consulta completa en nombre del cliente
- **Autorizados**: Contiene la informacion oficial
- **Raiz DNS**: Dominio padre

> La distribucion puede ser vista como un arbol invertido donde la raiz 
hace referencia a servidores de nombre raiz(root name servers).

Los **root name servers** se usan cuando el servidor de nombres local 
no pueden resolver un nombre. El root name server luego:

1. Contacta a un name server autorizado si no conoce la respuesta
2. Almacena
3. Retorna la respuesta al servidor de nombres local

Hay 13 de estos en total.

> Generalmente el **DNS server local** es el router.

> Las preguntas y las respuestas de los servidores DNS son UDP.

## Cache

Una vez que el servidor de nombres aprende el mapeo, lo almacena en cache.

--

Los servidores raiz DNS son operadoes por IETF. Cuando alguien obtiene un nombre 
de dominio es el responsable de mantener, en un servidor DNS primary(antes master), 
actualizado ese dominio(Completa la idea de lo que ocurre cuando cambio mi IP).

En Unix y Linux DNS es implementado generalmente con **BIND**(Berkeley Internet Name 
Domain). 

## Configuracion de servidor DNS

Hay cuatro niveles de configuracion:

- Resolver only systems
- Caching-Only
- Primary(Antes master)
- Secondary(Antes slave)

## Registros DNS

DNS es una base de datos distribuida de **resource records**(RR). Los registros son:

- `A`
- `NS`
- `CNAME`
- `MX`
- `AAAA`
- `SOA`
- `SRV`
- `RP`
- `TXT`
- `SPF`

Los registros asocian el request a distintas cosas, por ejemplo, `A` devuelve la IP.

> SRV es tipo MX, pero mas generico

### Formato RR

```
(name, value, tyype, ttl)
```

> El `@` surgio para separar el nombre del dominio del nombre del usuario, sino 
se tornaba confuso.

El tiempo de vida minimo para respuestas negativas se refiere a cuando el servidor 
tiene que ir a preguntar a otros por un nombre de dominio. Si no lo encuentra, 
durante las proximas, por ejemplo, 3 horas no volvera intentar a preguntar por el 
nombre a otros(va a devolver que no lo encontro).

## Split-horizon

Un nombre se resuelve con distintas IP dependiendo del origin de la consulta. 
Por ejemplo, desde el ITBA el servidor de pampero es 10.16.1.103, mientras 
que desde afuera es 200.5.121.139.

No es si la pregunta se hace desde adentro o desde afuera, sino que donde esta 
el servidor DNS, si esta afuera me va a dar la IP de afuera por mas que yo este 
adentro del ITBA.

## DNS dinamico

DDNS permite actualizar en forma dinamica un servidor DNS. Introducido en BIND a 
partir de la version 9. Si cambia la IP, el servidor se va a actualizar 
automaticamente.

## DNS spoofing

Es el arte de lograr que un registro DNS apunte a un IP que no sea el que deberia 
apuntar. De manera que algun actor malisioso podria crear una copia de la 
pagina del ITBA y hacer DNS spoofing para que el nombre de dominio del ITBA apunte 
a su pagina. Esta seria una manera de realizar un ataque de phishing. Por eso es 
importante preguntar para corroborar la resolucion DNS.

> Es comun que muchas empresas sigan usando versiones viejas de software como BIND.

### Metodo: Enviar respuesta sin recibir pregunta

Se envia como falsa respuesta el IP de algun nombre de dominio.
Se debe verificar que la respuesta corresponda con alguna pregunta. DNS establece que 
los mensajes de pregunta y respuesta tengan un mismo ID que los identifique.

### Metodo: DNS sniffer

Person in the middle(no man in the middle), se obtiene el ID para poder responder con 
una IP falsa en nombre del servidor DNS.

### Metodo: DNS cache poisoning

Inyecta otras resoluciones a una simple pregunta para ensuciar el cache del servidor. 
Pisa las resoluciones anteriores.


