# Unidad 6: Final

La idea es pasar todo lo que hicimos a una API REST y interactuar con una 
**single page application**. 

## REST

**REpresentational State Transfer**, la idea es trasmitir informacion a un sistema, no 
necesariamente de frontend. Hace uso extensivo del protocolo HTTP, de manera que 
contruir una buena API REST requiere de un buen dominio de HTTP. 
Se diferencia de HTTp mediante la terminologia:

- Metodo --> Verbos
- Path --> Recursos

Los recursos representas partes del estado, entonces no hay un `/register` sino que necesita 
un verbo sobre el cual decidir la accion:

- `POST /user` --> Crear usuario(unico verbo no idempotente)
- `GET /user` --> Obtiene informacion de un usuario

Los recursos en REST son sustantivos que luego tienen un verbo que define la accion. Son 
jerarquicas, por ejemplo, `/users/34/addresses`. Los recursos deben estar definidos por 
**URN**, la cual es universalmente unica(canonica). Esta mal tener `/users/addresses/34`, pues 
ahora hay dos URN que representan la misma entidad. 

> **Muy importante**: El body es exclusivo para la representacion de la entidad, los 
headers se encargan de lo demas(vamos a usar muchos).

- `GET /users/1234` --> `200 OK` o `404`

> La mayoria de las API "REST" que hay en internet no es REST

## No REST 

- `/me`, responde cosas distintas segun quien pregunta(MAL)
- Usar JSON, si cambia lo que devuelve la API(estructura) se rompe todo. Usar `/v1/users` esta 
MAL(v1, v2 no son entidades de dominio)
- 

> Estas cosas se resuelve con negociacion de contenido, resuelto desde dia 1. 

Vamos a crear nuestros propios MIME-types y ser semanticos al respecto. Define entidades, 
resuelve codificacion y resuelve versionado. Tendremos MIME-types del formato:

- `categoria/tipo` --> `image/jpeg; application/json`

Vamos a usar: `application/vnd.paw.user.v1+json` (vendor.organizacion.entidad.version+tipo). 

## Que pasa con la autenticacion

Se usa el header `authentication`, NO existen:

- `/login`
- `/register`
- etc.

Con esto deja de haber session, no hay mas cookie, significa que nuestra aplicacion es 
stateless. Nosotros vamos a usar JWT(Json Web Token), un string encoded en base64, usa `Bearer`. 
El refresh token sirve para rotar las claves, cuando el token es rebotado, la idea es que se mande 
el refresh token para obtener uno nuevo. 

## Java REST

Java tiene un stardard para construir API RESTs, llamado `JAX-RS`. Una implementacion de este 
standard es **Jersey**. La idea es que nosotros dejemos de usar lo actual para armar una API 
REST pura(Jersey es la implementacion mas purista, pero no unica). Sin embargo, no dejaremos de 
usar Spring, en vez de usar WEB vamos a usar Jersey.
