# Ingenieria de requerimientos

Conocer bien los requerimientos puede ser la diferencia entre un corto o un largo tiempo de desarrollo, es muy importante.

## Que es un requerimiento

- Cualquier factor que afecte el diseno del sistema
- Condicion o capacidad que un sistema debe satisfacer para cumplir con una necesidad
- Especificacion(texto, dibujo, otro tipo de herramienta visual) de lo que debe ser implementado. Descriptores de como el sistema debe comportarse, o propiedad o atributo del sistema.
- Pueden ser una restriccion en el proceso de desarrollo del sistema.

A su vez, un requerimiento puede tener muchos subrequerimientos, por ejemplo, seguridad implica muchas funcionalidades pues esta compuesta por muchas areas. 
Comportamiento y propiedad no necesariamente son lo mismo, presentan diferencias.

## Tipos clasicos

- **Funcionales**: Comportamientos, funciones, servicios. Las que se refieren por las parsonas comunes, como el cliente.
- **No funcionales**: Restricciones de calidad o condicionales del entorno(seguridad, expansibilidad, etc. cosas que terminen con -ad).
- **Dominio / restricciones**: Proviene del entorno o regulaciomnes externas. Depende de las normas de calidad(por ejemplo, algun ISO), industria, etc.

### Funcionales

Se suelen representar como **user stories**, describe lo que el sistema debe hacer, es un comportamiento especifico del sistema.

**Ejemplo**: El sistema debe permitir que los usuarios se registren con email.

### No funcionales

Define propiedad o restriccion, pero no un comportamiento especifico, son de los mas dificiles de definir y cumplir. 
Asociados generalmente a los **atributos de calidad**, son los mas importantes a la hora de disanar la arquitectura del sistema.

Algunos atributos de calidad son:

- Rendimiento
- Usabilidad
- Seguridad
- Mantenibilidad
- Portabilidad
- Interoperabilidad
- Confiabilidad
- Escabilidad

Su importancia dependera del contexto, puede ser que unos sean despreciables mientras otros sean sumamente importantes. 

Esta clasificacion es util debido a que generalmente se suele utilizar en plantillas formales y documentacion tecnica. 
Tambien porque facilida la organizacion y tracibilidad, es requerida en auditoria, licitaciones, contratos. Y es util para roles especificos: tester, arquitectos, legales.

Sin embargo, la division entre funcional y no funcional es un poco **artificial**, no necesariamente son categorias distintas. 
Por ejemplo, si el sistema no es accesible, eso seria un requisito funcional para las personas que no puedan usar el sistema sin la accesibilidad, mientras que para otros que si, es no funcional.

## Actividades de Ingenieria de Requerimientos.

Esto es secuencial, una vez se termina con una actividad se pasa a la siguiente.

1. **Extraccion o elicitacion**

Identificacion de requerimientos y stakeholders. Se utilizan tecnicas como:

- Entrevistas
- Workshops
- Observacion

2. **Analisis**

Comprension y organizacion de los requerimientos. Se utilizan tecnicas como:

- Modelado
- Prototipado
- Analisis de impacto

3. **Especificacion**

Redaccion formal de los requerimientos. Se utilizan tecnicas como:

- Plantillas
- Casos de uso
- User stories
- Diagramas

4. **Validacion**

Asegurar que los requerimientos reflejan las necesidades reales. Se utilizan tecnicas como:

- Revisiones
- Prototipos
Pruebas de aceptacion

5. **Gestion**

Control de cambios, trazabilidad y priorizacion. Tecnicas:

- Herramientas de gestion de requerimientos
- Matrices de trazabilidad

## Stakeholders

Personas o grupos que afectan o son afectadas por el sistema. No solo usuarios finales, involucra patrocinadores, soporte, legales, IT, etc. 
Cada uno tiene necesidaes y espectativas diferentes. Por lo tanto es muy importante recompilar informacion de cada uno.

