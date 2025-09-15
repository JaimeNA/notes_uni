# Teorica 4: Threads

Tradicionalmente un proceso tiene su espacio de direcciones y un unico hilo(thread) de ejecucion.

Existen casos en los que es muy util tener multiples hilos en un mismo espacio de direcciones corriendo de forma pseudo paralela como 
si fueran procesos separados, salvo por el espacio de direcciones.

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




