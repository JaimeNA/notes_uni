# Notas final 

> Tips utiles y what not

99% de las APIs que dicen ser REST, no lo son.

## REST

- Lo mas importante es no tener verbos, si hay es un desaprobado automatico
- Si parece mas ineficiente y parece mejor usar un vervo o similar, usar la manera ineficiente sin dudarla
- Un metodo para cada intencial, debe ser entendible por un humano
- Metodo comunica intencion. La URL identifica el recurso. Ninguno invade el rol del otro. 
- Esta mal retornar siempre un 200 OK, usar los codigos de respuesta, no meter todo en el body.
- No hace falta implementar 429, no hay que contemplar ataques DDoS 
- No esta bueno retornar 500 salvo casos como: se cayo la base de datos
- Frontend completamente al backend, si hay un recurso se obtiene el recurso entero o se usa un 
  MIME type custom. Se pide con el header `accept`. Se matchean con @consumes(para requests) y @produces, 
  en spring.
- Una vez se autentica, se retorna el token en `X-Access-Token`, y el refresh token, que no se manda en 
  todos los requests y por eso dura mas, se manda con `X-Refresh-Token`.
- El frontend podria tener un path `/login` la API NO.
- Se podria poner el el token cuando expira, pero tambien esta bien esperar al primer 401 y hacer el refresh.
- Para registrar se usa un POST a user, pero, porque no PUT? Porque PUT es a un, por ejemplo, /users/{id}(un recurso) 
  y el POST a /users(la coleccion entera)
- Por otro lado, el verify, no se puede hacer nada de llamar a `/verification`, se agrega el OTP al basic. 
- En el JWT es recomendable tener el self(donde vivo yo como entidad), roles(para conocer el nivel de acceso. 
  Por defecto sprint no entiende JWT para verificar, va a haber que trabajar en un filtro para usar JWT, mapear los 
  atributos del JWT y transformalo en un principal para poner en security context. Traducir del mundo JWT claim a 
  un principal que pueda entender sprint. 
- Recursos unicos, si existen dos maneras de acceder al mismo recurso, es mal la API. 
- Que pasa si se cambiaba el role, antes habia que reloguear al usuario para cambiar la cookie. Ahora se podria 
  revocar el JWT(emitiendo un nuevo par_, pero, si lo cambia un admin desde afuera o de manera forzada, se toma como 
  valido esperar 5 min para que se refresque el token por si solo. 
- Tenemos que hacer manualmente el chequeo de cuando el token esta vencido.

## HATEOAS 

- El servidore le dice al cliente que puede hacer a continuacion. El cliente **nunca** hardcodea URLs. 
- Si no se sabe que metodos tiene, se puede usar OPTIONS, se hace solo, no hay que programarlo
- Se debe llegar a todos los endpoints empezando desde la base (`/`), si no es el caso, hay algo 
  hardcodeado y no se esta haciendo bien el HATEOAS. 
- Para paginacion esta mal usar una seccion en el body indicando info de la pagina, cant de pag restantes, etc. 
  Se rompe el principio de entidades individuales pues se devuelven dos recursos juntos(MAL). 
- Para hacer paginacion bien, hay que usar el Header Link(RFC 8288). Aca estran los custom headers, como usar 
  X-Total-Count para indicar la cantidad total de items.
- Lo unico que se podria tener hardcodeado es `/` pues se empieza de ahi

> No respetar algo de esto es un desaprobado directo 

## Frontend 

- Tiene que correr todo junto, tomcat sirve todo. El WAR de Spring incluye el build del frontend.
- Sin embargo, importante compilar el frontend antes de empaquetar todo en el WAR. Esto lo hace un 
  plugin por nosotros, de manera que con Maven se compila y empaqueta todo(`frontend-maven-plugin`). 
- Importante poner a webapp como dependency de front end, frontend va como scope=provided en webapp, 
  sino lo marca como error la catedra pues si se cargo el otro antes no funciona.
- Importante que para que el navegador cachee se use cache incondicional, esta mal sino. Para 
  verificar esto ver que los recursos esten como `main.abc123.js`, importante el `abc123` que 
  identifica la version del archivo y permite que sean cacheados los archivos. 
- El WAR generado solo contiene clases Java y recursos del modulo webapp/. Navegas a / -  no 
  hay index.html del frontend. 
- Para que las rutas del front sean atendidas(Spring tira 404, no conce el SPA) vamos a tener distintos 
  servlets, uno para la API(atiende a /api), uno default(para atender recursos estaticos como JS), uno 
  llamado index que retorna el index.html para cualquier cosa que no entre en los otros dos(por eso se tiene que 
  definir al final). Luego, el index.html se encargara de usar el router.
- Desactivar, JSessionId no se este mandando, MUY IMPORTANTE, se tiene que agregar en el modulo del frontend 
  que desactivan los token. La config va arriva del JSON, que lo lee Tomcat y va a frenar el envio de 
  la cookie. se pone un pedazo de json en la config del builder: `<%@ ...`. Esta mal si hay un json Id en cualquier 
  request



