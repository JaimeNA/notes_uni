# Unidad 3: Estructuras lineales

## Arreglos 

### Indices y estructuras posibles

En la seccion anterior se utiliza un indice como estructura auxiliar para guardar los terminos. Es muy importante poder realizar busquedas de la manera mas eficiente posible. 
Se utiliza una clave de busqueda que permite encontrar rapidamente al resto de la informacion(como un mapa). Por ejemplo, puedo buscar la informacion de un aluno por legajo, por edad, etc.
Para el caso de la edad, el indice esta **descompactado** ya que aparece el 20 mas de una vez. Si el indice esta **compactado**, aparece una edad una unica vez con una lista asociada de los alumnos con dicha edad.

#### Operaciones

Ahora bien, el indice no solo se utiliza para buscar, sino que tambien hay operaciones:

- Busqueda
- Insercion
- Borrado 

La complejidad temporal de cada una depende del tipo de estructura, por ejemplo, si se trata de un arreglo con espacio prealocado, si quiero insertar es simplemente cuestion de insertar al final, pero todo cambia si tiene que ser ordenado o tengo que aumentar el espacio.

No hay estruturas de datos perfectas, dependen del caso. Nosotros vamos a utilizar un arreglo ordenado,

Para el caso de la **insercion** es muy costoso tener que alocar memoria en caso de requerir expandir el arreglo, por lo tanto, es muy malo ir alocando de a uno. Es una practica comun hacer crecer el arreglo de a chunks. 
Para insertar en el medio del arreglo, hay que ir swapeando del final hacia adelante.

Por otro lado, en caso del **borrado** tambien deberia ser de a chunks ya que no es ideal tener mucho espacio vacio, pero comunmente no se hace. Si queremos hacerlo podemos, pero no va a estar mal no hacerlo.

### Teorema maestro

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

#### Casos

- Si $a < b^d$ entonces el algoritmo es $O(N^d)$
- Si $a = b^d$ entonces el algoritmo es $O(N^d \cdot \log(N))$
- Si $a > b^d$ entonces el algoritmo es $O(N^{\log_b (a)})$

> **Nota**: No siempre se puede aplicar el teorema, como el caso de Fibonacci

### Expansion recursiva

Otra manera de encontrar la complejidad de los algoritmos recursivas, lo importante es encontrar Times(N), si esta mal despues todo va a estar mal.

### Complejidad para version iterativa y para recursiva

Ya calculamos la complejidad para busqueda binaria implementada como recurrencia, pero si la implementamos de manera iterativa, cambia la complejidad?

De manera iterativa, solo voy ajustando las variables, mientras que de manera recursiva vuelvo a llamar a la misma funcion. Pero **no** cambia la complejidad temporal, por lo tanto yo puedo elejir el metodo que mas me guste.

Sin embargo, la coplejidad espacial si cambia, la complejidad espacial de la recursiva es $O(\log_2(N))$ mientras que la iterativa es $O(1)$.

### Metodos de ordenamiento

#### Quicksort

Opera **in-palce**, es decir opera sobre el mismo arreglo, aplica tecnica **Divide&Conquer** y puede implementarse recursivamente o iterativamente.

Particiona en subarreglos:

- En cada subarreglo elige un pivot y ordena para que todos los elementos a la izquierda del pivot sean menores que el y los de la derecha sean mayores que el. Comeinza con el primer elemento, luego toma el primer elemento a la derecha del lugar donde quedo el primer elemento del arreglo. 
- Si un subarreglo tiene 0 o 1 elemento, esta ya ordenado(no continua) y termina la recurrencia.

##### Complejidad temporal

En el peor caso no se puede aplicar teorema maestro, el peor caso es cuando el arreglo viene ordenado(acendente o decendentemente). Esto se debe a que no se parte a la mitad.

Entonces, usamos otra forma para calcular la complejidad temporal para el peor de los casos.
$$
    Times(N) = N + Times(N-1)
