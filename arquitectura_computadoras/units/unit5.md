# Unidad 5: GPU

> **Nota**: No nos dieron clase de GPU, todo lo que esta a continuacion es del TP07.

## Motivación
A lo largo de la materia, introducimos el concepto de CPU como la unidad de
procesamiento de la computadora, encargada de interpretar y ejecutar las instrucciones de
software que componen al programa en ejecución. Si bien hasta hace poco la consideramos
mono núcleo y, en consecuencia, capaz de procesar una sola instrucción a la vez,
recientemente introdujimos el concepto de procesadores multi núcleo, que permiten ejecutar
más de una instrucción en simultáneo.

Para entender qué es una GPU y qué la hace tan útil, pongamos nuestro foco en este
punto: es posible para una computadora moderna ejecutar más de un conjunto de
instrucciones en simultáneo. Naturalmente, y para evitar tener que sincronizar los distintos
núcleos, estos correrán bloques de código independientes uno del otro; es decir, un
programa con lógica lineal tradicional, de los que han escrito cientos, ejecutado en una PC
multi núcleo, correrá en uno solo, que se le asignará, sin dividirse y sin introducir ninguna
rareza. Cada bloque de instrucciones, corriendo en cada núcleo, tendrá siempre su propio
Stack, su IP, y todos los recursos a los que estamos acostumbrados. 

En consecuencia, la
utilidad más evidente para multi núcleo es correr distintos programas independientes, uno
en cada núcleo. La distribución de los programas en los distintos núcleos ya se encarga, sin
que lo sepamos, el Sistema Operativo. Entonces, ¿de qué sirve saber todo esto, más allá
de entenderlo como una optimización que aprovecha el Sistema Operativo?
La respuesta es que, efectivamente, como programadores podemos aprovechar la
existencia de varios núcleos para acelerar flujos de procesamiento paralelos. A lo largo de
la siguiente guía exploraremos ejemplos de problemas paralelizables, y entenderemos
cómo puede la GPU ayudarnos a resolverlos de forma eficiente.

## La GPU

¿Qué es, entonces, una GPU? La sigla significa Graphics Processing Unit (pues suele
usarse, aunque no de manera exclusiva, para lidiar con problemáticas relacionadas a los
gráficos, como la renderización de un videojuego), y se trata de una unidad de
procesamiento que se comporta de forma similar a un procesador, en el sentido de que
ejecuta código, aunque con un set de instrucciones diferente, y una capacidad gigantesca
para ejecutar hilos de código simultáneos. Supongan que se les pide escribir un algoritmo
que, dadas las listas de notas de todos los alumnos universitarios de la Argentina, calcule la
nota más baja de cada alumno.

La solución es trivial: por cada alumno, se recorre cada una de sus calificaciones y se
encuentra el menor valor, todo de manera secuencial. Ahora, esta solución crecerá, en
tiempo de ejecución, linealmente con la cantidad de alumnos, puesto que la secuencialidad
del algoritmo implica que solo después de calcular el valor para un alumno, se procederá a
hacerlo con el siguiente.

Ahora, la pregunta que surge es: ¿por qué hacerlo de manera secuencial en lo
absoluto? Asumiendo que sabemos cómo y qué disponemos de un procesador multinúcleo,
¿qué nos impide calcular la nota más baja de varios alumnos en simultáneo? Resulta que
nada, porque no requerimos de los resultados del algoritmo para un registro anterior para
calcular otro cualquiera. Este es un caso de uso ideal para programación concurrente:
podríamos tener varios núcleos de procesamiento calculando, de manera independiente y
simultánea, la nota más baja de varios alumnos.

Un Intel Core i7, por ejemplo, puede tener cuatro núcleos, por lo que, idealmente, podría
correr 4 hilos que calculen la nota más baja de 4 alumnos simultáneamente, y repetir este
proceso hasta acabar con todos. Esto ya reduciría el tiempo de procesamiento total
sensiblemente.

Las GPU existen específicamente para lidiar con este tipo de problemas, y ponen a
disposición del programador no ya 2, 5 o 7 hilos simultáneos, sino miles. Una GPU
competente podría calcular la nota más baja para miles de alumnos en simultáneo,
reduciendo drásticamente el tiempo de procesamiento.

## Herramientas de trabajo

