# PDV

## 1. Determinar requerimientos funcionales y no funcionales 

### Funcionales 

- Distribucion en sucursales 
- Manejo de promociones, cupones, etc 
- Recibir y enviar depositos 
- Exportar en Excel 
- Etc.

### No funcionales 

- Escalabilidad 
- Un cliente no puede pasar mas de 5 minutos esperando (rapido)
- Tiempo real 
- Disponibilidad 

### Atributos de calidad que aplican 

- Escalabilidad, pues soporta expansion, como los picos el dia de la madre 
- Portabilidad, pues pide uso de tabla 
- Performance, pues tiene que ser rapido 
- Usability, pues debe ser facil de usar 
- Seguridad, pues tiene procesamientos de procesamientos
- Interoperabilidad, pues habla con e-commerce y procesadores de pago 
- Fault tolerance, pues el proceso de pago puede fallar 
- Customizability, configuracion de promos y descuentos 
- Presicion, reportes para toma de decisiones 
- Disponibilidad, pues es un sistema de gestion de ventas y sin ventas no hay plata 
- Mantainability, pues pide poder agregar nuevos medios de pago para expansion 
internacional
- Accessibility, pues necesita soportar diferentes usuarios al expandirse a nuevos 
mercados 

> Nunca hay un atributo de calidad implicito, siempre hay alguna pista

> Nunca desaparece un atributo de calidad, puede importar uno mas que otro 

Ahora, con 12 atributos hay que armar todos los escenarios que verifiquen que el sistema 
cumple con todos. Pero esto tiene un costo exponencial, asi que **solo** para el 
parcial nos vamos a quedar con 4 atributos arbitrarios. En el final si hay que 
analizar **todos**.

Para decidir cuales usar, cuales son mas importantes, es importante no usar los 
explicitos porque es un grave error. El cliente no dice que quiere seguridad, pero 
se quiere siempre eso. 

La forma mas facil es sacar las que nos van, y quedan las 4 mejores, aca sacamos: 

- Portabilidad 
- Usabilidad 
- Precision 
- Accessibility
- Tolerancia a fallos, cuantas apps conoces que no fallan si se cae la primer 
opcion?

> Lo que mas duele al negocio, lo que mas afecta a las ventas o usuarios 

Entonces los que seguros que si son: 

- Disponibilidad
- Interoperabilidad
- Escalabilidad
- Performance, por democracia

Finalmente, hay que ordenarlos:

1. Disponibilidad
2. Escalabilidad
3. Performance 
4. Interoperabilidad 

Hay mucha subjetividad en esto, pero generalmente hay una convergencia. La 
recomendacion es no comparar los atributos que pusieron los demas. 

### Los mas importante, decir cuanto de cada atributos 

- Disponibilidad, 99%, 14/7 - en la practica no siempre es la mas representativa, esmejor 
con volumen de ventas, aunque es mas difil y ni lo planteamos. 
- Escalabilidad, un orden de magnitud x100(cientos de sucursales)
- Performance, < 5 en busqueda
- Interoperabilidad, no hay mucho que dimensionar 

Para la practica, si pedis menos de 99% de disponibilidad entonces ni deberia ser 
prioridad. Aunque sigue siendo no trivial conseguir 99%. 

## 2. Identificar sistemas externos 

- Vendedor, usando tablet. Aplicacion web.
- Sistema e-commerce
- Procesadores de pago 
- Puntos de despacho 

El comprador interactua con vendedor o e-commerce, nunca con nosotros. Va a ser una 
webapp. Diagrama util para hacer una minima representacion de un sistema que 
conecta las partes y cumple con los requerimientos funcionales. 

Pero con todo conectado simple no hay tolerancia a falla, se cae todo por lo mas 
minimo que pase, hay que pensar escenarios para reforzar el modelo. Por ejemplo, 
se corta el internet. 

### Como mejorar disponibilidad 

- Base de datos secundaria(suelen ser dos)
- Se puede cortar conexion a las sucursales, se puede asumir el riesgo o no. Hay 
varias soluciones, pero lo mas importance es darse cuenta. 

Que pasa si se corta el internet? No es valido ir por AWS porque cuentan la 
disponibilidad como el uptime de sus servidores, si no hay conexion a sus 
servidores no es problema suyo. Si se mantiene funcionando offline va a haber una 
colision cuando vuelva la conexion con la base de datos, puede pasar que el 
cliente este dispuesto a solucionar el problema de logistica de haber vendido mas 
stock del que habia por un corte de conexion. 

Al final la solucion mas costo-efectiva es usar un proveedor de internet por medio 
fisico y otro por celular. Pero, si se corta la luz es un riesgo asumido pues 
poner generadores en todos lados no es viable.

Luego, determinamos donde hostear, vamos por AWS. Esto va a solucionar varias 
cuestiones pues lo garantiza el proveedor. 