- Internos vs Externos
- Operativos(usuarios directos)
- Gerenciales(responsables de decisiones y presupuesto)
- Tecnicos(IT, arquitectura, seguridad)
- Indirectos(legales, reguladores, soporte, marketing)

> **Nota**: Suele pasar que los sistemas se basan en necesidades gerenciales(no tienen experiencia directa), pero son inposible de usar para los usuarios.

### Riesgos de una mala gestion stakeholders

Puede ocurrir que los requerimientos esten incompletos o sean contradictorios, en ese caso se debe decidir cual es mas importante. 
Tambien puede haber un cambio de alanze tardio debido a falta de comunicacion, o puede haber falta de validacion o aceptacion del producto.
Finalmente, pueden realizarse decisiones basadas en suposiciones erroneas.

### Tenicas para identificar stakeholders

- Entrevistas iniciales y mapeo organizacional.
- Stakeholders map, se realiza en base a la informacion obtenida en el punto anterior.
- Personas, tecnica donde se arman arquetipoc que resumen motivaciones y necesidades(perfil inventado).
- Workshops colaborativos, alinean espectativas.

> **Nota**: Esta parte es sumamente importante, que en el fondo no son mas que requerimiento funcionales y no funcionales. Hacer las preguntas correctas.

> **Nota**: No se enamoren de la solucion, sino que del problema.

### Mapa de stakeholders

Tenemos que tener una conversacion muy fluida con los mas importante y mantener contacto y mantener al tanto a los menos importantes

#### Circulo 

Poner la solucion en el medio y luego agregar circulos concentricos. Permite ver quienes son los grupos de personas con los que se va a interactuar de manera mas fluida, 
pero tambien los que hay que mantener en contacto.

![Circulo stakeholders](graphics/mapa_stakeholders_circulo.png)

#### Esquema

Ver figura.

![Esquemas stakeholders](graphics/mapa_stakeholders_esquema.png)

#### Persona canvas

Hay muchas maneras de hacerlo, depende del proyecto. Se pueden encontrar facilmente online y rellenar. 

![Stakeholder persona](graphics/stakeholder_persona.png)

> **Nota**: Puede ser que no nos pidan usar estas herramientas, pero valoran mucho que las usemos, incluso que inventemos nuestro propio metodo.

### Buenas practicas

- Involucrar a los stakeholders desde el inicio
- Documentar que se dijo(trazabilidad)
- Mantener una comunicación continua y adaptativa
- Gestionar conflictos de prioridades con transparencia

## Extraccion de requerimientos

Es el proceso de descubrir, recopilar y entender los requerimiendos desde las partes interesantes. No se trata solo de recoger pedidos, 
sino de interpretar necesidades reales, a menudo tacicas o no articuladas. 

Vale mucho mas la pregunta correcta que la especificacion completa y con lujo de detalle. Porque suelen pasar malentendidos en el caso contrario.

> **Nota**: Hosting, db, cloud(AWS, azure, etc.) cuentan como proveedores.

### Porque es dificil

- Los stakeholders no siempre tienen claro lo que necesitan
- Expresan soluciones, no problemas (ej: “Quiero un botón para exportar a Excel” -> ¿para qué?)
- Uso de lenguaje ambiguo o contradictorio, sobre todo cuando entrevistamos personas distintas
- Suposiciones tácitas o conocimientos tribales
- Conflictos entre diferentes áreas o roles

### Tecnicas principales

- **Entrevistas**: Abiertas o estructuradas. Escuchar mas de lo que hablas.
- **Workshops o talleres**: Sesiones colaborativas(por ejemplo, event storming), permite alinear a varias personas al mismo tiempo.
- **Observacion directa(etnografia lijera)**: Ver como los usuarios realmente interactuan con el sistema(con un sistema similar), detecta necesidades no mencionadas explicitamente.
- **Cuestionarios o encuestas**: Utiles para escalar informacion cuando hay muchos usuarios.
- **Analisis de documentos existentes**: Manuales, procesos actuales, formularios, bases de datos. Por ejemplo: ver como se hacia en papel para entender el sistema.
- **Prototipos / maquetas / wireframe**: Permiten validar ideas rapido y revelar requerimientos ocultos.
- **Historias de usuario y ejemplos concretos**: Pedirle al stakeholder que *cuente como haria algo*.

