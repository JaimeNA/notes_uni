# Notas final 

> Tips utiles y what not

99% de las APIs que dicen ser REST, no lo son.

## REST

- Lo mas importante es no tener vervos, si hay es un desaprobado automatico
- Si parece mas ineficiente y parece mejor usar un vervo o similar, usar la manera ineficiente sin dudarla
- Un metodo para cada intencial, debe ser entendible por un humano
- Metodo comunica intencion. La URL identifica el recurso. Ninguno invade el rol del otro. 
- Esta mal retornar siempre un 200 OK, usar los codigos de respuesta, no meter todo en el body.
- No hace falta implementar 429, no hay que contemplar ataques DDoS 
- No esta bueno retornar 500 salvo casos como: se cayo la base de datos
- Frontend completamente al backend, si hay un recurso se obtiene el recurso entero, no existe pasar solo 
  una parte porque solo necesito eso.
- Una vez se autentica, se retorna el token en `X-Access-Token`, y el refresh token, que no se manda en 
  todos los requests y por eso dura mas, se manda con `X-Refresh-Token`.
- El frontend podria tener un path `/login` la API NO.
- Se podria poner el el token cuando expira, pero tambien esta bien esperar al primer 401 y hacer el refresh.
- Para registrar se usa un POST a user, pero, porque no PUT? Porque PUT es a un, por ejemplo, /users/{id}(un recurso) 
  y el POST a /users(la coleccion entera)

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


