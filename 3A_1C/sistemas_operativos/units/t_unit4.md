# Teorica 4: Threads

Tradicionalmente un proceso tiene su espacio de direcciones y un unico hilo(thread) de ejecucion.

Existen casos en los que es muy util tener multiples hilos en un mismo espacio de direcciones corriendo de forma pseudo paralela como 
si fueran procesos separados, salvo por el espacio de direcciones. Los threads comparten el codigo, el heap, pero no stack, esa es la principal distincion entre dos threads y dos procesos. El stack no se comparte porque tiene informacion de la ejecucion, el nivel de anidamiento y las variables locales, entonces, usar el mismo stack no tiene sentido.

Un proceso solo ejecuta una instruccion en particular en cualquier momento, no confundir esto con la nocion de threads.
Todos los hilos tienen el mismo codigo, heap y variables globales. En cuanto al stack, no pueden tener el mismo, es privado a cada hilo.

La motivacion es poder correr muchas actividades simultaneas, algunas bloqueantes. Desglosar la situacion:

- Aumenta el uso del CPU
- Simplifica la programacion
- Aprovecha arquitecturas con multiples CPUs

Tiene la misma nocion de modelo de procesos, donde se pensaba en procesos secuenciales, pero con el agregado de que comparten un espacio de direcciones.

Ademas, son mas 'baratos' pues se crean y se destruyen, y son hasta 10-100 veces mas rapido que un proceso. Un thread tambien se puede reutilizar, 
cambiando su funcion.

Un ejemplo seria el web server, un thread es el **dispatcher** que recibe conexiones entrates y delega la atencion de una conexion a otro thread, 
cada conexion termina con un thread individual.

> **Nota**: Como simular threads con solo procesos? Con memoria compartida.

**Como seria un servidor sin threads?**

- Syscalls no bloqueantes.
- Almacenar el estado de cada pedido para retomarlo cuando llegue la pagina del disco, esto es muy costoso.
- Se pierde la nocion de computacion secuencial.
- Estamos simulando threads **the hard way**.

Esto es basicamente modelar una maquino de estado finita, es paralelismo de manera que estan ocurriendo cosas de forma paralela, por ejemplo,
el disco busca algo en cache mientras se corre codigo.

En las syscall no bloqueantes, se necesitan interrupciones para avisar al proceso que la syscall termino(por ejemplo, el read), 
debido a que sino el proceso no tiene manera de saberlo(sacando polling).

> Es mucho mas facil programar cuando se bloquea.
> No es recomendable usar syscalls no bloqueantes.

## Modelo de threads 

El modelo de procesos esta basado en 2 conceptos independientes:

- Agrupacion de recursos(PID, FD, pointer to text segment)
- Ejecucion(Register, program counter, CPU time used, process state)

### Proceso vs. thread

Un thread esta contenido en un proceso, pero un proceso agrupa recursos mientras que thread es 
una entidad planificada(scheduled) para ejecucion en el CPU. Los threads permiten multiples ejecuciones en el mismo conjunto de recursos.

Los threads comparten el espacio de direcciones, por tanto, un thread podria modificar informacion mientras otro la esta leyendo, 
podria ocurrir un **race condition**. No existe mecanismo de proteccion por el kernel ante esta situacion, 
esto se debe a que es problema del programador, como el caso de memoria compartida. El kernel no se puede interponer en cada acceso a memoria.
Como mucho el kernel puede tener memoria virtual y configurar el hardware para que me alerte con una interrupcion cuando se esta 
intentando acceder a memoria sin permisos.

El **yield** basicamente termina el quantum del thread antes, permitiendo que el scheduler le de el control a otro thread. Por ejemplo,
si libero algun semaforo conviene hacer un yield inmediatamente para que otro thread lo pueda usar. 
Basicamente, ayuda a la cooperacion entre procesos.

## Implementacion en espacio de usuario

- El kernel desconoce de su existencia
- Desde la perspectiva del kernel son procesos con un unico thread
- Provee soporte para threads en caso de que el kernel no los provea
- Se implementan como una libreria

Esta no es la implementacion mas comun. Esta implementacion busca complementar kernels que ya de por si no tienen una implementacion de 
threads, de manera que se implementa como libreria.

Cada proceso necesita su tabla de threads privada, almacenada en el user space por cada proceso. 

> Para librerias como errno, las partes que tienen permisos de escritura tienen una copia en cada proceso o al menos es COW.
> El resto del codigo que sea soloo lectura se lee del mismo lugar por todos los procesos para que sea mas eficiente.

En la implementacion con el kernel, si un thread se bloquea, para un mismo proceso, el resto puede seguir corriendo pues el kernel 
tiene conocimiento de sus existencia y permite que el resto corra. Esto no ocurre en la implementacion del usuario, si se ejecuta 
una syscall bloqueante, se bloquea todo el proceso.

### Ventajas

Ademas, cada proceso tiene su propio algoritmo de scheduling de threads. Si un thread se bloquea en un join 0 localmente, 
entonces, como no se requiere bajar al kernel en ese caso se bloquea solo un thread pues la libreria detecta que se esta ejecutando una 
instruccion bloqueante. Tambien es mucho mas eficiente que la version del kernel debido a que no se debe cambiar de context y 
tampoco hay flusheos de cache. Esto tambien nos permite decidir nuestro propio algoritmo de scheduling pues estamos en user space.

### Desventajas

- Syscall bloqueante
- Page faults, si ocurre bloquea todo, igual que con la anterior
- Inanicion, se soluciona con una analogia timer
- Uso de threads

## Implementacion en espacio de kernel

- No es necesario el run-time system ni tabla de threads(en espacio de usuario)
- Un thread se bloquea como es usual y el kernel elige otro thread(u otros procesos)
- Debido al mayor costo, se pueden reutilizar los threads.

## Implementacion hibrida

Hay threads en espacio de usuario y threads en espacio de usuario. Por cada thread del kernel, se puede tener varios threads en el espacio de usuario.

### Schedulers activators

Uno de los threads(del user space) bloquea todo el proceso, entonces se soluciona con:

- Notificar al kernel(thread manager) para que se entere de la situacion y cree otro thread, y 
le de el control al run-time system a traves de ese nuevo thread(de kernel).
- Sin embargo, viola la estructura de un sistema de capas con el **upcall** del kernel al user space.

