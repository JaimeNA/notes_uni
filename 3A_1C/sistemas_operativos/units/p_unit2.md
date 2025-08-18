# Unit testing

No nos lo van a pedir, pero sirve un monton. Puede haber un bug en el codigo, pero tambien puede haber un bug en el codigo que prueba el codigo.
Para evitar esta situacion hay varias tecnicas.

## Unit test

Se utilizan para probar por separado todas las partes de forma independiente, si se cambia el codigo entonces se pueden detectar errores.

> Todo junto se escribe separado.

Sin embargo, puede ocurrir que se pasen todos los test y al integrar no funcione nada. De manera que hay que prestar especial atencion a la integracion.

## Test driven development

Se utilizo en otros cuatrimestras, pero fue descartado. Basicamente se comienza con los test y luego se va al codigo.

## Testing en C

Hay varias maneras:

- Usar la macro assert, no recomendado ya que aborta con el primer fallo y no da informes.
- Hacer tu propia libreria de testeos
- Usar frameworks de terceros

## CuTest

Framework para realizar testeos, conviene usar este ya que es muy simple y lo suficientemente completo para el alcance de la materia.
Corre todos los test, no aborta, y presenta mensajes mas detallados. Ademas, tiene mayor variedad de funciones, entonces mejora la calidad.

### Terminos

- Test unitarios: Funciones que reciben por parametro un `CuTest *` e invocan alguna funcion de la libreria.
- Suite de test: Funcion que retorne un `CoSuite *` y tiene al menos un test unitaro. Engloba los test que pertencen a un modulo en particular.

> **Nota**: Tener un archivo separado para cada suite.

## Unit test structures & patterns

- Behavior-Driven Development(BDD), Given-When-Then(Precondiciones-Determidado caso-Entonces)

Repo de materia con ejemplo: https://github.com/alejoaquili/c-unit-testing-example

## Git hooks

Son scripts que Git ejecuta antes/despues de algun evento. Estan en `.git/hooks`, eventos

- Pre-commit
- Post-commit
- Pre-push
- etc.

Si falla, corta el commit. 

# Workflow GIT

Workflow basico de git para poder trabajar de manera ordenada y minimizar errores. La idea es tener un sistema de tracking de cambios
de un set de archivos,

## Branch

Representa un espacio de trabajo que contiene el codigo del repositorio y sobre la cual se pueden generar distintas versiones.

A su vez git tiene un buen sistema para unificar los cambios realizados en varias branches si se tiene una jerarquia correcta entre distintas ramas.
Generalmente se tiene la ultima version funcional en main, mientras que en la branch develop se tiene lo ultimo de desarrollo(es la branch mas activa).
Luego, se tienen las branch de develop que son las features, entonces se hace marge de una feature con develop primero y por ultimo de hace un merge con
main. Comandos utiles:

- rebase
- stash
- cherry pick
- log
- reset
