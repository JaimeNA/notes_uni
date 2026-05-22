# Unidad 12: Scokets no bloqueantes

- Antes con `close()` se bloqueaba esperando al `FIN` del otro lado, ahora se 
puede usar la opcion de `SO_LINGER`. Hace un `close()` y hacer un 
`RST`(fuerza cerrar la conexion). 

## Signals

Se utilizan para realizar cosas de forma asincronica, al recibir una el proceso decide 
que hacer.

---

En UDP no bloquean(no hay buffer de salida), en TCP `connec()` asigna `EINPROGRESS` a 
`errno`. `getAddrInfo` bloquea de todas formas porque tiene que hacer el request DNS, 
de igual manera no tiene nada que ver con sockets.
