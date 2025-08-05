# Unidad 1: Caracteristicas de un DBMS

Significa *Data Base Management System*. Las bases de datos se utilizan en todos lados, incluso es normal que una organizacion manege mas de una base de datos. 

Se define como una coleccion de datos relacionados, que cumplen con las siguientes propiedades:

- Representan **algun aspecto del mundo real(mini-universo)**, no todo(por ejemplo, que idiomas hable un alumno) pues depende del objectivo
- Tienen **coherencia logica** con algun significado inherent
Se disena, contruye y se completa con datos para un **proposito especifico**. Pueden tener diferentes grupos de usuarios cada uno con **intereses distintos** y ademas cuenta con cierta aplicaciones preconcebidas para lograr cierto objetivo.

La base de datos recide en disco, se trae de a **chunks** para procesarlos. 

Una base de datos ofrece una **abstraccion** sobre los datos que almacena, por cuandto oculta detalles de su almacenamiento y manejo.

Una base de datos no posee limite en la cantidad de informacion que puede manipular, sin importar la performance la base de datos no debe pedir al usuario que debe guardar la informacion en otro lugar pues se quedo sin espacio.

## Se puedde considerer que una Planilla Excel es una base de datos?

No, debido a que:

- No se pueden definir perfiles de usuarios que accedan a determinada parte de la planilla o tengan distintos tipos de permisos. *En un excel todos pueden leer todo.*

- Aunque pueda almacenar datos, si el volumen es muy grande el recupero de la informacion en forma eficiente se vuelve conflictiva.

Basicamente, es un archivo, no una base de datos.

## Sistema de manejo de base de datos(DBMS)

Coleccion de programas que permiten crear y mantener una base de datos. 

- Crearla implica **definir los tipos** de datos, estructura y **restricciones** de los datos que se almacenen.
- Mantenerla implica **actualizar sus datos** para que reflejen los cambios del milti-universo que representa, generar reportes y poder **consultar** la informacion que posee almacenada.
- El software de DBMS no responde a fines especificos, a diferencia de la base de datos.

## Diferencias entre DMBS y un sistema de procesamiento de archivos

- **Autodescripcion(catalogo)**: En Java no habia un repo aparte donde se declaraba la informacion, sino que estaba todo embebido en el codigo(strings, constantes, etc.).
- **Independencia entre datos y programas(mejor que TAD)**: La base de datos puede cambiar en el tiempo, mientras que el programa puede no cambiar nada. Significa que hay niveles por lo cual los niveles mas bajos pueden cambiar sin que los demas se den cuenta. Esto no se puede hacer un un TAD.
- Ofrece vistas a distingtos grupos de usuarios(views)
- **Integridad de datos**: Cada vez que cambie algo, en una base de datos se valida si lo que cambie cumple con todas las reglas. Si no cumple, se notificara al usuario para que se cumpla si o si. 
- **Manejo de concurrencia**: No puede haber problemas de concurrencia, si dos usuarios cambian el dato al mismo tiempo, debe ser manejado eso. Debe soportar varios usuarios en simultaneo.
- **Manejo transaccional(atomicidad)**: Se maneja como un todo, *todo o nada*. Si se corta la luz o se corta el servicio, no puede quedar inconsistente, debe volver al estado anterior o guardar el estado para que el usuario termine la modificacion.

> **Nota**: Las ultimas dos reglas son muy dificiles de programar, no es trivial(puede llevar anos). Tambien son la mayor diferencia, son la razon por la cual se elije usar una base de datos.

## Modelo de datos

Conjunto de conceptos que se utilizan para describir la estructura(tipos de datos, relaciones y restricciones) de una base de datos.

```
Modelo -> Abstraccion
```

La mayoria de los modelos de datos ofrecen tambien un conjunto de operaciones basicas(built-in) para el recupero y actualizacion de la base de datos, 
e inclusivo algunas permiten especificar omportamientos o sea operaciones definidas por el usuario.

Como usuario solo quiero saber como va a quedar, no me interesa todos los detalles tecnicos de coo se llego a ello(como seria contruir una casa, por ejemplo). *Modelo siempre es abstraccion, sino no es modelo*.

### Categorias

- **Conceptual**, alto nivel semantico, basado en entidades, objetos.
- **De representacion o implementacion**, Modelo de red, jerarquico, relacional.
- **Fisico**, de bajo nivel o interno(estructuras, almacenamiento).

El mejor, con el que vamos a trabajar, sera el modelo basado en entidades.

### Arquitecturas de tres niveles

Definidos por ANSI. Para base de datos relacionales.

- **Nivel interno o vista interna**: Posee un esquema interno que describe la estructura de almacenamiento fisico
- **Nivel conceptual o visual conceptual**: Posee une squema conceptual que describe la estructura de la base de datos completa.
- **Nivel externo o vista externa**: Posee varias vistas de usuarios.

Se debe cambiar de uno a otro sin afectar los otros.

> **Nota**: Es el lenguaje defacto para todas las bases de datos relacionales que hay.

## Arquitecturas tipicas para un DBMS

- Cliente-Servidos centralizado 2-tier
 -...