Otro problema es que se caiga el servidor, simplemente ponemos muchos servidores
(se representan como tarjetas solapadas) y aplicar un load-balancer que apenas falle 
un request saque el servidor que fallo del round-robin(solucion mas uniforme a gran 
escala). El problema del round-robin es que si la aplicacion no es stateless entonces 
todos los request de un mismo usuario deben ir al mismo o persistir las sesiones en una 
base de datos. Tradeoff de poner todas las sesiones en una base de datos es tener la 
base de datos, sino ver de hacer que sea stateless, todo tiene un tradeoff. 

Hay una tercer solucion que es no usar round-robin, donde el load-balancer mantiene 
un mapa en memoria de que servidor tiene cada sesion. La primer contra es que no 
garantiza distribucion uniforme del trafico y la segunda es que el load-balancer debe 
mantener una instancia en memoria y cuando falla se pierden todas las sesiones(tiene 
menos desventajas que no tener nada). En conclusion, lo hacemos stateless. 

- Router 5G 
- Hostear en AWS 
- Stateless

Asumimos el riesgo de que haya que reiniciar el load-balancer pues tarda 30ns en 
reiniciar. Tambien poner SPA(single page application). 

> En el examen no importa el proceso, solo les importa el final y lo del medio lo 
pasan por arriba

### Como mejorar escalabilidad 

Los picos de venta es otro orden de magnitud, un escenario es el dia de la madre con 
x10 usuarios. El trafico ira a el load balancer, que esta hecho para eso, entonces 
el cuello de botella va a ser la conexion o la implementacion de la API. 

Entonces, se limitara mas que nada por los recursos que tenga el servidor. Sin embargo, 
esto ya lo cubrimos anteriormente con el agregado de multiples servidores. La otra 
limitante sera la DB, esto se resuelve dependiendo de el patron de acceso, para este 
caso habra muchas lecturas y algunas escrituras. 

Lo primero que se rompe en carga sera la base de datos, especialmente las lecturas. 
Para asegurarnos que no sea un problema podriamos escalar la base de datos o cache. 
Agregar mas instancias es mas complejo, que, por ejemplo, una instancia de la API, por 
lo que no se podra hacer un escalamiento dinamico(estatico no sirve pues no es 
constante). 

Para cache, podemos usar Redis, guarda en memoria los datos y eso es lo que necesito. 
Hay que ver que valores se guardan y por cuanto tiempo, hay que asegurarse que no 
quede desactualizado. Si dura muy poco se pierde la utilida, si dura mucho se quedan 
valores falsos. No todas las queries van a ser cacheadas. La aplicacion deberia 
invalidar la key del cache en caso de realizar una operacion de escritura. 

Super importante el orden, si primero se invalida y dsp se actualiza se podria llegar 
a una condicion de carrera. La aplicacion se volvera muy compleja debido a que tendra 
que manejar estos escenarios. Decir que la API corre en cluster no tiene sentido si 
ya la defini como stateless.

> Una aplicacion es cluster porque no solo son muchas corriendo en paralelo sino porque 
tambien se pueden tratar como una sola. 

Usando un Redis cluster dara flexibilidad, pero habra que gestionar todo eso. 
Tambien hay que definir que consultas cachear. 

Nuevo componente, ahora hay que volver a analizar los escenarios. No tener 
disponible el Redis no debiera impactar en disponibilidad o algun otro. Pasamos 
al siguiente escenario. 

El proximo escenario es que falle la API, se podria hacer un escalamiento dinamico. 
Para ello se puede usar Kubernetes aunque tiene su complejidad y requerimientos(todo 
debe estar dentro de containers). El costo de Kubernetes debe ser justificado con 
una gran demanda y cantidad de usuarios. No afecta los otros escenarios, pasamos a 
otro escenario.

### Como mejorar performance 

Podemos garantizar busquedas de 5s? No. Queremos mejorar las busquedas, donde la 
busqueda de texto libre es la parte mas compleja, pero por suerte esta resuelto. 
Lucene soluciona este problema, indexando json y haciendo busqueda de texto libre. 
Hay librerias que usan Lucene, las mas populares:

- Solar 
- OpenSearch
- ElasticSearch 

> Todas estas corren en Java. Al final del dia no importa cual pues la 
performance es muy parecida 

Elegimos ElasticSearch, donde va a tener lo minimo indispensable, apenas tenga un 
id se busca en Redis. 

### Como mejorar interoperabilidad 

Quiero que funcione con muchos sistemas, escenario: Se cae un proveedor, pasamos 
al siguiente y sobrevivie. Por ahora solo fueron escenarios en runtime, podemos 
pensar de otra manera. Escenario: Hay un procesador nuevo y hay que integrarlo, 
hay que modificar el sistema para poder conectar el nuevo procesador de pago. 

Esto se debe implementar con una arquitectura que permite la facilidad de 
implementacion, vamos a usar el patron **adapter**.

## Riesgos 

- Caida del LB -> reboot rapido 
- Gestion de inconsistencia entre search, redis y debe

## Supuestos 

- Incremento del trafico de 10x 
- Cientos de tiendas 

## Tradeoffs 

- Agregamos redis y elasticsearch con el tradeoff de que si caen esos el sistema deja 
de funcionar.

Fin.

--- 

Con esto llegamos a la arquitectura candidata. Asumimos y documentamos riesgos.

