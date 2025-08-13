# Practica 1: POSIX

## Entorno de desarrollo

Trucos de bash:

- `ctrl + r` para buscar historial
- `!!` expande con el ultimo comando (`$sudo !!`)
- `file.{jpg, png}` expande a `file.jpg file.png`
- `$(...)` da el output de un comando (`touch file-$(date -I)`)
- `ctrl + a` para ir al comienzo de la linea
- `ctrl + e` para ir al final de la linea
- `cd -` vuelve al dir que estabas antes
- `fc` fix command, abre el ultimo comando en un editor y corre la version editada
- `type` indica de si algo es builtin, program, o alias (`type ping`)

En bin/ estan todos los comandos de administracion, para configurar el sistema. Por otro lado, en bin/ estan los comandos de uso general.

## Crear procesos

Se pueden crear utilizando `fork`, syscall que la llamo una vez y vuelve dos veces, el proceso original y el que se creo.

Los dos procesos son identicos salvo el valor de retorno de `fork`. Si `fork` no puede crear un proceso(raro, pero puede pasar), devuelve -1.

![Pipeline fork](graphics/fork.png)

Como cada proceso puede crear varios procesos, pero tiene un solo padre, si quiero saber cual es el `pid` del padre es necesario que el padre sepa cual es el `pid` del hijo. Esto se logra devolviendo el `pid` del hijo en el padre.

Es muy importante la llamada a `wait` porque sino queda un monton de procesos **undefined**, consume entradas en la tabla de procesos. Todas las variables que tenga un proceso original, incluyendo los punteros, el hijo los hereda. Es importante por ese motivo cerrar todos los **file descriptor**.  

![File descriptors fork](graphics/file_descriptors.png)

## `ps` 

Muy recomendado que lo aprendamos a usar bien para debuggear los TP.
Se corre generalmente con `aux` pued indica: a+x -> mostrar todos los procesos, u -> incluir la columna con informacion del usuario de cada programa. Se puede combinar con otros arg(todo junto):

- Muestra los procesos que estan corriendo en el momento. Usando `w` como argumento(wide) muestra los argumentos que se utilizaron para correr cada programa.  
- Si se corre con `e` va a mostrar las variables de entorno.
- Si se corre con `f` se mostrara un arbol de los procesos con caracteres ASCII

> **Nota**: Hay mas arg en la presentacion de campus.

## Copy on write

Los procesos clones usan la misma memoria que el padre(heap, stack, memory maps), copiar esa memoria cada vez que usamos fork es lento y ocupa memoria. 
Entonces, linux permite que ambos procesos utilicen la misma memoria fisica y solo copia la memoria cuando alguno de los procesos intenta escribir. 
Linux implementa esta dandole la misma **page table** a cada programa, pero marcadas como **read only**. Entonces, cuando alguno intenta escribir ocurre una **page fault**.

![Representacion de copy on write](graphics/cow.png)

## File management

Todo lo que es I/O es universal en Unix, se maneja como si fuera un archivo.  Es muy eficiente el read write, lo unico que cambia entre tipos como lo accedo.
Para trabajar con archivos como humanos les asignamos nombres, pero las computadoras prefieren tratar con numeros. Por lo tanto, cuando trabajamos con bajo nivel utilizamos 
**file descriptors** los cuales indican la referencia a un archivo **abierto**. 

Una vez con el fd se puede leer y escribir, el fd 0 es la salida estandar. Comandos utiles:

- `head`, primeras lineas de un archivo
- `tail`, ultimas lineas de un archivo
- `stat`, muestra metadata del archivo
- `lseek`, corre el puntero a otra posicion

Una vez termino de trabajar con un fd, se libera y el numero queda disponible para cuando abra algun otro archivo. Cuando abro un archivo, usa el fd mas chico disponible. 
Cuando se lee de disco es muy importante que lo que uno lea o escriba este alineado con el **tamano del bloque** para evitar fragmentacion, no es obligatorio, pero es una buena practica.

Un archivo tiene un formato predefinido? No, para el sistema operativo no importa, hay metadata guardada en disco para saber su ubicacion o permisos, pero el archivo en si son bytes para el SO.
Es problema de la aplicacion saber que formato tiene, si tubieran formatos para el SO seria demasiado complejo e innecesario.

> **Nota**: Cada proceso tiene su tabla con el mapeo de fd a nombre, es un error muy comun tener una tabla global para todos los procesos(esta mal eso). Todo lo que comentan es **local** al proceso. Si dos procesos tienen abierto el mismo archivo el fd no tienen porque coicidir.

## `pipe`

Es un canal donde se envia todo lo que saca un proceso se lo manda a otro, es unidireccional y es un metodo de comunicacion. Es el mas sencillo que hay. 
Es un buffer compartido, donde un proceso escribe y otro lee, una cola. No hay posibilidad de reabrir un pipe, pues es un objeto anonimo(no tiene nombre).

En caso de que el programa que lee termine y el otro siga escribiendo se lanzara una senal de error nivel sistema operativo. No es comportamiento normal, por lo tanto se lanza un error.
Detecta que el otro programa dejo de leer pues desaparece el fd. Pero que pasa si deja de escribir el programa, que pasara con el que lee? 
Se vacia el pipe y cuando en ultimo read devuelva 0, entonces se cierra el pipe.

Otro caso, el proceso que lee, lee despacio, entonces, se llena el buffer. Cuando la otra parte quiera escribir y el SO ve que el buffer esta lleno, 
bloquea la parte que escribe hasta que se pueda ejecutar write. No da error, pues esto es una situacion normal.

### dup

El manejo del pipe se realiza de la siguiente manera:

1. Corro pipe en shell. Se llama syscall `pipe`
2. `pipe` devuelve a la shell fd escritura y fd lectura
3. Se hace un fork, de manera que los hijos(las dos shell) tienen los mismo fd que el padre(pues se hacen copias identicas).
4. La shell cierra los dos fd, pero siguen abiertos para los hijos. Se separa del pipe.
5. Se elimina el fd de lectura de un hijo y el de escritura para el otro. Quedando el diagrama de pipe convencional.
6. Luego para el proceso 1 se configura el stdout como el pipe y para el proceso 2 es el stdin. Duplicando los fd, cerrando stdout y stdin para los proceso.
7. Una vez duplicados a std y stdin, se cierran los originales.
8. Se hace el exec en las shell.

Aca child 1 y child 2 son una shell, al final del todo se hace el exec y se ejecutan los programas. Esta es una de las razones por la cual stderr esta separado de stdout.

> **Nota**: Para ver la representacion grafica ver apuntes. Puede haber dos fd, uno duplicado, abiertos, pero es mala practica.


