# Unidad 4: Spring security

Usa otro id, asi que hay que definir otra version(no es la misma que spring). 
Spring security no nacio dentro de spring, a esto se debe la diferencia de versiones. 
Empezo como una extension a spring para una empresa australiana que decide hacerla 
open-source. Fue luego muy famosa y donada a spring.

## Roles

Se tiene:

- ACL - Access Control List
- RBAC - Role Based Access Control

En general las aplicaciones contruidas en esta materia son tan simples que no es necesario 
utilizar algo tan poderoso como las aplicaciones basadas en roles. Es poderoso porque se 
separa el usuario de los roles. 

### ACL

Es engorroso tener que generar un nuevo tipo de usuario por cada distincion que quiero hacer, 
la falta de granularidad termina permitiendo que haya usuarios con mas permisos de los que 
necesita. Si quiero un usuario con dos permisos a la vez, termina obligando a crear aun otro tipo 
o subiendo el nivel de privilegios. Esto no escala.

### RBAC

Se genera, por ejemplo:

- EDITOR
- MODERATOR 
- CREATOR
- etc.

Luego, se le asigna uno o mas roles a un usuario determinado, dandole a la gente los permisos 
minimos que necesita. 

## Autenticacion

Junto a DCrypt se puede tener un flujo muy robusto, DCrypt basicamente maneja como 
se almacena la password y spring-security se encarga de chequear el matcheo.

> A diferencia de `servlet`, que mapea las url, `filter` decide que request pueden entrar 
o salir. Es lo mas basico de seguridad.

### Logging

Hasta ahora estabamos usando los logs internos de jetty y estos no proveen mucha informacion, 
vamos a intentar tener registro de lo que esta pasando dentro de nuestra aplicacion.
Hay tres elementos fundamentales:

- Logs
- Trazas
- Metricas

> Imprimir en salida estandar es ineficiente y feo

La herramienta mas popular para realizar logging es **log4j**, que esta deprecada y hace algunos 
anos(2021) hubo caos pues tenia una vunerabilidad que permitia remote code execution. Log4j fue 
abandonado por problemas de design que no permitia escalar, entonces construye una nueva libreria 
de logging que se convierte en estandar. Esta es **SLF4**, un framework estandar agnostico a la 
implementacion, como spring. Para programar contra los contratos de SLF4 se tiene **Logback**, 
el cual soluciona varios problemas de log4j y mantiene el codigo lo mas puro posible.

