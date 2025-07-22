# Osciladores armonicos simples

Variacion oscilatoria(periodica) de una magnitud alrededor de un punto de equilibrio.

## Ejemplos

- Resortes
- Pendulos
- Ondas E.M.

## Modelo resorte
	
Caracteristicas:
- Suponemos muy liviano
- Debe estar en regimen lineal($F=k \cdot \Delta x$)
- Sin friccion

Por Newton:

\begin{gather}
	\nonumber F = k \cdot \Delta c = m \cdot a_x
\end{gather}

Debido al sistema de coordenadas elejido:

\begin{gather}
	\nonumber -k \cdot x = m \cdot a_x \\
	\nonumber -k \cdot x = m \cdot \ddot{x}
\end{gather}

Despejo:

$$
	\ddot{x} + \frac{k}{m} \cdot x = 0
$$

Proponemos solucion a la EDO(una manera de resolverla):

\begin{gather}
	\no x(t)=C \cdot e^{rt} \\
	\no x'(t)=rC \cdot e^{rt} \\
	\no x''(t)=r^2C \cdot e^{rt}
\end{gather}

Reemplazamos en la EDO
\begin{gather}
	\no (r^2 + \frac{k}{m})  \cdot C \cdot e^{rt} = 0 \\
	\no r^2 + \frac{k}{m} = 0 \\
	\no r = \pm i \sqrt{\frac{k}{m}}
\end{gather}

Pasamos a formula general:

\begin{gather}
	\no x(t)=C_1 \cdot e^{i\sqrt{\frac{k}{m}}t} + C_2 \cdot e^{i\sqrt{\frac{k}{m}}t} \\
	\no\text{(Oscilador armonico)}\\
	\no\text{Se definen } C_1 = \frac{A}{2}\cdot e^{i\varphi_0} 
	\no\text{ y } C_2 = \frac{A}{2}\cdot e^{-i\varphi_0}\\
\\
	\no x(t)=\frac{A}{2}\cdot e^{i\varphi_0} \cdot e^{i\sqrt{\frac{k}{m}}t} + \frac{A}{2}\cdot e^{-i\varphi_0} \cdot e^{i\sqrt{\frac{k}{m}}t}\\
	\no x(t)=\frac{A}{2}\cdot(e^{i(\sqrt{\frac{k}{m}}t + \varphi)} + e^{-i(\sqrt{\frac{k}{m}}t + \varphi)} )\\
	\text{Usamos notacion de Euler:}\\
	\no x(t) = A\cdot \cos{(\sqrt{\frac{k}{m}}t + \varphi_0)}
\end{gather}

$A$ es la amplitud, $\varphi$ es la fase(angul en cada $t$), $\varphi_0$ es el angulo inicial
($x_0=A\cdot \cos{ \varphi_0}$) y $T=\frac{2\pi}{\omega}$ es el periodo, entonces $\omega$ es la frecuencia angular. $T$ es constante, entonces $\omega$ es propiedad del sistema.

\begin{gather}
	\no x(t)= A \cdot \cos{(\omega t + \varphi_0)}\\
	\no v(t)= - A \cdot \omega \cdot \sin{(\omega t + \varphi_0)}\\
	\no v(t)= - A \cdot \omega^2 \cdot \sin{(\omega t + \varphi_0)}
\end{gather}

\begin{tikzpicture}
	\begin{axis}[axis lines=middle, xlabel=$x$, ylabel={$y$}, grid=major]
		\addplot[blue, domain=0:2*pi, samples=100] {sin(deg(x))};
		\addlegendentry{$x(t)$}

		\addplot[red, domain=0:2*pi, samples=100] {cos(deg(x))};
		\addlegendentry{$v(t)$}

		\addplot[green, domain=0:2*pi, samples=100] {-sin(deg(x))};
		\addlegendentry{$a(t)$}

	\end{axis}
\end{tikzpicture}

## Energia del oscilador armonico

\begin{gather}
	\no E(\text{mecanica}) = U + K \\
	\no E = \frac{1}{2} m v^2 + \frac{1}{2} k x^2 \\
	\no E = \frac{1}{2} m A^2 \cdot \omega^2 \cdot \sin^2{(\omega t + \varphi_0)} + \frac{1}{2} k A^2 \cdot \cos^2{(\omega t + \varphi_0)}\\
	\no\text{Como } \omega=\sqrt{\frac{k}{m}} \Rightarrow k=m \omega^2
