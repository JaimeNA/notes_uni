# Teorica 2: Procesos

Todo software se organiza en **procesos secuenciales** o simplemente **procesos**, hay que diferenciar bien entre:

- **Proceso**: Abstraccion de programa en ejecucion (si ponemos esto en el examen va a estar bien).
- **Programa**: Almacenado en disco, no hace nada.

> **Nota**: Esto puede ser pregunta de examen, como todo.

Que podria ocurrir si tenemos un programa que corre dos veces? Lo que ocurre es que tengo dos procesos y un programa.

Para que un programa pueda ser ejecutado, se debe cargar el codigo en memoria para poder convertirse en proceso. 
Posee sus propios registros y variables, habra un stack por cada proceso.

> Desde el punto de vista del proceso, el es dueno del CPU.

Esto se debe a que el proceso no se tiene que preocupar por los otros procesos, por ejemplo, saber si el CPU esta ocupado o si otro lo quiere usar.
Por esto se llama proceso secuencial, para el proceso es una instruccion atras de la otra, no percibe los bloqueos de tiempo.

## Pseudo concurrencia

Periodo de tiempo muy corto, llamado **quantum** o **time slice**, en el cual se corre un proceso antes de ser interrupido. 
Estos periodos son tan cortos que son imperceptibles para el usuario.

Tambien se lo suele llamar **pseudo paralelismo**, no es concurrencia porque en realidad no esta ocurriendo todo al mismo tiempo.
Para tener paralelismo real se necesitan de multiples nucleos compartiendo memoria fisica, eso es **paralelismo real**.

No hay que pensar en los **switches**, es decir, que no notamos los saltos entre procesos dentro del CPU. Este switch no es uniforme ni reproducible ya 
que no puedo hacer supuestos sobre tiempo de ejecucion.

![Modelo de proceso](graphics/modelo_proceso.png)

### Reflexion

Cuando se tiene un bug, es deseable que sea facilmente replicable, que sea aislable. Luego, si se tiene dos programas con memoria compartida,
donde el proceso 0 es el que initializa la variable compartida y el 1 la utiliza, por los switches, no puedo suponer que 0 se ejecutara antes de 1.

Este es un supuesto de tiempo de ejecucion que no es correcto hacer, entonces, a veces funciona y a veces no, dependiendo de como se ejecuto el switch, haciendo que sea dificil de replicar.
La idea es programar de manera que seamos inmunes al orden de ejecucion del SO.

> **Nota**: El context switching es costoso para el CPU.

---

Ademas, la responsabilidad principal del **scheduler** es repartir de manera homogenea el tiempo de ejecucion dentro del CPU de cada proceso, 
la manera mas simple es haciendo una lista circular(0123012301230123...) e ir ejecutando cada programa por una cantidad de tiempo determinada 
antes de ir al siguiente(se conoce como **round rolling**).

> Siempre conviene hacer algo funcional(basico) primero(no tiene que ser eficiente ni lindo) para poder empezar con algo, 
para asegurar que es correcto y usar de referencia. Permite hacer experimentos y verificar que todo funcione todo primero.

## Creacion de procesos

Un proceso tiene una vida en el SO: nace -> ejecuta -> muere. 
La creacion de un proceso requiere de un SO, cualquier cosa que no este siendo creada por un kernel no es un proceso, por ejemplo, una syscall.
Puede tener el mismo codigo y hacer lo mismo, pero no sera un proceso.

Casos que requieren de un proceso:

- Se inicia un SO(Daemons).
- Proceso solicita la creacion de otro(Script bash y comandos UNIX).
- Usuario solicita crear un nuevo proceso(Terminal, GUI).
- Inicio de un trabajo por lotes.

Siempre es un proceso creando otro proceso, entonces, de donde sale el primer proceso?

El sistema operativo es la respuesta correcta, no el "proceso" sistema operativo, pues el SO provee la abstraccion de un proceso, 
pero el por si mismo no es un proceso. De igual manera que el scheduler no es un proceso. La creacion del primer proceso es mas "artesanal", 
es la excepcio a la regla, luego los demas van a ser creados como se describio antes.

### Win32

Para crear un proceso se llama a `createProcess()` el cual tiene 10 parametros:

- El programa a ejecutar
- Parámetros
- Argumentos
- Atributos de seguridad y control
- Prioridad
- Ventana a asociar con el proceso

Hay mas de 100 syscalls extras para administrar y sincronizar procesos, en ambos casos(UNIX y Win32) los procesos 
creados tienen su propio espacio de direcciones, diferente al padre.

UNIX es mucho mas elegante ya que tiene mucho menos syscalls y no cambiaron los prototipos de las funciones. Basicamente, 
el **core** se ha mantenido igual durante un monton de tiempo.

## Terminacion de procesos

Un proceso puede terminar:

