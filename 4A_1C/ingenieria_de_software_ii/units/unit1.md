# Clase 1: Aclaraciones

- Es diferente dependiendo de la comision
- Es totalmente practica
- Hacer y consultar por mail todo lo posible 
- Mediados de octubre unico parcial 
- El final es teorico/practico, IMP mandar mail de como esta formado el grupo
- No se manda el enunciado del final hasta que todos manden el mail
- El cuadro de atributos es el unico que se puede tener en el examen 
- 


# Unidad 1: Introduccion a la Arquitectura de Software

## Definicion: Ingenieria de Software

La Ingeniería de Software es la rama de la ingeniería que aplica los principios
de la ciencia de la computación y las matemáticas para lograr soluciones
costo-efectivas a los problemas de desarrollo de software, es decir, permite
elaborar consistentemente productos correctos y costo-efectivos.

Contruir un proyecto de software es muy complejos, ocupa tareas distintas y por 
tanto requiere de perfiles distintos(que podrian o no ser de la misma persona).

> Software architecture encompasses the set of significant decisions about the
organization of a software system including:
> - The selection of the structural elements and their interfaces,
> - Behavior as specified in collaboration among those elements,
> - An architectural style that guides this organization.”

## Definicion: Estilo arquitectonico

Igual que con patrones, el **estilo arquitectonico** tambien se toma de la arquitectura. 
Por ejemplo, decir que un edificio es brutalista o gotico nos dice un monton de que 
se puede encontrar adentro, cuando se contruyo, etc. La totalidad del edificio sigue 
estos lineamientos, y esa es la idea que llevamos al software. Nos indica como 
funciona nuesto sistema como un todo.

> La coherencia interna es importante y no se puede cambiar, sobrevivira el paso del 
tiempo.

> Un sistema legacy es un sistema sumamente existoso pues perduro a pesar del paso del 
tiempo. Se sigue manteniendolo pues aun aporta valor.

## El rol del arquitecto 

Su definicion varia segun la empresa, pero son tren tipos: 

- Arquitecto de soluciones(rol de negocio)
- Arquitecto de integracion(rol especifico)
- Arquitecto de software(rol operativo, el mas generico)

Vamos a tratar especialmente del arquitecto de soluciones, y un poco 
del de integracion en SOA. Actualemente, el rol del arquitecto esta desapareciendo, en 
la practica cada uno del equipo de programacion esta creando una arquitectura de 
software sin la necesidad de tener el rol. Sin embargo, para ello se requiere: 

- **Liderazgo**: Debe ser el modelo a seguir.
- **Negocio**: No debe perder de vista que el proyecto soluciona un problema.
- **Técnico**: Desde programación a hardware.
- **Comunicación**: Debe convencer a jefes, equipo, cliente, etc.
- **Negociación**: Siempre hay compromisos.
- **Visión**: Las decisiones son hacia el futuro.

Es importante conocer el negocio, la necesidad presente y futura sobre la que 
tiene que operar. Hay que predecir la direccion que tomara el negocio para tener 
en cuenta futuros cambios. 

## El cliente 

Al final del dia hay un problema, ya sea interno o externo de la organizacion, 
para el cual se necesitauna solucion. El cliente tienen una idea general y el 
grado de detalle suele variar mucho. Sabe lo que quiere conseguir, pero no sabe 
la complejidad y el costo que tiene. El cliente: 

### Tiene una idea de la solución

- Sabe qué quiere (a grandes rasgos)
- IWKWYSI (I will know when I see it)
- Ignora la complejidad real de la tarea

### Tiene un presupuesto

- Cuanto menos, mejor

Incluso si dice que no, tiene un presupuesto y se va a bsucar lo mos barato.

### Tiene restricciones

- Fecha de puesta en producción
- Tecnológicas
- Regulaciones

## Planificacion previa 

Se necesita **mucha** planificacion previa. 

### Qué

- Alcance
- Factibilidad
- Riesgos

Son los requerimientos, los de usuario(AKA casos de uso), los del sistema, 
los funcionales y los no funcionales. Los no funcionales no son autocontenidos, 
son trasversales al sistema y no tienen principio ni fin, a diferencia de los 
funcionales(no pueden ser un user story). 

#### Funcionales

Los funcionales son los faciles, tanto de programar como de validar. Nos vamos a 
concentrar en los no funcionales pues ya sabemos como hacerlos. Sin embargo, 
son dificiles de relevar, como se si son todos? como se si son los correctos? 

Se puede hablar con el cliente, pero como sabemos si el cliente los sabe. Es la 
**incertidumbre**, inherente a cualquier proyecto de software, y solo desaparece 
cuando el proyecto se termina. 

Nunca se va a poder eliminar la incertudumbre, pero hay que empezar eventualmente. 
Las cosas que no quedaron claras se resuelven con supuestos, los cuales deben ser: 

- Posibles 
- Probables 

> No hay forma de que la arquitectura no falle, no es improbable 

Siempre deben ser documentados, al final del dia es la definicion misma del 
sistema. 

### Cómo

- Diseño del sistema
- Recursos de hardware y software requeridos
- Equipo necesario

### Cuándo

- Armar un plan de trabajo, estimar y planificar