\end{gather}

Finalmente:

$$
	E=\frac{1}{2} m \omega^2 A^2 = \frac{1}{2} k A^2 
$$

Notar que es constante.

# Movimiento Amortiguado

SE agrega resistencia de tipo viscosa($b$: Coeficiente de viscosidad), para identificar un movimiento de va y van hay que encontrar un punto de equilibrio.

El MAS es sumamente ideal, oscila eternamente, el MA se acerca mas a la realidad.

## Planteamiento de la segunda ley de Newton

Teniendo en cuenda que la fuerza realizada por $b$ viene de $b \cdot \vec{v}$, y que el origen de coordenadas esta en el punto fijo del resorte.

\begin{gather}
	\no -k \cdot (x - l_0) - b \cdot \dot{x} = m \cdot \ddot{x} \text{ (EDO inhomogenea)}\\
	\no \ddot{x} + \frac{b}{m} \cdot \dot{x} + \frac{k}{m} \cdot x = \frac{k \cdot l_0}{m} \\
\end{gather}

Llamando $\gamma = \frac{b}{2m}$(amortiguamiento) y a $\omega_0 = \sqrt{\frac{k}{m}}$ (frecuencia angular natural). Se da que a mayor viscosidad, mayor amortiguamiento.

> Notas que si $x_{eq} = 0 \Rightarrow$ La EDO es homogenea.

### Solucion EDO:

$x(t) = x_h(t) + x_p(t)$

**Particular**

Se propone una constante, quedando $x_p(t) = l_0)$

**Homogenea**

Se propone $x_h(t) = C \cdot e^{rt}$

Quedando $r = \pm \sqrt{\gamma^2 - \omega_0^2}$, por lo que dependiendo de $\gamma$ el resultado serareal o imaginario.

Como hay dos soluciones, la expresion general para la posicion de la masa viene dad por una combinacion lineal de ambos:

$$
    x_h(t) = C_1 \cdot e^{- \gamma t} \cdot e^{\sqrt{\gamma^2 - \omega_0^2} \cdot t} + C_2 \cdot e^{- \gamma t} \cdot e^{- \sqrt{\gamma^2 - \omega_0^2} \cdot t}
$$

## Casos

- **Oscilacion sobreamortiguada($\gamma > \omega_0$)**

En este caso $\sqrt{\gamma^2 - \omega_0^2}$ es real, por lo tanto $x_h(t)$ no sufre modificaciones. No habra oscilacion pues el entorno es demasiado viscoso.

$$
    x_h(t) = e^{- \gamma t} \cdot ( C_1 \cdot e^{\sqrt{\gamma^2 - \omega_0^2} \cdot t} + C_2 \cdot e^{- \sqrt{\gamma^2 - \omega_0^2} \cdot t} )
$$

- **Oscilacion criticamente amortiguada($\gamma = \omega_0$)**

Se tiene que:

$$
    x_h(t) = C \cdot e^{- \gamma t}
$$

Sin embargo, dado que la ecuacion diferencial es de segundo orden es necesario agragar un termino lineal:

$$
    x_h(t) = (C_1 + C_2 \cdot t) \cdot e^{- \gamma t}
$$

Por la forma de la ecuacion se puede ver que no oscila, ademas, comparando el grafico en funcion de tiempo se puede apreciar que la criticamente amortiguada decae mas rapido que la sobreamortiguada.

- **Oscilacion subamortiguada($\gamma < \omega_0$)**

Ahora $\sqrt{\gamma^2 - \omega_0^2}$ es imaginaria, se define $\omega'$ tal que:

$$
    \sqrt{\gamma^2 - \omega_0^2} = \sqrt{-1} \cdot \sqrt{\omega_0^2 - \gamma^2} = i \omega'
$$

La solucion toma la forma:

$$
    x_h(t) = e^{- \gamma t} \cdot (C_1 \cdot e^{i \omega' t} + C_2 \cdot e^{-i \omega' t})
$$

Se resuelve de similar manera a la MAS, utilizando las propiedades de Euler. Para expresarla de manera mas intuitiva, proponemos que las constantes tengan la siguiente forma:

