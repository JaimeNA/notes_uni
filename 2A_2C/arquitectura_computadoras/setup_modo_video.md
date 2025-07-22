# Modo video

Primero habilitarlo desde la carpeta `Bootloader`, modificando `sys_var.asm`. Hay que cambiar `cfg_vesa` a 1, ahi ya dejo configurado el modo video. 
Esto no se puede cambiar durante ejecucion, una vez configurado queda asi hasta que se recompile.

## Implementacion del driver

En el mismo archivo, dentro de la seccion `VESA` se encuentra toda la configuracion relativa al buffer de video, esto lo vamos a poder leer durante la ejecucion y es la que tenemos que usar para 
usarlos en la configuracion de driver de video. Asi se evita *hardcodear* valores. 

> **Nota**: En campus hay un .c de un driver de video basico.

## Dibujar letras

Una manera muy comun de hacerlo es cno bitmaps de 8x16.
