# Unidad 4: Lenguajes de consulta: Algebra y calculo relacional

Hay dos tipos de lenguaje de consulta:

- **Procedurales**: Se deben especificar las operaciones
- **No procedurales**: Se describe la informacion buscada

La mayoria de los lenguajes comerciales ofrecen una mezcla de ambos. El lenguaje de algebra relacional fue propuesto por el mismo Codd.
Sirve para extraer la info de la BD.

## Algebra 

Hay dos categorias de operador:

- **Unario**: De una relacion R obtengo una relacion S.
- **Binario**: De R y R' se obtiene una relacion S.

De manera que los operadores binarios permiten anidar operaciones, y esto es muy importante.

Las relaciones del modelo relacional son conjuntos, entonces los operadores seran conjuntistas. Verifican la **propiedad de clausura** debido a que el dominio y codominio son conjuntos, 
es decir, no hay elementos repetidos. No tiene sentido que exista un operador que borre elementos repetidos.

Los operadores se pueden clasificar como:

- Fundamentales, muy pocos.
- Derivados, a partir de varios fundamentales(no potencial el lenguaje) se obtiene uno derivado, simplificando las expresiones.

### Fundamentales

- **Seleccion**: Seleccioa subconjunto de tuplas de una relacion. $\sigma_{\text{condicion}}$ (nombre de relacion)
- **Proyeccion**: Selecciona cirtas columnas de la relacion original, seran menos tuplas si no se usa una clave(repetidos). $\pi_{\text{lista de atributos}}$ (nombre de relacion)
- **Union**: Entre relaciones compatibles, nunca se obtendran menos elementos que la relacion con menos elementos, mismas propiedades que union matematica. $\text{r} \cup \text{s}$
- **Diferencia**: No es conmutativa. r - s.
- **Producto cartesiano**: Se obtiene nueva relacion con una tupla por cada combinacion de tuplas de las relaciones originales, siempre tendra |r| * |s| tuplas. 
Un problema es que haya atributos repetidos(nombre), habra operacion de renombramiento. $\text{r} \times \text{s}$

Vamos a considerar que las relaciones no contienen el valor `NULL`, salvo que se indique lo contrario. Debido a que no hay forma de determinar si dos `NULL` son iguales por definicion.

> **Nota**: Para union y diferencia deben ser **compatibles**.
> **Nota**: Un truco muy utilizado es usar > o < en vez de != para evitar que se repita la informacion(pero en distinto orden) al hacer producto cartesiano.

#### Renombramiento

Se denota con $\rho$ y se utiliza de la siguiente manera(X es el nuevo nombre):

$$
    \rho_X \text{(E)}
$$

Simplemente cambia nombre de relacion. Por otro lado, se agregan parentesis y se pone una lista de atributos, misma cantidad que habia en E(vieja relacion) para renombrarlo: 

$$
    \rho_{\text{(X)}} \text{(E)}
$$

Sin embargo, en ocaciones especiales, se puede evitar el renombramiento simplemente agregandole un prefijo a la segunda aparicion del atributo repetido(al primero no se cambia). 
De manera que se agrega el nombre de la relacion '.' y el atributo.

### Derivados

Recordar que no agregan nada, si no existieran solo llevaria mas trabajo realizar las **queries**, pero no agregan funcionalidad.

- Asignacion
- Division
- Interseccion(Similar a union, pero es derivada pues se puede obtener de restas)
- Junta
- Cociente

> **Nota**: Lo evalua en todos los examenes, muy potente.

#### Asignacion

Permite asignar una expresion del algebra relacional en una variable temporal, se usa una flecha para denotarla.
Permite definir operaciones que no son solo de consulta(insercion, borrado y actualizacoin), pero en esta materia no los vamos a usar. Por un lado, sin asignacion(ejemplo anidado):

$\pi_{\text{nombre, nota}}$ ( $\sigma_{\text{alumno.legajo=examen.legajo}}$ (alumno $\times$ examen))

En comparacion con utilizar asignacion:

auxi <- $\sigma_{\text{alumno.legajo=examen.legajo}}$ (alumno $\times$ examen)

$\pi_{\text{nombre, nota}}$ (auxi) 

#### Junta

- **Theta join**: Selecciona de todas las tuplas de un producto cartesiano aquiellas tuplas que verifican cierta condicion. r $\theta_{\text{condicion de junta}}$ s
- **Equijoin**: Parecido al theta, pero donde solo se aplica implicitamente =. En vez de $\theta_{\text{alumno.legajo=examen.legajo}}$ se usa simplemente $\theta_{\text{legajo}}$. Sin embargo, no se usa mucho pues el resultado no saca columnas.
- **Natural join**: Tal cual esta en SQL, muy util. Realiza un producto cartesionano de las relaciones, aplica la seleccion forzada la igualidad de todos los atributos que 
aparecen en ambos esquemas de relacion, y luego se aplica la proyeccion para eliminar los atributos duplicados. Tiene un simbolo que no encontre en latex, 
pero es importante conocer todos los simbolos para el examen. Pide que haya coincidencia en los valores del producto cartesiano, 
si no hay atributos en comun es equivalente al producto cartesiano. r |x| s.

