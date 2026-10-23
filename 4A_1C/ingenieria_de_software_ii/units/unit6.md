# Unidad 6: Integracion de sistemas / SOA / Microservicios

## Residuality theory

The problem: why is software architecture so difficult? The three fundamental things are:

- Time
- Change over time 
- Uncertainity about change 

Those are things that cannot be represented and that are vital in an architecture since it changes 
over time.

Another one: what is the unit of SWE?

Physics have atoms and particles, but SWE have time, and is cannot be represented. 

- Some systems are ordered 
- Some systems are disordered <-- here is where the problem appears 

In SWE we put an ordered system inside a disordered system, and that enviroment is what 
tears the architecture apart. The enviroment can be made smaller, but reality never goes away, 
that's called technical debt. 

### Complexity science 

- Random simulations
- Kauffman networks(a.k.a. Random Boolean Network)

All we do as software engineers is a two-step algorithm:

1. Create a random simulation 
2. Do a NKP analysis on it 

### Residuality theory 

Make the simulation more random and make the NKP analysis explicit, it surfaces what we already do 
as architects and make it better. It's ar hearth a phylisophical idea.
After something happends to the complex system, it changes, the result of this is called a 
**residue**. And something, unknown, will happend to the residue. We can't predict the future, but 
we can control de residue. 

So, we need to check, before building, whats left after some attractor, what survives of the 
original naive architecture. We compress all these residues and the new architecture starts to 
survive, to resists different attractors. 

One residue con survive a multiple number of attractors, the same residue that was originated by 
on attractor. So, randombly simulating the architecture we build a stronger architecture. 

However, people are very bad at creating random simulations, so we tend to stick to conservative 
ideas that don't strenghten the architecture too much. We need to be as random as possible. 

There is something called the **edge of chaos**, which defines the boundary between a monolith 
system and a full microservices systems. Too much modules and its really hard to mantain and 
debug until it eventually collapses, one single failure in a monolith and the whole things goes 
down. 

### Contagion analysis 

Sometimes a stressor may affect two nodes that are completaly unrelated, that means that they 
are related, but we didn't see it. This is known as coupling.

## Estrategias de integracion 

Siempre hay sistemas que no son necesariamente el foco de una organizacion(pagos, 
documentos, etc.). Esto produce que en algun momento empiecen a aparecer diferencias 
que producen problemas de integracion. 

> Es un problema tan antiguo como la informatica misma 

Y por su antiguedad, estas soluciones para sincronizar sistemas se terminan repitiendo, 
es muy raro que aparezca una nueva. 

### Point-to-point

El mecanismo de integracion puede variar, pero al 
final del dia la manera mas sencilla es ir al lugar y realizar la operacion. Esto 
trae mucho acoplamiento y eventualmente colapsa si algo colapsa. El costo de maneter 
estos es sumamente alto.

### Integration hub

Una primer iteracion del sistema anterior es agregar una capa de integracion, en la 
cual la logica de integracion se encargara del acoplamiento y el resto de los 
sistemas son agnostico a todos los demas(protocolos, cantidad, etc.). En la practica, 
hay un unico punto de integracion para cada sistema(todos se conectan al entreprise 
service bus). Generalmente los ESB se compran, no se desarrollan internamente. 

Estos que se comprar suelen agregar otras cosas que le dan valor agregado, como 
herramientas de gobernado, herramientas de consiliacion, etc. Por ejemplo, el 
Business Activity Monitoring(BAM).

## SOA(Service-Oriented Architecture)

En la practica, antes era un monolito. Si se cae el SOA se para la organización, esto 
cambio mas tarde con los servicios.

### BPM y BAM 

El BPM sirve para definir y manejar los procesos definidos dentro de la
organización, usando una herramienta e (idealmente) un lenguaje de representación
de los mismos llamado BPMN (Business Process Modeling Notation) o BPEL
(Business Process Execution Language).
Los procesos son flujos con pasos (humanos o automáticos) que se pueden
mejorar, logrando aumentar la eficacia del mismo.
Un BAM es una herramienta para controlar el cumplimiento de los procesos de
negocio en tiempo real.
Veo en este momento que está pasando con mi negocio y puedo tomar acciones
para evitar problemas (Definir umbrales y notificaciones)

### Governance 

Los consumidores del servicio y los proveedores del servicio ejecutan diferentes 
procesos, son desarrollados y gestionados por diferentes departamentos y requieren de 
mucha coordinación para trabajar juntos de forma exitosa. Para que la SOA tenga éxito, 
varias aplicaciones necesitan compartir servicios comunes, lo que significa que 
necesitan coordinar para que esos servicios sean comunes y reutilizables.

