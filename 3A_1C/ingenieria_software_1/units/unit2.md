# Unidad 2: Extreme Programming

El desarrollo de software presenta varios problemas:

- Entregas tardias y/o incompletas
- Cancelacion de proyectos
- Sistema desactualizado
- Alta tasa de errores
- Reglas de negocio sin entender
- Cambios de requerimientos
- Desercion de programadores
- Ambientes de alto grado de incertidumbre

## Agile manifeso

Para solucionar esto se propone las siguientes reglas:

- **Individuals and interactions** over processes and tools
- **Working software** over comprehensive documentation
- **Customer collaboration** over contract negotiation
- **Responding to change** over following a plan

> **Nota**: Habia anotado mas cosas, pero se perdio. Basicamente era un desarrollo de cada punto, pero se puede deducir.

## Extreme programming

- Metodología liviana, eficiente de bajo riesgo, flexible, predecible y científica.
- Orientada a equipos pequeños o medianos.
- Para desarrollar software teniendo como premisa que los requisitos cambian rápidamente.
- Especialmente diseñadas para ambientes de gran incertidumbre.

### Valores

![Valores de XP](graphics/valores_xp.png)

### Practicas

#### Juego de planificacion

Determinar rapidamente el aclance de la proxima release combinando las prioridades del negocio y las limitaciones tecnicas. Planificar como maximo dos releases por adelantado.
Como la realidad supera la planificacion, actualizar el plan.

#### Releases pequenas

Poner en produccion el sistema mas pequeno que funcione, luego agregar funcionalidad iterativamente. 
Cada release debe tener una unidad de coherencia.

#### Metaforas

Guiar a todo el grupo de desarrollo con una historia simple de como debe funcionar el sistema. Ayudar a comprender los elementos basicos del sistema, debe ser refinada a medida que avanza el proyecto.
Es una vista desde *10k metros* del sistema.

#### Diseno simple

El sistema debe ser lo mas simple posible, remover toda complejidad. El diseño correcto
 1. Debe pasar todos los testeos
 2. No tiene lógica duplicada
 3. Muestra la intención del programador
 4. Tiene la menor cantidad posible de métodos y clases

El futuro es incierto, entonces no diseñar de más para una futura funcionalidad

#### Testing

Los testeos unitarios deber ser parte del codigo. Los testteos de funcionalidades ...

#### Refactoring

Implica llevar el programa a la forma mas simple una vez que se introducen cambios, favorece el agregado de nuevos cambios. Si existe codigoduplicado debe refactorizarse.

#### Pair programming

Una de las personas escribe el codigo y piensa en la mejor forma de hacerlos, la otra persona piensa de forma estrategica(Es correcta la solucion? Hace falta definir otro testeo? Hay forma de simplificarlo?).
La pareja debe ir rotando para fomentar el conocimiento del sistema.

#### Compartir autoria

Cualquiera que ve la oportunidad de agregarle valor a un codigo debe hacerlo, no hay una persona que sea duena de un bloque de codigo. Mejora los tiempos de correccion de bugs, se ve favorecida por la programacion de a pares.

#### Integracion continua

El codigo debe ser integrado y testeado a intervalos regulares. Economicamente conviene tener una maquina dedicada a esta tarea, cualquier persona que agregue cambios a la release debe cerciorarse que el 100% de los testeos sean exitosos.

#### 40hs semanales

Trabajar más de esta cantidad de horas degrada la capacidad creativa del equipo. Esto repercute directamente en la cantidad de bugs presentes, se permite que una semana se trabaje más horas. 
Si esta situación se presenta en dos semanas consecutivas entonces el problemano puede solucionarse trabajando más horas

#### Cliente on-site

El cliente debe estar con el equipo, disponible para responder cualquier duda que pueda surgir. Por cliente se entiende alguien que va a utilizar el sistema cuando el mismo se encuentre en producción.
Mientras no es consultado, el cliente puede seguir realizando su trabajo.

#### Estandares de codificacion

Se deben definir para facilitar la práctica de la autoría compartida de código. Deben tener como premisa hacer el mínimo trabajo posible
Debe enfatizar la comunicación. El equipo debe adoptarlo de común acuerdo.

### Lifecycle

![XP lifecyle](graphics/lifecycle_xp.png)

### Resuelve problemas de software

Prevee cuando se va a hacer algo mal, siempre corrijiendo. 

> **Nota**: Hay mas en el apunte.

### Agile benefits

- Better product quality
- Customer satisfaction
- More control on projects
- Risk reduction
- Faster ROI
- Faster time to market
- Higher productivity

> **Nota**: XP es solo usado por el 1% de los programas de desarrollo.


