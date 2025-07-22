# Ondas propagantes

## Definicion: Onda

Perturbacion de alguna magnitud fisica que se propaga sin arrastrar al medio.

## Clasificacion

Hay tres maneras distingtas de clasificarlas:

1. **Medio**:
    - **Mecanicas**: Hacen vibrar al medio.
    - **Electromagneticas**: Pueden propagarse en el vacio.
2. **Direccion del movimiento**:
    - **Transversales**: Vibracion perpendicular a la propagacion.
    - **Longitudinales**: Vibracion paralela a la propagacion.
3. **Direccion**
    - 1D
    - 2D
    - 3D, si es uniforme, cada direccion radial es 1D(similar con 2D)

## Casos de estudio

- **Cuerda**: Mec - Transv - 1D
- **Sonido**: Mec - Long - 2D
- **Luz**: EM - Transv - 3D

## Diagrama cuerpo libre

![Diagrama cuerpo libre](graphics/diagram_waves.png)

Si se analiza cada seccion de una cuerda de longitud $\Delta l$, se pueden observar las siguiente fuerzas:
$$
    \text{x) } F_2 \cdot \cos (\theta_2) - F_1 \cdot \cos (\theta_1) = m a_x
$$
$$
    \text{y) } F_2 \cdot \sin (\theta_2) - F_1 \cdot \sin (\theta_1) - P = m a_y
$$

Si se tienen en cuanta las siguientes consideraciones:

- La cuerda esta tensa con tension $F$
- Es liviana($P << F \Rightarrow F_1 \approx F_2$)
- Es uniforme, densidad lineal de masa, incluso a pesar de que cambia cuando se estira.

Con estas consideraciones se pueden simplificar las ecuaciones:
$$
    \text{x) } F \cdot \cos (\theta_2) - F \cdot \cos (\theta_1) = m a_x
$$
$$
    \text{y) } F \cdot \sin (\theta_2) - F \cdot \sin (\theta_1) = m a_y
$$

## Oscilaciones de pequena amplitud

Estas seran las que estudiaremos nosotros, con $\theta$ muy chica se llega a la siguiente conlusion:
$$
    \cos(\theta) \approx 1 \text{ ; } \sin(\theta) \approx \tan(\theta) \approx \theta
$$

Apliando esto a las ecuaciones anteriores:
$$
    \text{x) } 0 = a_x
$$
$$
    \text{y) } F \cdot \tan (\theta_2) - F \cdot \tan (\theta_1) = m a_y
$$

Notar que la tangente sera la tangente del angulo que forma la pendiente, de manera que:
$$
    F(\frac{\partial y}{\partial x}_{x + \Delta x} - \frac{\partial y}{\partial x}_{x}) = \mu \cdot \Delta x \cdot \frac{\partial^2 y}{\partial t^2}
$$

> **Nota**: $\mu$ es la densidad lineal de la cuerda, entonces $m = \mu \Delta x$

Operando, vemos que queda lo siguiente:
$$
    \frac{(\frac{\partial y}{\partial x}_{x + \Delta x} - \frac{\partial y}{\partial x}_{x})}{\Delta x} = \frac{\mu}{F} \cdot \Delta x \cdot \frac{\partial^2 y}{\partial t^2}
$$  

Luego, si $\Delta x$ tiende a 0, vemos que queda la definicion de derivada. Suponiendo que la curva es lo suficientemente suave y puede derivarse varias veces, queda:
$$
    \frac{\partial^2 y}{\partial x^2} = \frac{\mu}{F} \cdot \Delta x \cdot \frac{\partial^2 y}{\partial t^2}
$$

Esta ecuacoin se conoce como **ecuacion de onda** y su solucion como **funcion de onda**. Representando la funcion de onda como $y = f(x, t)$, donde $y$ es el desplazamiento vertical, se observa que la onda se desplaza dependiendo del valor de $x$ y $t$, donde el desplazamiento lo representaremos como $k = vt$. Entonces defininimos $v$ como la velocidad de propagacion, esta solo depende del medio(la cuerda).

![Funcion de onda](graphics/frequency_waves.png)

Para que se propague la onda sin deformarse:
$$
    y = f(x \pm vt)
$$

Esta es la solucion de $\frac{\partial^2 y}{\partial x^2} = \frac{1}{v^2} \cdot \Delta x \cdot \frac{\partial^2 y}{\partial t^2}$, es igual a ecuacion de onda, por lo tanto, teniendo en cuenta la igualidad queda:
$$
    v = \sqrt{\frac{F}{\mu}}
$$

> **Nota**: La velocidad depende del medio, pero no la frecuencia, esta puede cambiar.

## Onda armonica

Al ser armonica, puede representarse con una funcion sinuosa:
$$
    y(x, t) = A \cdot \cos[k(x \pm vt) + \varphi_0]
$$

Quedando:
$$
    y(x, t) = A \cdot \cos[kx \pm kvt + \varphi_0]
$$

Si se fija la x, queda una oscilacion armonica de la forma $y(x, t) = A \cdot \cos[cte \pm kvt]$, luego la frecuencia angular queda:
$$
    \omega = kv
$$

Ademas, se define la longitud de onda como $\lambda$.

![Longitud de onda](graphics/wavelength.png)

### Frecuencia espacial

Teniendo que $x = vt$, queda:
$$
    y(x, t) = A \cdot \cos[cte \pm kx]
$$

Por lo tanto, $k$ es la frecuencia espacial, mide que tan juntos o separados estan los picos. Se conoce mas comunmente como **numero de onda**.

### Relacion entre periodos

Se sabe que $k = \frac{2 \pi}{\lambda}$ y que $\omega = kv$, entonces:
$$
    \frac{2 \pi}{T} = \frac{2 \pi}{\lambda}
$$
$$
    \lambda = vt
$$

## Energia en ondas propagantes

Nosotros vamos a estudiar el caso de una cuerda, entonces:
$$
    \text{Onda no arrastra medio} \rightarrow \text{Algo se propaga} \rightarrow \text{Energia}
$$

### Energia potencial

Deformacion del medio, compresion de un gas. Para el caso de la cuerda, esta sera la **energia elastica**.
$$
    \Delta U = F \cdot (\Delta l - \Delta y)
$$

Usando polinomios de Taylor, se tiene que $(1+x)^n = 1 + nx = \text{ ... }$, entonces si $x$ es muy chica, $x^2$ es aun mas(y sigue para el resto de las potencias). Por lo tanto, la gran parte de la funcion esta en los primeros terminos, quedando:
$$
    (1 + x^n) \approx 1 + nx \text{ si } |x| << 1
$$

Aplicado a la formula anterior(ya que se trata de pequenas oscilaciones):
