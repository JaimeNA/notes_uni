# Teorica 7: File systems

Hay que resolver 3 problemas:

- Almacenar mucha informacion, que quizas no entra en el EDV
- Persistencia luego de la muerte del proceso
- Multiples procesos accediendo a la misma informacion

Tambien nos justaria que un usuario no pueda acceder a los archivos de otro usuario, 
entonces, por si solo el disco soporta solo dos operaciones:

- Leer bloque k
- Escribir bloque k

La soluciones a estos problemas son resueltas por el file system del SO.
Si uno no usa el file system de manera adecuada, se puede llegar a una situacion peligrosa.

Volviendo al proceso de abstraccion, los archivos son una abstraccion del disco, 
se pueden ver como un espacio de direcciones.

Las responsablilidades del file system seran como los archivos seran:

- Estructurado
- Nombrados
- Accedidos
- Usados
- Protegidos
- Implementados(como se almacenan en disco)
- Administrados

## Formas de almacenar la informacion

### Asignacion continua

Por ejemplo, con bloques de 1KB y un archivo de 50KB, usamos 50 blockes, aca no tenemos 
fragmentacion interna, pero con bloques de 3KB si.

Entonces esta implementacion tiene ventajas:

- Facil de implementar(solo inicio y longitud)
- Exelente rendimiento de disco(secuencial y random)

**Porque es secuencial y random?** 

Es random pues si se quiere acceder al bloque 5, simplemente voy a 5KB si es que cada bloque es de 
1KB(los bloques son todos iguales).

Tambien tiene desventajas:

- Fragmentacion externa
- Conocer size a priori(ejemplo, con un editor de texto esto es mas complicado)

Esta implementacion puede no ser util en sistemas operativos para OSs de consumidor, pero 
si tiene su utilidad en casos como en los CDs.

### Asignacion con listas enlazadas(en disco)

En este caso la primer palabra de cada bloque es un puntero al siguiente bloque, entonces 
solo necesitamos un puntero al primer bloque.

Que ocurre con la fragmentacion? Para la fragmentacion interna el peor caso es tener un pedazo de 
bloque sobrante(solo en el ultimo bloque). Por otro lado, la fragmentacion externa queda solucionada 
pues no necesariamente necesito una zona contigua.

Para el acceso, el secuencial no tiene problema, saltando de bloque en bloque. Pero el acceso 
random es muy molesto, hay que recorrer el archivo(requiere recorrer las otras direcciones).

Cuando programamos, nos damos cuenta que manejar los size con potencias de dos es mucho mejor, 
queda todo mas alineado con el funcionamiento interno del sistema.
El problema aparece con el puntero, si los bloques tienen size de potencia de dos, la informacion 
no estara en una potencia de dos por el puntero. Tirando todo lo que intento el programador 
a la basura.

### Asignacion con listas enlazadas(en memoria)

Idem a su contraparte en disco, pero la tabla con los punteros van en memoria, solucionando el 
problema de la potencia de 2. Pero surgte otro problema, al tener la tabla en memoria, si 
tenemos un disco muy grande esto nos ocupara una gran cantidad de memoria.

**La tabla es por procesos o global?** Es global, una sola tabla para todos, esto es porque disco 
hay uno solo.

### i-nodes(index-node)

Pasa a ser un sistema mas a *bajo demanda*, no hace falta tener toda la tabla en memoria.

Cada archivo tiene un i-node que contiene atributos y la lista de bloques. Solo esta en 
memoria si el archivo esta abierto. Entonces, se reserva la ultima posicion para un bloque 
con mas direcciones... Para que?

## Implementaciones reales

Ahora se va a explicar como funciona en Unix/Linux.

El disk layout tiene un boot block, un super-block, los inode blocks y por ultimo los data blocks.

### Super-block

El **super-block** es la estructura de datos mas critica, si se corrompe el file system se perdio.
Almacena toda la metadata del file system:

- Umount status
- Corrupcion presente
- Estado del montaje
- Free inodes
- Espacio total
- Espacio libre
- Free block list
- Y mas...

