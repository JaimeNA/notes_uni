# Unidad 5: Arboles binarios

Por ejemplo, se utilizan para compilar expresiones, como las matematicas. Se pueden usar estrategias para optimizar las expresiones a la hora de evaluarlas(re estructurar el BT), como sacar factor comun, por ejemplo.
Se pueden utilizar para cualquier cosa que tenga una representacion jerarquica. Tambien, si el BT estuviera ordenado, podria usarse como soporte para indices.

## Arboles de expresiones

Se utiliza para representar expresiones algebraicas. Los nodos internos representan operadores(binarios o unarios) y las hojas(nodo sin hijos) constantes(o variables). Hay distintas maneras de recorrer el arbol. 
Para la implementacion tenemos dos casos, si el token es un parentesis entonces se crea un nodo con dos hijos, y si es constante se le agrega una hoja a dicho nodo. Si tengo dos parentesis anidados, es decir, `((2+3))` dara error, pero cuando llegue al segundo cierre y se de cuenta de que no hay otro operando para aplicarle al primer parentesis.

### Caracteristicas

- Permite representar expresiones en notacion infija(no esta ordenado por el contenido).
- Asi como usabamos una pila y una tabla de precedencia de operadores para pasar de una expresion en notacion infija a postfija(para eliminar ambiguedad) y luego con una pila evaluabamos la expresion, 
ahora tambien a partir de una expresion contruiremos el arbol de expresiones asociado y lo evaluaremos para devolver el valor de la expresion.

### Aclaracion

Para evitar la discusion sobre la precedencia de operadores, vamos a aceptar solo expresiones infijas que tengan parentesis. Los operadoes son todos los binarios y es obligatorio que toda expresion tenga parentesis.. Para el input vamos a pedir que finalize en un `\n`.

### Formalmente

Una expresion arismetica E esta dada por las siguientes reglas de derivacion:

- E $\rightarrow$ (E + E)
- E $\rightarrow$ (E - E)
- E $\rightarrow$ (E \* E)
- E $\rightarrow$ (E / E)
- E $\rightarrow$ (E ^ E)
- E $\rightarrow$ cte

Es importante poder hacerlo de manera formal, hace que el proceso sea mucho mas ordenado a la hora de implementar algo.

> **Nota**: Hay una manera de testear outputs a consola, es la siguiente(Para ejemplos, ver testeos de ExpTree): 

``` Java
        System.setOut(System.out);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        myExp.postorder();
        assertEquals( "15 3 + ", outContent.toString());
```

---

## Arboles binarios en general

Si quisieramos guardar los datos en un arcihvo de texto, para luego recontruirlo en otro momento desde el archivo de texto, algun recorrido vendria bien. 
Que convenciones hay que tener en cuenta para generar un archivo de texto en el cual se guarde un arbol y que tmb se pueda reconstruir?
Queremos poder: 

- Recontruir leyendolo.
- Sin haber contruido nunca, editar un archivo, escribir la info con su estructura y leer.

Podria servir: preorder, postorder, inorder y por niveles. **Por niveles** es la mas facil de leer para las personas y mucho mas linda, la representacion coincide con el grafico(cosa que no se puede mantener con las otras opciones). Para pasar a texto, hay que colocal un *dummy*, simbolo para completar los espacios, vamos a usar un metalenguaje `?`(suponiendo que no se usan en los demas datos). Quedaria, por ejemplo:

```
    * 
++     -10
  /
++     -10  
 ?3.5
```

Pasa a:

```
* 
++ -10 
? / ? ? 
? ? ++ -10 ? ? ? ?
? ? ? ? ? 3.5
```

### Algoritmo

Tiene 3 acciones a contemplar

1. Genero raiz y postergo que hay que hacer con ella, hasta que llegue el token. Ni siquiera se si tendra cero, uno o dos hijos. Le asigno peso a cada nodo, para diferenciarlos cuando se resuelve esto en papel(primero es $20).
2. Saco pendiente y leo token. Completo dato, como no se si $20 tendra cero, uno o dos hijos(depende del token), pido que cuando llegue el momento se los proceso, los agrego al queue a los hijos.
3. Si me encuentro con signo de pregunta, tengo que consumirlo y avisar que tiene que consumir dos token, entonces agrego al queue dos nodos null.

Todo esto se realiza con un queue interno.