Para facilitarles encarar los siguientes ejercicios, la cátedra pone a su disposición un
equipo con una poderosa GPU para que utilicen. El mismo es accesible desde la red interna
del ITBA, por lo que será visible desde pampero. Su nombre es “titan-arq”, y tiene habilitado
el acceso por SSH para que ingresen con sus credenciales actuales. Cabe destacar que el
equipo tiene ya instaladas todas las herramientas que necesitarán para encarar la guía.
Desde su terminal, ingresen a pampero con sus credenciales:

```
$ ssh miusuario@pampero.itba.edu.ar
```

Una vez dentro de pampero, ingresen a la PC "arquitectura" mediante SSH, con las
mismas credenciales:

```
$ ssh arquitectura -L8080:localhost:8080
```

## Entonrno de programacion - Nvidia

Así como Intel y AMD utilizan la arquitectura 8086, y ARM la propia, las GPU de Nvidia
utilizan una llamada CUDA. Para compilar código al lenguaje ensamblador de CUDA, Nvidia
ofrece un paquete llamado CUDA Toolkit1, que contiene un compilador y un analizador de
performance, entre otras herramientas.

El compilador, nvcc, es muy similar a gcc, pero trabaja con una variante de C++ que
amplía su sintaxis con algunas anotaciones útiles que ya discutiremos. La extensión
esperada de los archivos de código es .cu, y él mismo genera ejecutables que pueden
correrse como cualquier programa normal.

Por lo pronto, es importante mencionar que todo programa C válido es también un
programa C++ válido, por lo que todo lo que saben de C, como el uso de headers, sintaxis
de funciones y librería estándar, sigue valiendo. Salvo por las anotaciones específicas de
GPU que discutiremos a su debido tiempo, pueden trabajar como si estuviesen en C.
También, cabe destacar que a todo el código que veremos en esta guía lo compilaremos
con nvcc.

## Modos de programacion 

Así cómo es posible, mediante las system calls, comunicarse y hacer pedidos al
Sistema Operativo, la GPU también ofrece no una, sino dos interfaces de comunicación que
permiten al programador dialogar con el dispositivo. Las mismas son la Runtime API2 y la
Driver API3. Ambas pueden incluirse como una librería en un archivo de código, y exponen
distintas funciones que pueden invocarse para interactuar con la GPU. En la mayor parte de
los casos, la Runtime API y la Driver API pueden usarse de modo intercambiable.

Nota: Para incluir funciones de la Runtime API o de la Driver API, deben agregar, a la
llamada a nvcc, el flag -lcuda, para asegurar que el compilador linkeará contra las librerías
de CUDA.

## ¿Cómo hacer que una función corra, en cambio, en la GPU?

Nvcc define la etiqueta global que, aplicada a una función que debe devolver void,
correrá en la GPU y no en la CPU si se la invoca apropiadamente. Esta función deberá ser
invocada utilizando una sintaxis especial, que incluye, entre picos, el número de hilos que
se crearán, aunque a los efectos de este ejemplo, crearemos uno solo.

Además, es importante recordar que la GPU es un dispositivo independiente, es decir,
que tiene sus propias componentes, como caché, registros, y, fundamentalmente, su propia
memoria, totalmente independiente de la RAM que utilizamos desde la CPU; esto se
traduce en que no podemos generar un puntero desde la CPU (por ejemplo, con malloc, o
pasando como parámetro la dirección de memoria de una variable local) y referenciarlo
desde la GPU, puesto que las memorias RAM y de vídeo son totalmente independientes.

Existe una función equivalente a malloc, llamada cudaMalloc, que reserva espacio en la
memoria de vídeo, otra equivalente a free llamada cudaFree, y una llamada cudaMemcpy,
que permite copiar información de una memoria a la otra, y así transferir la información para
procesarla donde corresponda. Más recientemente, Nvidia implementó la función
cudaMallocManaged, que facilita la gestión de memoria generando un único puntero válido
tanto en la CPU como en la GPU, aunque sacrificando control sobre el copiado de memoria.
La utilizaremos por simplicidad.

Por último, debe contemplarse que la creación de hilos en la GPU es asíncrona: cuando
ordeno a la GPU que corra una función, desde la perspectiva de la CPU, la misma retorna
automáticamente, y no existe garantía de que haya aún terminado. Como nosotros
queremos aguardar a que el cómputo termine antes de verificar el resultado, debemos
poner a la CPU en espera hasta que esto ocurra. Esto lo hacemos mediante la función
cudaDeviceSynchronize, que congela el código de la CPU hasta que todos los hilos de la
GPU hayan concluido su procesamiento.