Las organizacion que usan esto no son muy agiles, pero si son muy sensibles al riesgo. 
Termina habiendo departamentos enteros que garantizar la gobernanza y decidir que 
apureban y que rechazan. 

### Servicios

Es tanto un problema que sea un monolito, que en los ultimos anos se modifico SOA, 
ahora se puede probar y modificar todo de forma aislada. Cada aplicacion ahora es un 
servicio propio. Los servicios tienen asociados el concepto de granularidad:

- Los servicios de grano fino son la base de negocio sobre la que se puede
  desarrollar funcionalidad.
- Los servicios de grano grueso son servicios más complejos realizados en base
  a servicios de grano fino (y posiblemente algún adicional)

Podemos hacer servicios que realicen funcionalidades muy complejas a partir de
servicios básicos, y agregar validaciones, transaccionalidad, contingencias, etc.
Estrategias de composición(formas de coordinar):

- **Orquestacion**: Dirigida desde el ESB. Hay alguien que manda y sigue el proceso paso 
  a paso. El mas sencillo, pero tiene problemas de que pasa si un nodo falla.
- **Coreografía**: Coordinada entre los servicios. Nadie coordina, todas las piezas 
  soben lo que tienen que hacer y trabajan en paralelo. Mas facil de implementar en 
  sistemas que usan eventos. 

Aca la governance garantiza que no se publiquen servicios duplicados. Esto fue muy bueno 
porque se pueden empezar a agregar servicios alrededor de sistemas legacy, pasar a 
un SOA moderno de a poco. 

Estas tecnologias siguen siendo extremadamente reelevantes, muchas empresas son 
dependientes de sus sistemas de integracion. 
Microservicios supero en tendencia SOA, pero responde a necesidades completamente 
distinto de negocio. Como microservicios terminan siendo un monton de servicios que 
interactuan entre si, muchos de los problemas son iguales. Esto se debe a que al final 
del dia queremos combinar sistemas, mayor grado de observacion, mayor grado de 
ownership, aumento en decoupling, etc. 

El problema es, cada cajita que agrego es un punto de fallas nuevos, nadie construye 
un sistema distribuido porque queria hacer un sistema altamente confiable. 

En conclusion, SOA es aún hoy el modelo más usado en las empresas para integrar sus
aplicaciones corporativas. Resuelve problemas clásicos de integración en forma 
satisfactoria. Presenta limitaciones en cuanto a escalabilidad, fault tolerance, d
esarrollo rápido y deployments frecuentes.

> `SOA != microservicios`

## Microservicios 

Organizaciones más modernas, suelen tener más software propietario, y el mecanismo 
predilecto de integración es microservicios. Mientras SOA busca fundamentalmente 
resolver un problema primero de integración y luego de gobernanza; microservicios 
busca, ante todo, permitir a las organizaciones crecer e ir más rápido.

Cualquier organizacion tecnologica moderna, mas o menos grande, hoy en dia usa 
microservicios, pero no por gobernanza o integracion. El objetivo, es desarrollar un 
producto mas rapido. Esto es contraintuitivo pues implica un monton de trabajo, 
trae muchos problemas para una org. chica(seria mas facil hacer monolitos). Sin 
embargo, los resultados son contundentes, las organizaciones maduras capaz de adoptar 
microservicios son obsurdamente mas rapidas que aquellas que no. La diferencia es de 
varios ordenes de magnitud. 

> Un dia promedio en Pedidos Ya son 800/900 deploys 

Los monolitos son mas faciles de modificar, actualizar, deployar, etc. Pero los 
problemas aparecen cuando crece la organizacion, pues todos tocan el mismo codigo. 
Se acumulan los PR, tardan en salir y producen merge conflicts. Si uno rompe el 
build frena a todos los demas, se vuelve mucho mas necesario garantizar la calidad de 
cada build y que este bien la logica de negocio. Rapidamente una org. con un monolito 
que empieza a crecer, empieza a tener mucha mas burocracia para el flujo de deploy. 
Esto causa que los deploy terminen siendo poco frecuentes, y esto es una de las cosas 
que determina que tan rapida es una organizacion(una de muchas).

Para lograr que se de todo mas rapido deben ocurrir varias cosas:

- Reducir segmentos de conexion(partir el monopolio en pedazos y darle un pedazo a 
  equipos individuales). 
- Procesos de integracion continua 

Microservicios permite estas cosas. Por eso no tiene sentido microservicios en org. 
chicas, no hay nada que distribuir, entonces todo el overhead no vale la pena. 

### Desventajas 

