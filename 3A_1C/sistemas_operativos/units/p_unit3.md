# Practica 3: TP02

- Mantener un repo simple, no complicarse. Por ejemplo, las funcionalidades graficas.
- Cada modulo dependera del anterior, asi que hay que implementarlo secuencialmente.
- El administrador debe funcionar de forma trasparente, el contrato es siempre el mismo: devuelvo o pido memoria. 
- Nos van a pedir tests. No deben ser pantalla en negro, debe imprimir lo que esta ocurriendo en todo momento.
- La shell es un proceso, va a haber que ofrecer un mecanismo para permitir procesos en foreground y en background. Con el `&` o cualquier otro caracter reservado.
- Notar que background y foreground son una abstraccion del front, basicamente reciben o no input del teclado.
- Que pasa si un proceso en background realiza la syscall `read()`. Esta es una situacion bastante sensible y si se tiene una implementacion con pipes es muy bueno. Pero se podria simplemente devolver un `EOF` si esta en background y listo(negarle el acceso al read).
- Para probar si anda bien todo lo de procesos es meter un break antes de la clase y crear 4 procesos que impriman constantemente por la salida. Si se imprime todo al continuar la ejecucion, entonces esta bien.
- Si no se ve nada por salida estandar, el test es invalido.
- Bajan nota si se hace esto, la lectura del teclado es un loop preguntando por mas caracteres. Esto valia en arqui, pero ahora no se puede hacer. Ahora el read debe ser bloqueante, no se debe consumir CPU. Muy facil de implementar con semaforos(una vez los hayamos implementados).
- La shell es la demostracion de que todo funcione, sino se ponen a ver el codigo, pero no es lo ideal.
- Una vez se llega a procesos, se puede comenzar a separar tareas para semaforos, comandos de la shell, etc.


## Memory management

Tiene varias capas(de mas alto a mas bajo nivel):

- User-Space Allocator(address space disponible)
- Virtual Memory Manager
- Physical memory manager / Page Frame Allocator

### Responsabilidades del memory manager

- Liberar memoria previamente asignada
- Asignacion **exclusiva** de memoria libre

Estas son las dos funcionales, pero la catedra agrega como no funcional la capacidad de consultar 
el estado, cuanta memoria esta asignada y cuanta esta libre.

### Consideraciones para el TP

- 1:1, sin memoria virtual.
- Memoria para el MM se arma con el manual de Pure64 y la guia de building.
- Fragmentacion.
- Alineacion de memoria con el tamano de palabra con la arquitectura en la que estemos trabajando.

### Buddy allocation

- Trabaja con un arbol binario.
- Se puede ver en el libro mencionado en la presentacion.
 

