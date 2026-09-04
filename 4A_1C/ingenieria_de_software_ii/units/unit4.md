# Unidad 4: Cuando se disena una arquitectura

Hay que entender en que momento se realiza una arquitectura, de donde sale. 
Hay dos grandes opciones: 

- BDUF(Big Design Up Front)
- YAGNI(You Are Not Gonna Need It) 

BDUF requiere de una inversion mas significativa en estapas iniciales. 
YAGNI se va adaptando a medida que avanza el proyecto, involucra realizar 
refactors cada tanto, lo que implica costos. El costo aumenta a medida que mas 
se realizan desiciones, por lo que es importante tomar decisiones con la mayor 
informacion posible. 

Ambas plantean un tradeoff de cuanto se quiere invertir upfront vs que tan flexible 
se quiere que sea el sistema. Un paso intermedio es JEDUF(Just Enough Design Upfront), 
que ayuda en los casos que tenga que tirar todo y empezar de nuevo mientras. 

Para los proyectos pequenos, usar YAGNI es mas eficiente. Mientras que para proyectos 
grandes usar BDUF suele ser mas favorecedor pues hay una arquitectura de fondo. 

## Architectuural guaardrails 

Las organizaciones modernas, valoran mucho la velocidad y autonomia de los equipos, 
que puedan armar sus propias soluciones. Por lo tanto, no suele haber rol de 
arquitecto. Sin embargo, establecen limites dentro de los cuales deben operar. Estos 
limites son los **Arquitectural Guardrails**. 

Son lineamientos general que setean expectativas y limitan opciones. Se busca un 
balance entre autonomia y el nivel de predictividad que tiene cada equipo. 

**Ejemplos**: 

- Todo el procesamiento se hace sobre AWS 
- Todo servicio debe responder en menos de 50ms 
- Todo servicio debe programarse en Go o Kotlin 

## IDP 

Internal Development Platform, son internas a cada organizacion, son mecanismos 
para automatizar y enforzar los Arquitectural Guardrails. No te dan una cuenta AWS, 
sino que si o si se usan estas herramientas. 

Todas ofrecen un **Single Pane of Glass**, los developer pueden ver como esta todo 
por detras, un lugar unico y central donde los equipos pueden entender y administrar sus 
aplicaciones de punta a punta. 


Productiza **Golden Paths** para que los equipos apliquen los guardrails practicas de 
ingenieria organizacionales. 

> Es muy dificil que se dejen salir y hacer algo de otra manera, hay que tener una muy 
buena razon para irse de lo permitido

Si se usa algo nuevo, no se sabe como reaciona, como falla, si es bueno. Eso es un 
riesgo con un costo altisimo, por eso se trata de que no ocurra. 

## Evaluacion 

Hay que evaluar todos los atributos, ya sean cualitativos o cuantitativos. 

Se puede evaluar con: 

- Escenarios 
- Checklists 
- Metricas 
- Simulaciones(DSL para evaluar comportamiento) 
- Experimentos(software para probar una hipotesis sobre comportamiento) 
- Prototipo(version preliminar, podria pasar a ser el producto) 
- Modelos matematicos(justifica su empleo en casos de alta critticidad) 
- Metodologias basadas en escenarios(SAAM, ATAM, Lightweight ATAM)

> Ejemplo de DSL para simular arquitecturas: Palladio 

> Los drivers de negocio son los atributos de calida 

### Arbol de utilidad 

Sirve para enfocar el trabajo a realizar, se arranca con un primer nivel donde cada 
nodo es un atributo de calidad. Luego, un nivel mas abajo estan los escenarios(segundo 
nivel), buscan ayudar en la cuantificacion de los atributos. Luego, cada escenario 
tiene como atributos(tercer nivel): 

- Dificultad(de resolver la problematica)
- Importancia 

> En el parcial te puede pedir armar un arbol de calidad hasta nivel 2 o mas(no se) 

### Lightweight ATAM 

En vez de tardar 20 dias, se puede hacer en medio dia. Sacan reporte y procesos 
burocraticos y se enfocan en armar la arquitectura. Como ATAM, se usa para validar un 
sistemas, que podria ser para validar un sistema que me estan vendiendo o uno que es 
interno a una empresa que estoy comprando. 