$$
Por lo tanto, sera $O(N^2)$.

Para el resto de los casos, se divide a la mitad cada vez, entonces:
$$
    Times(N) = Times(\frac{N}{2}) + \frac{N}{2}
$$

Sera $O(N \cdot \log(N))$

Notas que en el mejor caso si se puede aplicar el teorema maestro y se llega al mismo resultado.

##### Variantes

Como mejorar quicksort para que cuendo venga casi ordenado no de una complejidad temporal tan mala. 

#### Mergesort

Hay dos versiones, una facil que genera un arreglo paralelo y otra dificil que es **in situ**(no crea otro arreglo). Funciona de la siguiente manera:

1. Divide el arreglo en dos mitades recursivamente hasta que no se pueda dividir mas
2. Recursivamente ordenar cada mitad
3. Unir las dos mitades

##### Complejidad temporal 

Primero planteo el numero de ecuaciones:

$$
    Times(N) = 5 + (N-1) \cdot 4 + 2 \cdot Times(\frac{N}{2})
$$

Veo que tiene la forma del teorema maestro, entonces:

$$
    Times(N) = N \cdot 4 + 2 \cdot Times(\frac{N}{2})
$$

Sera $O(N \cdot \log(N))$

### Generics en Java

Java es un lenguaje estaticamente tipado, hay que decir que tipo es una variable antes de usarla. Sin generics, son casteos son una posibilidad de errores que se detectan en tiempo de ejecucion. Generics esta pensado para parametrizar y minimizar errores.

Tecnicamente hablando, Generics fue implementado usando la **Tecnica de Erasure**, la cual consiste en reemplazar todo tipo de parametro con su "bound/restriccion" y si no lo hay reemplaza por Objects.

**Por ejemplo**:

- `<T> is unbound => Object`
- `<T implements Comparable> => Comparable`


`private T[] arreglo = new T[];` falla, no compila. 
Entonces, `ArrayList` internamente implementa un arreglo, pero para Generics no se puede simplemente declarar un arreglo, como lo hace? No puedo crear dinamicamente un arreglo de tipo parametrico(en tiempo de ejecucion) porque su tipo no se conoce ya qye en compilacion se hizo erasure.
Hay tres maneras que podriamos usar para solucionar esto, pero la tercera no nos sirve en nuestro caso:

1.  Guardar un arreglo de `Objects`(no T). Castear cuando sea necesario, entonces cuando me piden un elemento simplemente uso el casteo(seguro porque ya se el tipo). 
2. Usar reflection, internamente uso un arreglo de T, pero el new tiene que usar reflection. Por lo tanto, en `initialize` pido que me de `Class<t> the class` con un `@SuppressWarnings("unchecked")`.
Usando `(T[]) Array.newInstance(theCLass, dim)`.
3. Usando `(E[]) new Object[dim]` (con el `SuppressWarnings`), parece similar al reflection, pero en realidad estoy con un arreglo de `Object` que tengo que castear cada vez que necesite un elemento, mientras que reflection tiene un arreglo de T.

Vamos a descartar la tercera, pues no permite tener un Generics que sea **bounded**. Por lo tanto, vamos a usar la primera o la segunda.

### Como incluir `.jar`

Primero, dejar la biblioteca que quieras agregar en un path sin blancos. Luego, en Maven:

``` xml

<!-- Sin Reflection -->
<dependency>
    <groupId>ar.edu.itba.eda</groupId>
    <artifactId>IndexWithArray</artifactId>
    <version>1</version>
    <scope>system</scope>
    <systemPath>/.../IndexWithArray-1.jar</systemPath>
</dependency>
```

## Stack

En esta estructura nos enfocamos en el orden de llegada de los elementos. Por ejemplo, los editores de texto implementan este tipo de estructura para "deshacer" acciones. Las operaciones que ofrece son:

- `push`
- `pop`
- `isEmpty`
- `peek`, no obligatorio, pero muy util.

