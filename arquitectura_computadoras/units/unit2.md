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

