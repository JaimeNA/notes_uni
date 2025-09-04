# Unidad 2: Introduccion desarrollo interfaces web

## HTML

### Como accedemos a la web

#### Navegadores

- Las personas acceden a los sitios web
a través de navegadores como
Chrome, Firefox, Edge, Safari y Opera.
- Para visualizar una página pueden:
    - Ingresar una dirección web
    - Hacer clic en un enlace
    - Usar un favorito guardado.
    - Los desarrolladores de navegadores lanzan nuevas versiones con mejoras y funciones actualizadas en los lenguajes web.
- Muchos usuarios no estarán utilizando
la última versión de los navegadores, por
lo tanto, no nos podemos confiar en que
los visitantes de nuestro sitio podrán
utilizar las últimas funcionalidades
ofrecidas por los mismos.

#### Servidores web

- Cuando se le solicita a un navegador una
página Web, esta petición es enviada por
Internet a una computadora especial
conocida como Web Server la cual hospeda
la misma.
- Los servidores Web son computadoras
especiales que se encuentran
constantemente conectadas a Internet y se
encuentran optimizados para enviar páginas
Web a aquellos que se las soliciten.
- Algunas grandes compañías tienen sus
propios servidores Web, aunque es más
común el uso de los servicios de hospedaje
Web de una compañía que cobra por dicho
servicio.

#### Dispositivos

- Accedemos a sitios Web desde una amplia
gama de dispositivos incluyendo
computadoras de escritorio, laptops,
tabletas y teléfonos móviles.
- Es importante tener en cuenta que los
diferentes dispositivos tienen diferentes
tamaños de pantalla e incluso algunos
conexiones más rápidas que otros.

#### Screen readers

- Son programas que leen al usuario el
contenido de la pantalla de una
computadora.
- Son utilizados mayoritariamente por
personas con algún tipo de discapacidad
visual.
- Varios países tienen legislaciones que
establecen que los sitios Web deben ser
accesibles para personas con discapacidades.

### Como se crean

#### Lo que vemos

- Cuando accedemos a un sitio Web,
el navegador recibe HTML y CSS
(entre otras cosas) del servidor Web
que hospeda el sitio Web.
- El navegador interpreta el HTML y
CSS para crear la página que vemos.
- Muchas páginas también incluyen
contenido extra como imágenes,
audio, video o animaciones.
- Algunos sitios Web también envían
JavaScript al navegador.

#### Lo que no vemos

- Los sitios Web pequeños se acostumbran a
escribir en HTML y CSS.
- Sitios Web más grandes (particularmente
los que son actualizados regularmente y
usan un sistema de gestión de contenidos,
herramienta de blog o software de e-
commerce) hacen uso de tecnologías más
complejas en el servidor Web, pero estas
tecnologías son utilizadas para producir
HTML y CSS y enviarlo al navegador.
- Sitios Web complejos pueden usar múltiples
bases de datos, lenguajes de programación
como PHP, ASP.Net, Java o Ruby en el
servidor Web, pero al igual que sucede con
los otros sitios estas tecnologías también son
utilizadas para producir HTML y CSS.

### Estandares 

- Desde los orígenes de la Web existieron
diferentes versiones de HTML (cada una de
ellas intentando ser una versión mejor que la
anterior).
- Debido a que HTML 5 se construyó a partir
de versiones previas, aprender esta versión
simplifica el entendimiento de las versiones
previas.
- El World Wide Web Consortium (W3C) es el
encargado de mantener y estandarizar el
lenguaje HTML.
- Desde hace algunos años, el Web Hypertext
Application Technology Working Group
(WHATWG) colabora con el W3C para que el
lenguaje HTML sea un estándar vivo (en
continua actualización mientras se reciban
propuestas de la comunidad)

### Como funciona la web 

