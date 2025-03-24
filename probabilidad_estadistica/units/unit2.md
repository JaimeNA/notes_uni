# Introduccion a la probabilidad

## Algebra de sucesos

- **E**: Experimento aleatorio
- **S**: Espacio muestral
- **A**: Espacio o evento

Por ejemplo, en caso de tirar un dado, el espacio muestral serian todos los numeros del 1 al 6 y un eventos seria:
$A_1 = \text{sale par} = \{2, 4, 6\} \subseteq S$

De manera que se cumplen las siguientes condiciones:

1. Si $\exists A \Rightarrow \exists A^c / A^c = S-A$
2. Si $\exists A_1, A_4 \Rightarrow \exists A_1 \cup A_4$ (ocurre uno o el otro, 'o' no exclusivo)
3. Si $\exists A_1, A_4 \Rightarrow \exists A_1 \cap A_4$ (ocurre uno y el otro)
4. Siempre debemos poder hablar de que salga algo

### Definicion: $\sigma$-algebra

Sea $S$ un conjunto y $\Sigma$ una coleccion de subconjuntos de $S$. $\Sigma$ es un $\sigma$-algebra de $S$ si:

1. $S \in \Sigma$
2. $A \in \Sigma \Rightarrow A^c \in \Sigma$
3. $A_1, A_2, .. \in \Sigma \Rightarrow A_1 \cup A_2 \cup ... \in \Sigma$

La 3 incluye la interseccion(Demo en apuntes, hay que usar complemento y De Morgan). Ademas, el conjunto vacio es el suceso imposible.

## Axiomas de Kolmogorov

### Definicion: Frecuencias relativas de eventos

Sea $n$ la cantidad de veces que se repite el experimento, $S$ espacio muestral y $A$ un evento.

$f_A = \frac{\text{numero de ocurrencias de }A}{n}$

A partir de esto se cumple:

1. $f_A \ge 1$
2. $f_s = 1$
3. Sean $A$, $B$ eventos $\Rightarrow f_{A \text{ "o" } B} = f_{A \cup B} = \frac{\text{numero de veces que ocurre A o B}}{n}$

Entonces si $A \cap B = \emptyset \Rightarrow f_{A \cup B} = f_A + f_B$

### Definicion: Mutuamente excluyente

Dos eventos $A$ y $B$ son ME $\Leftrightarrow A \cap B = \emptyset$

### Definicion: Espacio probabilidad

El triplete $(S, \Sigma, P)$ es un espacio de probabilidad si $S \ne \emptyset$, $\Sigma$ es un
$\sigma$-algebra de $S$ y $P: \Sigma \rightarrow \R$ cumple con las siguientes condiciones:

1. $P(A) \ge 0 \forall \text{, }A \in \Sigma$
2. $P(S) = 1$
3. $E_1, E_2, ...$ son ME $\Rightarrow p(\cup^\infty_{i=1} E_i) = \Sigma^\infty_{i=1} P(E_i)$

> $P$ se denomina medida de probabilidad

## Consecuencias

1. Si: 
$$
    A, A^c \text{, } A \cup A^c = S \land A \cap A^c = \emptyset (A, A^c\text{ son ME})
$$

Por axioma 3: $P(A \cup A^c)= P(A) + P(A^c) = P(S) = 1$, luego:
$$
    P(A^c) = 1 - P(A)
$$

2. **Monotzi:** $A \subseteq B$, $A,B \in S$
$$
    B = A \cup (B \smallsetminus A)
$$
Notas que por definicion $A$ y $B \smallsetminus A$ son ME, entonces:
$$
    A \subseteq B \Rightarrow P(A) \le P(B)
$$

> **Nota**: Para conjuntos, hay que usar $\smallsetminus$ en vez de $-$ sino esta mal

3. $S^c = \emptyset \Rightarrow P(S^c) = P(\emptyset) = 1 - P(S) = 0$, entonces:
$$
    P(\emptyset) = 0
$$

4. Sean $A$, $B$ eventos no necesariamente ME:
$$
    B = (B \cap A^c) \cup (B \cap A) \Rightarrow A \cup B = (A^c \cap B) \cup A
$$
$$
    P(A \cup B) = P(A^c \cap B) + P(A) = P(B) - P(A \cap B) + P(A)
$$
Entonces:
$$
    P(A \cup B) = P(A) + P(B) - P(A \cup B)
$$

5. $P(A \smallsetminus B) = P(A) - P(B)$ 

## De Morgan

- $\bar{A \cup B} = \bar{A} \cap \bar{B}$
- $\bar{A \cap B} = \bar{A} \cup \bar{B}$

## Regla de Laplace

Se aume que todos los casos posibles son igualmente probables, de manera que si:
$$
    S = {S_1, S_2, ...,S_n} \text{ con } \#S=n
$$
Se asume que todos los $S_i$ son M.E., por lo que $S= \cup_{i=1}^n \{S_i\}$ y $\{S_i\} \cap \{S_j\} = \emptyset$ con $i \ne j$.

### Aplicando los axiomas

