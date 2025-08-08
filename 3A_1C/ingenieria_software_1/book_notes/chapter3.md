# Capitulo 3: Desarrollo agil

Algunos autores creen que los procesos prescriptivos tienen una gran desventaja y es que:

>  Olvidan las flaquezas de las personas cuando construyen software. Los ingenieros de software no 
son robots. Sus estilos de trabajo varían mucho; tienen diferencias significativas en habilidad, 
creatividad, orden, consistencia y espontaneida.

> Como la consistencia de las acciones es una debilidad humana, 
las metodología que requieren mucha disciplina son frágiles.

## Manifesto agil

- Los **individuos y sus interacciones**, sobre los procesos y las herramientas
- El **software que funciona**, más que la documentación exhaustiva
- La **colaboración con el cliente**, y no tanto la negociación del contrato
- **Responder al cambio**, mejor que apegarse a un plan


## Que es la agilidad?

Aun mas especificamente, que es la agilidad en el contexto del desarrollo de software. 
Es algo mas que una respuesta efectiva al cambio, incluye la filosofia descripta en el manifesto.
Pone el énfasis en:

- La entrega rápida de software funcional y resta importancia a los productos intermedios del trabajo (lo que no siempre es bueno).
- Adopta al cliente como parte del equipo de desarrollo y trabaja para eliminar la actitud de “nosotros y ellos” que todavía invade muchos proyectos de software.
- Reconoce que la planeación en un mundo incierto tiene sus límites y que un plan de proyecto debe ser flexible.

Es importante para poder aplicar la agilidad que se disene el proceso de manera que:

- Permita al equipo adaptar las tareas.
- Ejecutar la planeacion de manera fluida.
- Dejar solo los productos del trabajo esenciales.
- Poner enfasis en una estrategia de entrega incremental.

## La agilidad y el costo del cambio

Empiricamente te puede observar que el costo de un proyecto evoluciona de forma no lineal a medida
que el proyecto avanza. En especial si se requiere ahcer un cambio en una etapa avanzada, 
donde es muy costoso y lleva mucho tiempo evitar que se haga un cambio sin efectos colaterales
no intencionados.

Un proceso agil bien disenado aplana el costo de la curva de cambio(ver imagen).

![Curva de costo durante un programa de desarrollo](graphics/agil_costo.png)

Cuando ésta se acopla con otras prácticas ágiles, como las pruebas unitarias continuas 
y la programación por parejas (que se estudia más adelante, en este capítulo), 
el costo de hacer un cambio disminuye.

## Que es un proceso agil?

Cualquier proceso del software ágil se caracteriza por la forma en la que aborda cierto número
de suposiciones clave:

- Es dificil predecir como cambiaran los requisitos.
- El diseno y la construccion deben ejecutarse, generalmente, de forma simultanea.
- El análisis, el diseño, la construcción y las pruebas no son tan predecibles como nos gustaría.

Un proceso agil debe:

- Adaptarse incrementalmente(con feedback del cliente).
- Instuirse una estrategia de desarrollo incremental.
- Entregar incrementos de software(prototipos o porciones).

### Principios de agilidad

Se definen 12 principios de agilidad para aquellos que la quieran alcanzar:

1. La prioridad más alta es satisfacer al cliente a través de la entrega pronta y continua de software valioso.
2. Son bienvenidos los requerimientos cambiantes, aun en una etapa avanzada del desarrollo. 
3. Entregar con frecuencia software que funcione, de dos semanas a un par de meses, de preferencia lo más pronto que se pueda.
4. Las personas de negocios y los desarrolladores deben trabajar juntos, a diario y durante todo el proyecto.
5. Hay que desarrollar los proyectos con individuos motivados. Debe darse a éstos el ambiente y el apoyo que necesiten, y confiar en que harán el trabajo.
6. El método más eficiente y eficaz para transmitir información a los integrantes de un equipo de desarrollo, y entre éstos, es la conversación cara a cara.
7. La medida principal de avance es el software que funciona.
8. Los procesos ágiles promueven el desarrollo sostenible. Los patrocinadores, desarrolladores y usuarios deben poder mantener un ritmo constante en forma indefinida.
9. La atención continua a la excelencia técnica y el buen diseño mejora la agilidad.
10. Es esencial la simplicidad: el arte de maximizar la cantidad de trabajo no realizado.
11. Las mejores arquitecturas, requerimientos y diseños surgen de los equipos con organización propia.
12. El equipo reflexiona a intervalos regulares sobre cómo ser más eficaz, para después afinar y ajustar su comportamiento en consecuencia.

