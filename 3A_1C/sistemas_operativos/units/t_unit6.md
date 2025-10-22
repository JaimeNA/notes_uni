# Teorica 6: Memoria

Para cumplir con sus responsabilidades(Teorica 1) el S) realiza:

- Abstraccion
- Interposicion(ponerse entre dos procesos)

La manera mas simple de ambos es que no haya, que sean nulos.

## Problemas que pueden ocurrir

### Cada proceso accede directo a memoria fisica

1. **Colisiones:**

Primero que todo, van a tener colisiones, es muy dificil tener multiples procesos ejecutando simultaneamente,
porque cada uno decidiria que bloque de memoria van a usar(y no necesariamente coordinan, pero hay casos en los que si).

2. **Memoria insuficiente:**

Otro problema, es que puede haber programas que necesiten mas memoria que la que haya disponible.

**Ya sea diskettes o Overlay, quien se encarga de particionar los programas?**

El programador, es un trabajo muy importante. Hoy en dia, cuando un programa no entra en memoria lo 
resuelve el sistema operativo.

3. **Programas cargados parcialmente en memoria:**

Por ultimo, es dificil ejecutar programas que no estan completamente cargados en memoria.

4. **Cada proceso accede directamente a la mmisma libreria:**

Es muy dificil que se pongan de acuerdo, entonces conviene que cada uno tenga su propia copia. 
El linkeo estatico es meter todos los asrchgivos de la libreria en el mismo binario.

Hoy en dia se usa el linkeo dinamico, ocupando menos memoria RAM, **libc**(2-5MB) tiene una sola copia en memoria en todo momento, 
la cual es compartida por los programas. Haciendo que los programas ocupen menos memoria 
hasta dentro del disco pues no necesitan tener la libreria en el binario.
Es necesario que la libreria este en el sistema si esta linkeada dinamicamente, 
tambien tienen que tener la misma version para la cual se compilo el programa.
Una version puede tener un error que otra version no.

Una ventaja de linkeo estatico, no hay que averiguar donde esta es **muy importante** saber donde esta 
la libreria dinamica a la hora de ejecutar el programa.

Entonces, la libreria(dinamica) puede no estar o tener otra version en el sistema, y eso es un problema.

5. **Programa que maneja direcciones fisicas obsolutas:**

Es muy dificil que este tipo de programas funcione si no esta cargado exactamente donde esta. 
Para que funcione es necesario conocer a priori la organizacion de la memoria.

6. **Fragmentacion:**

Es muy dificil de imperdir que la memoria se fragmente, que cresca el heap significa que malloc hace la syscall para aumentar la memoria disponible.

Es muy dificil manejar esto si no existe un mecanimo para permitir que cresca dinamicamente. 

7. **Establecer permisos para diferentes areas de la memoria:**

Se relaciona con el problema del principio: Quien protege al SO? Estaria bueno que haya secciones que no se puedan tocar directamente.

Lectura y ejecucion en syscalls, hace falta estructuras en memoria que sean modificables por las syscalls, 
un proceso debe tener mapeado en su espacio de direcciones la ubicacion de estas estructuras 
para saber que permisos tienen y donde.

**Como se evita que el proceso no pueda modificar dichas estructuras si las syscalls deben tener permisos de escritura?**

Hay que ejecutar las syscalls de manera indirecta(ya que si son parte del proceso es un problema), 
hay que saber diferenciar entre llamas a funcion del programa y llama a funcion de kernel.

Con interrupciones, el handler es codigo de kernel y ahi esta todo bien. Y no se puede cambiar el RIP directamente 
porque no se puede hacer ya que no hay permisos de acceso a la zona de memoria del kernel. 
El hardware no lo permite.

> **Nota**: Todos y cada uno de los accesos a memoria son chequeados.

---

Ahora vamos a cambiar y como abstraccion vamos a tener memoria virtual, mientras que como interposicion, 
vamos a tener completa(con ayuda del hardware).
La idea central de memoria virtual es separar el espacio de direcciones virtuales(EDV) del fisico(EDF).

## Espacio de direcciones

Necesario para entender memoria virtual. Estamos poniendo una capa entre medio que realiza el mapeo de 
memoria.

## Como se implementa memoria virtual

El EDV se presenta a cada proceso como un bloque **contiguo** y **exclusivo** de memoria, similar 
a lo que ocurre en el quantum de CPU.

Mientras que espacio de direcciones fisica no es visible por los procesos, el SO centraliza la administracion 
del espacio fisico y se encarga de mapear la memoria virtual a la fisica. Esto se puede interpretar 
como una funcion matematica que ademas **verifica permisos**(lectura, escritura y ejecucion). 
Cada acceso a memoria es supervisado por la MMU, es lo que verifica que la interposicion 
sea completa. Cada direccion virtual que sale del procesador se traduce a una direccion fisica 
chequeando los permisos con la MMU.

> **Nota**: Todos y cada uno de los accesos a memoria deben ser auditados por el kernel.

## Como se resuelven los problemas

El SO evita colisiones simplemente no dando el mismo bloque de memoria a dos procesos distintos.


Por otro lado no mapea areas de memoria que no deben ser accedidas.

