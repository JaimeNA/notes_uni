# Unidad 3: Desarrollo de interfaces mobiles

## Android Studio

Entorno de desarrollo oficial de Android, basado en IntelliJ IDEA. Pero no es suficiente para 
desarrollar una aplicacion y debe complementarse con componentes dependientes de la plataforma.

Hay varias plataformas, que serian las distintas versiones de Android, recomiendad usar la ultima.
El problema principal es que consume muchos recursos.

## Estructura del proyecto

Hay distintas visualizaciones posibles para los proyectos, la default es la de Android.
La estructura en disco difiere de la representada, para ver la estructura en disco hay que cambiar 
la vista a Proyecto.

Se usa **Gradle** para manejar dependencias y copmilacion dedl proyecto con scripts.

## Android Debug Bridge

ADB es una herramienta que permite conectarse al emulador 
o dispositivo para debuggear.

## Kotlin

Lenguaje de programacion orientado a objetos, es tipado. Esta tratando de posicionarse como alternativa a React.
Soportado por cualquier SO que soporte JS o una maquina virtual de Java. 

### Beneficios

- Expresivo y conciso
- Codigo mas seguro
- Interoperabilidad(coexiste con codigo Java, compila a vitecode)
- Concurrencia estructurada

Tiene una filosofia muy fuerte con respecto a los valores nulos, no los permite.
Esto es muy bueno ya que lo hacce mas seguro debido a que esta comprobado que la mayoria 
de los errores de seguridad son causados por valores nulos.

### Caracteristicas importantes

Para el manejo de funciones en **compose**, sino es muy dificil.

- Default parameters
- Named parameters
- Trailing lambdas

### Compose

Jetback compose es un kit de herramientas moderno de Android para compilar UI nativas.

#### Porque compose

Porque requiere de menos codigo, todo es Kotlin, es mas preciso y conciso. 
Por otro lado, es mas intuitivo debido a que es declarativo.

- Menos codigo
- Intuitivo
- Acelera el desarrollo
- Potente

---

Para pensar de la manera correcta con Compose, no hay que pensar en el paradigma antiguo de Android). Hoy en dia se una un paradigma dedclarativo, 
se regenera las partes de la pantalla en base a los cambios de estado.

Todas las funciones que se usan en Compose tienen que tener `@Composable`, estas funciones deben 
ser simples en cuanto a logicas. Esto se debe a que es importante que sean rapida, no tener efectos secundarios e idempotentes(mismos parametros devuelven el mismo resultado).

Si el argumento no cambia y se vuelve a llamar la funcion, Compose no vuelve a invocar la funcion y 
usa el valor devuelto anteriormente(debido a la idempotencia).

Compose tiene una recomposicion inteligente, solo actualiza las cosas necesarias(principalmente para ahorrar bateria).

> **Nota**: Se llama compose Compose pues las funciones componen la interfaz.

#### Precauciones

- Las funciones ded composicion pueden ejecutarse en cualquier orden(detecta si se relacionan o no)
- Las funciones de composicion pueden ejecutarse en paralelo
- La recomposicion omite la mayor cantidad posible de funciones de composicion y expresiones lambda
- La recomposicion es optimista y se puede cancelar
- Una funcion de composicion se puede ejecutar con bastante frecuencia

Si el valor cambia mientras se ejecuta la funcion, Compose cancela la ejecucion, por eso tienen que ser rapidas. Deben ser como funciones matematicas, devuelven siempre el mismo valor para los mismos argumentos, independientemente del instante en el cual se ejecuta.

> **Nota**: **NO** usar valores globales

**Pregunta final**: Como desarrollador tengo que tener una consideracion a la hora de hacer funciones de compose?

SI, a pesar de la recomposicion inteligente, nosotros como desarrolladores tenemos que ayudar a compose haciendo que las funciones cumplan con las caracteristicas(devolver el mismo parametro a igual argumentos, etc.)

### Evitar recomposicion

A veces no queremos que se vuelva a componer un elemento(podrian tener los mismos datos, pero ser un objeto diferente), entonces se encapsula la funcion en un `key()` a el cual se le pasa un identificador unico. De esa manera no se reomponen todas las llamadas, solo las necesarias.

### Efectos secundarios

Pueden surgir y podrian ser necesarias, por ejemplo, si no existe la funcion que necesito. De manera que se termina utilizando una funcion afuera de `Compose`(efecto secundario).

### Estado y composicion

Sin un layout, todo serenderiza en el origen de coordenadaas, quedando todo apilado. Se puede pasar un argumento llamado `Modifier` al layout como `columns()`, esto es como un CSS, son variantes de la presentacion.

`TextField` no se actualiza a si mismo, sino que lo hace cuando cambia su parametro `value`.

