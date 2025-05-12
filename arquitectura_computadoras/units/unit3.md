# Unidad 3: Gestion de memoria

## Modo protegido

### Conmutacion de tareas

Lo mencionado del timer-tick, habilita correr varios programas casi en simultaneo debido a la velocidad a la cual ocurre todo, basicamente va intercambiando el programa que esta corrienda cada cierto intervalo de tiempo. El **scheduler** es una rutina del sistema operativo que decide que tarea/programa sera el siguiente en ser ejecutado luego de la interrupcion.
El scheduler manda el `IP` al codigo de la siguiente tarea, esto trae un problema, necesito saber cual es el momento en que se interrumpio la tarea anterior. Esto se soluciona guardando el **contexto**(todos los registros) en memoria para tener listo todo para continuar cno la ejecucion mas tarde.

![Diagrama scheduler](graphics/scheduler.png)

> **Nota**: El SO es una tarea, tiene la ventaja de ser la primera que corre, entonces es la que gestiona.

Que pasa si no hay tareas? Corre una tarea "tonta" que es basicamente un loop que no hace nada, en Windows es el proceso 0 llamado **Proceso inactivo del sistema**. Cuando no tiene a quien entregarle el procesador, se lo da a este programa.

### Proteccion de tareas

Esto exite para evitar que los programas malignos hagan cosas como leer el espacio de memoria de otras tareas, de esa manera podrian robar informacion.
El unico que se da cuenta que hay un programa accediendo a una zona de memoria que no es de el es el hardware, pues el SO u otros no se estan ejecutando. Entonces, el SO, crea unas tablas que indica que programas pueden acceder a que secciones de memoria, estas tablas son chequeadas por el CPU antes de permitir que un programa lea/escriba en memoria. Este sistema fue creado por Intel.

### MMU

Es lo que valida cada acceso a memoria y determina si esta bien o esta mal a partir de las tablas.
Se le va asignando espacio a los programas, este espacio puede ser random(lo que pida coada programa) o fijo. Entonces, una vez termina uno, ese espacio termina siendo liberado(desasignado), y puede haber programas que necesiten la misma cantidad de memoria que se libero antes o requieran mas y no entren. La **Unidad de Segmentacion** se encarga de asignar el espacio variable, mientras que la **Unidad de Paginacion** asigna **paginas** de memoria(y es la que chequea las tablas), este fue el sistema que implemento Intel. La unidad de paginacion puede ser desactivada, pero la de segmentacion no(pero se puede armar toda la memoria como un gran segmento y se skipea la unidad de segmentacion, es una manera de 'desactivarla').

- **Tamano variable**(Unidad de Segmentacion): Permite dar el espacio perfecto, pero una vez termina el programa queda un espacio vacio y hay muy pocas probabilidades de que otro programa requiera exactamente el mismo espacio(poco facil de sustituir).
- **Tamano fijo**(Unidad de Paginacion): Se da memoria de a bloques, en el peor de los casos sobra espacio. Las sustituciones son mucho mas simples.

![MMU](graphics/mmu.png)

> **Nota**: En el TPE vamos a usar un gran segmento de memoria que va a ocupar toda la memoria, para desabilitarla virtualmente.

#### Unidad de segmentacion

Segun Intel, son dos tablas **GDT**(Gocal Descriptor Table) y **LDT**(Local Descriptor table), dentro de la GDT van todos los descriptores de segmentos del SO y desntro de la LDT van los descriptores de todos los segmentos del resto de las aplicaciones. 
Se encuentran en memoria, el procesador tiene dos registros, `GDTR` y `LDTR`, que apuntan a estas tablas. En la actualidad nadie usa la LDT, solo se trabaja con la GDT. Estas tablas son las que deja el SO antes de ser interrumpido para que corra otro programa.

Sin embargo, que impide que un programa modifique la tabla de descriptores? Hay un descriptor llamado **descriptor de GDT** que se describe a si mismo, se pone los permisos mas altos dentro del procesador y el unico programa que puede modificarlo es el SO(Kernel Space, segmento de memoria con los permisos mas altos) pues, al ser el primero que llega, se pone el permiso mas alto a el mismo y al resto de las aplicaciones se les asigna un permiso menor. De esa manera, se evita que otro programa que no sea el SO modifique las tablas.

**Ejemplo**: Si corriendo el programa con `IP`=10xxh, descriptor dice que el programa en esas direcciones tiene entre 1001h y 1FFFh, si quiere acceder a 3222h al verificar con GDT saltara un error. 

> **Nota**: El administrador o root, es el usuario con mas permisos del **User Space**, no del **Kernel Space**. Ademas, es el unico usuario que puede instalar cosas en el Kernel Space.

## Descriptores

Describen a pedazos de memorias, estructura de datos que armo Intel. Tiene varios datos, pero nos interesa el `SEGMENT BASE`.

### `SEGMENT BASE`

Indica donde empieza. Cada segmento de memoria tiene que estar descripto por un descriptor de segmentos, dice donde empieza, termina, si es de datos, si es de pila, etc. 

### `SEGMENT LIMIT`

- **P**: Bit p, indica si esta presente o no esta presente en memoria. Esto se debe a que existe lo que se conoce como memoria virtual, es decir, memoria que esta en disco pues la memoria esta llena.
- **DPL**: Indica el nivel de privilegios del segmento. Hay 4 niveles de privilegios, pero solo necesitamos dos, unos para kernel y otro para user space.