> **Nota**: En informatica, codigo y dato son intercambiables. Si es dato puedo pasarlo a accion y viceversa.

### Definicion: Arbol binario completo(complete)

Un arbol binario es completo si todos sus niveles tienen todos los nodos posibles, excepto posiblemente el ultimo. El ultimo nivel tiene los nodos lo mas a la izquierda posible(completa de izquierda a derecha).
]
### Definicion: Arbol binario lleno(full)

Un arbol binario esta lleno si todos los niveles tienen todos los nodos posibles. No es lo mismo que el completo necesariamente.

---

**La estrategia de serializar a disco generico un arbol de que tipo respecto al concepto de completitud/lleno?**

Es completo, lo garantiza. No necesariamente full al menos que hayamos especificado que complete hasta el final el ultimo nivel.

---

### Definicion: Altura

Longitud(cantidad de ejes) del camino mas largo desde la raiz hacia las hojas. Un nodo formado por una raiz tiene altura 0.

## Arboles de busqueda - BST

Los usos de los arboles son multiples, uno es manejar una lista ordenada. El BST es un árbol binario donde cada nodo no vacío cumple la siguiente condición: todos los datos de su subárbol izquierdo son menores o iguales que su dato, y todos los datos de su subárbol derecho son mayores que su dato.

### Operaciones

- **Insertar**: Crece desde las hojas, si es mayor o igual que el nodo, se inserta como hoja a la derecha y si es menor se inserta como hoja a la izquierda. Empieza en root, si es mayor o igual va al subarbol derecho y viceversa, de esa manera con todos los nodos hasta llegar a una hoja. CHEQUEAR
- **Borrado**: Si el nodo a eliminar es una hoja, actualizar a quien lo apunta a el. Si el nodo a eliminar tiene un solo hijo, le entrego a el que lo apunta su unico hijo. Si el nodo a eliminar tiene dos hijos se procede en dos pasos: 
Primero se lo reemplaza por un nodo lexicograficamente adyacente(su predecesor inorder, el mas grande de los nodos de su subarbol izquierdo, o bien su sucesor inorder o sea el mas chico de los nodos de su subarbol derecho), y finalmente se borra el nodo que lo reemplazo(sera facil de borrar porque no tendra dos hijos).

> **Nota**: El projecto de BST lo vamos a estructurar de manera MVC(Model View Controller).

> **Nota**: Java no me deja parametrizar el iterador, pero podria pasar el parametro anteriormente, por ejemplo, con un metodo `setTraversal()` anterior a llamar a `iterator()`.

## Arboles balanceados

El BST presenta problemas, el peor caso es cuando esta totalmente desbalaneado, haciendo que operaciones como busqueda tengan una complejidad O(N). Lo que queremos es que tenga la menor altura posible, entonces hay que tratar de controlar la altura. 

### Algoritmo AVL

BST balanceado por altura, crado por rusos en 1962, la primer version de BST que se balancea dinamicamente. 
Un AVL es un BST donde en cada nodo la **diferencia**(Factor de balance) de alturas entre sus dos arboles es a los sumo 1. 

Un AVL es un arbol con buena forma, las inserciones y borrados en un AVL estan definidas de tal manera que garantizan que se pueden realizar en O(log(N)) y garantizan ser **invariantes** ante su propiedad. Es un BST donde en cada nodo el factor de balance es a lo sumo 1.

#### Operaciones

- **Insersion**: Se inserta como BST, si esta desbalanceado de aplican rotaciones para que siga siendo AVL, se rota el arbol con pivote mas joven desbalanceado(mas cercano al nodo insertado), hay dos casos:
    - **Caso 1**: Si se inserta a la izquierda de un nodo con factor balance 1, ese nodo va a tener factor balance 2 y deja de ser AVL. Hay que rotar.
    - **Caso 2**: Si se inserta a la derecha de un nodo con factor de balance -1, ese nodo va a tener factor de balance -2 y deja de ser AVL. Hay que rotar.

> **Nota**: Para determinar el factor de balance se hace altura de arbol izquierdo menos altura del arbol derecho.

#### Consideraciones

Como en inserciones/borrados tenemos que calcular la diferencia de altura entre los subarboles muy frecuentemente, conviene almacenar el valor en cada nodo. El peor caso de este tipo de arboles
es el arbol de fibonacci, es el AVL que presenta el peor caso de balanceo. 

