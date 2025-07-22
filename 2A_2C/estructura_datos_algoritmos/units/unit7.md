# Unidad 7: Tipos de Algoritmos-Heuristicas

Muchos juegos se pueden resolver explorando un grafo implicito, es decir, se explora un espacio de soluciones generadas implicitamente por un grafo. Por ejemplo, basado en la cantidad de veces que voy a tirar un dado, se pueden obtener todos los posibles valores con un grafo(serian las hojas).

## Tecnica de busqueda exhaustiva

Tecnica para buscar todas las posibles soluciones explorando el espacio de soluciones en forma implicita.

> **Nota**: El grafo es implicito, no lo genero. Tipicamente se implementa con recursion(o stack) aunque podria hacerse con un queue.

Idea para la tecnica:

1. Si el nodo no puede expandirse mas(no hay mas opciones a partir de el), entonces retornar.
2. Sino, por cada posibilidad para este nodo de expandir un proximos nivel(ciclo for).

    - El nodo puede resolver un caso pendiente
    - Explora nuevos pendientes(soluciones quizas parciales)
    - El nodo puede deshacer/quitar el caso pandiente generado.

### Caso: Hay restricciones

Si resuelvo con busqueda exhaustiva el chequeo de las restricciones lo hago al final de la exploracion de todas las posibles soluciones.

## Tecnicas backtracking

Ante la presencia de restricciones, pueden aprovecharlas para no explorar todo el grafo de soluciones y eliminar aquellos nodos que no conducen a la solucion. Ahi en donde las tecnicas de backtrackin estan en juego: no expande innecesariamente nodos que ya se saben que no conducen a la solucion.

### Programacion dinamica

En vez de recalcular la suma del nodo cada vez, se puede agregar como parametro, entonces ahi estamos introduciendo programacion dinamica.

## Ejemplo: Eigtht Queens

Dado un tablero de ajedrez, queremos saber cuantas reinas se pueden colocar sin que queden en jaque entre si. Una opcion es recorrer cada casillero e ir probando si se al colocar una reina se viola la condicion, en caso de que asi sea se debe deshacer lo hecho.

Ideas para mejorarlo:

1. No hace falta tener todo el tablero  de N x N. Si solo almacenamos un vector de N posiciones y guardamos en cada posicion la fila usada nos ahorramos de chequear.
2. No hace falta  chequear la diagonal. Los valores que se pueden generar par la diagonal son fijos.
3. Similarmente. No hace falta chequear la contradiagonal. Los valores que se pueden generar para la contradiagonal tambien son fijos.

## Tecnicas algoritmicas

### Divide & Conquer

Tecnica que descompone un problema de tamano N en problemas mas pequenos que tengan solucion. Finalmente, se debe proponer como construir la solucion final a partir de las soluciones de los problemas menores. Por ejemplo, mergesort, quicksort, busqueda en BST, etc.

### Algoritmos avidos(greedy)

Tecnica que busca en cada etapa un optimo local con el objetivo de llegar al obtimo global(aunque de esta forma no siempre se consigue el optimo global). Por ejemplo, algoritmo de Kruskal.

### Fuerza bruta/Busqueda exhaustiva (con Stack o Queue)

Calcula y enumera todas las posibles soluciones. Si se agregan restricciones, por ejemplo, se pide la mejor, recien en el final evalua cual es la mejor, se evaluaran las hojas.

Pero si la restriccion me permite reducir el arbol en ramas que son inncecesarias(las que no conducen a la solucion), entonces, es mucho mas eficiente. En este caso, **backtracking** es una opcion. Solo se exploran las posibles soluciones. 

Por otro lado, se puede aplicar **programacion dinamica** para no recalcular cosas que ya calcule innecesariamente. Por ejemplo, Levenshtein, Dijkstra, Fibonacci, etc.
