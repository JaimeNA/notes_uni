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

**Ejemplo de uso**: El code segment trabaja con el instruction pointer, el CS apunta al inicio del codigo y el IP al offset. Luego el CS no apunta exactamente a memoria sino que saca un descriptor del GDT(base apunta al inicio de segmento), y de ahi saca en inicio del segmento. Es decir, si CS es 20h, al leer la memoria va a la GDT y la GDT le devuelve la lectura de la direccion equivalente especificada en el descriptor, se conoce como **indireccion**. Entonces, puede ser que sea la direccion 20h de memoria real o ABBA000h de memoria real. 

Esto se usa para organizar mejor y para controlar los permisos, agrega indireccion(o mapeo) y seguridad. Luego, si no hay espacio suficiente, se usa el `P`(persistencia), y de esa manera si no hay memoria, le da al programa espacio en disco. Las aplicaciones estadisticamente menos utilizadas las va bajando al disco.

> **Nota**: El administrador o root, es el usuario con mas permisos del **User Space**, no del **Kernel Space**. Ademas, es el unico usuario que puede instalar cosas en el Kernel Space.

## Descriptores

Describen a pedazos de memorias, estructura de datos que armo Intel. Tiene varios datos, pero nos interesa el `SEGMENT BASE`.

### `SEGMENT BASE`

Indica donde empieza. Cada segmento de memoria tiene que estar descripto por un descriptor de segmentos, dice donde empieza, termina, si es de datos, si es de pila, etc. 

### `SEGMENT LIMIT`

- **P**: Bit p, indica si esta presente o no esta presente en memoria. Esto se debe a que existe lo que se conoce como memoria virtual, es decir, memoria que esta en disco pues la memoria esta llena.
- **DPL**: Indica el nivel de privilegios del segmento. Hay 4 niveles de privilegios, pero solo necesitamos dos, unos para kernel y otro para user space.

## Stack State Segment(TSS)

El procesador de Intel, nos da una herramiento para guardar el contexto de ejecucion, ya no se lo utiliza, pero por mucho tiempo esta estructura de datos de intel se fue utilizada por linux y windows.

## Privilegios de E/S

El procesador tiene dos mecanismos:

1. Campo de 2 bits IOPL en los EFLAGS. Que especifica el minimo nivel de privilegios que de debe tener para poder usar instrucciones de E?S
2. Mapa de bits de E/S en TSS.

Instrucciones sensibles:

- IN
- OUT
- INS
- OUTS
- Cli   (Clear interrupt)
- Stu   (Set Interrupt)

## Memoria Virtual

### Paginacion

Objetivos de sistemas operativos:

- Proveer recursos a las apps
- Administrar recursos

Problemas:

- Memoria insuficiente
- Fragmentacion de memoria
- Seguridad

Esto se logra con abstraccion e interposicion.

Tiene los mismos objetivos que segmentacion, pero lo hace de una manera diferente. Divide al mapa de memoria(virtual y fisica) en **paginas**. Cada pagina tiene un tamano fijo, genera mapeo de pag-virtuales(paginas) a pag fisicas(marcos) y tiene esquema de permisos.

![Ejemplo de paginacion con memoria virtual](graphics/paginacion.png)

**Ejercicio**: Desarrolle un sistema de mapeo para un procesador Intel de 32 bits que puede manejar hasta 4GB de memoria fisica.

1. Que tamano de pagina elige?

Puedo hacer paginas de 4GB, pero tengo solo una, tambien puedo hacer paginas de 2B pero el indice de descriptores va a ser muy grande. Estas no me sirven, Intel en 1985 puso 4KB, para dia de hoy es muy bajo. Vamos por 4KB. 

2. En cuantas paginas quedo dividida la memoria fisica? y la virtual?

Hago la cuenta y queda $2^{20}$ paginas.

3. Como reacciona su sistema para asignar y para liberar memoria?

> **Nota**: Esto generalmente lo hacen mal, cuando dividis varias paginas de 4KB, cada pagina tiene un offset(del bit 0 al 11) y un numero de pagina(del bit 12 al 31). Pues casa pagina internamente tiene de la direccion 000h a la FFFh.

Cuando hay tantas paginas, surge un problema de tener que indexar a todas. Hay maneras de agruparlos, de manera que si cambio 30 min paginas, solo basta con mencionar el grupo. Entonces partio a la mitad a los bits 12 a 31, donde la primer mitad tiene el indice de directorio y la segunda mitad tiene el indice de pagina(Esto para 32 bits). 
Sumando los dos indices de obtienen las $2^{20}$ entradas. Entonces Tomando los 10 bits mas significaticos correspondientes al directorio, se agregan dos 0 al final para tener 3 valores hexa y de ahi se lee.
Es virtual, no tengo manera de saber si es la misma memoria fisica, es indireccion. 

