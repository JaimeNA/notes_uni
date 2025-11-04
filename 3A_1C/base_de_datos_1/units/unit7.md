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

## Repaso

Si se calculan mal las claves, va a salir mal todo lo demas. 
Hay que practicar un monton porque el calculo de las claves tiene que estar perfecto.

### Mal diseno

Un mal diseno tiene:

- Anomalia de insercion
- Anomalia de modificacion
- Anomalia de borrado

### Enfoque analitico

> El enfoque sintentico lo propuso Codd

No es topdown, parte de entidad-relacion, aunque esto solo no garantiza que este libre de anomalias.
Pues depende mucho del creador del modelo o de cambios en el negocio(cambios de reglas de 
negocios).

> Nunca quedarse con un solo algoritmo, tanto para cuando nos lo pidan como para tener plan B.

Se puede usar Tableaux para 2, pero generalmente conviene la otra forma.

> En el algoritmo para verificar las dependencias funcionales, es precondicion dejar 
a la derecha solo un atributo.

## Proceso de normalizacion

Hay varios niveles niveles de descomposicion en esquemas mas pequenios sin anomalias. 
Funcionan asi: un nivel incluye a todos los niveles anteriores, siendo el quinto el nivel 
mas alto. En sistemas transaccionales(constantes inserciones, mod, etc.), 
necesitamos un esquema que este como minima en la tercera forma normal. 

Para sistemas de toma de decisiones(no lo vemos), nos basta con segunda forma normal. 
Para sistemas de objetos relacionales se puede admitir priemra forma. 
En esta materia menos de 3 no. COmo se puede ver, depende mucho del contexto.

Usualmente, para llegar a la forma que a mi me interesa hay que pasar por las anteriores, 
pero Leticia tiene una forma de pasar a tercera sin pasar necesariamente por las anteriores.

### Basados en dependencias funcionales

Las primeras 3 fueron propuestas por Codd, las otras(de las funcionales) fueron propuestas por Codd y Boyce.

#### Primera forma normal 

Un esquema de relacion R se encuentra en primera forma normal si sus atributos son atomicos.

#### Segunda forma normal

Es si no existe atributo no primo en R que sea parcialmente dependiente de alguna 
clave(ver definicon de primo, son los que forman parte de alguna clave).

#### Recubrimiento minimal de F

Es un conjunto Fm de dependencias funcionales que cumple ciertas propiedades(ver apuntes), 
deben estar en orden o no va a funcionar.

#### Tercera forma normal

Un esquema de relacion R se encuentra en tercera forma normal si se cumple que 
$$
\forall \alpha \rightarrow \beta \text{: } \alpha \text{ es superclave o } \beta \text{ es primo}
$$

(Ver algoritmo de descomposicion a tercera en apuntes)

#### Forma normal de Boyce-Codd

(Ver apuntes)

Se procesa visualmente con un arbol, se parece un poco a la segunda forma normal. Es mas puro 
que tercera en cuanto redundancia, pero tiene un problema. El problema no es la perdida de 
informacion, pero si podria ocurrir que haya perdida de dependencias
(si ocurre eso, hay que volver para atras y quedarse en tercera).

### Basados en dependencias multivaluadas

Una dependencia multivaluada es, teniendo dos conjuntos $\alpha$ y $\beta$, la dependencia $\alpha \rightarrow \rightarrow \beta$
es multivaluada si para todo par de tuplas de la relacioni r que coinciden en todos los valores de los atributos de $\alpha$, al intercambiar los valores de los atributos de $\beta$ se obtienen
tuplas que tambien son de la relacion.

> **Nota**: El concepto de trivialidad no es lo mismo

#### Axiomas

**No son los mismos** que con dependencias funcionales.
Tambien hay reglas que se derivan de los axiomas.

#### Cuarta forma normmal

(Ver apuntes)

Es similar a BCNF, 

#### Errores en el DER

Clase de errores q
ue ocurre cuando el analista no entiende a fondo los requisitos o ocurrio un 
cambio de negocio.

### Basados en Dependencias de junta

(Ver ejemplos en apuntes)

#### Quinta forma normal

Toda dependencia multivaluada puede expresarse en junta.

Se puede usar tableau, mientras cambian las variables van produciento una valuacion 
$/ro$(T).

Dos tableau son equivalentes si tienen la misma valuacion.
Existe chase(T) respecto de CR. 

(Ver metodo y teoremas en apuntes)


