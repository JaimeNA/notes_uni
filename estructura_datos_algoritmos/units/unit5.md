# Arboles binarios

Por ejemplo, se utilizan para compilar expresiones, como las matematicas. Se pueden usar estrategias para optimizar las expresiones a la hora de evaluarlas(re estructurar el BT), como sacar factor comun, por ejemplo.
Se pueden utilizar para cualquier cosa que tenga una representacion jerarquica. Tambien, si el BT estuviera ordenado, podria usarse como soporte para indices.

## Arboles de expresiones

Se utiliza para representar expresiones algebraicas. Los nodos internos representan operadores(binarios o unarios) y las hojas(nodo sin hijos) constantes(o variables). Hay distintas maneras de recorrer el arbol. 
Para la implementacion tenemos dos casos, si el token es un parentesis entonces se crea un nodo con dos hijos, y si es constante se le agrega una hoja a dicho nodo. Si tengo dos parentesis anidados, es decir, `((2+3))` dara error, pero cuando llegue al segundo cierre y se de cuenta de que no hay otro operando para aplicarle al primer parentesis.

### Caracteristicas

- Permite representar expresiones en notacion infija(no esta ordenado por el contenido).
- ....

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