### Buenas practicas

- Prepararse, conocer dominio y el contexto del negocio
- Confirmar lo entendido
- Incluir stakeholders diversos para evitar sesgos
- Documentar decisiones, no solo pedidos

### Caracteristicas de requerimientos efectivos

- **Completo**: incluye toda la información necesaria para entenderlo
- **Correcto**: describe con precisión la necesidad real que el sistema debe satisfacer
- **Viable**: puede ser implementado dentro de las capacidades y limitaciones del sistema y del proyecto
- **Necesario**: realmente necesario para el negocio o el usuario
- **Priorizado**: tiene una importancia clara y definida
- **No-ambigüo**: no da lugar a múlti ples interpretaciones
- **Verificable (testable)**: cuenta con criterios claros de aceptación

> **Nota**: Check https://asana.com/es/resources/smart-goals

## Analisis de requerimientos

Un requerimiento generalmente aparece como una entidad, va a haber un rol que se encargara de analisarlo. Involucra alcanzar una comprensión más precisa y rica de cada requerimiento y 
representar los requerimientos de diferentes maneras. Incluye la identificación de dependencias, conflictos y prioridades.
Permite detectar requerimientos redundantes, innecesarios o faltantes. Técnicas: 

- Modelos
- Diagramas de flujo
- Casos de uso
- Prototipos

### Caracteristicas de un buen conjunto de requerimientos

Debemos poder ser flexible y ser capaces de arreglarlos durante el proceso.

- **Completo**: no están ausentes requerimientos o información necesaria
- **Consistente**: los requerimientos no deben contradecirse entre sí
- **Trazable**: un requerimiento debe poder ser rastreado a su origen y a su implementación
- **Modificable**: los requerimientos deben poder ser modificados y guardar un registro de los cambios

## Validacion de requerimientos

Se realiza cada vez que se termina un sprint, al comienzo del desarrollo, etc.

Es el proceso de asegurar que los requerimientos reflejan correctamente las necesidades reales de los stakeholders.
Responde a la pregunta: ¿Esto es lo que realmente necesitamos? Se realiza antes (y durante) el desarrollo para evitar retrabajos costosos.

### Buenas practicas 

- Validar con los usuarios correctos(no solo con el cliente interno)
- Incluir validacion en **Definition of Done**
- Validar requerimientos complejos con casos de uso o prototipos
- Documentar acuerdo(quien aprueba que y cuando)

## Gestion de requerimientos

Conjunto de practicas y herramientas para organizar, restear, priorizar y controlar los cambios en los requerimientos(es muy comun que cambien).
Debemos estar preparados y que no se nos escapen cosas. Su objetivo es mantener la alineacion entre lo que se necesita, lo que se contruye y lo que se entrega.

## Trazabilidad de requerimientos

Nos lo van a pedir, muy importante. Debemos tener capacidad de rastrear cada requerimiento desde su origen hasta su imlplementacion, prueba y despliegue. Para esto se usan herramientas como
Jira, Trello, Linear, ClickUp, etc. En algunos casos tambien puede ser importante la **trazabilidad inversa**(no muy usada): desde el codigo hasta el requerimiento que lo origino.

## Priorizacion de requerimientos

Recomiendan utilizar un metodo ya existentes, los mas comunes son:

- **MoSCoW**: Must, Should, Could, Won't
- **Valor vs. Esfuerzo**: Matriz de priorizacion
- **Kano**: Satisfiers, Dissatisfiers, Delighters
 
> **Nota**: Una correcta priorización no es solo técnica: también política y estratégica

## Gestion de cambios

Es inevitable que cambien los requerimientos, por ello, necesitamos tener un proceso claro. Hay que tener en cuenta:

- Quien puede solicitar cambios
- Como se analiza su impacto
- Quien lo aprueba

## Vision alternativa: Requerimientos como hipotesis 

