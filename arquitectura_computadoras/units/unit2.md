# Unidad  2: Transmision digital

Vamos a analizar como se comunica el procesador con el resto del hardware. Para representar 0s y 1s en un cable de cobre hay varias maneras, una manera es voltaje o no voltaje, otra seria +3,3V o -3,3V.
El procesador se comunicara de esa manera, hay dos maneras:

- **Transmision serie**: Va todo por el mismo tiempo, cada bit ocupa un paso.
- **Transmision paralela**: Si quiero transmitir 8 bits en un solo paso, es la manera mas rapida y la que se usa en la computacion, se conoce como un **bus**.

En las computadores y circuitos chicos tenemos los bus, pero en las redes que conectan al mundo y usan miles de kilometros de cable se utiliza la transmision en serie a pesar de ser mas lente(debido al costo principalmente).

Hubo dos propuestas principales para como estructurar la conexino del CPU con memomria:

- **Von Neumann**: Propone un solo bus entre la memoria(codigo y datos) y el CPU
- **Harvard**: Propone dos buses, uno a la memoria con los datos y otro para la memoria(otra memoria) con el codigo. De esa manera de paraleliza la lectura.

Sin embargo, hoyy en dia se utiliza Von Neumann a pesar de ser mas lento debido al costo y complejidad reducida.

## Sistema de entrada y salida

Tenemos dos buses principales, un bus de **direcciones**(address) y otro de **datos**. Estan todos los perifericos conectados a los mismos cables, para que no se crucen y se comuniquen todos de manera ordenada se utiliza el bus de direcciones, este se encarga de apuntar a la direccion de memoria que quiero escribir o leer.
Para saber si ecribir o leer, hay una linea extra **r/w** que con `1` indica `read` y con un `0` indica `write`. Los primeros procesadores tenian 8 y 8 bits para cada bus, pero se fue agrandando a medida que fue aumentando el tamano de los registros, hoy en dia son de 64 bits cada uno.
