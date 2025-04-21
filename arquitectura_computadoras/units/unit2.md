# Unidad  2: Componentes de hardware

Vamos a analizar como se comunica el procesador con el resto del hardware. Para representar 0s y 1s en un cable de cobre hay varias maneras, una manera es voltaje o no voltaje, otra seria +3,3V o -3,3V.
El procesador se comunicara de esa manera, hay dos maneras:

- **Transmision serie**: Va todo por el mismo tiempo, cada bit ocupa un paso.
- **Transmision paralela**: Si quiero transmitir 8 bits en un solo paso, es la manera mas rapida y la que se usa en la computacion, se conoce como un **bus**.

En las computadores y circuitos chicos tenemos los bus, pero en las redes que conectan al mundo y usan miles de kilometros de cable se utiliza la transmision en serie a pesar de ser mas lente(debido al costo principalmente).

Hubo dos propuestas principales para como estructurar la conexino del CPU con memomria:

- **Von Neumann**: Propone un solo bus entre la memoria(codigo y datos) y el CPU
- **Harvard**: Propone dos buses, uno a la memoria con los datos y otro para la memoria(otra memoria) con el codigo. De esa manera de paraleliza la lectura.

Sin embargo, hoy en dia se utiliza Von Neumann a pesar de ser mas lento debido al costo y complejidad reducida.

## Sistema de entrada y salida

Tenemos dos buses principales, un bus de **direcciones**(address)(A0 A1 ... A15) y otro de **datos**(D0 ... D7). Estan todos los perifericos conectados a los mismos cables, para que no se crucen y se comuniquen todos de manera ordenada se utiliza el bus de direcciones, este se encarga de apuntar a la direccion de memoria que quiero escribir o leer.
Para saber si ecribir o leer, hay una linea extra **r/w** que con `1` indica `read` y con un `0` indica `write`. Los primeros procesadores tenian 8 y 8 bits para cada bus, pero se fue agrandando a medida que fue aumentando el tamano de los registros, hoy en dia son de 64 bits cada uno. 

Sin embargo, hay que tener cuidado, si llenamos el bus de direcciones con direcciones de memoria, el CPU no va a poder comunicarse con otra cosa que no sea la memoria(otros perifericos, por ejemplo). Entonces, hay que ser conciente de como se utiliza el bus de memoria.

> **Nota**: Para que algo sea de 64 bits todo debe ser de 64, bus de datos, registros, etc.

## CPU

Presenta la unidad de control, que se encarga de recuperar intrucciones de memoria, decodificarlas y escribir en memoria, y la unidad de ejecucion, la cual lleva a cabo la ejecucion de la intruccion. Ademas, tiene los registros y los flags. Que pasa cuando se enciende un procesador? Cual es la primer instruccion ejecutada? La primer instruccion sera la que este en la direccion que apunte el IP, pero en memoria no puede estar porque es volatil. Lo que ocurre es que primero lee instrucciones de la ROM, mejor conocido como la BIOS.

## Mapa de memoria

Todas las direcciones que puede acceeder el CPU, no puede ver mas alla de lo que permite su bus de direcciones. Es decir, si tengo un bus de direcciones de 16 bits, tonces solo tengo 64K de memoria disponible. 

Los CPU de Intel hacen algo diferente al expandir a 32 bits(por ejemplo), si de la nada empieza a traer 4 bytes en vez de 1 se rompe todo el codigo viejo. Por lo tanto, para mantener retrocompatibilidad el cpu busca 8 bits a pesar de que el bus es de 32 o 64 bits. Entonces, en el bus de 8 bytes, de una direccion se traen los 8 bytes, pero cada uno tiene una direccion consecutiva.

Luego, los mapas de meoria de intel siempre van a tener un byte de ancho, independientemente del bus.

![Mapa de memoria](graphics/memory_map.png)

> **Nota**: K en informatica es una constante que se conoce como 2^10=1024, M es 2^20.
 
## Clasificacion de memoria

Hay dos grandes grupos de memoria, RAM y ROMM

### ROM(Read Only Memory)

Primer tipo de memoria, sean PROM, EPROM(esta se puede borrar), Flash o EEPROM. Mantinenen la informacion sin energia, no volatil, sin embargo la escritura es mas lenta que la RAM.
En la PC, se encuentra la BIOS en la ROM, este sera el primer codigo que ejecutee el CPU. Sin embargo, no dura para siempre pues usa capacitores, similar a la DRAM, pero tienen una gran resistencia, por lo tanto eventualmente se va a perder.
Suelen durar alrededor de 10 anios. Si lo escribis se reinicia el ciclo, pero si solo lo lees se sigue descargando.

### RAM(Random Access Memory)

La mas rapida, existe DRAM(Dinamic RAM) y SRAM(Static RAM), pierde la informacion sin energia.