Existe algo similar a `ref` en JS que se llama `MutableState`, si o si hay que utilizar la funcion `remember` cuando se usan estos objetos. Estas funciones almacenan un objeto en memoria, se puede usar para objetos mutables e inmutables. Cuando la funcion de recompone, todo lo que esta adentro se recompone, con `remember` se tiene los valores de la ejecucion anterior. Esto genera una especie de variable reactiva.

Aunque `remember` permite conservar el valor de las variables reactivas entre recomposiciones, todas las funcionan estan corriendo dentro de un proceso dentro del SO. Sin embargo, no se retiene entre cambios de configuracion. Para ello, se debe usar `rememberSaveable`, la cual almacena automaticamente el valor en un **Bundle**. Sin esto, no se guardan las reactivas en:

- Context switch(por ejemplo, cambio de idioma)
- Recuperar recursos SO(porque termino la app)

> **Nota**: Es importante que nuestra aplicacion soporte context switch, **lo van a probar**. Tiene que soportar no reiniciar todo si se cambia la orientacion de la pantalla.

### Dependencias

Originalmente las dependencias se ponian dentro del archivo Gradle, pero hace tiempo se movieron a otro archivo llamado .toml. Debido a que:

- La forma en la que se escribia la dependencia no era legible por un humano, el formato .toml es mas representativo.
- Dentro de un proyecto de android podes tener varios paquetes, y cada uno tendra su Gradle con sus dependencias. El problema es que al ser distintos las dependencias compartidas podrian tener distintas versiones, esto se soluciona en el .toml pues las dependencias van por nombre y todos los modulos tienen la misma version.

### Elevacion del estado

Es un patron que busca tener el estado en el nivel mas alto para obtener la menor cantidad de elementos con estado posible. Basicamente, el valor de algun elemento lo tiene el padre, y el elemento necesita el callback(generalmente una lambda) para cambiar el valor. No necesariamente tiene que haber un solo callback para cambiar el estado.

 Todo esto introduce un monton de buenas practicas de programacion, y tiene varias propiedades importantes:

 - Fuente unica de informacion
 - Encapsulamiento
 - Capacidad de compartir
 - Capacidad de interceptar
 - Separacion

 Las funciones de composicion no almacena la informacion, sino que lo hace una funcion externa.

 > Siempre que puedan traten de elevar el estado.

 Una alternativa a bundle, es usar paquetes, estos tienen la propiedad de `Parcelize`.

## Fases de Jetback Compose

Compose cuando tiene que armar la pantala a partir de las funciones pasa por varias estapas:

1. Composicion
2. Layout
3. Dibujo

> Los paquetes de Jetback son los `androidx...`, no usar los `android...`, puede generar errores.

## Arquitectura de aplicacion

Una aplicacion Android consta de varios componentes, como actividades, fragmentos, servicios, prooverdores de contenidos, etc.

Se hace asi porque sino cada desarrollador que quiera manejar contexto va a tener su propia implementacion privada. Para ello se utilizan los proveedores de contenido.

Todo esto debe especificarse en el **manifest** para que luego el SO lo interprete y decida cual es la mejor forma de la que funcione nuestra aplicacion.

### Factores mas importantes a tener en cuenta

- Multiples factores de forma
- Restricciones de recursos
- Condiciones de inicio variables

En especial hoy en dia, es muy importante soportar multiples factores de forma. Entonces, hay que tener en cuenta multiples factores de forma mientras se desarrolla una aplicacion.

> No se deberian almacenar datos ni estados en los componentes de la aplicacion.

No todas las aplicaciones debe tener los 4 tipos de componentes, por ejemplo, una aplicacion sin actividades. Depende de lo que tenga que hacer la aplicacion.

> Nosotros no controlamos la creacion de los componentes, esto lo hace el SO.

### Cosas importantes

Entonces, la arquitectura ayuda a organizar proyectos complejos y evitar codigos monoliticos. Mantiene las partes separadas y ordena el desarrollo(facilitanto la prueba, aunque nosostros no lo vemos en la materia).

Originalmente, no existian estos principios de arquitectura y se programaba toda la logica dentro del `Activity`. Esto hacia que sea muy dificil escalar la aplicacion.  

La idea es que la UI sea controlada a partir de los **modelos de datos**, por ejemplo, 
un usuario tiene nombre, telefono, email, etc. Estos son entidades que permiten que esos datos sean usados por otros componentes y representados en la aplicacion. Idealmente, estos modeloes de datos deben ser persistentes con una base de datos local o mediante una API.

Si no es persistente, si el SO mata la aplicacion se pierde todo lo que habia en memoria. Ademas, en caso de la persistente sea local esto mantiene todo funcionando offline o si la red es inestable.

