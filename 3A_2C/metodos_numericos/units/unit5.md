# Unidad 5: Integracion numerica

Se busca encontrar una formula para la primitiva de una funcion dada. Se aproxima 
con formulas de cuadratura.

## Definicion

Sea $f:[a, b] \rightarrow \R$ una funcion continua. Una formula
$$
Q[f] = \Sigma_{k=0}^m w_k \cdot f(x_k)
$$
Con la propiedad de que 
$$
\int_a^b f(x) dx = Q[f] + E[f]
$$
Se llama una **formula de cuadratura**.

- $E[f]$ es el error de aproximacion
- Los numero $x_j$ se llaman los nodos de cuadratura
- Los numeros $w_j$ se llaman los pesos

## Definicion

El **orden de precision** de la formula de cuadratura es el menor $n$ para el cual 
$E[P] = 0$ para todos los polinomios de grado menos o igual que $n$, pero existe un 
polinomio $P$ de grado $n+1$ tal que $E[P] \ne 0$.

## Reglas 

Hay dos tipos de reglas de cuadratura:

- **Simples**: No se subdivide el intervalo $[a, b]$.
- **Compuestas**: Se subdivide y luego se aplica una regla simple en cada uno.

> Si el intervalo es muy grande se usa una regla compuesta.

## Metodo de rectangulos

Grado de precision 0
$$
Q_D[f] = f(b)(b-a)
$$

## Punto medio

Grado de precision 1
$$
Q_M[f] = f(\frac{a+b}{2})(b-a)
$$

--- 

Todas estas son aproximaciones muy malas. Hay algo mejor.

## Formula de Newton Cotes

La idea es dividir el intervale en $n$ partes iguales de longitud $h=\frac{b-a}{n}$ con 
$a = x_0 < ... < x_n = b$, $x_{i+1} = x_i + h$ y usar el polinomio interpolador en la forma 
de Lagrange.
$$
P(x) = \Sigma_{j=0}^n f(x_j) I_j(x)
$$
integrando obtenemos
$$
\int_a^b P(x)dx = \Sigma_{j=0}^n \int_a^b I_j(x) dx = \Sigma_{j=0}^n f(x_j)w_j
$$

### Regla del trapecio

Se toma $n=1$. Usando la **regla de trapecio** se obtiene una precision de 1. El error es 
$E[f] = \frac{1}{12} f'(\xi)(b-a)^3$ donde $\xi \in (a, b)$.

### Regla de Simpson 1/3

Se toma $n=2$. La precision es de 3. Se $f$ es de clase $C^4$ en el intervalo $[a, b]$ entonces
$$
E[f] = \frac{h^5}{90} f^{(4)}(\xi)
$$
con $\xi \in (a, b)$.

### Regla de Simpson 3/8

Se toma $n=3$, es precision 3 y la formula de error con la misma hipotesis que en 1/3 es
$$
E[f] = \frac{3h^5}{80} f^{(4)}(\xi)
$$

## Cuadratura de Gauss Legendre 

Queremos algo mejor, se abandona la idea de tener nodos igualmente espaciados y se 
eligen los nodos y los pesos directamente de la formula de cuadratura. 
Pedimos que tenga el maximo orden de precision posible. 

1. Tomar $f: [-1, 1] \rightarrow \R$
2. Cuadratura
$$
\int_{-1}^1 f(x)dx = \Sigma_{j=0}^n w_j f(x_j)
$$
3. Que sea exacto hasta grado $2n-1$

### Para dos puntos

$$
Q[f] = f(\frac{-1}{\sqrt{3}}) - f(\frac{1}{\sqrt{3}})
$$

### Para tres puntos

$$
Q[f} = \frac{5}{9} f(\frac{ - \sqrt{3}}{\sqrt{5}}) + \frac{8}{9} f(0) + \frac{5}{9} f(\frac{\sqrt{3}}{\sqrt{5}})
$$
