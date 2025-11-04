# Continuacion Scheduler

## Objetivos

Puntos a tener presentes para cada tipo de scheduler:

- Fairness
- Policy enforcement
- Balance

Nosotros vamos a tener un scheduler con prioridades, asi que es muy importante que pase el test.

Para los sistemas batch:

- Throughput(realizar la mayor cantidad de trabajos)
- Turnaround time(Tiempo medio que la tarea pasa en el sistemaa, esta presente)
- CPU utilization(Tener el CPU acupado todo el tiempo)

**Maximizar throughput minimiza el turnaround time?**

Para maximizar el throughput hay que elegir las tareas mas cortas, entonces las tareas largas se quedan colgadas todo el tiempo(casi como si fuera inanicion).

Como en un sistema por lotes tengo la informacion del tiempo total que necesita una tarea para tomar las decisiones, 
entonces se puede implementar un algoritmo para manejar entre las largas y las cortas.

Para los sistemas iteractivos: 

- Responsive time
- Proportionality(cumplir con las expectativas del usuario)

> Si se pone lento Chrome, no es culpa de Chrome, sino que es culpa del SO pues es el encargado de administrar los recursos.

Para los sistemas de tiempo real:

- Meeting deadlines(No perder informacion)
- Predictability(No perder calidad en reproduccion de multimedia)

## Algoritmos

### Batch: First-Come First-Serve

Un proceso corre hasta que termina, pero si se bloquea va al final de la cola.
Es un algoritmo sencillo de programar, pero hay un caso patologico: 
TEngo un proceso CPU-Bound que corre por 1s y se bloquea en disco y varios proceso I/O-bound que realiza 1000 accesos a disco cada uno.

Durante ese segundo solo se usa el CPU y los otros bloqueados en disco, voy a tener un pedazo de tiempo usando solo CPU y otro con solo I/O. 
Con esto no logro un balance ideal.

### Batch: Shortest job first

El tiempo que demora un parametro en ser completado es conocido en batch. Entonces, se aprovecha eso.
Es non-preemptive y optimo si todos los procesos estan disponibles simultaneamente, pero no funciona bien si el tiempo de llegada es diferente(No queda otra mas que esperar a que el primero termine).

Se puede calcular el turnaround time(ver apuntes) y observar que el orden en el que se ejecutan los procesos afecta el turnaround time(Esto asume que llegan al mismo tiempo).

### Batch: Shortest remaining time next

Version preemprive de shortest job first. Al que este mas pronto por terminar lo atiende. 
Al llegar un nuevo trabajo se copmara su tiempo restante(total) con el restante del trabajo actual.

**Tengo N tareas en el sistema, me llega una tarea nueva, no deberia compararlo con el tiempo restante de todos los trabajos y elegir al menor?**

Basicamente, el que se esta ejecutando ya es el que tiene el menor tiempo restando, por eso la comparacion es con el proceso actual.

**Que metrica beneficia este algoritmo?**

El troughtput porque siempre saca la tarea mas corta posible. Esto genera un problema 
en el caso que exista una tarea muy larga y que lleguen siempre tareas mas cortas.

### Interactivo: Round-Robin

Es preemptive(el que tenemos que implementar con algun que otro agregado), es la version preemptive del Batch:FIFS.

A cada proceso se le asigna un intervalo de tiempo(quantum) para correr), si al finalizar sigue ready, se le quita el CPU de todos modos. 
Si se bloquea antes de que venga el quantum, se pasa al siguiente.

Se van corriendo en orden de llegada, pero de a pedazos y se repite ciclicamente(como aplicar FIFO un monton de veces). 
Es facil de implementar con una lista de procesos ready. 

**El tamanio del quantum es importante. Por que?**

Porque el switcheo no es gratis y porque al ser interactivo tampoco conviene que haya quantums muy grandes(Eso convendria mas para batch).
El costo lo podriamos pensar como la relacion entre el tiempo del quantum y el tiempo del context switch.
(context switch de 1ms y quantum de 4ms contra un quantum de 100ms).

> Entre 20 y 50 ms es razonable.

### Interactivo: Priority scheduling

Round-Dobin aume que todos los procesos son igualmente importantes, en este caso 
se le asignan prioridades a los procesos y aquellos con la maxima prioridad son elegidos. Es preemptive, atiendo siempre al proceso con mayor prioridad.