- $1 = P(S) = \Sigma_{i=1}^n P(\{S_i\}) \Rightarrow P(\{S_i\}) = \frac{1}{n}$
- Sea $A = {S_{k_1}, S_{k_2}, ..., S_{k_n}}$, entonces:
$$
    P(A) = \Sigma_{S_i \in A} P(\{S_i\}) = \Sigma_{S_i \in A} \frac{1}{n} = \frac{\#A}{\#S}
$$

### Regla de Laplace

La regla de Laplace dice que la probabilidad de ocurrencia de un suceso es igual al número de casos favorables partido por el número total de casos posibles.

$$
    P(A) = \frac{\# \text{Casos "favorables" en }A}{\# \text{Total de clases}}
$$

**Ejemplo**: Hallar la probabilidad de sacar por sumo o bien 4, o bien 11 al lanzar 2 dados:

- **Casos posibles**: $6 \cdot 6 = 36$
- **Casos favorables**: $5$, pues ${(2, 2), (3, 1), (1, 3)} \text{ o } {(5, 6), (6, 5)}$

De manera que el resultado es $P = \frac{5}{36}$

> Supongamos que el procedimiento 1 se puede hacer de $n$ maneras y el 2 de $m$ maneras.
> Si no es posible que ambos, 1 y 2, se hagan juntos entonces el numero de maneras que se puede hacer 1 o 2 es $n+m$, es decir, no se intersectan.

### Combinatoria 

$$
    \left(
    \begin{array}{lcr}
        \text{cant. opciones} \\
        \text{cuantas elijo}
    \end{array}
    \right)
    \left(
    \begin{array}{lcr}
        \text{n} \\
        \text{m}
    \end{array}
    \right)
$$
Combinatoria que vimos en algebra, tiene en cuenta el orden.

## Independencia - Probabilidad Condicional

### Independencia de eventos

#### Definicion: Independencia

Dos eventos, $A$ y $B$, son independientes $\Leftrightarrow P(A \cap B) = P(A) \cdot P(B)$

Hay dos casos particulares:

1. **Caso 1**: $\emptyset$
$$
    P(A \cap \emptyset) = P(\emptyset) = 0 = P(A) \cdot 0 \text{, } \forall A \subseteq S
$$
Entonces $\emptyset$ es independiente de todo evento.
2. **Caso 2**: $S$
$$
    P(A \cap S) = P(A) = P(A) \cdot 1 = P(A) \cdot P(S) \text{, } \forall A \subseteq S
$$

Esta definicion tomo toma en cuenta dos conjuntos, una version mas general: Sea $\{A_k\}$ una coleccion numerable de eventos, los $A_k$ son independientes si:
$$
    P(\cap_k A_k) = \prod_k P(A_k)
$$

### Probabilidad condicional

Generalmente, los ejercicios de este estilo empiezan de las siguientes maneras:

- **Si** la suma es mayor a 8...
- **Sabiendo que** la suma es mayor a 8...
- **Dado que** la suma es mayor a 8...
- **De los casos en que** la suma es mayor a 8...

Basicamente, en estos casos, se reduce el universo a los casos en el que se cumple la primera condicion(importa el orden).

#### Definicion

Dado dos eventos no independientes, $C$ y $D$. Si $P(C) \ne 0$, la probabilidad condicional de $D$ dado $C$ es:
$$
    P(D|C) = \frac{P(D \cap C)}{P(C)}
$$

> **Nota**: La probabilidad condicional cumple con todos los axiomas de la probabilidad, no vemos la demo.

#### Teorema

Sean dos eventos, $C$ y $D$, con $P(C) \ne 0$. Son independientes $\Leftrightarrow P(D|C) = P(D)$

### Teorema de Bayes

Sea $B$ un evento y $\{A_k\}$ una particion. Luego:

$$
    P(A_i|B) = \frac{P(B|A_i) \cdot P(A_i)}{\Sigma_k P(B|A_k) \cdot P(A_k)} 
$$

> **Nota**: Es una manera de dar vuelta el condicional, la demo no la vemos.

**Ejemplo**: En una poblacion, el 4% de los varones y el 2% de las mujeres son daltonicos. Las mujeres son el 53% de la poblacion. 
Cual es la probabilidad de elejir un hombre al azar entre los daltonicos? 

Primero, determino que me estan pidiendo $P(V|D)$(varones dentro de el caso de daltonicos), luego determino las particiones:

- $D =$ Daltonico ; $\bar{D} =$ No daltonico
- $V =$ Varon ; $\bar{V} =$ No varon

Los datos que puedo sacar del enunciado son los siguientes:

- $P(V) = 0,53$ y $P(\bar{V}) = 0.47$
- $P(D|V) = 0,04$
- $P(D|\bar{V}) = 0.2$

Luego, la probabilidad de que al elejir un hombre no daltonico es $P(\bar{D} \cap \bar{V}) = P(\bar{V}) \cdot P(\bar{D}) = 0,53 \cdot 0,98$

Finalmente, uso el teorema de Bayes:
$$
    P(V|D) = \frac{P(V \cap D)}{P(D)} = \frac{0,0118}{0,0294} = 0,635.
$$

### Definicion: Particion

Una coleccion $\{A_k\}$ numerable es una particion de $S \Leftrightarrow$

- $A_k$ son M.E. 
- $S = \cup_k A_k$

#### Formula probabilidad total

Para todo evento $B$ y particion ${A_k}$ tenemos:
$$
    P(B) = \Sigma_k P(B|A_k) \cdot P(A_k)
$$
