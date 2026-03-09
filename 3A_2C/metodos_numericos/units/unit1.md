# Capitulo 1: Aproximacion y error

## Calculo numerico

Desarrollo de **metodos constructivos** para la solucion numerica de problemas matematicos. 
Problemas clasicos como calcular la integral, resolver un sistema lineal, etc. 
Estos problemas suelen ser dificil de encontrar una solucion mediante el calculo, entonces 
se utilizan aproximaciones(se conoce como **solucion numerica**).

## Ejemplo

Sea $f:[a, b] \rightarrow \R$ una funcion continuea en $[a, b]$ derivable en $(a, b)$ tal que $f(a)$ 
$f(b) < 0$ y signo $(f')$ constante en $(a, b)$. Entonces $f$ tiene un **unico cero** en $(a, b)$.

Esto tiene solucion, pero no me dice donde encontrarla. Para ello, debemos encontrar un 
procedimiento en un numero finito de pasos, pero casi nunca se encuentra la solucion.

## Error

Obviamente, aparecera un error, puede ser absoluto o relativo.

- Error absoluto = | valor aproximado - valor exacto |
- Error relativo = error absoluto / valor exacto (si valor exacto != 0)

Como el valor exacto no sera conocido, solo se podran establecer cotas para el error.
Hay dos factores que inducen error:

- De aproximacion o truncado(algoritmo-dependiente)
- De redondeo(maquina-dependiente, no puedo guardar un numero periodico en una computadoras)

## Formatos de salida Octave

- **`format`**: 5 cifras significativas
- **`format long`**: 15 cifras significativas
- **`format long e`**: Formato exponencial
- **`format bit`**: Formato de punto flotante de doble precision(en binario)

## Numeros en punto flotantes

La implementacion solo puede representar un subconjunto finitode numeros racionales y no 
estan distribuidos en la recta real. De manera que hay un maximo numero posible de 
representar.

Mas especificamente, dentro de la computadora, se utiliza la norma IEEE 754. Hay muy 
pocos numeros que se pueden representar sin error.

La distancia entre dos numeros consecutivos crece al aumentar el numero considerado. Donde:

$$
x_i = (1 + m) \cdot^E
$$
Y el siguiente:
$$
x_{i+1} = (1 + m + \text{eps}) \cdot 2^E
$$

Donde $m$ es la mantisa y $E$ el exponente. Ademas, eps es el epsilon de la computadora, 
el incremento mas chico posible.

### Numeros especiales

- **Cero**: Se representa con todos 0
- **eps**: Incremento mas chico posible. $2^{-52}$
- **realmin**: Numero mas chico posible. $2^{-1022}$
- **realmax**: Numero mas grande posible. 
- **Inf**: Cualquier numero mayor a realmax. Sale cuando, por ejemplo, se divide por 0

### Formula para pasar a decimal

$$
(-1)^{\text{bit signo}} \cdot 2^{E-1023} \cdot (1+m)
$$

Donde $E$ es el exponente y $m$ la mantisa.

> El error relativo de esta implementacion es $\frac{1}{4} \cdot \text{eps}$


