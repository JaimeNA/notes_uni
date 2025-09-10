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

## Sockets

Permite comunicar procesos a traves de una red, es bidireccional(full duplex).
Un socket se identifica con una IP y un puerto, generalmente sirve para una aplicacion cliente - servidor.

### FIFO vs Unix Domain Sockets

Los pipes que usamos son simples, tienen una sola direccion. Los sockets se pueden usar por muchos procesos a la vez, 
tienen un hilo escuchando conexiones entrantes y apenas llega una comunicacion se crea un socket paralelo para la comunicacion, 
pero eso no se muestra, desde afuera parece un solo socket(transparente para el cliente).

Como la radio de un puerto, todos van al canal 16, pero luego prefectura los manda a otro canal y libera el 16,
la unica diferencia es que hay un canal individual para cada embarcacion en caso de sockets.

> **Nota**: Las signals son async, ya que el programa que recibe una signal no esta esperando la signal, sino que esta corriendo y es interrumpido.

## Signals

Basicamente como kill es una syscall, el SO se encarga de buscar el proceso al que se le envio la signal y en base a la entry en el SO 
del programa se llama al handler. Pero el handler no lo llama el SO, sino que modifica el stack y los punteros del programa receptor de 
la signal para que la proxima instruccion que ejecute sea la del handler(el handler se corre en el contexto del programa).

No hay signals anidadas, pero tampoco es que no se recibe la signal. Lo que ocurre es que se recibe la signal y se espera a que termine el handler, 
una vez termina el SO procesa la otra signal. No hay un concepto re `ret` en un handler, el programa se interrumpte y luego de 
terminar el handler continua la ejecucion, pero no tiene porque hacer algo. Se podria definir una variable que se modifique en el 
handler para notificar al programa que este se ejecuto, pero nada mas.

## Race condition

Una situación en las que dos o más procesos están leyendo o escribiendo datos
compartidos y el resultado final depende de quién corre en cada momento.

No es sinonimo de estado inconsistente, sino que el resultado depende de la ejecucion. Es muy feo debuggear programas donde ocurren
condiciones de carrera.

Esto se soluciona con una especie de patron de sincronizacion, es decir, el programa que escribe tiene acceso exclusivo y 
una vez termina libera el acceso exclusivo. Esto no impide que el escritor sea interrumpido, 
pero si hay garantia que ningun otro proceso podra escribir al mismo lugar. No es inmune al **context switch**. 

Se ejecuta de manera atomica uno y despues se ejecuta otro.

### Region critica

Es una parte de un programa, la parte que accede a la memoria compartida. 

### Mecanismos de sincronizacion

- **Semaforos**: El wakeup waiting bit se plantea(Dijkstra - 1965) como entero representando la cantidad de wakeups disponibles, 
el cual llamaremos semaforos. Tiene dos operaciones: **down** y **sleep**.

> **Nota**: Mutex es basicamente **mutual exclusion**.

Si dos quieren acceder al mismo semaforo, el primero que llega "gana el semaforo". Es una especie de race condition, 
pero no importa porque no afecta el resultado del problema.

El semaforo mutex es solo para proteger el acceso a la zona critica, mientras que el otro semaforo simplemente bloquea alguno de los dos procesos. Ver problemas clasicos de los apuntes.

## Deadlock

Un conjunto de procesos está bloqueado (en estado de deadlock) si cada proceso del conjunto está
esperando un evento que solo puede causar otro proceso del conjunto.