En lugar de ver los requerimientos como verdades absolutos, considerarlos como hipotesis de negocio/producto a validar.
Permite adoptar un enfoque mas cientifico al desarrollo de software. Se toma como una hipotesis a validar, esta validacion
se realiza mediante la implementacion.

Si no funciona, lo decarto y pruebo otra hipotesis.

## TL;DR

- Los requerimientos son fundamentales para el éxito de un proyecto de software
- Existen distintos tipos de requerimientos y formas de clasificarlos
- La gestión de stakeholders es clave para la extracción efectiva de requerimientos
- La trazabilidad, priorización y gestión de cambios son esenciales para mantener el control
- La validación de requerimientos es crucial para evitar retrabajos y asegurar que se cumplen las necesidades reales

# User stories

Son una forma de trabajar requerimientos desde la colaboracion, son la unidad minima de trabajo en un marco de agile. 
Es un objetivo final y **no** una funcionalidad, la funcionalidad va a ser parte de como vamos a llegar a ese objetivo, vista desde la perspectiva del usuario final.

Son informales, explicacion general desde la perspectiva del usuario final o cliente. Consta de algunas sentencias que remarcan el resultado final. 
No contiene el detalle, los requerimientos son agregados despues una vez se priorice la historia. 

## Objetivos

- Foco en la mirada desde el usuario final
- Habilitan la colaboracion en el equipo de desarrollo
- Apuntan a crear soluciones creativas

## Espectativas vs realidad

Muchas veces pasa que por cuestiones de negocios, se intentan meter user stories que no estaban ni escritas, entonces, surge incertidumbre al cambiar el user story 
y el scrum master debe poder adaptarse y decidir cuales stories son importantes para el proximo sprint.

Hay varias maneras de resolver un user story, entonces en la etapa de planificacion se debe llegar a un acuerdo en comun para decir que requerimientos y que recursos
implica cada user story.

El scrum master debe poder manejar la reunion de planificacion y salir con un sprint backlog, traduciendo los user stories para planificar todo lo que
van a hacer para el proximo sprint. Se encarga de facilitar todo y lo que no sea posible discutir se pasara para la siguiente reunion. Puede ocurrir que
un user story sea muy grande, entonces se parte la gran user stoy en varias user stories, o dividirla en varias subtareas.

## User stories exitosas

Presentan la siguiente estructura:

- COMO \<rol\>
- QUIERO \<evento\>
- PARA \<funcionalidad\>

**Ejemplo**:

- COMO Usuario web
- QUIERO Consultar tabla de pedidos
- PARA Saber el estado de todos mis pedidos

Deben cumplir con el postulado de **INVEST**:

- Independent
- Negotiable
- Valuable
- Estimable
- Small 
- Testable

Con este postulado se puede trabajar con metodologias agiles mediante user stories.

## Story mapping

La idea es poder visualizar el **customer journey**. Todas las user stories juntas visualmente sera muy confuso y de ahi surge el concepto de
user stories mapping para facilitar la organizacion y visualizacion de los stories.

# Stack tecnologico

Servicios y herramientas, son importantes pues:

- Permite tener enfoque claro y coherente
- Disminuye complejidad
- Ayuda a garantizar calidad y estabilidad del software

Los factores a tener en cuenta son:

- Objetivos, para determinar que herramientas usar
- Experiencia, que ya es conocido por el equipo
- Presupuesto

Busca un balance entre la comodidad y beneficios que nos ofrecen los distintos software y herramientas.
El stack esta conformado por:

- Frontend
- Backend
- Project management

## Frontend

Define la forma en la cual los usuarios interactual con nuestro sistema. Establece parte de la identidad de nuestro producto,
buscando brindarle al usuario una experiencia memorable. Se comienza con prototipos.

### Prototipos

Permiten una validacion temprana de la idea, brindan retroalimentacion de los usuarios, y ahorran tiempo y recursos. 
No tenfra logica, es simplemente para que el cliente determine si le gusta o no, ahorrando tiempo y recursos.

