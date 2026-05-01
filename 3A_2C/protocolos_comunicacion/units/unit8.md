# Unidad 8: Protocolos de ruteo

Sigue siendo parte de la capa de red. Hay varios tipos de ruteo:

- Ruteo minimo(trivial)
- Ruteo estatico(configurado manualmente)
- Ruteo dinamico(configurado automaticamente)

## Ruteo minimo

En cada host hay una tabla de ruteo, por mas que se conecte directo a otra 
computadora, va a haber ruteo. Agarra una IP al azar cuando se prende el host.

## Ruteo estatico

La tabla de ruteo es construida por un administrador en forma estatica. 
Sin embargo surge un problema, si hay que hacer un workaround o algo asi, hay que 
cambiar todas las tablas de ruteo de todos los routers.

> Hay una columna mas en la tabla de ruteo, se llama metrica y se le puede 
asignar un peso para elegir.

Si la red es muy grande es muy complejo asignar pesos y configurar todo.

## Ruteo dinamico 

La tabla de ruteo es construida en base a informacion intercambiada por 
protocolos de routing. De manera que si hay algun corte el algoritmo se encargara 
de volver a balancear las tablas de ruteo. La **convergencia** es el tiempo que se 
tarda en balancear las tablas de ruteo, se busca que el algoritmo converga lo mas 
rapido posible. Hay varios tipos:

Los protocolos pueden ser IGP o EGP, internos o externos a una organizacion 
respectivamente. 

### Algoritmos de ruteo descentralizado

Los routers solo se comunican con su vecino para construir las tablas. 
**Distance vector algorithm**, pero cuando hay un corte de 
enlace o hay despasaje de tiempo se propaga y se rompe. Se quedan contando entre si 
y paran cuando se llega a infinito y sale el problema de definir infinito. Tiene un 
par de workaround, el mas sencillo es **spli-horizont**, tambien esta 
**reverse...(no lo entendi)** y **hold-down-timer**(desconfia de los demas por un 
cierto tiempo, tiempo de hold mayor que el de actualizacion).

- RIP(IGP)
- BGP(EGP)

### Algoritmos de ruteo globales

**Link state algorithm**, usa grafos y primero propaga un paquete de manera de 
inundacion, y esto tiene como resultado que si hay muchos routers se saturan los 
routers y se empiezan a tirar paquetes. Luego, usa djikstra para determinar el 
mejor camino.

- OSPF(IGP)
- IS-IS(IGP)

### Sistemas autonomos

Conjunto de redes bajo una misma administracion o politica de routing comun. 
Utiliza algoritmos de routing para comunicarse internamente(IGP) y externamente(EGP).

### RIP

Routing Information Protocol, la version 1 era muy naive, no tenia autenticacion, 
notacion classful y no soporta CIDR/VLSM. Mientras que la version 2 mejoro bastante y 
era classless(no determinada la mascara en base a la clase). Fue ampliamente usado 
porque venia preinstalado en BSD. 

### OSPF

Open Shortest Path First, soluciona el problema de la saturacion separando en areas. 
Entonces solo pueden hacer flooding en su area. Hay un backbone que recorre toda 
la red y se divide esa misma red. Entonces, todas las areas de OSPF son un sistema 
autonomo. Los routers entre medio toman un valor general de un area para pasarle a la 
otra. 

