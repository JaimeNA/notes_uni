# Unidad 4: Interpolacion polinomica

Queremos hallar una curva, lo mas simple posible, que pase por unos puntos determinados. 
Es **condicion necesaria** que dos pares de datos no pueden estar en la misma linea vertical.

## Teorema

Unicidad del polinomio interpolador. Dado $n+1$ pares $(x_i,y_i)$ $i = 0 \text{, ...,} n$ 
tales que $x_i \ne x_j$, $i \ne j$ hay a lo sumo un polinomio
$$
P(x) = a_n \cdot x^n + \text{ ... } + a_1 \cdot x + a_0 
$$
de grado $n$ tal que $P(x_i) = y_i$, $\forall i$.

> Notas que sin la restriccion sobre el grado, habra infinitas soluciones.

## Usos del polinomio interpolador

Se usa cuando se desconoce una funcion, con el polinomio interpolador se pueden hacer 
varias cosas:

- **Interpolacion**: Si min|$x_k$| $\le x \ge$ max|$x_k$| entonces $f(x) \approx P(x)$
- **Extrapolacion**: Si $x <$ min|$x_k$| 0 $x >$ max|$x_k$| entonces $f(x) \approx P(x)$
- **Aproximacion de derivadas**: $f^{(r)}(x) \approx P^{(r)}(x)$
- **Aproximacion de integrales**: $\int_a^b f(x)dx \approx \int_a^b P(x)dx$

---

## Teorema

Para obtener el polinomio interpolador se crea un sistema de ecuaciones en el polinomio 
y cada punto, lo que se obtiene es una **matriz de Vandermonde**. 

El determinante de la matriz de Vandermonde es
$$
\pm \prod_{i < j} (x_i - x_j)
$$

Pero esto tiene un problema, habria que invertir una matriz bastante grande.

## Generalizacion 

Recordemos que un conjunto $q_0(x)$, ..., $q_n(x)$ forma una base del espacio de polimonios 
de grado menor o igual que $n$ si todo polinomio $P(x)$ de grado menor o igual que $n$ se 
puede escribir de modo unico de la forma. Esto puede facilitar resolver el sistema.

## Teorema 

Si los valores $x_i$ son todos distintos, entonces el sistema de ecuaciones tiene 
solucion unica.

## Polinomios de Lagrange

Es una de las bases mas sencillas de usar y de programar. Dados $x_0$, ..., $x_n$ 
puntos distintos los polimonios de Lagrange $l_i(x)$ son los unicos polimonios de grado 
menor o igual que $n$ definidos por la siguiente propiedad:
$$
l_i(x_j) = \begin{cases}
    1,  i = j \\
    0,  i \ne j
\end{cases}
$$

Los polinomios de Lagrange forman una base del espacio de polinomios de grado menor o igual 
que $n$.

### Formula general

$$
l_i(x) = \frac{(x - x_0) \cdot (x - x_1) \cdot \text{...} \cdot (x - x_{i-1}) \cdot (x - x_{i+1}) \cdot \text{...} \cdot (x - x_n)}{(x_i - x_0) \cdot (x_i - x_1) \cdot \text{...} \cdot (x_i - x_{i-1}) \cdot (x_i - x_{i+1}) \cdot \text{...} \cdot (x_i - x_n)}
$$

> Notas que me salteo el $x_i$, sino daria 0 el producto.

### Formula para el polinomio interpolador

En la base de los polinomios de Lagrange el polinomio interpolador para los datos 
$(x_i, y_i)$, $i$ = 1, ..., n se escribe
$$
P(x) = \Sigma_{i=0}^n y_i \cdot l_i(x)
$$

### Ventajas y desventajas

- Hay una formula general para los polinomios
- Los coeficientes del polinomio interpolador son triviales de calcular
- Son faciles de programar
- Son ineficientes de evaluar
- Cada vez que se cambia un nodo $x_j$ hay que recalcular todo de vuelta

## Implementacion

Es conveniente hacerlo en dos etapas:

1. Calcular los $l_i$(Aca no hago referencia a los de Lagrange, es otro)
2. Calcular el polinomio de Lagrange

### Polinomio 'l'

1. $xs$ = (x_0, \text{...}, x_n) lista de puntos $x$
2. $x$ la variable
3. $i$ el indice para el cual el polinomio debe valer 1
4. $p = 1$ un acumulador
5. Para $k$ desde 0 a $n$ hacer:

    1) Si $k = i$ no hacer nada
    2) Si $k \ne 1$, $p \rightarrow p = p \frac{(x - xs_k)}{(xs_i - xs_k)}$


