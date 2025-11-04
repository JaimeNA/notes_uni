# Teoria 5: Scheduling

La responsabilidad del scheduler es simplemente decidir quien es el proximo proceso o thread(indistinguibles en este caso) a correr.
La eleccion no es random, generalmente se siguen criterios, conviene empezar con **round robin** y luego hacerlo mas complicado.

Con multiprogramacion se hace referencia a compartiendo la misma memoria, varios procesos corren de forma pseudoparalela y si 
uno se bloquea se pasa al otro para evitar desperdiciar tiempo(se va de un proceso a otro). Es muy costoso realizar un context switch, 
se borra la cache, por ejemplo.

Las decisiones de scheduling dependen fuertemente en el comportamiento del proceso, no son agnosticas.

**Pregunta parcial**: Que me permite determinar de que tipo de proceso estamos hablando?
La clave es pensar en la longitud de la rafagas, rafagas cortas es I/O bound y rafagas largas es CPU-bound. 
Es **importante** NO considerar las rafagas de espera, ya que estas suelen ser similares(Hay que ver las rafagas de CPU).
Esto se debe a que lo que hace el proceso esta en el tiempo de cpu, mientras que la longitud del tiempo que esta esperando por I/O 
depende de otras cosas(no del proceso).

La ejecucion de que proceso va a cambiar el estado en el cual los semaforos estan bloqueados? sem_post de cualquier proceso sobre 
ese semaforo.

> Con procesadores mas rapidos los procesos se vuelven mas I/O bound. Pues a más ciclos se procesan más instrucciones y la ráfaga de CPU es más corta.

## Clasificacion

### Non-preemptive

Elige un proceso y corre hasta que se bloquea o hasta que libera el CPU voluntariamente.

### Preemptive

Un schedular que evita que sigas teniendo el control del procesador quitandotelo, el scheduler de cualquier sistema. 
Elige un proceso y corre hasta que se vence un plazo establecido, incluso si esta en estado ready. 
Es necesaria la interrupcion del timer para darle el control al SO(scheduler).

> TO provent something from happening by taking action first.

---

La traduccion de apropiativo y no apropiativo no le gusta al profe porque es mas una pripiedad del proceso y no del scheduler.
Prefiere la definicion en ingles.

## Categorias

Las decisiones de scheduling para diferentes tipos de sistemas operativos pueden variar signitivativamente.

- **Batch**: Non-preemtive o preemptive con quantum largo. Sistemas no interactivos, entonces no es relevante la velocidad de respuesta en 
interacciones con el usuario. 
- **Interactivo**: Preemptive, porque sino podria causar inanicion o, mas general(y comun), 
para usar un programa habria que esperar a que otro termine y seria muy dificil de usar la computadora(cambiando entre programas como whatsapp y mail).
- **Real time**: A veces no necesita preemptive, uso especifico. 

En batch, porque eligiria un quantum mas largo? Porque se evita el costo de muchos context switch, basicamente hay menos overhead de switching entre 
procesos. Esto se debe a que no necesita que sean tan responsivos los programas.



