# Capitulo 1: Software y la ingenieria de software

## Naturaleza del software

### Definicion

El software es:

1. Instrucciones (programas de cómputo) que cuando se ejecutan proporcionan lascaracterísticas, función y desempeño buscados.
2. Estructuras de datos que permiten que los programas manipulen en forma adecuada la información.
3. Información descriptiva tanto en papel comoen formas virtuales que describen la operación y uso de los programas.

### Caracteristicas que difieren del herdware

- El software se desarrolla o modifica con intelecto; no se manufactura en el sentido clásico.
- El software no se “desgasta”.
- Aunque la industria se mueve hacia la construcción basada en componentes, la mayor parte del software se construye para un uso individualizado.

### Categorias

Hoy en dia hay siete grandes categorias:

- Software de sistemas
- Software de aplicacion
- Software de ingenierias y ciencias
- Software incrustado
- Software de linea de productos
- Aplicaciones web
- Software de inteligencia artificial

En ciertos casos se elabora software nuevo, pero muchas veces simplemente se corrige, adapta y mejora aplicaciones ya exitentes.
En base a eso, aparecieron nuevos desafios:

- Computacion en un mundo abierto(computacion distribuida)
- Construccion de redes(poder crear arquitecturas sensillas para algo tan complejo)
- Fuente abierta(el codigo debe ser autodescriptivo para que sea mas sensillo colaborar)

### Software heredado

Se denomina **software heredado** a los programas mas antiguos que siguen siendo utilizados, 
son costosos de mantener y muy dificiles de hacerlo evolucionar.

Este tipo presenta caracteristicas como las mencionadas anteriormente y tambien se le agrega que en general es de mala calidad.

- No facil de actualizar
- Codigo confuso
- Documentacion mala o inexistente
- Casos y pruebas que nunca se archivaron
- Historial de cambios mal administrada

Podria dejarse como esta y seguira funcionando, pero puede ser necesario actualizarlo si:

- El software debe adaptarse para que cumpla las necesidades de los nuevos ambientes del cómputo y de la tecnología.
- El software debe ser mejorado para implementar nuevos requerimientos del negocio.
- El software debe ampliarse para que sea operable con otros sistemas o bases de datos mmodernos.
- La arquitectura del software debe rediseñarse para hacerla viable dentro de un ambiente de redes.

## Naturaleza unica de las webapps

La mayoria presentan los siguientes atributos:

- Uso intensivo de redes
- Concurrencia
- Rendimiento
- Disponibilidad
- Orientada a los datos
- Contenido sensible
- Evolucion continue(por ejemplo, youtube, estado del subte)
- Inmediatez(Llegan rapidamente al mercado)
- Seguridad
- Estetica

## Ingenieria de software

- El software esta en todos los aspectos de nuestras vidas, muchos clientes. Se concluye que debehacerse un esfuerzo concertado para entender el problema antes de desarrollar una aplicación de software.
- Las demandas son cada vez mas complejas. Se concluye que el diseño se ha vuelto una actividad crucial.
- Se depende cada vez mas del software para tomar decisiones. Se concluye que el software debe tener alta calidad.
- Al aumentar su valor percibido, aumenta la cantidad de usuarios.  Se concluye que el software debe tener facilidad para recibir mantenimiento.

### Definicion

a ingeniería de software es una tecnología con varias capas. Cualquier enfoque de ingeniería (incluso la de software) debe basarse en un compromiso
organizacional con la calidad. La administración total de la calidad, Six Sigma y otras filosofías similares 10 alimentan 
la cultura de mejora continua, y es esta cultura la que lleva en última instancia al desarrollo de enfoques cada vez más 
eficaces de la ingeniería de software. 
El fundamento en el que se apoya la ingeniería de software es el compromiso con la calidad.

- **Proceso**: El proceso de software forma la base para el control de la administración de proyectos de software, 
y establece el contexto en el que se aplican métodos técnicos, se generan productos del trabajo (modelos, documentos, datos, reportes, formatos, etc.), 
se establecen puntos de referencia, se asegura la calidad y se adminis- tra el cambio de manera apropiada.
- **Metodos**:  Los métodos de la ingeniería de software se basan en un conjunto de principios fundamentales que gobiernan 
cada área de la tecnología e incluyen actividades de modelación y otras técnicas descriptivas.
- **Herramientas**: Proporcionan un apoyo automatizado o semiautomatizado para el proceso y los métodos.
Cuando se integran las herramientas de modo que la información creada por una pueda ser utilizada por otra, 
queda establecido un sistema llamado ingeniería de software asistido por computadora que apoya el desarrollo de software.

## Proceso del software

Conjunto de actividades, acciones y tareas que se ejecutan al crear algun producto:

- **Actividad**: Busca lograr un objetivo amplio.
- **Accion**: Conjunto de tareas que producen un producto(diseno de arquitectura).
- **Tarea**: Objeto pequeno, pero bien definido(prueba unitaria), produce resultado tangible.

El proceso no es una receta a seguir, sino que se adapta al equipo de trabajo. 
La estructura del proceso esta formada por las siguientes **actividades estructurales**:

- Comunicacion(con el cliente)
- Planeacion
- Modelado(para entender mejor los requerimientos)
- Construccion
- Despliegue(entrega al consumidor)

Estan son la base, sirven para proyectos sencillos, pero para software mas grande se vuelve mas complejo.

Las actividades estructurales del proceso de ingeniería de software son complementadas por cierto número de **actividades sombrilla**.
Estas son:

- Seguimiento y control del proyecto de software
- Administración del riesgo
- Aseguramiento de la calidad del software
- Revisiones técnicas
- Medicion
- Administración de la configuración del software
- Administración de la reutilización
- Preparación y producción del producto del trabajo

> **Nota**: Un proceso adoptado para un proyecto puede ser significativamente distinto de otro adoptado para otro proyecto. 

## Practica de la ingenieria de software

### Escencia de la practica

Para poder resolver un problema, Polya plantea lo siguiente:

1. Entender el problema (comunicación y análisis).
2. Planear la solución (modelado y diseño del software).
3. Ejecutar el plan (generación del código).
4. Examinar la exactitud del resultado (probar y asegurar la calidad).

### Principios generales

Estos ayudan a establecer herramientas mentales para una practica solido. Hooker porpone siete principios:

1. Lo razon de que exista todo, debe dar valor a sus usuarios.
2. KISS(Keep It Simple, Stupid), MSE en castellano.
3. Mantener la vision, una visión clara es esencial para el éxito de un proyecto de software.
4. Otros consumiran lo que usted produce, siempre establezca especificaciones, diseñe e implemente con la seguridad de que alguien más tendrá que entender lo que usted haga.
5. Abrase al futuro
6. Plantee por anticipado la reutilizacion, no reinventar la rueda
7. Piense! Pensar en todo con claridad antes de emprender la acción casi siempre produce mejores resultados.

> **Nota**: Si todo ingeniero y equipo de software tan sólo siguiera los siete principios de Hooker, se elimi narían muchas de las dificultades que se experimentan al construir sistemas complejos basados en computadora.

