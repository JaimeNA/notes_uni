# Unidad 4: Procesadores

Antes, AMD hizo clones de los procesadores de 32 bit de Intel(IA-32), tubo problemas de temperaturas y perdio reputacion. Como nadie los queria, los vendian muy baratos y AMD termino ganandole a Intel y fue el primero en sacar un procesador de 64 bits(antes que intel), por eso se llama AMD64. 
IA-64, Tecnologia de Intel de 64 bits(Itanium) no retrocompatible, fracaso.

- **Arquitectura**: Definida por el set de instrucciones.
- **Microarquitectura**: Como se implementa la arquitectura a nivel hardware. Depende a que hardware sea destinado(mobile, netbook, server, etc.). Define si soporta virtualizacion, cache, gasto de energia, etc.
- **Procesador**: Por nombre, lo que compras.

## Microprocesadores ARM

**Advance RISC Machine**, actualmente la empresa no fabrica procesadores, sino que hace las IP(Intellectual Property), comercializa licencias. Tiene procesadores de 32 y 64 bits, alta relacion MIPS/watts, es decir, logra ejecutar muchas instrucciones y consume muy poca energia, lo cual lo hace ideal para dispositivos que funcionan con baterias. 
Las empresas les compran licencias para poder ellos producir y vender estos disenos de procesadores.

### Diseno

Para el diseno se utiliza software para simular el microcodigo que realizan las compuertas de silicios.

- Simulacion logica
- Simulacion electronica
- Simulacion termica

Ejemplo de lenguaje: VERILOG(lenguaje de tipo HDL(Hardware Descriptor Table)).

Entonces, si sos in Ingeniero Informatico trabajando para esta empresa, tu trabajo es el de crear este software de simulacion.

### System on Chip(SoC)

Un SoC esta integrado por:

- Procesador
- Memorias(ROM, RAM y flash)
- Osciladores
- Conversores A/D y D/A
- Interfaces (USB, Ethernet, USART)

Tiene la ventaja que al crear un dispositivo, tengo un chip que tiene todo lo que necesito y solo me falta ponerlo en una placa y el software. Sin embargo, una desventaja importante es que no se puede mejorar, es obsolecencia programada, ya que si quiero un poco mas de espacio en el disco por ejemplo, ya no me sirve y tengo que tirarlo a la basura.

### Cortex A9

Mas adelante, aparece esta arquitectura, permite que el procesador pueda configurarse con distintos niveles de consumo para poder ir regulando el consumo de energia.
Usa la arquitectura Hardvard modificada, es decir, una memoria para codigo y otra memoria para datos. Tambien tiene cache, una para cada memoria, entonces primero se fija en cache y si no esta lo que busca se va a memoria. Los Soc que los usan, por ejemplo:

- Apple A5(Ipad 2 y 3, Iphone 4s)
- OMAP4(Motorola Razr, kindle Fire)
- Exynos 4(Samsung Galaxy S3, Samsung Note)

> **Nota**: ARM rompe compatibilidad hacia atras, por ejemplo, ARMv7 no es compatible con ARMv6. Sin embargo, no es tan problematico como el caso de Intel debido a la obsolecencia programado.

### Arquitecturas y familias

Similarmente al modo protegido de intel, los procesadores ARM tienen siete modos de operacion basicos, cada modo tiene acceso to su propio stack y registros, y algunas operaciones solo pueden ejecutarse en modo privilegiado.
Notar que si queres trabajar con exceptciones o interrupciones, el procesador tiene que cambiar de modo. Lo importante de todo esto es que no se invento nada nuevo, hace algo similar a Intel.

![Modos de procesadores ARM](graphics/arm_modes.png)

### Set de registros

Nada de nombres, bien sovietico, van asi: r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, ....
En la doc te dicen que hace cada uno por lo menos.

### Flags

![Flags de procesador ARM](graphics/arm_flags.png)

### Caracteristicas generales

- Casi todas las intrucciones se ejecutan en un ciclo de clock y tienen tamano fijo, pero hicieron un poco de trampa pues hicieron que las instrucciones mas sencillas tarden un poco mas.
- Todas las familias de procesadores comparten el mismo conjunto de instrucciones
- Tipo de datos de 8/16/32 bits
- Pocos modos de direccionamiento
- No se crea fragmentacion de memoria

> **Nota**: Que todas las instrucciones duren el mismo tiempo tiene muchas ventajas en lo que es contar el tiempo.

### Mapa de memoria

Es un solo mapa, no tiene mapa E/S. Viene predefinida la zona reservada para perifericos, es decir, no sera posible tener 4GB(En caso del de 32 bits) de RAM completos. Similarmente, hay una zona definida para ROM, no se puede cambiar.

### Pipeline

Este es un concepto muy interesante que se da en los procesadores. Aca vamos a ver el ejemplo del ARM7. A la hora de ejecucion, hay tres pasos basicos para hacer:

1. Fetch
2. Decode
3. Execute

Estos tres pasos son independientes entre si, los desarrolladores se dieron cuenta que esto se puede paralelisar. Es decir, en todo momento, estas tres etapas se pueden dar en simultaneo, pues son independientes.

![Pipeline](graphics/pipeline.png)

Esto seria idealmente, no sucede completamente de forma perfecta todo el tiempo. Hay cierta combinacino de instrucciones que produce que el pipeline no se ejecute de la manera esperada, provocando que algunas de las etapas quede esperando a que otra termine. El enemigo principal de pipeline es el `jmp` pues podria hacer un fetch al `jmp`, mientras hago el decode del `jmp` busco la siguiente instruccione, pero si el `jmp` se realiza eso tiene como consecuencia que la instruccion que busque siguiente al `jmp` ya no me sirve. 
Sin embargo, es muy poco el procesamiento extra de manera que sigue siendo provechoso, lo unico es que con cada `jmp` se limpia el pipeline. Tambien podria ser un ciclo, lo que significa que cada jump gasta recursos de mas, haciendo que los ciclos sean ineficientes.

Para solucionar esto, ARM creo un nuevo tipo de instruccion que tienen condicion a tal de evitar los `jmp`. Algunas son:

- `cmp`
- `addeq`
- `subne`

Esto evita que se vacie el pipeline, lo constoso es vaciar el pipeline. Para implementar esto, el CPU lee primero los 4 bits mas significativos de cada instruccion(pues tienen todas el mismo tamano) para determinar si se debe ejecutar o no (se conoce como bits de condicion). La condicion `0000` significa `EQUALS`. Por lo tanto, solo se ejecuta si el flag `Z`(cero) esta activo.

Entonces, existen los `jmp` en ARM, pero el comilador trata de usarlos lo menos posible.

> Todos los procesadores del planeta y planetas por conocerse, tienen pipeline - Santi

### TDMI

ARM le agrego a los procesadores lo que se conoce como extensiones(Intel no implementa esto), estas son extensiones de hardware. Cada letra es un tipo de extension.

#### THUMB

El microprocesador tiene 2 sets de extensiones, el ARM clasico de 32 bits y el THUMB de 16 bits. El THUMB tiene instrucciones de 16 bits, se descomprimen en forma dinamica en el moduo de **decode** del pipeline. El THUMB lleva las instrucciones de 16 bits a 32 bits para que el procesador las pueda ejecutar, se podria desear esto por un tema de espacio(cada instruccion ocupa la mitad), velocidad o retrocompativilidad.

#### Jazelle

Esta extension permite ejecutar codigo de Java directamente en hardware, las intrucciones de Java que no posee las emula cno las instrucciones de ARM. Basicamente, es implementar en hardware la maquina virtual de Java, esto trae un problema, pues no puede actualizarze.

