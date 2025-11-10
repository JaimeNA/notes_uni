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

## Analisis de costos

Hay que tener dos cosas en cuenta:

- La traduccion de DV a DF debe ser rapida.
- Si el EDV es grande, la tabla de paginas sera grande.

Habria que hacer dos lecturas a memoria por cada lectura, es decir, hay una lectura extra la cual 
involucra buscar la traduccion de la direccion virtual a la direccion fisica en la tabla de 
paginas.

Con DV de 32 bits tendriamos una tabla de paginas de 4MB, esto es mucho. Entonces hay que buscar un 
punto medio, que no este totalmente en memoria y que no este totalmente en un registro.

## TLB

Esto se soluciona con una **Translation Lookaside Buffer**, pero primero, que ocurre?

- Los procesos hacen muchas referencias a pocas paginas.
- Los procesos hacen pocas referencias a muchas paginas.

Debido a que el codigo se encuentra contiguo, lo mismo ocurre con el stack, termina siendo verdadera 
la primera opcion. 

Es mucho mas chica, ya que un proceso accede a un conjunto chico de paginas, asi que solo tiene 
256 entradas. Es muy parecido a la tabla de paginas. El primer acceso a una pagina va a terminar 
funcionando como cuando teniamos antes, con dos accesos a memoria. Sin embargo, el segundo acceso 
ya va a ser con la TLB, la cual tendra una entrada generada por la primera vez. Si se queda sin 
espacio, habra que descartar alguna entrada.

Cuando viene un pedido, hay un hardware especial que permite comparar todas las entradas de la 
TLB de manera simultanea. Chequea permisos y devuelve la direccion fisica.

> Es exactamente el trabajo que hace la tabla de paginas.

Una entrada de la TLB desalojada no hay que pisarla simplemente, sino que hay que actualizar la 
tabla de paginas pues podria haber cambiado el bit **modified** o el **page frame**(notar igual 
que el bit de presencia no esta en la TLB).

(Investigar mejor, ver clase alrededor de 19:15, porque se modifica el page frame).

De todas maneras, todavia no solucionamos el problema de una EDV muy grande, hay dos enfoques:

- Tabla de paginas multinivel
- Tabla de paginas invertidas

### Tabla de paginas multinivel

Evita tener toda la tabla en memoria, solo tenemos lo necesario. Basicamente es lo mismo que 
el directorio de tablas de paginas visto en arqui.

En el nivel principal(PT1) tengo 2^10 entradas, luego en el PT2 otras 2^10 y finalmente el offset 
de 2^12 posibles valores.

Cuanto ocupa cada tablita? 2^10 * 4B, oh casualidad, entra exactamente en una pagina. Esto 
no es casualidad, sino que fue pensado deliberadamente. Entonces, no tengo todas las entradas, 
puede empezar con cuatro tablas y listo. En caso de necesitar una pagina que no este, vamos a 
tenes que reservar una nueva pagina para la nueva tabla, pero sigue siendo mucho menos espacio.

Esto fue usado por Intel, lo que conocemos como page directory - page table, tambien conocido como 
esquema 2^10 2^10 2^12. Diez anos despues se extienden las entradas a 64 bits con el Pentium Pro, 
entonces se uso un esquema 2^9 2^9 2^2 2^12, basicamente para que entre en una pagina.

Sin embargo, ahora que se incorporo el soporte completo a 64 bits se agrega un cuarto nivel con 
un esquema 2^9 2^9 2^9 2^9 2^12 = 2^45. Page Map Level 4.

A partir de 2017, se incorpora un quinto nivel de paginacion en algunos procesadores llamado PLM5
(Page Map Level 5). Con esquema 2^9 2^9 2^9 2^9 2^9 2^12 = 2^52.

### Tabla de paginas invertidas

Hasta ahora las tablas que vimos tenian una entrada por cada pagina, pero ahora tenemos una entrada 
por cada PF en lugar de cada VP. Ahora hay que buscar toda la pagina hasta encontrar la VP, 
para solucionar esta busqueda se usa una tabla hasheada. 

