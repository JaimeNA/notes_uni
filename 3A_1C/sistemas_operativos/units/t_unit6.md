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