### Implementacion

Con una simple lista encadenada o un arreglo se puede implemeentar. En caso de arreglo la contiguedad esta garanttizada y nunca hara falta mover elementos, pero si se acaba el espacio se debe buscar espacio conttiguo en otro lugar. Por otro lado, si se uttiliza una lista lineal simplementte ecadenada, el elemento mas a la "izquierda" sera el tope. 
Java ya cuenta con una implementacion para el Stack, pero no es muy buena ya que al extender otra clase, tiene un monton de metodos innecesarios. Es decir, desde el punto de vista OOP, es una mal practica.

Se nos permite usar la implementacion de Java, pero es recomendable que hagamos nuestra propia version. Podemos usar la de Java, pero solo con los metodos mencionados anteriormente, por ejemplo, no podemos usar el `add`.

### Caso de uso: Evaluador de expresiones

En este caso solo vamos a considerar los operadores binarios. Las expresiones se pueden clasificar la la siguiente manera: prefija(operador antes de operandos), infija(operador enntre operandos) y postfija(Operados despues de operandos). Un problema con la infija es que hay operadores con mayor procedencia, tambien se pueden operar de izquierda a derecha, pero en casos como la potencia es de derecha a izquierda, hay que tener en cuenta esto. Tambien, exite la polaca, infija reversa, etc. pero solo nos enfocamos en las tres de antes.

Hay varios algoritmos para evaluar expresiones, el siguiente algoritmo toma expresiones posfijas:

1. Cada operador es una expresion posfija se refiere a los operandos previos a la misma.
2. Cuando aparece un operando hay que postergarlo porque no se puede hacer nada con el hasta que no llegue el operador, se pushea al stack.
3. Cuando aparezca un operador en la expresion implica que llego el momento de aplicarselo a los operandos, se procede a popearlos del stack.
4. Cuando se termine de analizar la expresion de entrado el resultado de su evaluacion es el unico valor que debe quedar en la pila.

> **Nota**: La clase `Scanner` de Java es muy util para este tipo de aplicaciones pues permite recibir un input por archivo, recivir un input por entrada estandar, separalo en toquens. Hay que tener cuidados con los metalenguaje, como con el +, hay que escparlos con `\\`.

### Infija a posfija

Existe un algorimo muy famoso para realizar esta operacion. La idea es que cada vez que aparezcan varios operadores se consultara una tabla que indique cual se evalua primero. Si dos operadores tienen la misma precedencia, se utiliza la regla de asociatividad para saber cual se evalua primero.  
Para comparar, vamos a usar una tabla donde en la primer columna va el operador previo y en la primera fila el operador actual. Entonces, si en fila hay un * y en columna un +, entonces se devuelve un `false` pues el que estaba en tope de la fila tiene menos precedencia sobre el elemento actual.

Pasos del algoritmo:

1. Si la pila esta vacia, se pushea el operador **current** ya que no se lo puede comparar.
2. Si la pila no esta vacia:
    - Si el tope de la pila tiene mayor precedencia que el current, entonces se realiza un pop.
    - Si el tope tiene menor precedencia, el current no se puede ir todavia(lo pushea).
3. Al finalizar, se popea lo que haya quedado en el stack.

Esto funciona para los operadores asociativos a izquierda, pero ahora si queremos implementar el operador exponencial necesitamos considerar el asociativo a izquierda, pero no hace falta cambiar el algoritmo, simplemente basta con modificar la tabla.
Es un buen diseno debido a su flexibilidad.

|       | +     | -     | *     | /     | ^     |
| ----  | ----  | ----  | ----  | ----  | ----  | 
| +     | true  | true  | false | false | false |
| -     | true  | true  | false | false | false |
| *     | true  | true  | true  | true  | false |
| /     | true  | true  | true  | true  | false |
| ^     | true  | true  | true  | true  | false |

Par agregar soporte para parentesis, hay que considerar lo siguiente:

1. Si se encuentra un parentesis que abre, debe postergarse hasta encontrar uno cerrado.
2. Si el operador current es un parentesis que cierra el mismo debe sacar todos los operadores del stack hasta encontrar el parentesis que abre. Luego, se sca el parentesis que abre de la pila y de lo descarta(no hay parentesis en postfija.

Para incorporar esto vamos a usar la tabla. Notar que no tiene sentido que en el stack haya un parentesis que cierra.

|       | +     | -     | *     | /     | ^     | (     | )     |
| ----  | ----  | ----  | ----  | ----  | ----  | ----  | ----  |
| +     | true  | true  | false | false | false | false | true  |
| -     | true  | true  | false | false | false | false | true  |
| *     | true  | true  | true  | true  | false | false | true  |
| /     | true  | true  | true  | true  | false | false | true  |
| ^     | true  | true  | true  | true  | false | false | true  |
| (     | false | false | false | false | false | false | false |

## Cola

Son muy utilizadas para algoritmos, por ejemplo, BFS en grafos. O simplemente se usan para manejar una fila the eventos como el caso de un **pipe**.

### Definicion: Queue

Coleccion de datos ordenada por orden de llegada. La unica forma de acceso es por medio de dos elmentos distinguidos: FIRST(El mas antiguo) y LAST. Las operaciones que ofrece son:

- `queue(element)`
- `deque()`
- `peek()`
- `isEmpty()`
- `size()`, esta es opcional, pero puede ser util.

Si se implementa como lista, conviene que el `first` sea el primer elemento y `last` el ultimo. Esto permite O(1) para obtener `first`. 

En el caso de implementarlo com arreglo, ambas maneras tienen un problema, tengo que mover todo si `last` es el primero y si `first` es el primero va a haber espacios en blanco cada vez que haga un `deque()`. Si se acaba el espacio, hay que realocar(conocido como **unbounded queue**), pero existe una forma de arreglar esto: Utilizando un arreglo circular. Si no el arreglo no va a crecer, entonces se conoce como **bounded queue**, este seria el caso del queue circular.

En conclusion, si se tiene un unbounded queue la mejor implementacion es la que utiliza una lista, pero si tengo un bounded queue la mejor implementacion es la qu utiliza un arreglo.

## Lista

Supera el problema de contiguedad y re-alocacion de espacio, podemos pensar en estructuras de datos que permitan que los elementos esten "fisicamente aislados y logicamente conectados". Sin embargo, nunca superara una lista ordenada al arreglo ordenado.

### Lista lineal simplemente encadenada

Es una estructura de datos que esta compuesta por 0 o mas nodos. Cada nodo(elemento) tiene referencia al siguiente.

### Insertar

Hay tres maneras de insertar un nodo, tenemos que saber y entender las 3:

1. Iterativa desde afuera de nodo.
2. Recursiva desde afuera de nodo.
3. El nodo mismo debe tener su propia funcion insert. Se conoce como **delegacion**.

### Iterador

Vemos que la lista no presenta ventajas en comparacion con un arreglo pues la complejidad de la busqueda, insercino  y borrado es O(N). Hay un caso especial que seria el del iterador, ya que utilizando el iterador y quiero eliminar un elemento, al ya estar apuntadno al elemento, las operaciones seran O(1).

Siguiendo las especificaciones de Java: 

- `remove()` de iterador tiene que invocarse luego de un `next()`.
- No se pueden invocar 2 `remove()` seguidos(tiene que haber un `next()` en el medio).
- Si no se satisfacen esas condiciones de lanza una `IllegalStateException`.

Hay cosas que no vamos a poder validar y que haran la lista inconsistente(responsabilidad del programador, no nuestra):

- Tener dos iteradores anidados.
- Tener dos iteradores anidados y hacer el remove del segundo iterador y luego el del primer iterador(estas eliminando el elemento sobre el que estas iterando).