Una libreria en el EDF puede estar mapeada en multiples EDV.

El SO puede retringir los permisos de cada area de memoria mapeada.

Areas fragmentadas del EDF pueden ser mapeadas desde un area continua en el EDV al igual que 
areas que crecen dinamicamente.

Si no hay memoria suficiente usa memoria virtual, guardando en disco para hacer 
espacio. No es un problema tener un programa que use mas memoria de la que esta instalada.

Similarmente, todo el codigo del programa no tiene porque estar completamente cargado en memoria.

En conlusion, hasta ahora nunca tubimos que ocuparnos del pasaje de memoria virtual a 
fisica de manera que sea transparente para el proceso(similar a lo que hacia overlay).

> Cada procesos tienen sus propios espacios de direcciones(diferentes!), esto es pregunta de examen.

Es decir, cada proceso tiene una unica funcion propia(funcion en el sentido matematico).

---

Hasta ahora nunca hablamos de paginas, hasta podriamos haber estado hablando de una subdivision a nivel de bytes(aunque no sea conveniente). 
Esto se debe a que memoria virtual y paginacion son cosas totalmente distintas(que sualen ser mostradas en la misma teoria, pero podes tener cada uno por su cuenta).

De manera que memoria virtual soluciona colisiones, falta de memoria, librerias compartidas. Esas son problemas que soluciona, no necesariamente ventajas.

## Paginacion

Ahora comenzamos a hablar de granularidad con permisos y todo lo demas. Entonces, 
es agrupar bytes contiguos en bloques de igual tamano.

Todos los permisos van a ser a nivel de pagina, no podriamos hacer que parte de pagina si y parte de pagina no.

La pagina virtual esta en memoria virtual y el marco de pagina esta en memoria fisica(VP y PF respectivamente).

### Tabla de paginas

Es basicamente la definicion de la funcion de memoria por partes, donde cada parte es una pagina. Posee una entrada por cada VP, traduce de a paginas enteras.

La DV(seria dentro de la MMU) se divide en dos partes:

- **Bits mas significativos**: Numero de VP(indice en la tabla).
- **Bits menos significativos**: Offset dentro de la pagina.

En la entrada de la tabla hay varias flags que indican:

- PF number
- Present/absent(para cargar dinamicamente el codigo de un programa, indica si se guardo alguna vez en disco o nunca se cargo)
- Protection
- Modified
- Referenced(indica si se referencio en el pasado cercano, se usa para saber cual bajar a disco). 

Los bits se verifican en el momento que se produce(en especial el de proteccion).

> **Nota**: Hay **una** tabla de paginas por proceso.

### Page fault

Ocurrio alguna violacion de acceso, generalmente salta debido a estos bits:

- Present/absent
- Protection

La MMU lanza una excepcion antes de que se acceda a la memoria y le pasa el control al SO: 

- Si el PF no esta presente, se trae del disco(hard) o se mapea si ya esta en memoria(soft).
- Si se violan los permisos, se mata el proceso. A menos que sea un caso de COW.
- Si se accede a una VP que no esta mapeada directamente, se mata el proceso. A menos que hablemos de **asignacion lazy**(en este caso el kernel no lo cargo realmente y lo tiene que hacer en ese momento, es decir, no lo hace hasta que sea estrictamente necesario).

> Not in memory **USUALMENTE** significa que no esta en memoria. Caso contrario, significaria que esta en otro lugar(el kernel lo puede guardar como quiera, mismo con el estado de un proceso en un context switch, no necesariamente tiene que ir a disco o a memoria).

### No todo es color de rosa

Tenemos el dilema de como manejar la memoria libre, hay que usar un algoritmo para determinar donde colocar las cosas, que esta libre y que esta ocupado. 
Esto ya lo tubimos que solucionar en el TP02, hay dos enfoques: 

- **Bitmap**: se divide la memoria en bloques y cada bit representa su estado.
- **Free list**: cada proceso y espacio libre tiene un nodo con inicio, longitud y el puntero al siguiente.

Pero, como se busca el bloque libre? Hay varias opciones:

- First fit(Comienza desde el principio)
- Next fit(Comienza donde quedamos)
- Best fit(Bloque que mejor se adapta)
- Worst fit(Buscar bloque mas grande)
- Quick fit(4 free lists de bloques de 4KB, 8KB, 12KB y 16KB. Elije el primer bloque de la lista correspondiente)

Porque worst first tiene sentido? Porque best fit generalmente deja un pedazo muy chico de memoria sin usar(generalmente no se encuentra exactamiente el bloque requerido). Eso es muy malo y causa mucha fragmentacion, worst fit no hace eso y deja bloques libres razonables. De manera de worst fit es mejor que best fit.

El problema de quick fit es que va a haber block sizes mas demandados que otros, entonces se van a acabar mas rapido mientras los otros sobran.

Por otro lado, la traduccion tiene un costo(por mas que es a nivel de hardware). 
Cada acceso a memoria debe traducirse.

Tercer punto, si la EDV es grande, la tabla de paginas sera grande.

Finalmente, con que criterio se determina que pagina se baja a disco.