$$
    C_1 = \frac{A}{2} \cdot e^{i \varphi} \text{ y } C_2 = \frac{A}{2} \cdot e^{-i \varphi}
$$

Luego, reemplazo en la ecuacion de movimiento:

$$
    x_h(t) = e^{- \gamma t} \cdot (\frac{A}{2} \cdot e^{i \varphi} \cdot e^{i \omega' t} + \frac{A}{2} \cdot e^{-i \varphi} \cdot e^{-i \omega' t})
$$

De esa manera, estamos en condiciones de aplicar las propiedades de Euler y obtener la ecuacion final.

$$
    x_h(t) = A \cdot e^{- \gamma t} \cos(w' t + \varphi)
$$

Auque la oscilacion desminuye con cada cicle, $w'$ permanece constante, por lo tanto el pseudoperiodo $T'$ tambien lo es. Se definie como pseudoperiodo pues nunca vuelve al mismo lugar, a diferencia del MAS.

$$
    T' = \frac{2 \pi}{\omega'}
$$

## Factor de diminucion

Por ejemplo, para la energia, el factor de disminucion de la energia es:

$$
    \frac{\Delta E}{E}
$$

Esta se puede aproximar para casos en los que el factor de calidad sea muy grande(~100) de la siguiente manera:

$$
    \frac{\Delta E}{E} \approx \frac{2 \pi}{Q}
$$

## Energia del oscilador subamortiguado

$$
    E = E_c + E_p + \frac{1}{2} \cdot m \cdot v^2 + \frac{1}{2} \cdot k \cdot (k - l_0)^2
$$

Luego, a partir de las ecuaciones de movimiento, se puede obtener la ecuacion de la energia en funcion del tiempo. Conviene deducirla en vez de aprendercela de memoria.

![Energia de un oscilador subamortiguado en funcion del tiempo](graphics/energy-osc.png)

### Caso de amortiguamiento debil

Esto se da cuando $\gamma << \omega_0$ (tiene que ser por lo menos un orden de magnitud menor para que sea valida esta estrategia).

Esto busca simplificar: 

