# Clase 2: Frontend

No van a pedir la UI mas moderna posible, sino algo que sea simplemente facil e 
intuitivo de usar. Aplicando conceptos de HCI como los modelos mentales.

Algo a evitar es agregar cosas al UI que todavia no son funcionales, como 
un boton de login que no hace nada en la esquina de la pagina, no sirve(no ponerlo).
Hay que agregar una funcionalidad a la UI una vez este implementada.

Links para empezar a investigar:

- **Sitio**: paw.juarce.com
- **API**: api.juarce.com

## CSS

Vamos a ver lo ultimo en practicas de la industria.

### Centrar contenido

`text-align` funciona, pero es solo horizontal. Se podria usar `display: flex` o 
directamente codeando los pixeles de offset con `position: absolute`, 
mas naive(overridea cualquier otra cosa).

Usar **flex** es la mejor manera, las otras tres pueden generar problemas(
responsiveness, mantenimiento). 

### UI Kits vs Frameworks

En PAW vamos a poder usar: 

- Bootstrap
- Materialize

No vamos a poder usar:

- Vue
- Angular

Simplemente porque la tecnologia no es compatible con nuestro modelo de 
arquitectura.

Los UI Kits nos dan UI elements, los frameworks nos van a agregar cosas por encima 
de todo lo que ya teniamos, pero nos van a hacer mas mal que bien. 
Recomiendan elejir en base a que tanto nos gusta visualmente, o simplemente buscar 
componentes individuales e incorporarlos en el codigo.

## Frontend - Spring

Vamos a utilizar JSP(Java Service Pages), un pseudo-codigo de Java para evitar 
que las paginas sean simplemente listas, botones, etc. Nos permite crear paginas 
mas dinamicas. 

Cuando un request llega al servidor, este va a pasar el JSP a servlets que luego 
se compilan pues el cliente va a estar usando un browser. Aca la logica esta del 
lado del servidor(como se hacia antes), hoy en dia esta todo dado vuelta.

> Cuidado con caracteres raros, prueben todo. Aprovechen las primitivas. 

JSP tiene un **Expression Language** que simplifica la forma de acceder a los beans.
Por ejemplo:

``` jsc
<h3>Hola ${user.name}</h3>
```

## JSTL

JSP Standard Tag Library, define un conjunto de tags para resolver operaciones comunes.
Evita que un archivo JSP contenga codigo Java.


### TAGs - Core - c

Que pasa si se usa `c:out` en vez de solo `${expression}`? Basicamente, uno permite 
injeccion de codigo malisioso, por ejemplo, en vez de su nombre el usuario pone 
una funcion. `c:out` escapa todo esto y hace que nuestro sitio sea mas seguro.

> Es el mas importante y el que van a tener que usar si o si.

### TAGs - Core 

- `if`: Permite tener expresiones condicionales
- `url`: Bastante importante, permite generar una url a alguno de nuestros recursos, 
de manera que funciona desde cualquier lugar. 
Si tenemos localmente `/img/img.PNG` y luego en produccion es `paw/img/img.png`, 
no va a funcionar en produccion. `c:url` soluciona eso, generando la URL.

> Hay muchos mas TAGs que son super interesantes y los podemos investigar.

### TAGS - Custom

JSP permite definir nuestros propios TAGs, pero no es tan intuitivo de hacer. 