La TLB sigue sirviendo, una vez tenemos la informacion se puede guardar en la TLB. Sin embargo, 
una TLB miss requerira recorrer toda la tabla para encontrar la VP. Si no se encuentra una VP, 
entonces es similar a un Page Fault y se debera buscar un lugar libre y actualizar la tabla.

## Algoritmo de reemplazo de paginas

Con memoria virtual(no paginacion) podemos darnos el lujo de no tener todo el proceso en memoria 
para que funcione y cuando hace falto un PF que no esta presente se trae del disco a memoria.
Ahora, que PF vamos a desalojar?

### Optimo

Es facil de describir, pero imposible de implementar(no se puede predecir el futuro).
Si se pudiera clasificar las paginas de acuerdo a cuando van a volver a ser referenciadas en el 
futuro, entonces se va a desalojar a que que tardara mas en ser usado. Se quiere postergar traer 
una pagina del disco tanto como sea posible.

Entonces, para que se menciona si es imposible? Porque se puede usar para saber que tan cerca 
se esta del optimo, al ser el optimo tenemos una cota superior de lo mejor que podria ser un 
algoritmo.

Pero, como se puede medir que tan optimo es un algoritmo? Podemos evaluar el optimo haciendo un 
poco de trampa: Hacer una ejecucion en condiciones controlares y medir cuando se accede a 
cada pagina. Luego repetir la ejecucion, pero usando la informacion obtenida para saber que 
pagina hay que desalojar. Una manera de evaluar esto seria midiendo el overhead o cantidad de 
accesos a discos.

### Not recently used(NRU)

Usa los bits R(referenciado) y M(modificado) para tomar las decisiones de reemplazo. 
El bit R se resetea periodicamente, pues sino no sirviria mucho y resetearlo permite saber 
si se referencio hace poco. Pero no se resetea M ya que se usa para otra cosa, si no se modifico 
entonces no hace falta actualizar su version en disco y se evita el costo.

En funcion a los dos bits tenemos cuatro clases diferentes:

- **Clase 0**: ~R & ~M
- **Clase 1**: ~R & M
- **Clase 2**: R & ~M
- **Clase 3**: R & M

De manera que se elije dependiendo de la clase, prefiriendo la clase 0 antes que la 1, etc.

### First-in first-out(FIFO)

Simplemente se registra el tiempo en el cual cada pagina fue cargada en memoria y de desaloja 
la que mas tiempo estuvo en memoria.

Pero bueno, que sea la primera no implica que sea la menos usada. Cuando se quita la mas antigua, 
puede que se vuelva a usar y pasa a ser la mas nueva.

### Second Change(SC)

Modifica el FIFO y le agrega el bit R. Se sigue usando reseteando en R periodicamente y si 
la ultima pagina tiene el bit en 1, se la modifica y se la manda al comienzo de la fila sin 
tener que bajarla a disco. Si todas fueron referenciadas, vuelve a ser FIFO comun.

### Clock(C)

....


### Least recently used(LRU)

Las paginas muy usadas recientemente suelen seguir siendo muy usadas, mientras que las 
escasamente usadas recientemente suelen seguir sin ser muy usadas.

Desaloja la pagina que no a sido usada el mayor tiempo, no es mas que una estimacion de lo que 
ocurrira en el futuro. Tiene dos enfoques:

- Tener una lista de paginas ordenada por tiempo de acceso. Desalojar a la mas antigua.
- Tener un registro especial que cuente instrucciones y equipar a cada entrada de la tabla con 
espacio para este registro. Cuando se referencia una pagina se actualiza su contador. Desalojar 
a la que tenga el contador mas chico.

### Not frequently used(NFU)

Se requiere de un contador por software para cada pagina y periodicamente se suma el bit R de 
cada pagina a su contador. Entonces, se desaloja la pagina con el contador mas chico. Durante 
toda la vida del proceso se mantiene el contador, asi que nunca se va a desalojar la pagina 
incluso si el proceso dejo de usarla. Hay que esperar hasta que otras paginas le ganen.

### Simulacion LRU

