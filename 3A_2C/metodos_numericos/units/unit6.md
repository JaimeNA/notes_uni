# Unidad 6: Algebra lineal numerica

Uno de los grandes temas de metodos numericos. Se usa para resolver sistemas de ecuaciones 
lineales. Hay dos familias de metodos:

- Metodos directos, basados en la eliminacion de Gauss
- Metodos iterativos, utiliza una sucesion

## Metodos directos

### Eliminacion Gaussiana

Es facil resolver un sistema de ecuaciones que esta en forma triengular superior. Se realiza 
substitucion hacia atras, de manera que la solucion del sistema no cambia. Se logra realizando 
varias operaciones de fila:

1. Intercambiar dos filas
2. Multiplicar una fila por un escalar no nulo
3. A una fila sumarle otra multiplicada por un escalar no nulo

Para trabajar con este metodo, se utiliza la matriz ampliada. Pero tiene un problema, si se 
cambia cualquier cosa hay que resolver todo de nuevo. La idea basica es que es facil 
tambien sistemas de ecuaciones en forma triangular inferior. 

### Descomposicion LU

Una matriz no singular $A$ admite una descomposicion $LU$ si se puede escribir $A = LU$ donde 
$L$ es una matriz triangular inferior y $U$ una matriz triangular superior. Las matrices 
triangulares superiores tienen un par de propiedades: 

- Si $L_1$ y $L_2$ son matrices triangulares inferiores, entonces $L_1L_2$ es una matriz 
triangular inferior.
- Si $L$ es una triangular inferior, entonces $L$ es invertible y $L^{-1}$ tambien es una 
matriz triangular inferior.

### Teorema 

Supongamos que la matriz $A$ se puede llevar a una forma triangular usando eliminacion 
Gaussiana sin usar inctercambio de filas. Entonces la matriz admite una descomposicion $LU$.
Esto es mucho mejor que la eliminacion Gaussiana pues se tiene que hacer la descomposicion 
$LU$ una sola vez. 

### Matrices de permutacion

Una matriz que en cada fila y en cada columna tiene exactamente un 1. El resto de los 
coeficientes son 0.

### Teorema 

Si $A$ es una matriz cuadrado no singular, entonces existe una matriz de permutacion $P$ tal 
que $PA$ tiene una descomposicion $LU$. Toda matriz de permutacion tiene una inversa que 
tambien es una matriz de permutacion. Si $P'$ es la inversa de $P$ obtnemos
$$
A = P'LU
$$

## Metodos iterativos

Hay dos metodos 

- Metodo de Jacobi 
- Metodo de Gauss-Seidel(converge mas rapido)

### Condiciones de convergencia

Aplica a ambos metodos, la matriz del sistema debe ser estrictamente dominada por la diagonal.
Una matriz es dominada por la diagonal si
$$
|a_{ii}| > \Sigma^n_{j=1, j \ne i} |a_{ij}
$$

Para todo $1 \le i \le n$. Caso contrario no converge a la solucion.

> Convergen independientemente de cual sea la condicion inicial.