Otra cosa importante, es tener una **unica fuente de datos**. Cada entidad que forma parte de la aplicacion(usuario, despensa, etc.) debe tener una fuente unica. Basicamente, quien tiene la informacion actualizada y real de estas entidades(tener un origen confiable de datos). Esto facilita rastrear cambios, evitar tener que decidir entre dos fuentes con informacion diferente y garantiza la consistencia de los datos.

Ademas, otro pricipio es el **flujo unidireccional**(UDF), en el cual se pasa el estado de las funcoines de mas arriva a las de mas abajo. Evita que los datos viajen en ambos sentidos y haya colisiones. Mientras que los eventos suben de las funciones mas bajas a las mas altas.

Aumenta coherencia, permite mockear y testear mas facilmente.

### Arquitectura recomendada

1. UI Layer
2. Domain Layer(Optional)
3. Data Layer

Data Layer maneja toda la logica de negocios y expone los datos. Es unidireccional tambien, de UI pasa a dominio y de dominio pasa a data.

Estas capas estan compuestas por mas capas, entonces, UI Layer esta compuesta por:

1. UI elements
2. State holders

Esta mal manejar el estado dentro de las aplicaciones, conviene manejarlo en los state holders, el mas comun es el `ViewModel`.

Analogamente, en la data layer tambien hay varias capas:

1. Repositories(uno por tipo de entidad, uno para listas, otro para usuarios, etc.)
2. Data Sources

Incluso si solo se tiene un tipo de entidad, conviene comenzar con un repository para evitar futuros inconvenientes.

### Administrar dependencias

Como todas las partes de la arquitectura se tienen que conocer entre si, hay dos premisas que permiten esto:

- **Inyeccion de dependencias(DI)**: Permite que las clases definan sus dependencias y en tiempo de ejecucion otra clase es responsable de proporcionar las mismas(por ejemplo, Dagger). Esta es la recomendada, pero es muy complejo y vamos a usar el otro patron.
- **Localizador de servicios**: Como un lugar a donde puedo ir y pedir lo que necesito. Brinda un registro en el que las clases pueden obtener sus dependencias en lugar de contruirlas.

### Capa de la UI

Tiene dos responsabilidades:

- La interfaz de usuario es una representacion visual del estado de la aplicacion. **Nada** mas, es simple.
- La capa de interfaz de usuario es el canal que convierte los cambios en los datos de la aplicacion en una forma que la UI pueda mostrar.

Algunos pasos basicos que debe hacer:

1. Consumir los datos de la aplicacion y transformarlos para la UI
2. Consumir datos que se puedan renderizar en la UI y transformarlos en elementos de la UI
3. Procesar los eventos de la interaccion del usuario y reflejar los cambios que vayan teniendo los datos en la interfaz.
4. Repetir pasos 1 y 3 hasta tener todo renderizado.

Para definir el estado de la aplicaciion exiten las `data class`, las cuales sirven para transportar datos.

Muy importante, el estado es **inmutable**, si quiero modificarlo debo crear un nuevo estado con las nuevas propiedades. Si los cambio directamente se estaria violando el principio de unidireccionalidad. Entonces, estos estados se cambian mediante eventos.

Otra caracteristica es la convevncion de nombre, donde cada uno debe tener `UiState` en el nombre. 

> Modificar el estado tiende a generar bugs.

El ViewModel no tiene internamente desperdiagadas todas las variables(podria,
pero no es lo mas adecuado). Lo que sucede es que el dato va a venir del Data Layer y va a ir 
hasta el ViewModel, donde se creara el estado de la aplicacion. Este `UiState` sera recibido por 
UI elements donde se construira la interfaz.

## Efectos Secundarios

Un efecto secundario de compose es que un cambio en el estado de la aplicacion ocurre fuera de la 
funcion de compose, de manera que surgieron los **efectos** y otros tipos que solucionan estos 
efectos secundario. 

### Effects

Funcion de composicion que no emite una IU y que permite que se ejecuten los efectos secundarios 
cuando se complete la composicion.

> Bajo ninguna circunstancia usar esto para llamar a la API.

- `LaunchedEffect` ejecuta funciones de suspencion en el alcance de la funcion de composicion.
Cuando ingresa a la composicion, inicia una corrutina(como threads, pero mejor).
Esto se usa para resolver las animaciones, se lanza la corrutina y esta se encarga de la animacion.
- `DisposableEffect`: Realiza limpieza despues de que cambian las claves o si la funcion 
abandona la composicion. MUY importarnte implementar el `onDispose` como sentencia final.
- `SideEffect`: Permite generar estado de Compose que no esta en compose, se podria usar para obtener imagenes. Pasa datos que no son de Compose en un estado compatible con Copmose.

> Cuidado con `derivedStateOf`, es muy constoso.

## Navegacion

Es super importante y en Android hay muchos principios que debemos seguir para que se adapte al 
modelo mental de usuario. La libreria **Navigation** es la encargado de esto, como un router.