Si no se referencia en 4 ticks entonces sera 00001XXX. El LRU sera mas detallado, tiene todo el 
historial. De manera que el pasado lejano se conserva. Con LRU se tiene todo el historial, pero 
en la simulacion sera de los ultimos 8 ticks, evitando guardar tanta informacion.

Esto funciona, pues el comportamiento reciente de una pagina es muy buen estimador de lo que 
va a ocurrir en un futuro cercano. Entonces, no es tan granular, pero es mas realizable.

Se dice simular pues se trata de simular el contador de instrucciones mediante software, no es un 
registro sino que lo tenemos nosotros en memoria.

**Como se podria llamar esta tecnica?** Se podria decir que el valor envejece, de manera que 
es una forma de aging.

## Algunos conceptos

### Demand paging

Basicamente, no subir todas las paginas de una para un proceso. Sino que traerlas a demanda.

### Locality of reference

Ya lo venimos mencionando, un proceso puede hacer referencia a un grupo reducido de paginas 
o hacer referencia a paginas distribuidas por todos lados. Tenemos un conjunto de paginas muy 
usado y otro conjunto que practicamente no se usan. Hay mas probabilidad a que haya referencias 
cercanas a que sea totalmente random.

### Working set

Conjunto de paginas siendo usado actualmente.

### Trashing

Con todo el working set en memoria, es una situacion ideal. Sin embargo, si no entra todo el 
working set vamos a tener que ir y venir del disco constantemente. Esto es malo y estariamos usando 
al disco como "memoria". Este escenario se denomina **trashing**. No hay nada que hacer, 
por mas optimo que sea el algoritmo de reemplazo, el working set no entra en la memoria.

### Working set model - prepaging

En sistemas multiprogramados, se prepara la memoria para que quede la memoria exclusiva para el 
proceso actual. Se bajan todas las paginas de un proceso a disco y se cargan las de otro 
proceso a memoria en un context switch. 

Esto es muy costoso, asi que teniendo la idea de un working set, se podria observar el working 
set de un proceso y levantar dichas paginas antes de que sean accedidas. 

## Seguimos con algoritmos

### Working set

El working set se un proceso tiene un comportamiento asintotico y llega a un punto en el cual 
tiene un size constante. De manera que la funcion w(k, t) determina el size de un working 
set para un tiempo t basado en las ultimas k referencias a memoria.

> Podemos decir que un proceso no tendra un WS de 1000 paginas basado en el principio de 
**locality of reference**.

Ariel difiere del libro, dice que w(k, t) es el working set y en el libro lo muestran como 
el size del working set.

Una vez se determina k, se obtiene una cota superior. Una vez armada una lista de working set, 
se puede volver a ver que pagina no fue referenciada y sacarla en tal caso.

Al igual que LRU, la idea es muy buena, pero la implementacion no es tan simple. 
De manera que se utilizan aproximaciones para que no sea tan costoso, una aproximacion consiste 
en reemplazar referencias a memoria por unidades de tiempo, entonces, en lugar de actualizar la 
lista en cada referencia se guarda el timestamp de la ultima referencia. 

Entonces, se usa el bit R(reseteandolo periodicamente), de manera que si la pagina no 
fue referenciada y su ultimo timestamp es mayor a t, es candidato ideal para reemplazar.
Esto es analogo a reemplazarlo despues de 10 millones de referencias, pero de una manera 
menos costosa.

### Working set clock

Recorrer toda la tabla en cada page fault hace ineficiente al algoritmo WS, entonces WSC 
combina clock y WS, en apuntes se pueden ver los pasos. Este algoritmo es muy utilizado en 
la practiva por su simplicidad y eficiencia.

Un parametro de este algoritmo puede ser el size de la lista circular para evitar recorrer muchas 
paginas. Esta bueno discutir con que criterio se va a mirar para determinar cual desalojar, 
se puede tener una politica local o una global(desalojar de otro proceso 
o del mismo proceso que se esta ejecutando).

> Tendria que revisarlo, pero creo que la global era la mas optima. Tiene mas sentido.