No todos los modelos agil aplican los 12 principios, pero definen la idea principal y la filosofia de agil.

### La politica de desarrollo agil

Hay mucho debate hacerca de la efectividad de agil en comparacion con los metodos tradicionales. Esto
se torno en un tema de debate sencible que rapidamente puede llevar a una discusion. Sin embargo, 
la verdadera pregunta es cual es la mejor forma de implementar agil? 

No hay respuestas absolutas a esto, entonces, hay mucho por ganar si se considera lo mejor 
de ambas escuelas, y virtualmente no se gana nada si se denigra cualquiera de los enfoques.

### Factores humanos

Los defensores de agil se enfocan principalmente en los **factores personales**.
Si los miembros del equipo de software son los que van a generar las características del 
proceso que van a aplicarse a la elaboración de software, entre ellos debe existir cierto 
número de características clave, mismas que debe compartir el equipo ágil como tal:

- Competencia
- Enfoque comun
- Colaboracion
- Habilidad para tomar decisiones
- Capacidad para resolver problemas difusos
- COnfianza y respeto mutuos
- Organizacion propia

## Programacion extrema(XP)

Es el enfoque mas utilizado en el desarrollo agil, a continuacion se dara un panorama.

### Valores

Se define un conjunto de 5 valores:

- Comunicacion
- Simplicidad
- Retroalimentacion
- Valentia
- Respeto 

### Proceso

Usa un enfoque orientado a objetos como paradigma preferido. Las actividades estructurakes 
clave son:

- Planeacion
- Diseno
- Codificacion
- Prueba

![Actividades de XP](graphics/actividades_xp.png)

### XP industrial

IXP(Programacion Externa Industrial), es la evolucion organica de XP,
tiene un espiritu minimalista, centrado en el cliente y orientado a las pruebas que tiene XP.

IXP incorpora 6 practicas nuevas para ayudar a garantizar que un proyecto XP tenga exito:

- Evaluacion de la factibilidad
- Comunidad del proyecto
- Calificacion del proyecto
- Administracion orientada a pruebas
- Retrospectivas
- Aprendizaje continuo

Ademas, modifica algunas de las existentes en XP. La IXP hace modificaciones más pequeñas a 
otras prácticas XP y redefine ciertos roles y responsabilidades para hacerlos más asequibles 
a proyectos significativos de las organizaciones grandes. 

### Debate XP

Los nuevos modelos y métodos de proceso han motivado análisis provechosos y en ciertas 
instancias debates acalorados. La programación extrema desencadena ambos. Entre los aspectos que destacan algunos críticos de la XP están los siguientes:

- Volatibilidad de los requerimientos
- Necesidades conflictivas con el cliente(con varios clientes)
- Los requerimientos se expresan informalmente
- Falta de un diseno formal

## Otros modelos agiles de proceso

La historia de la ingeniería de software está salpicada de decenas de descripciones y 
metodologías de proceso, métodos de modelado y notaciones, herramientas y tecnología,
todos ellos obsoletos. El mas usado es XP, pero hay mas. Entre ellos se encuentran los siguientes:

- Desarrollo adaptativo de software (DAS)
- Scrum
- Método de desarrollo de sistemas dinámicos (MDSD)
- Cristal
- Desarrollo impulsado por las características (DIC)
- Desarrollo esbelto de software (DES)
- Modelado ágil (MA)
- Proceso unificado ágil (PUA)

Ver libro para una breve descripcion de cada uno.

## TL:DR

En una economía moderna, las condiciones del mercado cambian con rapidez, 
los clientes y usuarios finales necesitan evolucionar y surgen nuevas amenazas competitivas
sin aviso previo. Los profesionales deben enfocar la ingeniería de software en forma que les permita mantenerse ágiles para definir procesos maniobrables, adaptativos y esbeltos que satisfagan 
las necesidades de los negocios modernos.

El pricipal modelo agil es XP, pero otros modelos de proceso ágil también insisten en la 
colaboración humana y en la organización propia del equipo, pero definen sus actividades
estructurales y seleccionan diferentes puntos de importancia.