Herrramientas para prototipado:

- Adobe XD
- Figma

### Tecnologias web

Hacen que los productos que las utilizan puedan ser utilizados desde distiintos dispositivos(a diferencia del mobile). 
Brindan mejor experiencia en pantallas de mayor tamano y facilitan la creacion de PWA's que tambien mejoran la eperiencia mobile.
Involucra:

- HTML 5
- CSS
- Javascript
- Typescript, por encima de Javascript, con tipado estatico(detecta errores)

Se genera una interfaz web en base a ellos.

#### Frameworks de Javascript

Proporcionan un conjunto de herramientas y funcionalidades preconstruidas, ayudan a construir soluciones de forma mas rapida. 
Facilitan la reutilizacion de codigo, funcionan con Typescript. Algunos:

- React, no realmente un framework, no te limita maneras de hacer la UI
- Vue
- Angular
- Svelte

Podemos elegir el que queramos, incluso uno que no mencionaron.

#### Meta-frameworks

Se integran con frameworks y librerias existentes, ofrecen soluciones estandarizadas para problemas recurrentes.
Proporcionan funcionalidades **Out of the Box**.

Algunos:

- Astro, puede usar React y otros
- Nuxt, de Vue
- Remix, orientado a React
- Next, mas conocido(empieza a mezclar el front y el back)

#### Frameworks de CSS

Ayudan a construir interfaces visuales de una forma mucho mas rapida, se encarga de las incompatibilidades **Cross-Browser**. 
Crean una abstraccion, permiten reutilizar estilos entre componentes.

Hay varios tipos:

- **Utility libraries**: Permiten escribir CSS mas rapido.
- **Style systems**: Provee estilos predefinidos.
- **Component libraries**: Ademas de estilos predefinidos, incluyen funcionalidades predefinidas(la logica).

Algunos:

- TailwindCSS
- StyleX, utility library
- Bootstrap, style system
- NextUI, component library
- Mantine, component library
- Radix, headless component library(recomiendan si te dedicas al front)
- shadcn, headless component library

La diferencia entre ellos es el nivel de abstraccion y libertad que se le da al programador.

### Tecnologias mobile

Crean una experiencia mas cercana y personal al usuario, potencian la interaccion del usuario con las distintas funcionalidades de los dispositivos.

Se usaa mucho cuando se quiere tener una interaccion constante con el usuario, siempre cerca. Sin embargo, ahora nos veremos limitados por la plataforma,
idealmennte tendria que ser multiplataforma. Similarmente a web, hay:

- Desarrollo nativo, generalmente mas rapido y eficiente
- Desarrollo multiplataforma

Algunas: 

- Xcode, ios
- Flutter, ambas
- React Native, ambas
- Ionic, no esta buena, para browser, pero se instalan en el dispocitivo

## Backend

Encargado de gestionar y procesar la logica de negocios, junto a los datos del sistema. Define el alcance de proyecto , su estabilidad y el sustento de su correcta operacion.

Algunas funcionalidades:

- Autenticacion
- Cloud computing
- Web screping
- Base de datos
- AI y machine learning

### Propiedades de servidor

Puede ser self-hosted o third party. Cuando es **third party** hay diversos grados de compromiso por tu parte(de menos a mas costoso):

- Bare metal
- Infractruture as a service, VM
- Platform as a service, centra en desarrollo de aplicaciones
- Backend as a service, servicios preconfigurados

### Maquinas virtuales

- Amazon EC2
- Railway
- render
- Koyeb

### Backend as a service

- Azure
- Amazon Web Services(AWS)
- Firebase, mobile
- AWS Amplify, mobile
- supabase, soporta SQL(hace poco firebase tambien)

### Autenticacion

- Auth0
- clerk, orientado a starup, tiene componentes armados de frontend

### Busqueda indexada

- Algolia, pago, mas rapido
- Meilishearch, lo podes hostear


### Mensajeria y notificaciones

- Novu

### Hosting y cloud functions

- Vercel

### Serverless

- upstash


