# Notas: Control de acceso

Sirve para resolver problemas como que solo el usuario pueda editar su perfil, no el 
de los demas.

## Prompts

### 1

Queremos permitir que desde el perfil de un usuario, si el usuario logeado es el mismo, el 
`username` sea editable. Editarlo debe disparar un POST a /profile/{id} para editar el perfil 
del usuario e impactar el cambio en la base de datos. Este endpoint debe estar securizado 
desde Spring Security para asugurar que solo el propio usuario puede hacer este POST.

**Correcciones**: 

- Duplico el user form y creo un UpdateUserForm, lo que tiene que hacer es usar grupos 
para los distintos sets de validaciones y el mismo user form.
- Estaba usando verificacion manual, lo que tiene que hacer es que tiene que ser automatica 
usando el browser. 
- Cambio la version de U

# Unidad 5: JPA

Esto va para la segunda entrega. Hay varios enfoques para resolver persistencia, JPA es uno y 
es un ORM. Mapea los objetos a tablas de bases de datos, es similar a los row mappers que 
estabamos usando. Estaria mal crear un objeto que represente una relacion M a N, eso no 
existe en el mundo de objetos(es del mundo relacional). Actualmente, lo que podriamos hacer 
es que, por ejemplo, cada objeto `User` tenga una lista de `userId` de los usuarios que son 
amigos suyos. 

Ahora, esta lista puede ser `null` o `Empty()` para representar que no hay entradas, para la 
base de datos esto sera equivalente, pero en el mundo de objetos no es lo mismo. Esto se 
conoce como transformacion **lossy**.

En base a esto, surgio el estandar JPA, donde el equipo de **Hybernate** estuvo muy metido 
y entonces influenciaron mucho en esto. Vamos a cambiar los JDBC DAOs por JPA DAOs, va a 
haber que agregar varias anotaciones.

(en el campus hay un apunte de como configurarlo)

**Nunca** poner un `hibernate.hbn2ddl.auto` como `create`(tira toda la base de datos y la 
crea de nuevo), sino que hay que usar `update`, va a intentar resolver todos los conflictos 
con la base de datos que no esten mapeados 1 a 1 con las entidades. Es una implementacion no 
destructiva.

Tiene su propia manera de hacer queries, esto se debe a que no depende de la implementacion por 
debajo, de manera que es agnostico al motor de base de datos que estemos usando. Hay dos 
propiedades que son muy utiles para DEBUG, pero **pesimo** en produccion, asi que cuidado 
con eso pues esas dos cosas imprimen a salida estandar.

Al entity manager le vamos a pedir persistir, sirve tanto para crear como para actualizar. 
Se va a encargar de todo eso, si es una operacion de creacion se encarga de agregar un 
ID al objeto. El lenguaje de consulta que usa se conoce como **JQL**. Sin embargo, para que 
esto funcione hay que agregar varias anotaciones a nuestros objetos para que sepa como 
resolver los mapeos. Por defecto, si solo se agrega `@Entity` entonces tira un monton de 
defaults que generalmente no nos sirve, por ejemplo, con esto asumiria que la tabla sera 
el nombre de la clase, lo cual no suele ser cierto. Otro problema es que Hybernate va a 
crear un unico sequence global para todos los ID al menos que especifiquemos lo contrario. 
Parece poco intuitivo, pero tiene sentido(lo vamos a ver mas adelante), pero para nuestro 
caso vamos a necesitar un sequence por entidad pues asi es como teniamos la base de datos.
Cuidado con los imports, `javax.persistence` puede fallar si no tenemos el 

> En la clase le pide a Maven que haga skip de un test y la version de hybernate es 5.3.38, 
sino se rompe todo.

Como parte del proceso de inicio, lo primero que hace es registrar todos los tipos que hay, 
luego genera los identificadores y luego se conecta a la base de datos, chequea la version, 
chequea si esta bien el dialect especificado y configura varias cosas. 
Mas especificamente, empieza a buscar las clases en el paquete que especificamos, las que 
estan anotadas como entidad y realiza los mapeos.

## Mapeo de relaciones

Con JDBC el dominio y las relaciones estan intimamente relacionadas, guardamos las foreign 
key en el modelo. Sin embargo, en el mundo de objectos no existe esto, lo que deberiamos hacer 
es tener una instancia de la entidad siendo referenciada. JPA permite tenes esta abstraccion, 
encargandose de la traduccion a la base de datos. Vamos a usar varias anotaciones:

```
@OneToMany
@ManyToMany
@ManyToOne
@OneToOne
```

Por ejemplo:

```
@ManyToOne
User author;
```

Propiedades reelevantes de estas anotaciones:

```
mappedBy
target
cascade
optional
fetch
```

`mappedBy` es importante, no ponemos nombre de columna, sino el nombre del atributo del objeto, 
pues estamos en el mundo de objectos.

Si se marca el `fetch` como `LAZY`, entonces, solo va a hacer la query cuando intente 
accederlo. De manera que puede no explotar hasta el ultimo momento.

## Que la sesion sobreviva transactional

La forma de resolver esto es poner un filtro para que se le aplique a multiples servlet, 
para que no libere la sesion hasta que se termine un request. Pero se tiene que tener mucho 
cuidado, pues se creara una conexion con la base de datos independientemente si la base de datos 
la necesita o no. La contracara es que perdemos control de que queries se estan ejecutando en que 
momento.

> Hay que revisar activamente que queries estoy haciendo y si estoy siendo ineficiente.

Cuando sale de un contexto transaccional de escritura, revisa todos los objectos que se trajo de 
la base de datos, si alguna fue modificada entonces se encarga de que eso se vea reflejado en la 
base de datos. Se conoce como **dirty check**.

> El mayor problema es que se pierde el control y no se puede interpretar del codigo. No abusar 
de esto.

## Mapear enums

Como mapeamos una entidad Enum? JPA tiene una anotacion `Enumerated` para mapear un Enum. 
Nosotros tenemos que decirle que estrategia queremos usar para mapear:

- `ORDINAL`, por numero.
- `STRING`, por nombre.

Sin embargo, hay tradeoffs, desde una persceptiva de base de datos, queremos que el contenido de 
las tablas sea legible. El uso de una u otra depende de la capacidad del programador de 
modificar el codigo, con el nombre no importa que posicion ocupa, pero no voy a poder cambiarle el 
nombre. En general se prefiere guardar el nombre, pues es autodescriptivo y mas flexible. 

## Paginacion resultados 

Mirar grabacion, se penaliza fuerte y error conceptual grave.
