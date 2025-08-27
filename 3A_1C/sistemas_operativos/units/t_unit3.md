#  Teorica 3: Inter process communication(IPC) 

Los procesos cooperativos se distribuyen una tarea entre todos, mientras que los independientes hacen todo ellos.

Motivacion:

- Compartir informacion
- Acelerar computacion
- Modularidad
- Conveniencia

Hay dos modelos fundamentales:

- Memoria compartida
- Pasaje de mensajes

![Dos modelos fundamentales](graphics/ipc.png)

## Memoria compartida

Los procesos acuerdan compartir memoria fisica(para ello hay que hablar con el SO), generalmente no requiere de syscalls para acceder a la informacion.
La organizacion de la informacion y sincronizacion es determinada por los procesos. Surgen problemas de cache coherency al aumental los nucleos.

Meter al kernel en el medio de la memoria compartida no tiene ningun sentido, no hace falta acceder a la memoria con un fd, es un gasto de syscalls.
Lo que hay que hacer es usar el puntero a la direccion de la memoria.

Para que dos procesos accedan a la misma porcion de memoria se utiliza el nombre, el cual es una ruta en el filesystem
(no es absoluta, en el manual aparece cual es la absoluta).

Hay que tener cuidado al manejar los punteros de la memoria compartida, pues no es el mismo para diferentes procesos por mas que sea la misma 
porcion de memoria. Por lo tanto, para posiciones relativas conviene leer el offset y compartir ese dato entre procesos, no el puntero 
en si.

## Pasaje de mensajes

En este caso el kernel interviene, impone una sincronizacion implicita, no hace falta hacer polling sino que se bloquea el proceso.
Es más simple en sistemas distribuidos, util para pequeñas cantidades de información y tiene un tamaño de mensaje fijo o variable.

Operaciones:

- send(message)
- receive(message)

Es necesario un canal de comunicación (memoria, bus, red)

### Sincronizacion

`send` y `recieve` pueden ser bloqueantes o no bloquearse. Hay varias opciones:

- **send bloqueante**: hasta que llega a la casilla / proceso.
- **send no bloqueante**: resume inmediatamente.
- **receive bloqueante**: hasta que hay mensaje disponible.
- **receive no bloqueante**: recibe mensaje o null.

### Buffering

Los mensajes residen en en un buffer

- **Capacidad 0**: no buffering -> send debe bloquear.
- **Capacidad acotada**: send debe bloquear si se agota el espacio.
- **Capacidad no acotada**: send no bloquea.

> No es conveniente usar syscalls en los programas que se bloquean.

Va a haber que desglozar el programa en dos modulos distintos si tiene que hacer cosas mientras espera a la informacion(no bloqueante).

## Ejemplos de IPC

### Files

Un archivo es una forma de comunicar procesos que se adapta bastante bien al modelo de memoria compartida, en este caso si interviene el kernel, 
pero el kernel no indica como almacenar las cosas. 

Generalmente no se lo menciona como memoria compartida, pero para Ariel si se puede interpretar de ese modo.

### Pipes

Mecanismo muy "estrecho" de pasaje de mensajes, solo en un sentido. Fue uno de los primeros mecanismos de IPC en UNIX, muy simple y muy usado.
Tiene un buffering acotado y la sincronizacion se basa en `send` bloqueando al llenarse el pipe, mientras que `recieve` bloquea hasta que haya algo.

Hay dos formas de definir pipes:

- **Ordinarios/Anonimos**: No tienen nombre, significa que no tienen una entrada en el file system.
- **Named pipes**: Tienen nombre, permite comunicar dos procesos ordinarios.

Si no tiene un path(anonimo) como puede el otro programa encontrar el pipe? 

Basicamente, se hereda, pues se mantienen los fd al hacer `fork()`. Pero no se limita solo a padre-hijo, sino que en UNIX se podria 
acceder a los fd correspondientes leyendo del directorio `/proc`.

### Files vs. pipes

Ventajas de los pipes: 

- Se eliminan automáticamente (anónimos)
- Cantidad arbitraria de información. Si el pipe se llena se bloquea el que envia, en cambio un archivo tiene un limite de espacio.
- Ejecución paralela
- Sincronización implícita

Pipe tambien es mas rapido, pues en caso de que haya un disco(podria no haberlo en UNIX) es muy lenta la escritura a disco.
Se puede lograr practicamente lo mismo, pero al final del dia es mucho mas conveniente usar pipe.

