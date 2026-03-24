# Unidad 3: Ecuaciones diferenciales

Tenemos problemas de valor inicial, que dada una funcion $f: \R \rightarrow \R^n$, 
se tiene una funcion que toma f y su derivadas dentro de la misma funcion.

## Definicion: Ecuacion diferencial explicita

Una funcion diferencial es explicita si se puede escribir en la forma:

$$
f^k(t) = \vec{F}(t, f, f', ..., f^{k-1})
$$

### Reduccion de orden

Una ecuacion explicita de orden k siempre se puede escribir como un sistema de ecuaciones 
de orden 1. Por ejemplo:
$$
x''(t) = \vec{F}(t, x, x'')
$$

Llamando $z=x'$ se puede escribir como:
$$
\left(
\begin{array}{lcr}
x' \\
y'
\end{array}
\right) 
= 
\left(
\begin{array}{lcr}
z \\
\vec{F}(t, x, z)
\end{array}
\right) 
$$

> Es mucho mas importante bajar el orden que tener mas ecuaciones.

## Definicion: Condicion de Lipschitz

Sea $R = {(t, y) / a \le t \le b, c \le y \le d}$ y sea $\vec{F}:R \rightarrow \R$ una 
funcion continua. Se dice que $\vec{F}$ cumple una **condicion de Lipschitz** en $y$ si existe 
$k>0$ tal que:
$$
| \vec{F}(t, y_1) - \vec{F}(t, y_2) | < K \cdot | y_1 - y_2 |
$$

## Teorema: Existencia y unicidad

Si $\vec{F}$ cumple una condicion de Lipschitz entonces el problema de valor inicial:
$$
y' = \vec{F}(t, y) \text{, } y(t_0) = y_0
$$

Tiene solucion unica en algun subintervalos $t_0 \le t \le t_0 + \delta$

> La demostracion de este teorema sale de aplicar punto fijo.

Ahora bien, incluso si podemos garantizar una solucion, puede ocurrir que sea casi imposible 
hayar la solucion exacta, por lo que se aplican metodos de aproximacin.

## Metodo de Euler

La idea es aproximar la curva por una poligonal, aproximar la funcion con sus derivadas(Taylor). 
Claramente, aparecera un error. Pasos a seguir:

1. Dividir el intervalo $[a, b]$ en $M$ pedazos iguales
2. Definir puntos:
$$
    t_k = a + kh \text{, donde } h = \frac{b-a}{M}
$$
entonces $t_{k+1} = t_k + h$.
3. Definir:
$$
y_{k+1} = y_k + \vec{F}(t_k, y_k) \cdot h
$$
4. Tomar la poligonal que pasa por puntos $(t_k, y_k)$
5. Esta poligonal esta definida por una funcion $\tilde{y}(t)$ que es la solucion aproximada.

### Convergencia 

Las curvas convergen a la solucion si:
$$
\text{lim}_{h \rightarrow 0} \tilde{y_h} = y(t)
$$

### Errores

Hay dos tipos de errores:

1. **Error de discretacion local $\epsilon_k$**: Se asume que en el instante $t_k$ la solucion 
aproximada $y_k$ coincide con la solucion verdadera $y(t_k)$. El error en el paso siguiente se 
define por: 
$$
\epsilon_{k+1} = y(t_{k+1}) - y_k - h \cdot \vec{F}(t_k, y_k)
$$
Este error mide el error en cada paso.
2. **El error de discretizacion global $e_k$**: Se define por:
$$
e_k = y(t_k) - y_k
$$
Este error toma en cuenta la acumulacion de los errores locales en todos los pasos anteriores al 
instante $t_k$.

## Teorema

Si la solucion verdadera del sistema es de clase $C^2$, entonces:
$$
| \epsilon_k | = O(h^2)
$$
$$
|e_k| = O(h)
$$

El error final $E = y(b) - y_M$ cumple:
$$
|E| = O(h)
$$

## Como validar la aproximacion

Se usan dos ideas:

- Estrategia Adelante-Atras
- Estrategia $h$, $\frac{h}{2}$

### Estrategia Adelante-Atras

Dado un problema $y′ = f (t, y)$, $a \le t \le b$ en general se toma
como condición inicial $y(a)$ y si se parte el intervalo $[a, b]$ en $M$
partes se toma $h = \frac{b − a}{M}$ y $t_0 = a$.

Para la estrategia adelante-atrás se debe hacer
1. Hallar la solución $\tilde{y}$.
2. Calcular $\bar{y}_0 = \tilde{y}(b)$ y Definir $t_0 = b$.
3. Correr el método de Euler con las ecuaciones
$$
\bar{t}_{k+1} = \bar{t}_k − h
$$
$$
\bar{y}_{k+1} = \bar{y}_k − h \cdot f(\bar{t}_k , \bar{y}_k)
$$

4. Calcular $E = |y_0 − \bar{y}_M |$. Este número debe ser pequeño.

> Si el numero grande debe haber algo mal. Sin embargo, el caso contrario no siempre signifique que 
este bien.

### Estrategia $h$, $\frac{h}{2}$

Dado un problema $y′ = f (t,y)$, $a \le t \le b$, $y(a) = y_0$ se hace lo
siguiente:
1. Se elige un número $M$.
2. Se construyen soluciones aproximadas $\tilde{y}_1$ con paso $h = \frac{b − a}{M}$ y 
$\tilde{y}_2$ con paso $\frac{h}{2}$
3. Comparar $y_1(k)$ con $y_2(2 \cdot K)$ para $k = 1, . . . , M$
