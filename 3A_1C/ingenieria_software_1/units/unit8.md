# Patrones de diseno

Estos patrones surgen en 1990s con el auge de OOP. Originalmente viene de la arquitectura, no del software.
El detecto que por cada proyecto que realizaba, habia soluciones a distintas situaciones que se repetian.

Hay uno muy util que se llama **mediator**, se usa en todas las aplicaciones web. En el cual se implementan diferentes capas, en la cual 
se procesa la informacion cada vez que entra a una capa(camino de ida) o cada vez que vuelve(camino de vuelta).

Otro patron es el **factory method**, se usa para crear una herrmanienta en el codigo para contruir nuevos objetos sin tener 
que armar una clase particular.

En conclusion, Los patrones se consultan como un catalogo, muchos patrones se hacen familiares si alguien paso mucho tiempo programando.
Es importante saber cuando usarlos y cuando no. Basicamente hay que ver si mi problema tiene forma de ese patron.

**Video con toda la teorica**: https://drive.google.com/drive/folders/1oqsQO63bX14igYZ-iWGWWJiEK1-w1sCv

> **Nota**: https://refactoring.guru/design-patterns

# Distribucion

Antes se distribuia todo fisicamente, una vez se obtenia la copia maestra, se produccian copias en masa y se distribuia. Hoy en dia, 
ese modelo se queda corto y el software se distribuye como servicio(no solo descargandolo de internet), es decir, que no hace falta ni 
descargarlo en la computadora.

Esto permite realizar cambios continuos en el software y experimentar(por ejemplo, darle una version experimental a un grupo reducido de usuarios).

# Cierre de clases teoricas

A nosotros nos interesa mucho que la solucion se encuadre dentro de los recursos que disponemos, 
de manera que se optienen las mejores soluciones desde la practica, pero no las mas optimas teoricas.

## DORA

Empresa que realiza encuastas y determino 4 metricas claves para determinar el exito de un equipo de desarrollo:

- Change Lead Time(Tiempo que demora en implementarse un cambio)
- Deployment Frequency
- Change Failure Rate(Que porcentaje de los deploy introduce algo que obliga un rollback o corregir en el momento)
- Mean Time To Recovery

### Core model

Modelo para analizar y predecir a que empresas les va a ir bien, no es estatico, cambia constantemente. 
Si se aplica el modelo y se nota que algunas areas no afectaron tanto el exito, entonces lo sacan.

### SLAs, SLOs, SLIa

- Service Level Agreements
- Service Level Objectives
- Service Level Indicators

La idea es poner los SLO arriba de los SLA, el SLI indica a que nivel esta el servicio. Hay dos maneras de medir SLI:

- Basada en metricas(eventos validos, sin errores)
- Basada en tiempo(uptime)