> Esto permite además conocer el costo. El costo mas grande en un proyecto de software 
son los desarrolladores.

## Calidad 

Define si el sistema cubre todos los casos de uso, pero no alcanza con si los cumple, 
sino que tambien debe tener en cuenta como. 

> The capability of a software product to satisfy stated and implied needs when
used under specified conditions(IEEE) 

La calidad tiene multiples dimensiones, las cuales se conocen como atributos de calidad. 
El ISO 25000 tiene una(no la unica) definicion de atributos de calidad, son muchos. 

## Atributos de calidad 

Todos son criticos para el exito, se infieren a partir de los requerimientos no 
funcionales. Es importante determinar cuales son los que tenemos que poner el foco. 

> En ingles, como son habilidades, terminan en -ity

No vamos a abarcar todos, sino los mas comunes(ver cuadro en apunte). Al momento de 
determinarlos se infieren de: 

- La operación del negocio
- El marco regulatorio
- El entorno competitivo y las expectativas del usuario

> Reliability es mas duro que disponibilidad, pues se refiere a cuanto el sistema 
pueda perdurar en el tiempo y funcionar de manera correcta

> Testeability no es tan trivial, hay sistemas que son dificiles de probar. Por 
ejemplo, el envio de mails en PAW 

Importante destacar, que no todos los atributos son **criticos**, hay que elegir cuales 
son los que realmente importa, no significa que se ignoren los demas, sino que no se 
pondra ningun esfuerzo ecepcional para complirlos. 

Por otro lado, hay que cuantificarlos a los que elegimos, un sistema que necesite ser 
disponible, cuan disponible? 90%, 99%? 

> No existe 100% de disponibilidad, cada 9 que le sumo incriementa el costo en un 
orden de magnitud

Entonces, hay que poner un limite de cuanto se quiere perfeccionar cada atributo, lo 
cual en si es otra decision que asume riesgos. Este tipo de desiciones debe poder 
ser justificadas y demostrar que se cumple. La mayor fuerza suele ser el marco 
regulatorio, de que se puede esperar como minimo. 

## Armado de la solucion 

El Arquitecto debe tomar todos los requerimientos y constraints y catalizarlos
en una solución. Todo esto, que no es poca cosa, no es todo lo que define una 
arquitectura, la arquitectura tambien es resultado de las influencias del arquitecto, 
de lo disponible tecnologicamente, de las buenas practicas del momento, la experiencia 
de la organizacion y del arquitecto(son factores internos y externos).

Pero estos no son los únicos inputs que definen la arquitectura…
La arquitectura es el resultado de influencias en aspectos técnicos, sociales,
culturales y de negocio.

Una arquitectura influye en aspectos técnicos, sociales, culturales y de negocio
de futuras arquitecturas.

> Esto es el Architecture-Business Cycle (ABC) 

## ABC - Influencias

Esto tiene sus consecuencias, pues el equipo mejora sus habilidades con las 
tecnologias, la organizacion tiene mas sistemas a mantener, etc. Esto genera un loop 
de auto-refuerzo donde sera mas propensa la empresa a volver a usar esas tecnologias. 

### Stakeholders 

Tienen expectativas y restricciones sobre el sistema, como presupuestarias, donde 
a veces se pueden contradecir entre si. El arquitecto debe detectarlas todas y 
buscar el mejor compromiso posible. 

### Negocio 

Hay dos tipos a tener en cuenta: 

- Negocio inmediato(Aprovecha sistemas existentes) 
- Negocio a largo plazo(Invertir en nueva infraestructura para el futuro) 

### Estructura organizacional 

Los conocimientos previos, que licencias hay disponibles, que herramientas hay, como 
trabaja el equipo. 

### Experiencia propia 

Es raro que se vuelva a utilizar una tecnologia que dio resultados pobres en el 
pasado. 

### Ambiente tecnologico 

La tecnología disponible, estándares de la industria o técnicas de
ingeniería de software influye sobre ella.

> Al final del dia el objetivo fundamental es reducir el riesgo

Cuando una tecnologia que ya usamos falla es mas facil de resolver que con una 
tecnologia nueva, y por lo general los fallos son en produccion. Esto plantea 
una paradoja, pues se quiere reducir los riesgos a la vez que se quiere 
mantener reelevante en el mercado. 

## ABC - Fin de ciclo 

Una vez se logra implementar una tecnologia nueva, se convierte en una decision 
estrategica, fue un riesgo. Por lo tanto, hay que ser cauto sobre que 
proyecto se utiliza para probar algo nuevo. 

Encima, una vez funcione el sistema, hay que mantenerlo, se debe seguir utilizandolo. 
Hay que decidir cuando se va a diversificar el stack tecnologico.

Una vez que la arquitectura está creada y el sistema ha sido desarrollado,
ambos afectarán:

- La estructura y objetivos de la organización que los desarrolló
- Los requerimientos futuros de los clientes
- La experiencia de los arquitectos y desarrolladores, dado que la base de
experiencia corporativa ha sido mejorada
- Generan tecnología que será tenida en cuenta para ser aprovechada en
futuros proyectos.

> Es un problema de gestion de riesgo 


