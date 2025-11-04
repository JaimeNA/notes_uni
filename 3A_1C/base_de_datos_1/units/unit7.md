# Unidad 7: Teoria de normalizacion

Hay varios tipos de anomalias:

- Anomalias de insercion(por ejemplo, insertar un alumno que todavia no rindio nada, pero tener que poner NULL en materias rendidas)
- Anomalias de modificacion(**no decir** que no se puede hacer el update, sino que decir que es un tema de performance ya que se encuentra en varios bloques y entonces hay que levantar un bloque, bajarlo y buscar otro...)
- Anomalia de eliminacion

El enfoque sintetico en la teoria esta bien, pero en la practica la ideal es unar un enfoque analitico. Es muy naive pensar que las cosas 
no cambian, podrian aparecer cambios en los cambios y en las tablas. El enfoque analitico esta alerta a posibles modificaciones.

## Definicion: Dependencia funcional

Sean X e Y dos conjuntos de atributos de un esquema de relacion R. Se dice que X determina funcionalmente a Y o que Y depende funcionalmente de X, lo cual se denota X $\rightarrow$ Y, si se cumple:

$$
    \forall t_1, t_1 \in r: (t_1[X] = t_2[X] \Rightarrow t_1[Y] = t_2[Y])
$$

F es el conjunto de dependencias funcionales de R.

> Las reglas puestas por el usuario no se cuestionan.

## Redefinicion: Superclave

Sea un esquema de relacion R, $X \subseteq R$ es superclave para R si se cumple:

$$
X \rightarrow R
$$

## Redefinicion: Clave candidata

Sea un esquema de relacion R, $X \subseteq R$ es clave para R si se cumple:

1. Es superclave:
$$
X \rightarrow R 
$$

2. Condicion minimal
$$
\nexists Y \subset X / Y \rightarrow R
$$

> Si bien ANCI pide dependencias funcionales, nadie lo obedece. Posgres no lo llego a implementar, lastima que no lo hace para UNIQUE, solo para PK.

## Dependencia funcional

### Clausura de F

El usuario me da las dependencias funcionales canonicas, pero hay un conjunto que se puede inferir. Se denota con $F^+$:

$$
F^+ = {\alpha \rightarrow \beta / F |= \alpha \rightarrow \beta}
$$

Hay algoritmos que lo definen. Para ello se usan los aximoas de Armstrong y las reglas de inferencia.

Con los aximos se pueden derivar todas las dependencias funcionales y no se encuentran de mas.

### Axiomas de Armstrong

1. Reflexividad
2. Aumentacion
3. Transitividad

### Reglas de inferencia

1. Union
2. PseudoTransitividad
3. Descomposicion(contrapuesta de la union)

> **Nota**: Las demostraciones las tenemos que saber usar.

### Clausura de un conjunto de atributos

Si X es un conjunto de atributos de R, la clausura de X, denotada con $X^+$, 
respecto del conjunto de dependencias F de dciho esquema, es el cojnuto de atributos determinador por X a traves de las dependencias de $F^+$:

$$
X^+ = {A / X \rightarrow A \in F^+}
$$

> Si hay atributos que no estan a la derecha de nadie, entonces pertenecen a **todas** las claves.

### Algoritmo de Tableaux

Se arma una tabla con los esquemas que quiero separar, habra variables distinguidas y no distinguidas.
(Ver apuntes para mas detalles).

---

Un buen algoritmo cumple:

- Sin perdida de informacion
- Preserva dependencias funcionales

### Definicion: Descomposicion sin perdida de dependencias

Dado un esquema R, se dice que no hay perdida de dependencias al descomponerlo en N esquemas Ri, 
si sus clausuras de dependencias funcionales son equivalentes:
$$
F'^+ = F^+ \text{ donde } F'^+ = \cup^N_{i=1} \pi_{Ri}(F^+)
$$

## Algoritmo para calcular la proyeccion de $F^+$ sobre un subesquema de R(el algoritmo)

(Ver apuntes)

## Tema clase 6/10

