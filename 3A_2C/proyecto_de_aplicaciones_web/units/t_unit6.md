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

Por ejemplo, se crea un usuario, entonces retorna 201 + Location.

Es muy **importante** que todos los paths sean URN, es decir, no puede la misma entidad estar 
disponible en multiples lugares, debe ser unica.

> Lo de URN es un error comun, por ejemplo, en el mapeo de relaciones.

Esta **mal** tener que buscar los libros favoritos de un usuario con `users/23/favorites`, lo 
que hay que hacer es buscar los libros a `books` y usar query params para filtrar: 
`GET /books?favoritedBy={userId}`. Si por otro lado, quiero marcar un libro como favorito 
podria hacer(tmb con DELETE) `POST /users/{id}/favorites/{xxx}` y luego tambien se podria 
devolver un 302 si se hace un get a `/users/{id}/favorites` y devolver la ubicacion bajo 
`/books`. Esta no es la unica manera de hacerlo, lo importante siempre es no mezclar entidades.

### Entidades debiles

Son las cuales accedo a traves de una entidad, en ese caso se usa `/users/621/addresses`.

### Respuesta paginada

Se avisa pues la respuesta va a tener unos params, por ejemplo `rel="first"` o `rel="last"`, 
asi no se necesita conocer la implementacion de la API, es super poderoso. Tambien `rel` podria 
ser `prev` o `next`. De esta manera, con solo descubrir un solo endpoint se puede recorrer la 
API.

### Ejemplo de retorno

Se van a vincular las entidades para permitir la navegacion, el resto que no es el contenido se 
negocia en los headers, etc. 

Ejemplo: 

```
[
{
    "name": ...
    "links": {
        "self": "../users/1231",
        "favoriteBooks": ...
    }
},
{
    ...
}
]
```

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

> Lo que esta a continuacion va de naive a la implemetacion correcta, leer bien.

Java tiene un stardard para construir API RESTs, llamado `JAX-RS`. Una implementacion de este 
standard es **Jersey**. La idea es que nosotros dejemos de usar lo actual para armar una API 
REST pura(Jersey es la implementacion mas purista, pero no unica). Sin embargo, no dejaremos de 
usar Spring, en vez de usar WEB vamos a usar Jersey.

Vamos a eliminar spring MVC, se dejara de usar la parte mas frontend, pero todo lo anterior lo 
dejamos igual. Lo principal que vamos a cambiar van a ser todos los contollers, con Jersey.
Spring tiene que poder reconocer los controllers para poder traer los services:

```
@Component
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
```

Se marca como `component` para que spring pueda encontrarlo y instanciarlo.
Y hasta aca llegan las similitudes, luego todo el resto sera diferente:

```
@Component
@Path("users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // No especifica Path, entonces el endpoint es /users/
    @GET
    public Response listUsers() {
        return Response.ok(new GenericEntity<List<User>>(userService.getAll(page, pageSize)) {
            });
    }

}
```

`Response` es un tipo generico que permite abstraerse de la implementacion. Jersey no sabe que 
tipos tiene que usar, por eso se especifica con una clase anonima:

```
return Response.ok(new GenericEntity<List<User>>(userService.getAll(page, pageSize)) {
    });
```

Se define una clase anonima pues genera un .class y la informacion en generic se puede verificar 
en tiempo de ejecucion. Tiene mucho que ver a como funciona Java internamente. Asi se logra 
verificar que todos los elementos de la lista sean iguales. 

Sin embargo, asi como esta la lista tendra dos elementos vacios. Esto se debe a que se quiere que 
el objeto de escritura(PUSH) sea el mismo que el de escritura(GET) al mismo endpoint. Entonces, 
el objeto user debe tener un contructor default y getters y setters. Es sumamente importante, sino 
no funciona. Seria raro igual, considerando el resto de la implementacion, que agregaramos 
setters para cosas que no queremos cambiar. Esto estaria mal, entonces, en vez de ir en el objeto 
se usa un DTO, objeto que solo se usa para trasmitir informacion entre dos partes del sistema que 
no tiene comportamiento propio. 

Asi que lo anterior no va, se usa el `userDTO`. Quedaria:

```
@GET
public Response listUsers(...) {

    List<UserDto> users = userService.getAll(page, pageSize).stream()
        .map(UserDto::fromUser).collect(Collectors()...);

    return Response.ok(new GenericEntity<List<UserDto>>(users) {
        });
}
```

El DTO tambien tendra los hiperviculos a los otros objetos. Para ello usamos un objeto que ofrece 
Jersey llamado `UriInfo`, tendra informacion relativa al request actual, de manera que los 
vinculos son realmente URNs y no un string relativo.

## Jersey validation config 

Motor de validacion de beans, hay que configurar un message source. 
En `getContext` se configura la conecion con el message source. Luego, en el DTO podemos 
copiar las validaciones que habia en el form. 

