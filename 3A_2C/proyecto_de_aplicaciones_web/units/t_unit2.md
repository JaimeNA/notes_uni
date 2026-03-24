# Unidad 2: Base del projecto

Para empezar a construir el proyecto, preguntara por un template,  
usaremos el que es para proyectos de multiples modulos.

```
mvn archetype:generate
```

Podemos filtrar la lista, escribiendo `pom` vemos que ahora solo son un par de opciones.
Luego, nos va a pedir los datos que simplemente hay que completar. 

## Modulo webapp

Ahora, entrar el directorio que recien creamos y volver a correr el comando anterior. 
Vamos a generar los submodulos del proyecto, comenzando por el webapp. Buscamos el template 
`maven-archetype-webapp` y seleccionamos el modulo. El groupId lo dejamos igual, el nombre 
del modulo puede ser algo como `webapp`.

> Notar que el pom padre es modificado.

Maven va a tratar el proyecto como un todo mas alla de que dentro tenga mas proyectos, se puede comprobar al 
correr: 

```
mvn compile
```

En el directorio padre.

## Modulo servicios

Vamos a crear otro submodulo, con el comando de antes y el template default(quickstart). Le 
llamaremos `services`. 

## Defaults

Los submodulos no necesariamente vienen con los mismos defaults, de manera que queremos mover 
todas las versiones de dependencias y defaults al pom padre para que este todo sincronizado.

> Los hijos heredan la configuracion del padre.

Vamos a borrar `properties` de los hijos(version de Java) y moverlo al padre con la version 21. Las 
dependencias las vamos a mover al pom padre, donde en el `dependencyManager` vamos a definir las dependencias.
Cuando las quiera usar, Maven va a traer la version definida en el pom padre, pero no las estoy declarando 
sino que solo estoy referenciando(estamos diciendo que en el proyecto **existe** alguna referencia a dicha 
dependencia).

## Dependencias 

Vamos a usar spring, algunos de sus modulos, asi que vamos a agregar eso al pom padre.

## Codigo

El resto es simplemente programar las clases siguiendo el modelo visto en clase.

## Configurar jetty

En el pom del webapp hay que agregar un plugin nuevo, el de jetty. Se corre dentro de `webapp/` con: 

```
mvn jetty:run
```

## Configuracion

En WEB-INF vamos a tener la configuracion en un XML, eso lo queremos evitar lo mas posible. De manera que 
vamos a poner lo minimo necesario en el XML y el resto de la configuracion sera por codigo.

> **Convention over configuration**

--- 

`QueryParams` es super generico, el parametro que pido puede venir en el body, header, o incluso 
una cookie y Spring me lo va a encontrar, parsear y devolver. Sino esta `pathVariable`, una 
alternativa mas especifica para recibir parametros via URI.

> Por defecto las instancias son singleton y son las mismas en todos lados, aunque se podria 
cambian(convention over configuration).

## Vistas

Hasta ahora lo que estuvimos haciendo para mostrar las vistas es muy rudimentario, recordar 
que todo lo que esta en `webapp/` es publico, con excepcion a `WEB-INF/` que tiene los archivos 
de configuracion que queremos que sean privados.

Se pueden esconder otros archivos que estan simplemente en `webapp/`, pero para ello necesitamos 
crear un `WebMvcConfigurer`(esta es una de las opciones, hay otra que no vamos a usar).

Al mismo tiempo se pueden simplificar los paths, en vez de escribir `WEB-INF/...` se puede 
agregar un `ViewResolver`. A este no le importa quien necesita el recurso, simplemente esta ahi 
para cuando pidan un recurso.

## Base de datos

Lo que nosotros conocemos es usar reflection y `ForClass`. Pero eso no es lo mejor, si falla la 
conexion con la base de datos se pierde todo lo que estaba escribiendo(si no se hace el close 
hay un leak de memoria).

Todo esto es extremadamente ineficiente, de manera que vamos a nuevamente recurrir a 
**Spring** con `JdbcTemplate`. Spring va a hacer lo mismo que queremos evitar, pero por 
detras, ahorrandonos el problema. 

Por otro lado, las declaraciones de las tablas podrian hacerce directamente desde el codigo 
en la inicializacoin, pero no es lo correcto. Hay que definirlo de modo formal. Esto se logra 
creando un directorio donde colocaremos nuestros archivos `.sql` y los cargaremos desde el 
codigo.

No lo vamos a ver por cuestiones de tiempo, pero hay alternativas como **FlywayDB**, que 
se encarga de configurar la base de datos sin importar la version de la base de datos.

## Unit testing

La forma mas versatil de testeo de codigo. Deben cumplir con varias condiciones:

- Deterministicos(o pasan o no)
- Rapidos
- Cumplen con propiedades de tests unitarios

Pero, cuales son las propiedades?

- Alto grado de aislamiento
- No depende de la implementacion de otros metodos(parte de aislamiento)

Un test unitario depende de un unico metodo y esto generalmente es lo que mas cuesta, 
Para poder escribir un test realmente aislado va a haber que darle al constructor 
un parametro de juguete. Este puede ser:

- Mock
- Stub
- ...

Nosotros vamos a usar un mock, y la mejor libreria(y ampliamente usada) es **Mockito**.
Usa APIs de programacion dinamica que implementa una interfaz, pero todo es de mentira y 
nosotros definimos como queremos que se comporte dicha implementacion.

En la practica mas pura la idea es agregar un unico `assert`, pero nosotros vamos a agregar 
todos los `assert` que tengan sentido.:

> Usar `mockito.verify` esta **mal**, si lo ven nos descuentan puntos.

