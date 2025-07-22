# Variables aleatorias discretas

## Motivacion

Tengo una urna con 6 bolitas negras y 4 blancas, hay reposicion. Defino:
$$
    C_i = \text{La i-esima extraccion es una bolita negra}
$$
Estas son independientes pues no cambian las probablilidades al sacar una bolita pues para la proxima extraccion la extraida fue repuesta.
Luego, defino:
$$
    D_k = \text{Entre las 4 extracciones hay k bolitas negras}
$$
Los valores de k que cumplen $P(D_k) > 0$ son $k \in \{0, 1, 2, 3, 4\}$. Ahora, calculamos las probabilidades para cada k.

- $k = 0 \Rightarrow P(D_0) = P(\bar{C_1} \cup \bar{C_2} \cup \bar{C_3} \cup \bar{C_4}) = (\frac{2}{5})^4$
- $k = 1 \Rightarrow P(D_1) = P(C_1 \cup \bar{C_2} \cup \bar{C_3} \cup \bar{C_4}) +  P(\bar{C_1} \cup C_2 \cup \bar{C_3} \cup \bar{C_4}) + \text{ ... }= (\frac{96}{625})$
- $K = 2 \Rightarrow$ ... (Se entiende la idea)

Podemos resumir las probabilidades como:
$$
    P(D_k) =  
    \left(
    \begin{array}{lcr}
        4 \\
        k
    \end{array}
    \right)
    \cdot 
    \left(
        \frac{3}{5}
    \right)^k 
    \cdot 
    \left(
        \frac{2}{5}
    \right)^{4-k}
$$
Con $0 \le k \le 4$

Notas que la suma de las $D_k$ es 1. Se puede definir, entonces a $D=k$, donde $D=k$ representa $D_k$. $D$ puede tomar valores entre 0 y 4.

### Definicion: Valor esperado

Como la frecuencia relativa(en caso de realizar un experimento) es similar a la probabilidad, para calcular cual es su valor central para $D$(se denota "E" y se denomina valor esperado).
$$
    E(D) = \Sigma^K_{k=1} k \cdot P(D=k)
$$
> **Nota**: Es similar a la media.

### Varianza y desvio

Para la variable probabilistica, $\frac{f_i}{n}$ converge a la probabilidad correspondiente. Se puede extender varianza y desvio a $D$.
$$
    V(D) = \Sigma^K_{k=1} (k - E(D))^2 \cdot P(D=k) \Rightarrow \sigma (D) = \sqrt{ \Sigma^K_{k=1} (k - E(D))^2 \cdot P(D=k) }
$$

> **Nota**: $\frac{f_i}{n-1} = \frac{f_i}{n} \cdot \frac{n}{n-1}$, entonces si $n$ es muy grande $\frac{n}{n-1}$ tiende a 0 y $\frac{f_i}{n}$ tiende a $D$.

## General

### Definicion: Variable Aleatoria

Se nota con la letra $X$, no se pueden predecir(aleatoria). Son funciones que transforman el espacio muestral $S$ en valores reales. Es decir, $X:S \rightarrow \R$

Por lo tanto, la probabilidad de cada valor $X$ esta asociada a la probabilidad del espacio muestral.

### Definicion: Recorrido

Conjunto de valores que para cada variable aleatoria $X$, tienen asociada una posibilidad positiva.

**Ejemplo**: Para $D$(de ejemplo anterior), los valores con probabilidad positiva son $R_D = \{0, 1, 2, 3, 4\}$
Ademas, podemos afirmar que no hay mas pues la suma de las probabilidades da 1.

### Definicion: Variable Aleatoria Discreta(V.A.D.)

El conjunto $R_X$ es discreto cuando puede construir un entorno alrededor de cada elemento sin interseccion con otro elemento del recorrido, $X$ es V.A.D. si $R_x$ es discreto.

**Ejemplo**: En caso de $D$ de los ejemplos anteriores, $R_D$ es discreto pues tomando entornos de longitud 0.5, no hay solapamiento. $1+0.5 \ne 2$

### Definicion: Probabilidad punctual

Dada una variable aleatoria discreta $X$, se denomina la funcion de probabilidad punctual $P_X$ a la funcion que a cada valor real le asigna su probabilidad.
$$
    P_X: \R \rightarrow [0, 1] / P_X(k) = P(X=k)
$$

> **Nota**: Si toma un valor que no es del recorrido se le da probabilidad nula.

### Particiones y suma de probabilidades

Sea $X$ V.A.D. y $R_X$ recorrido:

- Los eventos asociados a valores diferentes de $X$ son M.E. ya que $X$ es funcion y no puede devolver dos valores.
- Como cada variable tinene que valer algun valor real, las probabilidades de todos los valores de $R_X$ deben sumar 1.

> **Nota**: Con estas propiedades, los eventos asociados a cada valor de $R_X$ froman una particion $\Rightarrow$ Puedo usar **Probabilidad total** y **Teorema de Bayes**.

### Propiedades valor esperado

#### Linealidad

- Sea c contante, $E(C) = c$
- Si a constante y $X$ V.A.D., $E(a \cdot X) = a \cdot E(X)$
- Si $X$, $Y$ variables, $E(X+Y) = E(X) + E(Y)$

#### Funciones

Sea $g: \R \rightarrow \R$ una funcion cualquiera.
$$
    E(g(x)) = \Sigma_{k \in R_X} g(x) \cdot P_X(k)
$$

> **Nota**: Si $X$, $Y$ son dos variables discretas, en la mayoria de los casos: $E(X \cdot Y) \ne E(X) \cdot E(Y)$

### Varianza y desvio

Para $X$ V.A.D., se define varianza:
$$
    V(X) = E((X - W(X))^2)
$$
Luego, desvio:
$$
    \sigma(X) = \sqrt{V(X)}
$$

#### Propiedades

- Sea c cte $\Rightarrow V(c) = \sigma(c) = 0$
- Sea c constante y $X$ V.A.D. $\Rightarrow V(c \cdot X) = c^2 \cdot V(X) \Rightarrow \sigma(c \cdot X) = |c| \cdot \sigma(X)$
- Sea c constnate y $X$ V.A.D. $\Rightarrow V(X+c)d = V(X) \Rightarrow \sigma(X+c) = \sigma(X)$
- Sea a, b constantes y $X$ V.A.D. $\Rightarrow V(a \cdot X + b) = a^2 \cdot V(X) \Rightarrow \sigma(a \cdot X + b) = |a| \cdot \sigma(X)$

> **Nota**: $X$, $Y$ V.A.D. $\Rightarrow V(X + Y) \ne V(X) + V(Y)$, siempre verificar que se pueda hacer.

### Funcion de distribucion acumulada

Para vualquier V.A.D. $X$, se define la siguiente funcion:
$$
    F_X: \R \rightarrow [0, 1] / F_X(k) = P(X \le k)
$$

#### Propiedades

- Es creciente, $k_1 \le k_2 \Rightarrow F_X(k_1) \le F_X(k_2)$
- $\lim_{k \to -\infty} F_X(k) = 0$
- $\lim_{k \to +\infty} F_X(k) = 1$
- Es continua a derecha, $\lim_{x \to K^+} F_X(x) = F_X(k)$

![Funcion de distribucion acumulada](graphics/vad.png)
![Resumen formulas](graphics/vad_summary.png)
 
