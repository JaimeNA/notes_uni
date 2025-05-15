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
- **Borrado**: 

**Nota**: El projecto de BST lo vamos a estructurar de manera MVC(Model View Controller).