- Salida normal(voluntaria)
- Salida por error(voluntaria)
- Error fatal(involuntario), se envia una senal y el comportamiento por defecto es terminar(no se puede cambiar el manejador).
- Muerto por otro proceso(involuntario), por senal, pero si se puede cambiar manejador.

> **Nota**: Exit es una syscall. Si necesito una syscall para iniciarlo, entonces necesito una syscall para terminarlo.

## Jerarquia de procesos

Un proceso y su creador preservan una relacion, generalmente padre / hijo. No significa que un proceso sea mas importante que otro, 
sino que se trata de una relacion entre procesos. En UNIX, el proceso padre de todos los procesos es `init`, es la raiz del arbol. Si se mata a un proceso padre, 
se manda una senal al padre y muere, pero si quiero matar a los hijos voy a tener que mandar una senal a cada uno, no mueren en cascada. 

> **Nota**: En nuestros TP02, la shell va a ser nuestro `init`, aunque podriamos usar otro proceso, suele ser la shell. 

### Grupo de procesos

Un padre y sus hijos se denominan un grupo, esto tiene ventajas como poder mandar una senal a un grupo entero o atencion de senales independiente.
En general, grupo de procesos que esta leyendo del teclado tiene un solo miembro. Para `init`, se puede secir que es analogo a la clase `object` en Java.

### Win32

No existe jerarquia de procesos, el padre recibe un handler para manejar al proceso. El handle se puede transferir a otro proceso.

## Estado de procesos

Un proceso puede tener uno de 3 estados fundamentales:

- Running(usando el CPU en ese instante)
- Ready(ejecutable; detenido para dejar que se ejecute otro proceso)
- Blocked(no se puede ejecutar hasta que ocurra algun evento externo)

En general, los primeros dos podrian no implementarse, se podria dejar simplemente como `runnable` y listo, 
no es siempre necesario llevar registro de los otros dos. Blocked, por otro lado, si debe tenerse en cuenta.

![Ciclo de vida de un proceso](graphics/estados.png)

Que un proceso este bloqueado(syscall implicita) significa que no se le da tiempo en el CPU hasta que ocurra la condicion esperada, 
esto se ve cuando se espera el input de un fd pues se usa la syscall `read()`.

> **Nota**: Se puede verificar el estado de un programa con `strace`.

### Transcisiones

Clasico ejemplo de **ejercicio de final**:

- **Evento que pasa de running a blocked**: waitpid
- **Evento que pasa de blocked a Ready**: exit
- **Evento que pasa de running a ready**: interrupt del timer
- **Evento que pasa de ready a running**: interrupt del timer(saca a un proceso y agarra uno en ready y lo pone en running) 

**Pregunta de parcial**: Se rompió el timer. No existe otra fuente de int. ¿puede el SO volver a tomar el control?

Si, ya que estan las interrupciones de software, las syscall. La ejecucion de una syscall no necesariamente le devuelve el control al proceso.
Una vez le damos el control al kernel, no tenemos garantia de lo que va a pasar.

**Pregunta de parcial**: Porque no hay transicion de ready a blocked y de blocked a running?

La transicion de running a blockeado ocurre de manera sincronica, mientras estando ready no puedo pasar a blocked simplemente porque no 
se esta ejecutando, no tendria ningun sentido la transicion. No se puede realizar division por 0 o inv. opcode estando ready.

> Para desbloquear el proceso tiene que primero ser desbloqueado para ready por el so luego de algo externo, 
entonces no pasa nunca de blocked a running sin un intermedio en el que se pone en ready mientras corre algo más.

## Implementacion de procesos

El SO mantiene una tabla de procesos donde almacena informacion privada de cada proceso. Es una tabla de structs, 
el struct pueden ser tan complejo como uno quiera. 
Cada entrada se denomina Process Control Block(PCB). Es la informacin necesaria para pasar de ready -> running -> blocked.

Si el proceso termino, a quien le puede interesar el valor de retorno de dicho proceso?

Al padre, al SO no le va a importar. Lo unico que le importa al SO es si el proceso termino. 
El padre puede conocer el exit status de su hijo mediante el PCB, pero no directamente pues es del SO. Entonces, se usa una syscall
en particular, se podria usar la syscall `wait()`. Recien cuando hace `wait()` el padre, el SO borra el PCB.

Entonces, si el hijo termino, pero no se borro el PCB, se coloca el estado **zombie**. Esta en ese estado desde que ejecuta `exit()` hasta que
alguien ejecute `wait()`. Si muere el padre, los hijos quedan en estado **huerfano** y son adoptados por `init`, 
de manera que rapidamente dejan de ser huerfanos, es decir, esta el estado, pero dura muy poco. 
Entonces, para evitar que se acumulen los huerfanos, `init` hace `wait()` a todos estos procesos.