### Polinomio interpolador

1. $ys = (y_0, \text{...},, y_n)$ lista de puntos y
2. L = 1 un acumulador
3. Para $k$ desde 0 a $n$ hacer $L \rightarrow L =  L + y_k \cdot l(k, xs, x)$(la otra l)

### Formula de error

Supongamos que $x_0 < x_1 < \text{...} < x_n$ y supongamos que $y_j = f(x_j)$ donde $f$ es una 
funcion de clase $C^n+1$. Sea $P(x)$ el polinomio interpolador. Si $x_0 < x < x_n$ entonces
$$
E(x) = f(x) - P(x) = \frac{f^(n+1) \cdot (\varphi)}{(n+1)!} \cdot (x - x_0) \cdot \text{...} \cdot (x - x_n)
$$
donde $\varphi = \varphi(x)$($\varphi$ depende de $x$)

> Si $f(x)$ es un polinomio de grado menor o igual que $n$, entonces $E(x) = 0$.

## Metodo de Newton

El problema del metodo de Lagrage es que no da algoritmos eficientes para la evaluacion. 
Un metodo mejos en ese sentido es debido a Newton. La idea es tomar un polinomio de la forma
$$
P_n(x) = a_0 + a_1 \cdot (x - x_0) + \text{ ... } + a_x \cdot (x - x_0)...(x - x_n-1)
$$

Se utiliza la base de Newton
$$
N_0(x) = 1
$$
$$
N_1(x) = (x - x_0)
$$
$$
N_2(x) = (x - x_0)(x - x_1)
$$

El metodo de Newton da algo balanceado pues de pueden evaluar estos polinomios usando un 
proceso iterativo.

### Algoritmo de evaluacion

Sea $q$ un punto donde se desea evaluar $P_n(X)$ se define

1. $q_0 = a_n$
2. $q_1 = a_{n-1} + (q - x_{n-1}) \cdot q_0$
3. $q_2 = a_{n-2} + (q - x_{n-2}) \cdot q_1$
4. ...
5. Entonces $P_n(q) = q_n$

Observar que se forma un sistema de ecuaciones donde su forma matricial es triangular 
inferior
$$
y_0 = a_0
$$
$$
y_1 = a_0 + (x_1 - x_0) \cdot a_1
$$
$$
y_2 = a_0 + (x_2 - x_0) \cdot a_1 + (x_2 - x_0)(x_2 - x_1) \cdot a_2
$$

### Diferencias divididas

Es una manera de calcular los coeficientes sin tener que resolver el sistema de ecuaciones.
Las **diferencias divididas** se definen de modo recursivo por
$$
f[x_{k-j}, ..., x_k] = \frac{f[x_{k-j+1}, ..., x_k] - f[x_{k-j}, ..., x_{k-1}]}{x_k - x_{k-j}}
$$

Vale que
$$
a_k = f[x_0,...,x_k]
$$

## Limites de la interpolacion polinomica

- Si hay demasiados puntos la curva oscila mucho.
- A veces el polinomio se aleja de la tendencia de los datos.

## Curvas de Bezier

Una de las aplicaciones mas importantes de la interpolaciones. Es un sistema de aplines dados 
por polinomios cubicos que se desarrollo cerca de 1960 para el trazado de dibujos tecnicos. 
Los **splines** son funciones, pero nosotros vamos a centrarnos en polinomios. La idea 
es que para cada par de puntos se utilizara un polinomio para unirlos de forma mas suave que 
si se usaria un poligonal(se unen los puntos con una linea recta).

### Polinomio de Bernstein

Sea $n \in \mathcal{N}$. Para cada $0 \le i \le n$ se define el polinomio
$$
B_i^n(t) = \left( 
\begin{array}{lcr}
n \\
i
\end{array}
\right) t^i (1 - t)^{(n-i)}
$$

### Formula general

Sean $C_0, ..., C_n$ puntos de control. Tomando la curva parametrica
$$
\phi(t) = \Sigma_{i=0}^n B_i^n(t) \cdot C_i
$$

Vemos que por definicion del polinomio de Bernstein entonces
$$
\phi(0) = C_0 \text{ y } \phi(1) = C_n
$$

