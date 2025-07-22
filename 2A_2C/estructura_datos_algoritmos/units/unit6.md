# Unidad 6: Grafos

La idea es representar relaciones, por ejemplo, el caso del recorrido Euleriano. Actualmente se utilizan para representar rutas, conexiones y trafico. O tambien, con las redes sociales para representar vinculos entre objetos, dada la compejidad de las interrelaciones se crearon alternativas de los algoritmos que ya vimos para que sean mas eficientes. 

Ademas, tambien son usados por los instaladores, compiladires y optimizadores.

## Tipos

Hay varios tipos de grafos, porque me permiten modelar situaciones distintas. Si se le agregan peso a los ejes tenemos un total de 16 tipos diferentes(8 sin el peso).

![Tabla de los tipos de grafos segun las propiedades](graphics/graph_types.png)

### No dirigidos

- **Simple**: Entre cada par de nodos hay a lo sumo un eje, no admite lazos.
- **Multigrafo**: Entre cada par de nodos puede haber varios ejes, no admite lazos. Normalmente se rotulan los ejes.
- **Pseudografo**: Entre cada par de nodos puede haber varios ejes y admite lazos.

### Dirigidos(digrafos)

- **Simple**: Entre cada par de nodos hay a lo sumo un eje, no admite lazos.
- **Multi digrafo**: Entre cada par de nodos puede haber varios ejes, no admite lazos.
- **Pseudo digrafo**: Entre cada par de nodos puede haber varios ejes y admite lazos.

> **Nota**: Hay algoritmos que dependen de la categoria. No hay clasificacion para simple grafo(o digrafo) con lazo.

### Con pesos

Presenta las mismas variantes que los anteriores.

## Implementacion

Usando la tabla, vamos a crear una jerarquia de clases para evitar repetir codigo. Si nos enfocamos en un grafo simple, podriamos representarlo como:

- Matriz de adyacencia. Ideal grafos densos.
- Lista de adyacencia. Ideal grafos esparcidos(**sparce**).
- Matriz de incidencia. Idem.
- Lista de incidencia. Idem.

Para relacionar los nodos con un subindice, podriamos usar hashing. Nosotros vamos a usar generalmente la lista de adyacencia usando factor comun. No a vamos a implementar completamente nosostos, vamos a usar *JGraphT*(usa sparce, por tiempo no implementamos este y los otros tipos, pero no son dificiles), pero tenemos que entender la implementacion.

GraphFactory (sea que lo invoca directamente el usuario o sea que se invoca a partir del GraphBuilder) genera una instancia de SimpleOrDefault y Multi.
Ambas clases tienen mucho en común. Sin embargo, difieren en algo:

- `addEdge`: en el caso de SimpleOrDefault, si se vuelve a crear otro eje entre el mismo par de vértices, se ignora. En Multi no se ignora, se crea.
- `removeEdge`: en el caso de SimpleOrDefault, si se indica un par de vértices, con sus propiedades y se lo encuentra, se borra el único eje encontrado. En el caso de Multi se borran todas las apariciones de ese eje con mismas propiedades entre esos vértices.

> **Nota**: Si no se especifican properties se borra en el caso de SimpleOrDefault el único eje que pudiera existir entre dichos vértices. Si es Multi lanza exception (para evitar ambigüedad). Es responsabilidad del usuario definir equals/hash en la clase que represente las propiedades de los vértices y las propiedades de los ejes.

## Testeos de unidad

Como podriamos hacer los testeos de unidad? Por ahi el grafo es el mismo, pero su representacion en memoria es diferente pues internamente usamos un hashing y no podemos saber como quedo realmente. 

## Recorridos

Los recorridos de nodos se usan para ver los nodos alcanzables a partir de un nodo de interes.

### Breadth-First Search (BFS)

Lo vamos a implementar con queue, hay algunos que le agregan un boolean a cada vertice para chequear si fue recorrido, pero es preferible tener una estructura paralella a los vertices booleana.
3

### Depth-First Search(DFS)

Similar BFS, pero en vez de queue se usa un stack pues se van visitando desde el proximo, sin chequear si se visitaron todos los del anterior(se agregan al stack).

## Dijkstra

Hay varias maneras de implementarlo, pero hay una estructra que java que permite implementarlo con mayor facilidad.

Solo va a funcionar con grafos `WEIGHTED`, idem al que vimos en matematica discreta, se crea una tabla con la distancia de cada nodo al nodo inicial. 
Con cada ciclo de agregan los adyacentes a la estructura previamente descripta y asi hasta que no quedan mas nodos por visitar.

En la complejidad algoritmica, se puede hacer mas eficiente con un arbol AVL o un RedBlackTree, pero hay una que ya viene implmentada en Java que se conoce como **BinaryQueue** que usa **Binary Heap**.

Un binary heap es un BT completo tal que cada nodo es menor o igual que todos los elementos de sus subarboles(no es un BST). De manera que el minimo esta en la raiz, entonces buscar el minimo es orden 1! Pero al quitarlo hay que buscar alguien que lo reemplaze, Java lo implementa con un arreglo.

Esto hace que sea muy eficiente, ya que si se coloca por niveles en el arreglo, cada elemento del arreglo cumple una propiedad matematica que permite encontrar su antecesor con un simple calculo:
$$
    \text{Indice antecesor} = \lfloor \frac{i-1}{2} \rfloor 
$$

Si al insertar, queda mal, solo hay que suapear con el antecesor hasta que quede bien. Peor caso seria O(log(n)), asi que es muy eficiente.
