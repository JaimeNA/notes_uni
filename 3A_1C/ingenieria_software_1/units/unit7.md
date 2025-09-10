# Testing

Es una de las areas mas importantes, pues tienen un impacto enorme en el software que se esta construyendo. 
Es la manera que tenemos de tomar mediciones para asegurarnos que un producto de software cumple con las especificaciones solicitadas.

Se recomienda incorporar el control de calidad dentro del proceso de desarrollo, no como paso final. Hay dos tipos: manual y automatico. 
Basicamente se deberia combinar ambos, pero la mayoria deberia ser automatico. 

## Tipos de testing

- Unitarios
- Integracion
- End-to-end
- Aceptacion
- Rendimiento
- Regresion
- UI
- Fuzzy testing
- Mutation testing
- Security testing(pentests)

## Test unitarios

Son los mas granulares, suelen cubrir una unidad de codigo, no hay definicion canonica de que es una unidad, pero es una funcion, clase, metodo.

Se sugiere una estructura **given_when_then**, son las tres partes en las que haya que testear el codigo.

## Test de integracion

Combina dos componentes para verificar que la interaccion sea correcta, suelen ser mas lentos que los unitarios.

> **Nota**: La definicion de integracion es mas difusa que la de unidad.

### Dobles de testeos

Se conocen como mocks, stubs o spies. Hay tres tipos:

- Dummy(pasar un objeto que no se usa)
- Fake(Cumple con implementacion, pero no de la manera correcta)
- Stubs(Da una respuesta fija)
- Spies(Registran informacio en base a como son llamados)
- Mocks(Esperan ser llamados de cierta forma y verifican esas expectativas)

Los mocks son los mas complejos.

## Test end-to-end

Ejercitan el stack completo desde la parceptiva del usuario. Son los mas completos, pero tambien los mas lentos y mas fragiles.
Muchos factores pueden contribuir a que estos test fallen, aun cuando el fallo sea casualidad. 

## Test portfolio

La variedad nos obliga a pensar en un portfolio de casos de testing que cubra la totalidad de la aplicacion y nos 
de una buena garantia de que el software funciona bien.

Se lo suele pensar como una piramide, donde la base consiste de unit test pues son los mas independientes y robustos. Luego, van los 
integration tests y encima los end-to-end tests. Finalmente en el vertice van los test manuales, pero generalmente no se debe contar con esos 
test para comprobar la funcionalidad completa. 

> **Nota**: No hay que tener test de cono de helado, donde la nube consiste de test manuales.

## Proyectos lecagy

Estos no tienen test y como estrategia es agregar unos pocos test a medida que empezamos a entender la base de codigo. 
A medida que nos familiarizemos con el codigo vamos a poder agregar test mas complejos.

## Test driven development(TDD)

El desarrollo esta guiado por la escritura de casos de testeos. Para implementar esta practica hay que seguir los siguientes pasos de forma repetida:

1. **RED**: Escribir casos de testeo para la proxima funcionalidad a implementar.
2. **GREEN**: Escribir codigo necesario para pasar el testeo.
3. **REFACTOR**: Refactoring del codigo viejo y nuevo hasta que tenga una buena estructura y calidad.

Es debatido si realmente conviene realizar los test antes o realizarlos primero.

## Code review

Es un componente fundamental, realizada por pares que ayuda a identificar problemas antes de integrar el codigo a produccion y 
a distribuir conocimiento sobre la codebase entre companeros de trabajo.

Hay que automatizar todo lo posible, como las reglas de estilo, etc. 
El tiempo de respuesta para una review dentro del quipo debe ser de no mas de 24/48hs.
En una code review se busca:

- Correctitud
- Legibilidad
- Mentinibilidad
- Rendimiento
- Estilo y consistencia

## Code coverage

Herramienta que permite identificar que partes no estan cubiertas por los test. 
Un codigo cubierto no significa que haya un test efectivo para esa parte del codigo.
Un porcentaje bajo indica un problema, pero un test completo tampoco indica que esta todo bien.



## Agile or die

### Service oriented architecture

Esta la arquitectura monolitica y la de microservicios, conviene ir de a poco desarmando la monolitica(en caso de empezar con esa) 
para llegar a una basada en microservicios. 

Una arquitectura basada en microservicios es mucho mas demandante en costo de desarrollo, pero es una evolucion natural a medida que aumenta 
la base de usuarios.

Hay una metodologia que propone Docker, Docker EE, para comparmentizar arquitecturas monoliticas.

### Tendencias en el desarrollo de software

Hay que entender cuales son las tecnologias que existen y aplicarlas a nuestros casos de uso. 
Es muy importante mantenerse actualizado, para evitar reinventar la rueda y ser mas eficiente.

> **Nota**: CBINSIGHTS es muy bueno para chequear estas cosas.
> **Nota**: Stackshare te permite ver stacks de diferentes empresas.