- Cuando visitamos un sitio Web, el Web Server que hospeda la
página puede estar en cualquier parte del mundo.
- Para poder encontrar la ubicación del Web Server, el navegador
se conecta primero a un servidor de DNS (Domain Name
System).
- Estos actúan como guías telefónicas, y asocian una dirección IP
al nombre de un dominio.
- Cada dispositivo conectado a Internet tiene una dirección IP
única (como el número de teléfono).
- Tradicionalmente estos números eran de 12 dígitos separados
por guiones o puntos, pero hace unos años fueron actualizados a
32 caracteres.

Ver apuntes clase 7.

## Tecnologias de la web

- **HTML** define la estructura.
- **CSS** controla la presentacio.
- **JavaScript** crea el comportamiento.

Tener una estructura presenta los siguientes beneficios:

- **Accesibilidad**: Utilizar etiquetas semánticas de HTML, permite a
aquellos usuarios que utilizan navegadores no visuales (por ejemplo,
screen readers) tener una mejor experiencia de la Web.
- **Portabilidad**: Separar y organizar tanto el CSS como el código
JavaScript, permite hacer uso de los mismos en otros proyectos.
- **Mantenibilidad**: Separar los componentes de una interfaz de usuario
Web, permite a un diseñador hacer cambios al CSS sin afectar el
contenido escrito en HTML o el código JavaScript.
- **Reducción de la latencia**: Organizar  el CSS y el código JavaScript en
archivos separados y referenciarlos en cada página del sitio Web,
permite descargarlos solo la primera vez y usar la versión almacenada
en el cache del navegador en las subsiguientes.
- **Progessive enhancement**: No confundir con **gracefull degradation**(como cambia entre navegadores), 
se arma en capas solidas, entonces todos los usuarios tengan una buena experiencia ya que todas las capas se armaron
con los distintos navegadores en mente.

## Origen de la incompatibilidad en la web

Causas:

- **Estándares**: no existían o llegaban tarde por no lograr un consenso a
tiempo.
- **Navegadores**: no respetaban los estándares.
- **Desarrolladores**: desconocen o no respetan los estándares.

Consecuencias:

- Páginas que solo funcionan en ciertos navegadores.
- Páginas de código complejo de mantener o diferentes versiones de
una misma página.
- Uso ineficiente del ancho de banda al tener que descargar diferentes
versiones de la misma página o componentes.

W3C trae un estandar para hacerle frente a los navegadores. Un estandar tiene distintos niveles de maduracion.
Pero W3C era muy lento, entonces surgio WHATWG para agilizar al proceso, el cual le pidio a W3C que les cedan el trabajo con HTML.

JavaSeript no esta controlado por W3C pues no se usa unicamente para la web, entonces ECMA se encarga de generar los estandares. 
C# tambien esta estandarizado por ECMA. 

### DOCTYPES

Debido a la existencia de multiples versiones HTML, cada pagina debe indicar al navegador su version de HTML. 
Su uso tambien ayuda al navegador a representar correctamente a la pagina.

## Herramientas para el desarrollo web

- W3C HTML Validation Service ayuda a validar HTMLS. 
- WAVE testea usabilidad.
- Devtools, estan en todos los navegadores con alguna que otra diferencia.
- Vue y Vuetify
- Vue.js, obligatorio
- Vue router
- Pinia, permite manejar el estado de una pagina(que se guarde la sesion, formulario, etc.)

## CSS

Cascading Style Sheets.

- Permite controla el diseño de las páginas Web para que sean más
atractivas
- Permite crear reglas que especifican como debe mostrarse el
contenido de un elemento
- Por ejemplo, se puede especificar que el fondo de la página debe
mostrarse de color crema, los párrafos deben mostrarse de color gris
utilizando la tipografía Arial o que todos los encabezados de primer
nivel deben mostrarse en color azul, en itálica y con la tipografía Times
- Luego de comprender la forma en la que se escribe una regla CSS,
aprender CSS mayoritariamente implica aprender las diferentes
propiedades que se encuentran disponibles

(Ver apuntes para cosas mas especificas como `<link>`)

Para cambiar especificidad, se puede usar `!important`, aunque se recomiendo no usarla(solo en casos muy especiales).

### Color

- RGB
- HSL/HSLA
- 