- **DRAM**: Necesita refresco de valores cada *n* milisegundos, menos compleja, mas economica y mas lenta. Por lo general es la que se tiene en las computadoras. Una **latencia** de entre 50 y 150 ns.
- **SRAM**: No necesita refresco, mas compleja, mas cara y mas rapida. Se suele utilizar para cache. Con una latencia(tiempo que tarda en devolver un dato) de menos de 10 ns.
- **SDRAM**

> **Nota**: A diferencia de la latencia, la **transferencia** es lo que tarda en viajar la informacion de un lugar a otro.

## Decodificacion

Para conectar mas de un dispositivo al procesador, tenemos que hacer "decodificacion". Para hacer esto necesitamos herramientas electronicas:

- Compuertas
- Decodificadores

### Compuertas

Los circuitos integrados vienen con mas de una, se pueden usar simultaneamente pues son independientes. Sin embargo, hoy en dia se utilizan compuertas en vez de decodificadores.

1. NOT
2. OR
3. AND
4. NAND, es como tener una compuerta AND seguida de una NOT
5. NOR
6. XOR

![Gates logicas parte 1](graphics/gates.png)
![Gates logicas parte 2](graphics/gates1.png)

> **NOTA**: El buffer es muy de bajo nivel y no lo vamos a ver, pero basicamente si tenes 4.8V lo pasa a 5V para que llegue a ser un 1.

### Decodificadores

La idea es que cada periferico se encienda cuando el procesador intente de acceder a las direcciones de memoria a la que estan mapeados.

![Buses de memorias](graphics/buses.png)

Del bus de direcciones van a salir cables al **circuito decodificador**, entonces cuando quiera acceder a la RAM, el circuito decodificador enviara una senal para activar la RAM, se conoce como CS(Chip Select). Entonces, cuando se cumplen las direcciones que corresponded a la RAM, se enviara un 1 por CS.

Ningun periferico esta construido para ir en una direccion de memoria, solo pueden ver su propia memoria interna. 
Hay dos sistemas que vamos a ver:

- **Sistema 1**: Un procesador y una ROM, la ROM ocupa toda la memoria. Entonces, deja el CS en 1 siempre.
- **Sistema 2**: Varios perifericos a la vez.

Los decodificadores tienen una tabla de verdad donde una salida siempre es 1. Entonces, por ejemplo, existe un decodificador que pasa 3 inputs a 8 outputs mediante una tabla de verdad. Se prende el output 4 si el numero en binario de los 3 inputs es 4, eso es basicamente lo que hace la tabla. Muy importante, solo una esta prendida a la vez, el resto se mantiene apagadas. 



**Ejercicio**: Se dispone de un microprocesador con 16 lineas de bus de direcciones y 8 lineas de bus de datos. 
Se desea conectar el procesador con dos integrados de ROM de 32k x 8. Aclaracion: Siempre esta el bus de control aunque no se lo mencione(sino no funcionaria).

Analizando, con 16 bits de direcciones, tenemos 2^16 direcciones, entonces son 2^6 * 2^10 y eso significa que son 64K de direcciones. Como es como cada direccino son 8 bits, seran hasta 64KB de memoria. 

Entonces, cuando la direccion que envie el procesador tenga el A_15 = 0, entonces el chip select de la primer ROM sera 1. Caso contrario, el chip select de la otra ROM sera 1. 
Notar que generalmente el circuito codificador se termina de definir al final del analisis.


> **NOTA**: La suma de todas las patitas que van al dispositivo y las que van al decodificador deben ser igual a la suma de patitas que tiene el bus de address. Sirve para verificar que hayas resuelto bien los ejercicios.

Los decodificadoes se pueden reutilizar solo si el tamano de los bit significativos siguen siendo los mismo, sino va a ver que usar otro. (Santi recomienda ver dark mirror). 

**Ejercicio**: Si tengo un sensor de humedad y temperatura, este tendra una patita read, otra CS, 8 para el BD y una para la direccion(o temp o humedad). Por lo tanto necesito un decodificador que tome todas las direcciones y para una direccion prenda el CS del sensor. 

### Pin IO/M

En los primeros procesadores, Intel necesitaba poder acceder a mas perifericos sin tener que modificar los registros. Entonces, se agrego al CPU un pin llamado IO/M(Input/Output memory(en realidad la barra deberia ir arriba de la M para indigar que esta negada)), de manera que permitio tener dos mapas de memoria, uno para la memoria y otro para los perifericos(basicamente son identicos).
Para ello se crearon dos intrucciones nuevas de 16 bits, `IN` y `OUT`. Si un programadoe escribe `mov ah, [1234h]`, la direccion va a salir por BA, el IO/M va a estar en 0, read en 1 y write en 0. IO/M va a salir al decodificador para prender el CS de la memoria. Si un programador escribe `IN ah, 1234h`, notar que **no** tiene corchetes, el IO/M va en 1 y el CS de algun periferico se va a prender. Basicamente, son maneras de agregar funcionalidad, como `*` en C.

> **NOTA**: Nosotros solo vamos a usar cosas que funcionan con PC, por ejemplo, el teclado esta en la direccion 60h.
