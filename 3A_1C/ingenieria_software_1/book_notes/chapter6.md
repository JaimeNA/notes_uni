# Capitulo 6: Modelado de los requerimientos: Escenarios, informacion y clases de analisis

El modelo de requerimientos, o conjunto de modelos, es la primera representacion tecnica de un sistema.

## Analisis de los requerimientos

Da como resultado la especificacion de las caracteristicas operativas del software, permite al profesional construir sobre los
requerimientos basicos establecidos durante el proceso de ingenieria de requermientos.

Esto da como resultado uno o mas tipos de modelo:

- Modelos basados en el escenario.
- Modelos de datos.
- Modelos orientados a clases.
- Modelos orientados al flujo.
- Modelos de comportamiento.

El modelo brinda al desarrollador y al cliente medios para evaluar la calidad del software final. 
A continuacion se centrara en el **modelado basado en escenarios**, tecnica cada ves mas popular.

![Modelo de requerimientos como punte](graphics/modelo_req.png)

### Objetivos y filosofia general

Durante el modelado se enfoca en el *que* y no en el *como*, El modelo de requerimiento debe lograr:

- Describir lo que quiere el cliente.
- Establecer una base para la creacion de un diseno de software.
- Definir un conjunto de requerimientos que puedan validarse una vez construido el software.

Ademas, todos los elementos del modelo pueden rastrearse directamente hasta las partes del modelo de diseno, 
diseno y analisis no estan completamente divididas(se mezclan un poco).

### Reglas practicas del analisis

Se sugiere que un cierto numero de reglas practicas utiles cuando se crea un modelo:

- El modelo debe centrarse que los requerimientos visibles(no irse mucho a los detalles).
- Cada elemento del modelo debe agregarse al entendimiento general de los requermientos.
- Hay que retrasar las consideraciones de la infraestructura y otros modelos no funcionales hasta llegar a la etapa del diseno.
- Debe minimizarse el acoplamiento a traves del sistema.
- Es seguro que el modelo agrega valor para todos los participantes.
- Mantener el modelo tan sencillo como se pueda. 

### Analisis del dominio

Como ya se dijo, es frecuente que los patrones de analisis se repitan en muchas aplicaciones dentro del mismo negocio. 
Si se definen y se clasifica de manera que puedan reconocerse, la creacion del modelo sera mas expedita, y mejora el tiempo para llegar al 
mercado(reduciendo costos).

Se podria considerar que el analisis de dominio es una actividad sombrilla para el proceso de software, es decir, no esta conectada
con ningun proyecto en particular.

### Enfoques del modelado de requerimientos