> NO mostrar el cartel de "Esta seguro que quiere salir de la aplicacion?" si el usuario va para atras hasta el final, viola los principios.

### Boton up y boton back

No son lo mismo, se comportan parecido, pero no son lo mismo y hay que implementarlos.
El de abajo va para atras en el stack y el boton de arriba para atras en la jerarquia.

### Deep link

Van a una pantalla profunda de la aplicacion que el usuario no hubiera podido llegar directamente, 
lo que hay que hacer es simular la pila como si el usuario habria llegado a esa pantalla de forma 
normal.

> Para no violar con los principios de navegacion hay que implementar esto.

## Disenos canonicos

Son disenios versatiles y comprobados que proporcionan una experiencia del usuario fluida.
Basicamente, son patrones de diseno para interfaces moviles.

Algunos son:

- NavBar
- Feed
- Panel complementario

En Android hay una diferencia entre diseno responsivo y diseno adaptable, responsivo implica que 
cosas puntuales de la aplicacion responden a input, mientras que adaptable es mas en general, al 
dispositivo en el cual se esta ejecutando.

> No usar `if` en cada componente para que se adapte, sino que hay que usar lo que ofrece compose, 
teniendo en cuenta las clasificaciones de pantalla existentes(hay calificadores por ancho y por 
alto).

Cada vez que quiero tomar una desicion hay que chequear `WindowSizeClass`, el cual es el tipo 
de pantalla pasado como parametro a las funciones de composicion.

No solo alcanza con el size de la pantalla, lo ideal es que los componentes individuales 
no se fijen en que tipo de pantalla estan. Sino que se debe chequear a nivel macro y 
a partir de ahi se acomodan las funciones de composicion.

## Como usar Navigation

Tenemos:

- NavHost
- NavGraph
- NavController
- NavDestination
- Route

Primero vamos a tener que crear el `NavController` con `RememberNavController`, hay que hacerlo 
lo mas arriba posible de la jerarquia.

Existen tres tipos de destinos:

- Hosted(default)
- Dialog(destino parcial)
- Activity(pantallas o funciones unicas, **no hay que utilizarlo**)

> No exite conccepto de pantalla y dialogo por separado, son como vecinos.

El activity existe solo para soportar cosas **legacy**, no deberiamos usarlo y deberiamos 
limitarnos a las primeras dos.

> En Kotlin, data class tiene solo atributos y no metodos, mientras que class si tiene metodos.

**Nunca** generar el `NavController` arriba y pasarselo por parametro a los hijos y que los mismos 
puedan modificarlo desde donde estan, eso viola los principios de compose. 
Lo que hay que hacer es que cada componente le indique al que le paso el controller lo que 
hay que cambias.

### Vinculos directos para un destino

Pueden ser explicitos o implicitos, un vinculo directo explicito podria ser una notificacion.
Para ello hay que usar `NavDeepLinkBuilder`.

### Manejo de historial de navegacion

Usa un stack, cada vez que se usa el metodo `Navigate` se hace push al stack. 

Un problema posible es que el punto de acceso sea login, entonces una vez el usuario inicia sesion 
va a ir a la pagina principal de la app. Pero entonces, cuando haga back, el usuario podra volver 
a la pantalla de login incluso si esta logeado, mal. Hay que ver como evitar que se pushee al stack.

Otro problema, es que si el usuario cambia entre pantallas 1, 2, 3, etc. usando la barra de 
navegacion cuando haga back ira por toda la secuencia de navegacion hasta llegar a la home. Esto 
esta mal y deberia volver a home directo, habra que borrar todo lo innecesario del stack y dejar 
solo home.

Ha distintas estrategias para mitigar estos errores:

1. `navigate().popUpTo()`
2. Congelar todo en un stack y a partir de otra pantalla se tiene otro stack.

Para la segunda opcion, se conserva el stack viejo porque quiero que cuando el usuario 
vuelva a la pantalla donde se dividio se restaure el historial antiguo. Es decir, se descongela 
cuando el usuario vuelve a esa pantalla en particular.

> La segunda opcion no es necesaria para nuestro caso(TPE).

## Conectividad con API

No exite API fetch, pero si cosas parecidas. Lo que vamos a tener es un framework llamado 
RetroFit, el cual coordina otros frameworks:

- **OKHTTP**: El que coordina el canal de comunicacion entre la API y la app.
- **KotlinSerializer**: Se encarga del el formato mediente el cual se transmite la informacion.

Hay que usar el serializer, el cual es mucho mas performante pues realiza todo en compilacion y 
no en runtime. El antiguo **converter** era una libreria de google que era muy poco 
performante.

La parte de asincronico no se maneja con `await` sino que con un **pool de threads**, de manera 
que no se bloquea toda la ejecucion.