> Si se desmonto limpio se setea un flag, de manera que es facil detectar si podria haber errores 
y el SO lo verifica.

### inode

Un **inode** tiene:

- Mode, permisos(9 bits) y algunos mas pues me sobra espacio(si es directorio, archivo comun, etc.)  
- uid
- gid
- Link count
- atime, ultimo acceso(esta desactivado por default pues afecta performance)
- ctime
- mtime, ultima modificacion
- size
- block count
- data blocks
- reference count
- direct blocks(10)
- single indirect
- double indirect
- triple indirect

**Si se cambia el owner de un archivo, se puede saber quien lo creo?** 
No, no se puede pues no se guarda en el inode.

**Donde esta el nombre del archivo?** 
La computadora no usa los nombres, prefiere ver todo en numeros. Entonces, el directorio 
es lo que mapea los numeros identificadores con el nombre. Donde vemos los nombres? 
Cuando listamos el directorio.

### Data blocks

Pueden ser directos o indirectos.

**Si no tengo permisos de escritura, puedo borrar un archivo?**
Si, pues no implica modificarlo.

## Manejo de consistencia

Las escrituras van al buffer cache/cache unificado. Cuando estamos modificando un archivo, no se 
guarda directamente en disco, sino que esto lo hace mas adelante. Esto introduce un problema, 
si se corta la luz se pierde todo lo que estaba en el buffer, quedando el file system en un estado 
inconsistente y el sistema tendra que hacer un file system check(esto solia tomar mucho tiempo).

Para solucionar este problema:

- Sync writes data y metadata(performance es horrible)
- Sync write metadata
- Soft updates(ordena las actualizaciones para que las relacionadas se escriban juntas de manera 
atomica)
- Journaling(como la de linux, se escribe en el journal lo que hay que hacer y se va completando, 
si falla se hace un rollback).

## Problemas de implementacion clasica

Los problemas son:

- Solo hay una copia del superblock
- La tabla de inodos esta al comienzo del disco
- No hay politica de asignacion de inodos

Hubo varias mejoras para solucionarlos.

### BSD FFS

Divide el disco en zonas logicas llamadas grupos de cilindros para minimizar el seek(caro).
Trata de alocar los inodos de los archivos de un mismo directorio en el mismo grupo de cilindros.
Aumenta el block size. 

### ext4

Agrega optimizacions para adecuerlo a hardware moderno:

- 64ZB de size de FS, 16TB de size de archivo
- Cantidad ilimitada de subdirectorios

Reemplaza bloques por extents(mas grandes que los bloques), agrega nuevos modos de journaling 
y delayed allocation(si pedis 5 bloques, no te los da hasta que los uses para buscar la forma 
mas adecuada de alocarlos en disco lo mas tarde posible).

## Problemas de FS tradicionales

- Susceptibles a silent data corruption
- Requieren chequeo si se apago anormalmente
- Asignacion de espacio estatica
- Requiere operaciones R-M-W

## Otra manera de hacerlo(ZFS)

Se propuso otra manera de hacer todo el FS para evitar seguir aumentando la complejidad.

Un FS tradicional tiene:

- Virtual disk como abstraccion
- Una particion/volumen por FS
- Expansion manual
- Cada fs tiene BW limitado
- Almacenamiento fragmentado

Mientras que ZFS:

- malloc/free como abtraccion
- Sin particiones
- Expansion automatica
- Cada FS tiene todo el BW
- Almancenamiento compartido

Basicamente es un FS contruido como un manejador de memoria.

### Modelo de integridad de datos

- **Todo es COW**: No se sobreescriben datos(estado de disco siempre valido)
- **Todo es transaccional**: Cambios relacionados ocurren o fallan como un todo, no hace falta 
journal.
- **Todo tiene checksum**: No hay silent data corruption


### COW transactions

1. Initial block tree
2. COW some blocks
3. COW indirect blocks
4. Reqrite uberblock(atomic)