- No es fácil definir correctamente los servicios.
- Multiples bases datos: ¿cómo se coordina la transaccionalidad?
-  Los microservicios constituyen un sistema distribuido, que tiene ciertas
  complejidades de desarrollo, testeo y deployment.
- El deployment de features que impactan multiples servicios debe ser
  coordinado cuidadosamente.

> Se debe decidir prudentemente cuando utilizar microservicios, porque existen
  complejidades de implementación.

La regla general es que ante la duda construir menos microservicios. 

> El unico motivo valido para decidir si agregar microservicios es la cantidad de 
  gente tocando el codigo. 

## Microservicios - Application patterns 

### Descomposition 

Dividir en subdominio y armar un servicio por cada subdominio. El patron **estrangler** 
pasa monolitos a microservicios y hace esto, va por subdominio(recorta funcionalidad 
del monolito de a poco). 

### Aggregates 

Organizar el modelo de dominio como una coleccion de aggregates, cada uno de los cuales 
es un grafo de objetos que puede ser tratado como una unidad. Busca dar una lista 
consolidada sobre entidades que en la practica estan desagregadas. 

> Permite crear entidades sinteticas que sirven para mostrar informacion

### Event sourcing 

Intenta de resolver problemas de los aggregates, en vez de construir on-the-fly trata 
de hacer una construccion local. Persistir los aggregates como una secuencia de eventos 
de dominio que representan los cambios de estado. Se puede “volver al pasado” y 
recrear la historia; y cada sistema es completamente independiente; pero a un costo 
mayor de desarrollo. 

## Persistencia 

Generalmente se tiene una base de datos, pero si son compartidas aparece lo que se 
conoce como **noisy neighbours**. Lo que ocurre es que si un servicio cambia algo, el 
otro podria dejar de funcionar. 

### Patron de sagas

Con bases de datos separadas, aparecen problemas de consistencia. Cada transacción de
negocio que es implementada por un conjunto de servicios, se implementa con una saga

**Saga**: secuencia de transacciones locales

Cada transacción local impacta la DB y publica un evento a la próxima transacción
local. Si una transaccion local falla, la saga ejecuta transacciones de compensación
para deshacer la transacción de negocio(como el two-phase commit en sharding).

No hay transaccionalidad, el sistema eventualmente hace los cambios o falla y 
revierte las cosas una por una. Hay que tener mucho cuidado con como se define la 
saga. 

### Patron CQRS: 

Command Query Responsibility Segregation. Define una replica read-only de la DB, 
se mantiene actualizada, no necesariamente es una entidad logica(la data esta duplicada). 
La replica se mantiene actualizada suscribiendo a eventos de dominios que son publicados 
por los servicios que son dueños de los datos.

## Communication patterns 

### API gateway 

Ofrece un punto de entrada sobre el cual poder implementar otras cosas y poder 
tener una separacion entre lo publico y lo privado. Luego, rutea los requests al 
microservicio correcto. Solo expone los endpoints borde(login, auth, etc.) y mantiene 
el resto de los endpoints privados(son de uso interno). 

### BFF(Backend For Frontend) 

Ofrece una API especifica para un tipo de cliente, una API para la aplicacion movil y 
otra API para la aplicacion web. Cada pantalla varia entre plataforma, entonces, se 
busca evitar overfetching. Agarra todo lo de grano fino y expone un endpoint que lo 
resume.

> Esto no se puede hacer con un servicio de grano fino(ahi si se hacen un monton de 
fetching) 

### Circuit breakers 

Operar en un sistema distribuido nos obliga a lidiar con problemas de red e
implementar:

- Timeouts
- Retries

Ante fallas masivas, esto puede generar un efecto de bola de nieve, donde la carga
sobre el sistema se incrementa en forma exponencial; e imposibilita que el servicio
se recupere. Un Circuit Breaker busca cortar esto y dar aire al sistema. Se puede 
implementar a nivel aplicación, o service mesh.

Es un problema, porque incluso cuando se repara el sistema, todos los requests llegan al 
mismo tiempo y se cae de nuevo. Lo unico que queda por hacer es cortar las requests y 
empezar de a poco, permitiendo escalar de a poco. Esto es un modo de falla muy feo que 
tienen estos sistemas y es lo que los circuit breakers vienen a resolver. Cuando la 
tasa de error supera un limite de tolerancia, corta el trafico de la raiz. 
Luego de determinado plazo, va a mandar un probe, permitiendo pasar un requests y si 
funciona abre de nuevo el trafico(puede hacerlo todo de una o de a poco, depende de la 
implementacion). Con esto se logra evitar la bola de nieve. 

## Infrastructure patterns

Hay varias:

- Physical machines
- Virtual machines
- Containers
- Containers + Kubernetes
- Serverless
