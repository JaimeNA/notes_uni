# Estructuras lineales

## Indices y estructuras posibles

En la seccion anterior se utiliza un indice como estructura auxiliar para guardar los terminos. Es muy importante poder realizar busquedas de la manera mas eficiente posible. 
Se utiliza una clave de busqueda que permite encontrar rapidamente al resto de la informacion(como un mapa). Por ejemplo, puedo buscar la informacion de un aluno por legajo, por edad, etc.
Para el caso de la edad, el indice esta **descompactado** ya que aparece el 20 mas de una vez. Si el indice esta **compactado**, aparece una edad una unica vez con una lista asociada de los alumnos con dicha edad.

### Operaciones

Ahora bien, el indice no solo se utiliza para buscar, sino que tambien hay operaciones:

- Busqueda
- Insercion
- Borrado

La complejidad temporal de cada una depende del tipo de estructura, por ejemplo, si se trata de un arregla con espacio prealocado, si quiero insertar es simplemente cuestion de insertar al final, pero todo cambia si tiene que ser ordenado o tengo que aumentar el espacio.

No hay estruturas de datos perfectas, dependen del caso. Nosotros vamos a utilizar un arreglo ordenado,

Para el caso de la **insercion** es muy constoso tener que alocar memoria en caso de requerir expandir el arreglo, por lo tanto, es muy malo ir alocando de a uno. Es una practica comun hacer crecer el arreglo de a chunks. 
Para insertar en el medio del arreglo, hay que ir swapeando del final hacia adelante.

Por otro lado, en caso del **borrado** tambien deberia ser de a chunks ya que no es ideal tener mucho espacio vacio, pero comunmente no se hace. Si queremos hacerlo podemos, pero no va a estar mal no hacerlo.

## Teorema maestro

Permite calcular la complejidad temporal de un algoritmo recursivo, solo sirve para recurrencias simetricas. Si formulando la cantidad de operaciones de un algoritmo recursiva me queda como:
$$
    T(N) = a \cdot T(\frac{N}{b}) + c \cdot N^d
$$
Entonces estamos en condiciones de utilizar el teorema maestro.

Donde: 

- N es el tamano de entrada del problema
- $a \in \text{N}_{\ge 1}$ es la cantidad de invocaciones recursivas realiza ese paso
- $b \in \text{N}_{>1}$ es la tasa en la que se reduce el tamano del input
- $c \in \text{R}_{> 0}$
- $d \in \text{R}_{\ge 0}$

### Casos

- Si $a < b^d$ entonces el algoritmo es $O(N^d)$
- Si $a = b^d$ entonces el algoritmo es $O(N^d \cdot \log(N^d))$
- Si $a > b^d$ entonces el algoritmo es $O(N^{\log_b (a)})$

> **Nota**: No siempre se puede aplicar el teorema, como el caso de Fibonacci

## Expansion recursiva

Otra manera de encontrar la complejidad de los algoritmos recursivas, lo importante es encontrar Times(N), si esta mal despues todo va a estar mal.

## Complejidad para version iterativa y para recursiva

Ya calculamos la complejidad para busqueda binaria implementada como recurrencia, pero si la implementamos de manera iterativa, cambia la complejidad?

De manera iterativa, solo voy ajustando las variables, mientras que de manera recursiva vuelvo a llamar a la misma funcion. Pero **no** cambia la complejidad temporal, por lo tanto yo puedo elejir el metodo que mas me guste.

Sin embargo, la coplejidad espacial si cambia, la complejidad espacial de la recursiva es $O(\log_2(N))$ mientras que la iterativa es $O(1)$.

## Metodos de ordenamiento

### Quicksort

Opera **in-palce**, es decir opera sobre el mismo arreglo, aplica tecnica **Divide&Conquer** y puede implementarse recursivamente o iterativamente.

Particiona en subarreglos:

- En cada subarreglo elige un pivot y ordena para que todos los elementos a la izquierda del pivot sean menores que el y los de la derecha sean mayores que el. Comeinza con el primer elemento, luego toma el primer elemento a la derecha del lugar donde quedo el primer elemento del arreglo. 
- Si un subarreglo tiene 0 o 1 elemento, esta ya ordenado(no continua) y termina la recurrencia.

#### Complejidad temporal

En el peor caso no se puede aplicar teorema maestro, el peor caso es cuando el arreglo viene ordenado(acendente o decendentemente). Esto se debe a que no se parte a la mitad.

Entonces, usamos otra forma para calcular la copmlejidad temporal para el peor de los casos.
$$
    Times(N) = N + Times(N-1)
$$
Por lo tanto, sera $O(N^2)$.

Para el resto de los casos, se divide a la mitad cada vex, entonces:
$$
    Times(N) = Times(\frac{N}{2}) + \frac{N}{2}
$$

Sera $O(N \cdot \log(2N))$

Notas que en el mejor caso si se puede aplicar el teorema maestro y se llega al mismo resultado.

#### Variantes

Como mejorar quicksort para que cuendo venga casi ordenado no de una complejidad temporal tan mala. 

### Mergesort

Hay dos versiones, una facil que genera un arreglo paralelo y otra dificil que es **in situ**(no crea otro arreglo).