**Para que necesita ser ajustada la prioridad periodicamente?**

Para evitar inanicion, pues un proceso con muy poca prioridad podria no ejecutarse.
En un sistema interactivo las prioridades cambian dinamicamente a partir de:

- Del usuario(depende de el)
- Del comportamiento del proceso(para I/O-bound puede ser 1/f, donde f es la proporcion del ultimo quantum usado). 
Esto implica que los CPU-bound tendran menos prioridad.

**Que sentido tiene aplicar esta tecnica?**

Prioriza los procesos que menos usan el CPU para maximizar el uso de los recursos, es decir, 
el balance.

Tiene prioridad porque si esta ready lo atiendo para que se bloquee inmediatamente, poniendo el disco a laburar mientras uso el CPU para otra cosa. 
Logrando un buen balance de carga de todos lo recursos. De alguna manera es inmune a los procesos que quieran usar todo el CPU.

### Interactivo: Priority scheduling - Multiple queues

Similar al anterior, solo que se agrupan procesos por clase, donde para procesos de una misma clase se aplica la politica de round robin.

En round robin deja de ser importante quien es el primero, porque se repite constantemente y estar primero o segundo es lo mismo que estar ultimo.

Con cierta frecuecia le le asigna un quantum muy grande a un proceso CPU-bound, y cuando usa todo su 
quantum se le baja la prioridad. Basicamente, si un proceso usa todo su quantum, es muy probable que sea CPU-bound.

### Interactivo: Priority scheduling - Shortest process nect

> No hace falta que se aprendan los nombres de memoria, me importa que lo sepan.

Es la contraparte interactiva de shortest remaining time next, en lugar de basarse en informacion conocida se estima el comportamiento previo.
La estimacion se hace basandose en ejecuciones historicas, para intentar se adivinar cuanto va a 
ejecutar.

Una manera de hacerlo es con una tecnica llamada **envejecimiento o aging**, dandole mas peso a las ejecuciones con menor tiempo. 

> **Nota**: En la diapo T0 es un tiempo real, pero le dice estimacion porque se usa el valor real para estimar el siguiente.

**Ejemplo parcial**: Se registraton los tiempos de las ultimas ejecuciones, con un valor de a de X determinar cuanto va a demorar la proxima ejecucion.

### Interactivo: Guaranteed scheduling

Prometamos que si hay n usuarios conectados, cada uno recibia 1/n del tiempo del CPU.
Alternativamente, si 1 usuario tiene n procesos, cada proceso recibira 1/n del tiempo del CPU.
Luego, registrar el tiempo de uso de cada usuario / proceso en terminos de la proporcion usada para 
elegir aquel proceso con la menor proporcion.

Trata de garantizar una distribucion igualitaria de tiempo de CPU, dando prioridad siempre a aquel con la menor proporcion.

### Interactivo: Lottery Scheduling

El siguiente a correr es una loteria, repartimos tickets a procesos y se sortea el uso del CPU.
Para darle mas prioridad a un proceso, simplemente hay que darle mas tickets 
para que tenga mas probabilidad de ganar. 

**Para que podria servir esto?**

Para algunos contextos es mas sencillo asignar prioridades, para darle el doble de prioridad a 
un proceso solo hay que darle el doble de tickets.

Procesos que cooperan entre si pueden compartir tickets. 
Por ejemplo: upongamos que en el campeonato de 
ChompChamps hay una categoria donde el bot que hicimos compite contra si mismo. Si puede detectar 
que es el mismo bot(su oponente), entonces se asigna uno como ganador y el ganador recibe los 
tickets del que se asigno como perdedor.

**La intervencion de quien seria necesaria para que se puedan compartir los tickets?**

Es necesaria la intervencion del SO mediante syscalls.

### Interactivo: Fair-share scheduling

Si empieza a tener presente la nocion de usuario, si un usuario tiene 50 procesos y otro solo 1.
Primero se corre un proceso del primero, luego un proceso del segundo, etc.

Si uno tiene procesos ABCD, y el otro solo tiene a E:

A E B E C E D E A B E....

> **Nota**: Correr `cat &` se bloquea y termina con una signal, esto ocurre porque no tiene sentido leer en background.


Para saber si un proceso esta bloqueado o ignorando una signal, el kernel simplemente se fija 
como esta configurado el proceso que hizo la syscall.
