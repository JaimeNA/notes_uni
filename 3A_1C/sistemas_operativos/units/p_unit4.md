# Practica 4: Context switch, scheduling y procesos

Importante mandarle al PIC que se recibio la interrupcion(EOI). Luego en conviene guardar el contexto de cada proceso en el stack y en el PCB 
guardar el stack de cada proceso.

Conviene al llegar al handler(que ya tiene los registros especiales en el stack) hacer `pushState` para guardar los registros de proposito general. 
Luego, al hacer `popState` se restauran todos los registros. Mandar al PIC el EOI despues del `pushState` pues sino se estaria pisando RAX.

Para hacer **context switch** con todo lo que armamos, es cuestion de cambiar el RSP a que apunte al stack del otro programa(que sufrio el 
mismo proceso) antes de `popState`. 

Y cada stack esta en el bloque de memoria designado para cada programa por el memory manager, en el PCB se guarda la direccion de RSP.

## Crear un proceso

### Stack

Al crear el stack, vamos a mentir un poquito, vamos a rellenar el stack frame manualmente. Esto es para que el scheduler pueda menjar el 
stack de un proceso recien creado y el stack de un proceso que ya estaba corriendo.

Si el stack no estaba alineado, va a haber un problema, por lo tanto hay que rellenar para que el stack este alineado a la palabra 
de la arquitectura en la que estamos trabajando.

Tener cuidado, pues el stack crece hacia abajo, entonces, para hacer malloc hay que hacer algo como esto:

```
rsp = ptr + STACK_SIZE;
```

### RIP

A `create_process` hay que mandarle algun argumento(son varios) que nos ayude a completar el RIP. Una opcion seria la direccion del `main` de 
dicho proceso, no tiene que ser exactamente `main`, pero tiene que ser de ese estilo. 

> Tener cuidado con los argumentos que sean punteros como el `argv`.

### Si no hay proceso ready?

Podemos disponer de un proceso cuya unica funcion es ejecutar `hlt`.