#### Arbol de fibonacci

Se define de la siguiente manera:

- Orden 0 es el arbol nulo.
- Orden 1 es un nodo
- Orden $h \ge 2$ es un arbol que tiene:

    - Como hijo izquierdo un fibonacci de orden $h-1$
    - Como hijo derecho un fibonacci de orden $h-2$

Un arbol fibo(6) tiene 20 nodos y una altura de 5, comparado con uno completo de igual cantidad de nodos como mucho hay una diferencia de altura(4 de altura). Por lo tanto:
$$
    AVL_h \text{ tendra 1 nodo } + \text{ cantidad nodos altura } h-1 + \text{ cant nodos altura } h-2
$$

![Relacion de la altura con cantidad de nodos](graphics/fibonacci_tree.png)

Por lo tanto: $N(h) = \text{nrofibo}(h+3)-1$. Esta sera la minima cantidad de nodos para un AVL de dicha altura.

Por otro lado, el numero de fibonacci puede aproximarce con en **golden number** $a = 1.618$, luefo la cantidad de nodos de un AVL de altura h sera(utilizando la formula anterior):

$$
    n \ge \frac{a^{h+3}}{\sqrt{5}} - 1
$$

Despejando h resulta ser logaritmica, entonces para un AVL con n nodos, la altura esta acotada por $O(\log{n})$. De manera que la busqueda y otras operaciones tienen complejidad logaritmica en el peor caso.

### Red-Black Tree 

Es un arbol balanceado donde cada nodo tiene un color asignado, solo puede ser negro o rojo. Sigue las siguientes reglas:

- La raiz siempre es de color negro
- Los nodos rojos no pueden tener hijos del mismo color
- Los caminos de un nodo negro a cada una de sus hojas tienen la misma cantidad de nodos negros
- Todos los nodos nulos son negros

La compejidad de la insercion, borrado y busqueda es O(log(n)). 

#### Altura negra

La altura negra de un red-black tree es el numero de nodos negros en un camino de la raiz a una hoja, todas las hojas se cuentan como nodos negros. Entonces la altura del arbol sera: 
$$
    \text{black height} \ge \frac{h}{2}
$$

Similarmente, la **black depth** de un nodo se define como la cantidad de nodos negros desde la raiz a dicho nodo.

#### Insercion

Primero, se inserta como un BST, como nodo rojo. Luego, se arregla cualquier violacion a las reglas de un red-black tree:

- Si el nodo padre es negro, dejo todo como esta
- Si el nodo padre es rojo, podria estar violando alguna regla

Para arreglar alguna violacion, depende del caso:

1. **Uncle is red**: Recolorear el padre y el tio de color negro, al abuelo de rojo. Luego, subir recursivamente chequeando por violaciones.
2. **Uncle is black**: Presenta dos subcasos:

    - **Es hijo derecho**: Rotar a la izquierda con pivot en el padre
    - **Es hijo izquierdo**: Rotar a la derecho con pivot en el abuelo y recolorear

Luego, se verifica si se esta violando alguna regla y se aplican los casos 1 o 2 al nodo primer nodo(desde el nodo insertado) que este violando la regla.

#### Busqueda

Idem a BST

#### Eliminar

Analogamente a la insercion, primero se borra de igual manera a un BST y luego se arregla cualquier violacion de las reglas. Cuando un nodo negro es eliminado, pordria ocurrir que aparesca un **double black**, hay dos casos:

1. **Hermano es rojo**: Rota al padre y recolorea al padre y al hermano.
2. **Hermano es negro**: Se presentan dos subcasos

    - **Los hijos del hermano son negros**: Recolorear al hermano y propagar el double black para arriba.
    - **Por lo menos un hijo del hermano es rojo**: Si el hermano mas lejano es rojo se realiza una rotacion con pivot en el padria y se recolorea. Si el hermano mas lejano es rojo se rota con pivot en el hermano y se recolorea.

> **Nota**: Las rotaciones son las mismas que para el AVL.

### Algunas definiciones

#### Arbol multicamino M-ario

Los nodos guardan hasta M-1 claves de información, con un máximo de M hijos. 
Cada clave $C_i$ de un cierto nodo será tal que las claves almacenadas en su subárbol izquierdo serán menores y las almacenadas es su subárbol derecho serán mayores que él.