Luego cada tabla, tiene su P, entonces puede haber un directorio entero en disco o solo una pagina. Si se encuentra un P=0 cuando se esta intentando de leer, el CPU lanzara una excepcion. Notas que las tablas tambien estan en memoria y por convension de arqui: primero va la tabla de directorio y despues va TP1, TP2, ..., TPN. El directorio y cada tabla de pafina tendran 4K cada uno(una pagina) pues tienen $2^{10}$ direcciones de 4B cada una.

cada aplicacion tiene su propia tabla de directorio con sus respectivos permisos. Por ejemplo, si corro el chrome, el chrome va a tener su propio directorio y tablas de páginas de paginas fisicas. y si intenta de acceder a otra direccion de pagina que no es suya, se genera una excepsion. se pueden bajar a disco paginas de una aplicacion que no se esten usando en vez de la aplicacion completa. 

![Tabla de paginacion](graphics/table_paginacion.png)

> **Nota**: Cuando una pagina vuelve del disco **no** necesariamente va a ir a la misma direccion de memoria en la que estaba antes.

Si el procesador no es Intel, no esta garantizado que tenga una tabla de directorios.

### Paging y Swapping

Cuando se necesita una página en memoria y la memoria está completa, Linux elige un pagina que no ha sido accedida últimamente y realiza un “page out”, que consiste en guardar esa página en disco.
Generalmente en una zona especial destinada para ello, puede ser una particióno un archivo en el filesystem Luego setea en la página “vieja” el bit de Presencia en 0, y guarda en su índice la dirección donde la debe encontrar en el disco rígido.

A este concepto se lo denomina “pagging”. El concepto de swapping se refiere a guardar en disco TODAS las páginas de un proceso.

En Linux existe un thread llamado “kswapd” que se encarga de hacer estevtrabajo. Es interesante estudiar los algoritmos que se aplican para decidir qué página debe ser “bajada”, ya que si se decide mal, dicha página puede llegar a tener la próxima instrucción a ejecutar, o un dato que debe ser accedido.

## Memoria cache

El cache del CPU es un area de memoria rapida ubicada en el procesador. El Cache Inteligente Intel se refiere a la arquitectura que permite a todos los nucleos compartir dinamicamente el acceso al cache de alto nivel. 
Todo lo que hace el procesador primero pasa por cache, siempre. Entonces cada direccion que le pido a la RAM se va guardando en el ciclo, si siempre busco la siguiente instruccion no sirve mucho, pero si aparece un ciclo ya tengo las instrucciones anteriores en cache, entonces va mucho mas rapido. No es perfecto, pero tiene una eficiencia de 95%(puede pasarr que un programa termine no usar la memoria cache, pero casi siempre aparece un ciclo). 

![Evolucion de la memoria cache](graphics/evolution_cache.png)

Donde lo Li son distintos, L1 es mas chico, pero mas rapido(1ns), L2 es mas grande y un poco mas lento(4ns), etc. Se compone de:

- Memoria de datos
- Memoria de etiquetas
- Controlador(selecciona cuantos y cuales bytes  se copian  a la memoria de datos. Utiliza diferentes algoritmos).

### Mapeos

Se utilizan bloques(similar a las paginas), indexados por etiquetas, las cuales son la direccion de memoria RAM que estan representando, como son de a bloques generalmente no son todos los bits(similar a painacion).

- Mapeo Directo.
    - Un bloque de memoria solo se puede mapear a una única ranura en el cache.
    - Sencilla, poco utilizada
- Mapeo Asociativo.
    - Un bloque de memoria se puede mapear a cualquier ranura del cache.
    - Compleja, más utilizada.

### Politicas de sustitucion

Se actualiza la cache al haber fallo o ausencia de palabra buscada.

### Actualizacion de la RAM

En caso de necesitar escribir un dato, en vez de copiar todo el bloque de cache, se actualiza la cache y se deja la RAM desactualizada, hay varias maneras de marcar cuales partes de la RAM estan actualizadas, pero no nos interesa.

- Escritura inmediata (write through)
    - Se actualizan ambas memorias juntas
    - Más lento
    - Más económico
- Escritura obligada (write back)
    - Solo se actualiza lo estrictamente necesario
    - Más rápido
    - Menos económica
    - Problemas con dispositivos DMA
    - Problemas con Multi-cores



