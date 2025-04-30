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

> **NOTA**: Si solo podes usar un deco con 3 patitas y solo vas a usar una, conectar las patitas restantes a la misma. Dejarlas sin conectar las deja vunerables a ruido. 
> **NOTA**: La ROM generalmente conviene colocarla al final del las direcciones de memoria debido a que es muy poco probable que se modifique, mientras que la RAM puede aumentarse, etc. De manera que deja espacio para perifericos que sean modificables.

### Pin IO/M

En los primeros procesadores, Intel necesitaba poder acceder a mas perifericos sin tener que modificar los registros. Entonces, se agrego al CPU un pin llamado IO/M(Input/Output memory(en realidad la barra deberia ir arriba de la M para indigar que esta negada)), de manera que permitio tener dos mapas de memoria, uno para la memoria y otro para los perifericos(basicamente son identicos).
Para ello se crearon dos intrucciones nuevas de 16 bits, `IN` y `OUT`. Si un programadoe escribe `mov ah, [1234h]`, la direccion va a salir por BA, el IO/M va a estar en 0, read en 1 y write en 0. IO/M va a salir al decodificador para prender el CS de la memoria. Si un programador escribe `IN ah, 1234h`, notar que **no** tiene corchetes, el IO/M va en 1 y el CS de algun periferico se va a prender. Basicamente, son maneras de agregar funcionalidad, como `*` en C.

> **NOTA**: Nosotros solo vamos a usar cosas que funcionan con PC, por ejemplo, el teclado esta en la direccion 60h.

### Palabra y procesador trasparente

Una palabra para un procesador generico siempre sera la cantidad de bits del BUS de datos o de los registros(La cantidad de bits que se pueden obtener en un ciclo). Por otro lado, en ASM Intel, una `word` son 2 bytes. Un procesador transparente es aquel que realiza su función sin alterar el contenido original o sin que el usuario lo perciba directamente. Por ejemplo, las memorias de 8 bits deben funcionar con el procesador de BD de 16 bits. 

## Mapeo en memoria

Un dispositivo es manejado como una o varias posiciones de memoria. Cada posicion de memoria puede ser un registro interno diferente del dispositivo. Por retrocompatibilidad, incluso despues de la introduccion del mapa IO, hay perifericos que quedaron del lado de memoria, como la pantalla en modo texto. Luego, mapear perifericos en memoria es basicamente poner perifericos en el mapa de mamoria, esto tiene ventajas y desventajas:

- **Ventajas**: Se utilizan las instrucciones de acceso a memoria, por lo tanto las alternativas de programacion son mayores por tener mayor cantidad de instrucciones para manejo de memoria. Ademas, se modifica directamente los registros del periferico sin necesidad de obtener el valor con las instrucciones IN y OUT.
- **Desventaja**: Reduce cantidad de memoria. El impacto es minimo relacionado con la cantidad de memoria que tienen las PC actuales.

Es decir, una razon para poner los perifericos en el mapa de memoria es que hay mas instrucciones que soportan `[]`, mientras que solo hay dos que soportan el mapa IO.

Para escribir en pantalla en modo texto, el mapeo en memoria de la tageeta de video sera a partir de **B8000h**, esto es asi para todas las computadoras con arquitectura x86.

## Interrupciones

Hay dos maneras de comunicarse con un dispositivo:

- **Polling**(Sondeo): Pregunta constantemente si tiene un dato nuevo. Usa mucho recursos, pues esta todo el tiempo preguntando. Es muy rapido.
- **Interrupciones:** Esperar a que el periferico avise que tenga un dato nuevo. Tardan en resolverse, pero no le saca recursos al procesador.

Nosotros nos vamos a enfocar en interrupciones, polling es simplemente un loop.

Una interrupcion es una senal externa que interrumpe al micro para requerir un servicio de atencion. Una vez recibe la senal, detiene el programa que esta corriendo y ejeccuta el programa de atencion de interrupcion, luego sigue.

La interrupcion puede ser generada por un evento externo(INTR o NMI) o por la instruccion INT. Una vez termina la rutina de interrupcion, termina con `IRET` y no con `RET`, esto basicamente vuelve al programa original. Esto hay que usarlo con cada rutina de interrupcion y la rutina se debe encargar de dejar todo como estaba antes de la interrupcion.
Hay distintos tipos de interrupciones:

- **Interrupciones de hardware**: Se interrumpe la ejecucion del programa activando alguna de las dos entradas que tiene el procesador(INTR y NMI). Es un evento externo al procesador.
- **Interrupciones de software**: Se interrumpe la ejecucion del programa al ejecutar la instruccion de assembler INT. Por ejemplo, `INT 44h`, donde 55h es el numero de rutina de interrupcion a ejecutar.

