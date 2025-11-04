# Practica 5: Sincronizacion

Lo vamos a necesitar no solo para semaforos, sino tambien para el wait() a los hijos.
En esta clase vamos a ver como implementar todo esto, hay que resolver como entrar y salir 
de la zona critica cumpliendo las condiciones dadas en la clase(ver apuntes).

Las primitivas que tenemos que implementar, no es implementar un semaforo, sino que son:

- Enter region
- Leave region

Esto es porque para implementar un semaforo, hay que protegerlo a su vez con otro semaforo, 
entonces ahi entran las primitivas.

## Dentro de la region critica

No se puede simplemente `cli` dentro de cada region pues dejaria de ser concurrente. 
Tampoco espera activa pues es lo que queremos evitar y porque se rompe facilmente la exclusion mutua, 
es debido a que el while necesita comparar una variable(condicion de carrera).

Otra es la alternacion estricta, pero solo se puede tener entre dos programas o que uno termine, 
entonces el otro se queda esperando para siempre pues depende del otro.

Tambien esta la solucion de Peterson, similar al anterior, pero agrega un flag. 

## Soluciones

### Spin lock

Hay un poquito de espera activa, pero como dura 1-4 quantums se asume que es correcto.
Implementa `adquire` y `release`, son las que tenemos que implementar en el TP.

> **Nota**: MUY MAL no delver el SPIN LOCK antes de bloquearse.