- **Outer join**: Variacion de natural join, permite que las tuplas que no cumplan con la igualidad se conserven, hay dos tipos: left, right y total. Cuando no coinciden, los atributos sobrantes van con `NULL`.
    - **Left outer join**: Se conservan las tuplas de r sin correspondencia en s, simbolo: r x| s.
    - **Rigth outer join**: Se conservan las tuplas de r sin correspondencia en r, simbolo: r |x s.
    - **Total outer join**: Se conservan las tuplas de ambos lados. r x|x s.

> **Nota**: La asignacion esta en un limbo, depende del autor se considerara derivado o fundamental. Incluso se debate si es un operador.

#### Cociente

Sirve para las consultas que incluyen la expresion "para todo". S(el divisor) debe estar incluido en R. Se define, una tupla t esta en r % s $\Leftrightarrow$

1. t esta en $\pi_{\text{R-S}}$(r)
2. Para toda tupla $t_s$ de s hay una tupla $t_r$ de r que cumple:
    - $t_r$\[s\] = $t_s$\[s\]
    - $t_r$\[r-s\] = t

Este operador es un buen ejemplo de porque se considera procedural, hay que tener mucho cuidado.

![Ejemplo de uso de cociente](graphics/cociente.png)

### Definicion: Relaciones compatibles

Dos relaciones r(A1, A2, ..., An) y a (B1, B2, ..., Bn) son compatibles si tienen el mismo grado N y si dom(Ai) = dom(Bi) para todo i entre 1 y N.

> **Nota**: Para nosotros no tienen que tener igual dominio, sino que deben ser compatibles. Por ejemplo, `int` con `long`.

## Calculo relacional

El lenguaje de consulta puro y no procedural esta basado en el calculo de predicados de primer orden de la logica matematica.

Hay dos tipos de calculo relacional:

- Calculo relacional de tuplas.
- Calculo relacional de dominios.

### Calculo relacional de tuplas

Las queries tienen la forma:

{ T | formula(T)}

T representa las tuplas y la formula es la que deben verificar las tuplas de respuesta. 

#### Atomos

Hay dos formatos de atomos:

- r (V), variable(tupla) V pertenece a relacion r
- V\[i\] op U\[j\], comparar algun atributo de las variables. 
- V\[i\] op c

Las variables que figuran en las formulas pueden ser libres(glbales) o ligadoas(locales).
Las ligadas son las que estan dentro del alcance de un cuantificador existencial o universal(existe o para todo).

> Las unicas variables libres que pueden aparecer en una expresion del calculo relacional de tuplas son aquellas ligadas con un existe o un para todo.

#### Formulas

Pueden ser:

- Atomo
- f1 $\lor$ f2
- f1 $\land$ f2
- $\neg$fi

- ($\exists$ U) ($f_u$)
- ($\forall$ U) ($f_u$)

#### Tipos

- **Seguras**: Devolver al usuario una consulta en un intervalo finito de tiempo y que este bien definida. 
- **No segura**: Devuelve una relacion, pero esta es infinito / no esta bien definida. Entonces, no es interesante(como una especie de loop infinito).


> **Nota**: Prestar atencion a esto, las no seguras aparecen generalmente con negacion y cuantificador universal.

El **dominio activo** de una BS son los valores que estan presentes dentro de las relaciones, 
entonces formalmente una formula segura no puede formular consultas que extiendan el dominio activo de la BD.

Hay que usar leyes de logica para transformar una formula no segura a una segura, las que mas vamos a usar dos:

- ($\forall$ X) ($p_X$) se puede reemplazar por ($\neg \exists$ X) ($\neg p_X$)
- ($\exists$ X) ($p_X$) se puede reemplazar por ($\neg \forall$ X) ($\neg p_X$)

> **Nota**: Ver equivalencias entre algebra y el lenguaje en campus.

Es declarativo!

### Calculo relacional de dominio

{ X1, X2, ..., Xn | formula(X1, X2, ..., Xn) }

#### Atomos

- r(X1, X2, ..., Xn)
- Xi op Xj
- Xi op c

#### Formulas

 - Un atomo
 - F1 $\lor$ F2
 - F1 $\land$ F2
 - $\neg$F1
 - ($\exists$ U) (FU)
 - ($\forall$ U) (FU)

 > **Nota**: Es valido poner ($\exists$ X1, X2, ..., Xn) en vez de ($\exists$ X1) ($\exists$ X2) ... ($\exists$ Xn)

#### Tipos

Igual concepto que con las de tupla, solo cambia de tupla a dominio.


