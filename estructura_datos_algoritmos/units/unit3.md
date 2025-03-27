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