$$
    \frac{\gamma^2}{\omega_0^2} \approx 0 \text{ y } \frac{\gamma \cdot \omega'}{\omega_0^2} \approx 0
$$

De manera que la ecuacion de energia queda como:

$$
    E(t) \approx \frac{1}{2} \cdot k \cdot A^2 \cdot e^{-2 \gamma t} = E_0 \cdot e^{-2 \gamma t}
$$

### Tiempo caracteristico

Con el fin de analizar el comportamiento principal de la energia se considera unicamente la contribucion exponencial, se considera el **tiempo caracteristico**. El cual indica cuanto tiempo demora la funcion en llegar a $\frac{1}{e}$ de su energia.

$$
    \tau = \frac{1}{2 \gamma}
$$

> Solo sirve para comparar en amortiguamiento debil

### Energia perdida de un oscilador debilmente amortiguado

La energia mecanica total que posee el sistema decrece exponencialmente en el tiempo debido al trabajo de la viscosidad.

$$
    E_{total} = E_{sis}(t) + E_{ent}(t)
$$

De manera que se puede deducir que en el caso de un amortiguamiento debil, la energia del entorno se puede obtener de:

$$
    E_{ent}(t) = E_0 \cdot (1 - e^{-2 \gamma t})
$$

## Factor de calidad

Parametro adimensional que indica la forma en la que un oscilador disipa energia mecanica, para determinar el regimen de oscilacion:

$$
    Q = \omega_0 \cdot \tau = \frac{\omega_0}{2 \gamma}
$$

- $Q < \frac{1}{2} \Rightarrow$ Sobreamortiguado
- $Q = \frac{1}{2} \Rightarrow$ Criticamente amortiguado
- $Q < \frac{1}{2} \Rightarrow$ Subamortiguado

> **Nota**: No de puede suponer que la amplitud $A$ de un movimiento subamortiguado vaya a tomar el mismo valor que la posicion inicial.

# Oscilaciones forzadas

Se agrega una fuerza de tipo armonica, la cual afecta a la masa y es de la forma:
$$
    F(t) = F_0 \cdot \cos(\omega t)
$$
Se tienen siempre las siguientes cosideraciones:

- Regimen lineal(del resorte)
- Masa puntual
- Sin rozamineto
- Resorte ideal(sin masa)

## Planteamiento de la segunda ley de Newton

$$
    F_0 \cdot \cos(\omega t) - k(c - l_0) - b \dot{x} = m \ddot{x}
$$
Acomondando todo queda una EDO de segundo orden:
$$
    \ddot{x} + 2 \gamma \dot{x} + \omega_0^2 x = \frac{F_0}{m} \cdot \cos(\omega t) + \frac{k l_0}{m}
$$
Donde $\gamma = \frac{b}{2m}$ y $\omega_0 = \sqrt{\frac{k}{m}}$

## Solucion EDO

### Homogenea

Idem a resorte amortiguado, en este caso, debido a que todas las soluciones del oscilador amortiguado decaen como exponenciales, luego de cirto tiempo decaen a 0.

![Oscilaciones forzadas](graphics/forzadas.png)

### Particular

Se propone $x_p(t) = A_{est} \cdot \cos(\omega t - \delta) + cte$, donde:

- $\omega$: Frecuecnia de oscilacion
- $A_{est}$: Amplitud
- $\delta$: Retraso en la respuesta del sistema

Pensar al retraso como el tiempo que tarda en responder la masa a la fuerza, es decir, el tiempo entre que se aplica ya fuerza y cuando la masa se comenzo a mover de acuerdo.

Luego, se remplaza $x_p(t)$ en la EDO y se resulve como en MA. Se toma la constante como $l_0$ para que quede como si se hubiese planteado el 0 en el equilibrio. Quedando:
$$
    -\omega^2 \cdot A_{est} \cdot \cos(\omega t - \delta) - 2 \gamma \omega A_{est} \cdot \sin(\omega t - \delta) + \omega_0^2 A_{est} \cdot \cos(\omega t - \delta) = \frac{F_0}{m} \cdot \cos(\omega t)
$$
Usando las propiedades trignometricas:

- $\sin(x \pm y) = \sin(x) \cos(y) \pm \cos(x) \sin(y)$
- $\cos(x \pm y) = \cos(x) \cos(y) \mp \sin(x) \sin(y)$
 
Para que no quede el $\cos(\omega t)$ solo. De manera que:
$$
    [(\omega_0^2 - \omega^2) \cdot \cos(\delta) + 2 \gamma \omega \cdot \sin(\delta) - \frac{F_0}{m A_{est}}] \cdot \cos(\omega t) +  [(\omega_0^2 - \omega^2) \cdot \sin(\delta) - 2 \gamma \omega \cdot \cos(\delta)] \cdot \sin(\omega t) = 0
$$
Dado que $\cos(\omega t)$ y $\sin(\omega t)$ son linealmente dependientes, para que de cero la suma los terminas que los multiplican deben ser ambos 0.

1. $(\omega_0^2 - \omega^2) \cdot \cos(\delta) + 2 \gamma \omega \cdot \sin(\delta) - \frac{F_0}{m A_{est}} = 0$
2. $(\omega_0^2 - \omega^2) \cdot \sin(\delta) - 2 \gamma \omega \cdot \cos(\delta) = 0$

De las ecuaciones anteriores se puede obtener:

1. $A_{est} = \frac{F_0}{m \cdot \sqrt{(\omega_0^2 - \omega^2)^2 + (2 \gamma \omega)^2}}$
2. $\tan(\delta) = \frac{2 \gamma \omega}{\omega_0^2 - \omega^2}$
3. $\sin(\delta) = \frac{2 \gamma \omega}{m \cdot \sqrt{(\omega_0^2 - \omega^2)^2 + (2 \gamma \omega)^2}}$

$A_{est}$ y $\gamma$ **NO** dependen de las condiciones inicioales, sino que dependen de la frecuecnia de oscilacion. MA y MAS difieren de esto. El punto 3 acota a $\delta$, pues solo puede estar entre $0$ y $\pi$.

## Curva de resonancia de amplitud

La frecuencia de resonancia en amplitud es aquella con la cual un sistema oscilante, cuando es sometido a una fuerza externa periodica, responde con la maxima amplitud de oscilacion posible. Por lo tanto, nos intereza conocer el maxima de la curva de resonancia. Para ello buscamos cuando la primer derivada de $A_{est}(\omega)$ de 0:
$$
    \frac{d A_{est}}{d \omega} (\omega_{RA}) = 0 \Rightarrow \omega_{RA} = \sqrt{\omega_0^2 - 2 \gamma^2}
$$
Luego:
$$
    A_{max} = \frac{F_0}{2 \gamma m \sqrt{\omega_0^2 - \omega^2}} = \frac{F_0}{b \omega'}
$$

> **Nota**: La ordenada al origen es $A_{est}(0) = \frac{F_0}{k}$

![Resonancia de amplitud](graphics/resonancia_amplitud.png)

## Potencia transmitida al oscilador

Recuerdo:

- **Trabajo de fuerza variable**: $W = \int \vec{F} d \vec{l}$ 
- **Por teorema fundamental del calculo**: $P = \frac{dW}{dt}$

Teniendo que $P(t) = F(t) \cdot v(t) = F_0 \cdot \cos(\omega t) (- \omega) A_{est} \cdot \sin(\omega t - \delta)$ obtenemos:
$$
    P(t) = F_0 \omega A_{est} \cdot \sin(\delta) \cos^2 (\omega t) - F_0 \omega A_{est} \cdot \cos(\delta) \cos(\omega t) \sin(\omega t)
$$

## Potencia media

Se define valor medio:
$$
    <f(t)> = \frac{1}{\Delta t} \int_t^{t + \Delta t} f(t) dt
$$
Para una funcion periodia:
$$
    <f(t)> = \frac{1}{T} \int_0^{T} f(t) dt
$$
Por lo tanto, aplicado a la potencia:
$$
    <P(t)> = \frac{1}{T} \int_0^T [F_0 \omega A_{est} \cdot \sin(\delta) \cos^2 (\omega t) - F_0 \omega A_{est} \cdot \cos(\delta) \cos(\omega t) \sin(\omega t)] dt
$$
Utilizando tecnicas de resolucion de integrales, combio de variable y tablas se obtiene:
$$
    <P(t)> = \frac{F_0^2}{4 m \gamma} \cdot \frac{(2 \gamma \omega)^2}{(\omega_0^2 - \omega^2)^2 + (2 \gamma \omega)^2}
$$

## Curva de resonancia en potencia

Similar a la amplitud, nos interesa cuando el sistema recibe la potencia maxima de una funente externa. La frecuencia de resonancia en potencia hace referencia a la frecuencia a la cual la potencia transferida desde una fuente externa a un sistema oscilante es maxima. En otras palabras, es la frecuecnia con la que el sistema absorbe la mayor cantidad de energia por unidad de tiempo de una fuente externa. 

El maximo de obtiene idem a RA:
$$
 \frac{d<P>}{d \omega} (\omega_{RP}) = 0 \Rightarrow \omega_{RP} = \omega_0
$$
Luego:
$$
    <P> (\omega_0)= \frac{F_0^2}{4 m \gamma}
$$

![Resonancia potencia](graphics/resonancia_potencia.png)

## Ancho de la curva de resonancia o de banda 

El ancho de banda es una medida de la gama de frecuencias alrededor de la frecuencia de resonancia
a las cuales el oscilador responde de manera significativa. Se refiere a la diferencia de frecuencias en las que la potencia del oscilador cae a la mitad de su valor máximo.

Este ancho de banda está relacionado con el amortiguamiento del sistema. Un sistema con menos
amortiguamiento (subamortiguado) tendrá una curva de resonancia más estrecha y pronunciada,
indicando una mayor selectividad de frecuencia. Por otro lado, un sistema con mayor
amortiguamiento (más amortiguado) tendrá una curva de resonancia más ancha, indicando que
responde de manera significativa a un rango más amplio de frecuencias.
$$
    \Delta \omega = \frac{\omega_0}{Q} = 2 \gamma
$$

- $Q$ es el mismo deinido en MA
- Mientras mas amortiguado sea, mayor sera el ancho de la curva de resonancia
- La respuesta del sistema cuando es sometido a una fuerza externa depende del amortiguamiento del medio.

![Ancho de banda](graphics/ancho_banda.png)