#### Arboles muticaminos balanceados

El equilibrio perfecto resulta muy costoso de mantener y es poco práctico. 

En 1970,  R. Bayer y E. M. Mc Creight postularon un criterio razonable que permite implementar algoritmos relativamente sencillos para búsquedas, inserciones y eliminaciones.  
Para mantener éstos árboles multicaminos balanceados se utiliza una estructura subyacente de la familia de los árboles B, que veremos a continuación

### Arbol B de orden N

Un árbol B de orden N es un árbol de búsqueda (ordenado) que cumple con los siguientes axiomas:

- Cada nodo contiene a lo sumo 2 * N claves.
- Cada nodo, excepto la raíz, contiene por los menos N claves.
- Cada nodo o es hoja o tiene M+1 descendientes donde M es el número de claves que posee realmente ese nodo
- Todas las hojas están al mismo nivel
- En cuanto al orden:  si un nodo tiene $c_1$ $c_2$ …$c_m$ elementos $c_1 < c_2 <  \text{ … }< c_m$, pero además para cada $c_i$ ($1 \le i \le m$) los elementos del subárbol  izquierdo de ci son menores que $c_1$ y los elementos del subárbol derecho de $c_i$ son mayores que $c_i$.

![Representacion de un nodo](graphics/b_tree.png)

#### Algoritmo de busqueda

Buscamos la clave X en un nodo. Para ello lo recorremos secuencialmente
desde C1 hasta Ck, siendo k el número de claves que realmente posee dicho
nodo, hasta que se den alguno de estos casos:

- Si $X < C_1$, como en el nodo las claves están ordenadas no tiene sentido seguir buscando en ese nodo, luego sigo buscando en el subárbol apuntado por $P_0$
- Si $X=C_i$ para algún $i \le k$ entonces lo encontré
- Si $C_i<X<C_i+1$ para algún i prosigo en la búsqueda en el subárbol apuntado por $P_i$
- Si $C_k < X$ siendo k la cantidad de claves que posee, entonces sigo la búsqueda en el subárbol apuntado por $C_k$

Si en algún caso el puntero por donde hay que seguir la búsqueda fuera null, entonces el elemento buscado no está.

#### Algoritmo de insercion

Si se quiere insertar una clave X en un árbol B de orden N, se
procede de la siguiente manera:

- La inserción siempre se hace en las hojas (para poder detectar si el nodo a insertar ya está presente o no)
- Para insertar se coloca el elemento X en la hoja que corresponda (el nodo debe estar ordenado)
- Si el elemento nuevo hace que la cantidad nueva k sea mayor que el $2 \cdot n$ permitido, el nodo se abre en dos, subiendo la clave del medio al nodo antecesor de dicho nodo. Este algoritmo es recursivo hasta la raíz, o sea si al ubicar la clave del medio en el nodo antecesor ocasiona que el nodo viole la condición de árbol B de orden n ($k > 2 \cdot n$) el procedimiento de repite.

Se mantiene balanceado explotando o contrayendo los nodos, cuando se explota se explota todo hasta la raiz. Nunca se inserta si no es en una hoja. Siempre subo el del medio, notar que es local,
solo sube hacia arriba.

#### Algoritmo de borrado

No es tan simple, es complidado pues tambien debe poder contraerse ademas de explotar.

- Si no encuentra en un nodo hoja, se lo reemplaza por unaclave lexicográficamente adyacente, por ejemplo el sucesor in order y se lo elimina de dicha hoja. Si fuera hoja se lo elimina directamente.
- Luego, para la hoja que colaboró el borrado se analiza si cumple las condiciones de árbol B de orden N. Si ha quedado en rojo (tiene menos elementos que los permitidos), 
se une dicho nodo con su hermano y medio antecesor (el cual es eliminado del nodo al cual pertenece, porque acude en ayuda de su hijo) armando un sólo nodo. Se verifica si cumple las 
condiciones de árbol B de orden N, y sino se lo particiona subiendo el elemento del medio. Después se analiza qué sucede con el nodo donde estaba su medio antecesor, y se sigue el proceso recurrentemente hasta llegar a la raíz.

> **Nota**: El borrado no nos lo van a pedir(whatever that means). Cuando dice subir, se refiere a alejarse de la raiz.