### Interrupciones de hardware

El flag **IF** indica si se debe atender a las interrupciones externas. Si IF=1, esta habilitado, si IF=0, esta deshabilitado. Dependiendo de la patita, la interrupcion va a it por:

- **Interrupcion enmascarable**: Ingresan por INTR, el flag IF se controla con las intrucciones `sti`(set interrupts, IF=1) y `cli`(clear interrumps, IF=0). Aca van las no critica, mouse, teclados...
- **Interrupcion no enmascarable**: Las interrupciones que ingresan por la patita NMI no pueden ser enmascaradas. Y siempre ejecutan la rutina que se encuentra en la posicion 2h del vector de interrupciones.(`INT 2h`). Aca van las criticas, como el sensor de temperatura, el boton de encendido.

### PIC(Controlador programable de interrupciones)

Como no alcanzaban las patitas de interrupcion para todo los perifericos, ss agrego otro periferico llamado **PIC**. Recibe ocho patitas(llamadas **IRQ**, interrupt request), las gestiona y decide cual pasa al procesador, cada una con su propia mascara. 
Hay que programarlo, hay que decirle cual pin tiene maas prioridad que otro y se puede controlar la mascara de cada uno individualmente. El PIC trabaja en las direcciones de E/S 20h y 21h. 
Ademas, luego de terminar con un interrupt, hay que mandar la senal **EOI** (End Of Interruption) al PIC, sino el PIC no va a mandar mas interrupciones.El CS del PIC se activa con 0, como se ve en el diagrama, tiene el circulo de negado. El valor que hay que enviar para senalizar el EOI, hay que mandar 20h a la direccion 20h(`OUT 20h, 20h`).

Ademas, se pueden agregar PIC en cascada, teniendo el master como el principal y el slave como secundario, el slaave interrumpe al master y luego el master interrumpe al procesador. El INTA se manda a los dos, ya que el procesador no sabe cual fue el que llamo la interrupcion. Se pueden agragar mas que dos PICs, hoy en dia se pueden encontrar hasta 15 PICs en cascada, y cabe aclarar que el segundo PIC no va a estar en 20h y 21h, sino que va a estar en otra direccion de memoria.

![Diagrama del PIC](graphics/pic.png)

#### IMR

Los puertos de entrada y salida en la PC son el 20h y el 21h, el 20h se utiliza para programar el PIC(lo utiliza el BIOS para arrancar el sistema). En el puerto 21h podemos acceder al registro IMR(Innterrupt Mask Register) del 8259, donde podemos setear que interrupciones llegan al microprocesador. 

> **NOTA**: Arrancar TP con todos los IRQ en 1(todos enmascarados) para que no falle. El teclado va al IRQ1

### IDT(Interrupt descriptor table)

Por cada interrpupcion, tiene una tabla con el puntero a la rutina correspondiente a cada interrupcion. Hay otra patita que sale del procesador llamada **INTA**(Interrupt acknowledge), funciona de manera de handshake. Una vez recibio el interrupt, el procesador le indica al PIC con la senal de INTA que esta listo para recibir informacion, luego el PIC manda por el bus de datos la informacion del periferico. 

![Interrupciones de hardware por default](graphics/default_int.png)

### Servicios de BIOS

El BIOS al iniciar la PC guarda en memoria rutinas basicas para poder empezar a operar. Relacionado con interrupciones de software, hay varias rutinas utiles suministradas por la BIOS y estan estandarizadas. Por ejemplo:

- `INT 10h`, rutinas de video
- `INT 13h`, rutinas de disco
- `INT 14h`, rutinas para puerto Serie
- `INT 19h`, rutinas para bootloader
- `INT 1Ah`, rutinas para el RTC

### Timer tick

Por default, cada 55ms milisegundos genera una interrupcion, mapeador a IRQ0 con una interrupcion de tipo 08h(Para ver mas mapeos ver tabla). Esto se debe a que anteriormente, las computadoras, teniendo un procesador, corrian un programa (o lote)hasta que terminara y recien ahi pasaba al segundo. Por lo tanto, el timer tick interrupe los procesos cada 55ms y luego se va a correr una rutina que va a decidir quien es el proximo programa en ser ejecutado, esta rutina se conoce como **scheduler**. 
El flag de timer tick, IRQ0 esta protegido y no puede ser cambiado por cualquier programa. Estos sistemas donde se deben interrumpir los programas ya que ningun programa va a ceder el IP para dejar correr a otro, esto se conoce como sistemas preemptivos. Por lo tanto, esto es lo que da la sensacion de que todos los programas estan corriendo al mismo tiempo.


