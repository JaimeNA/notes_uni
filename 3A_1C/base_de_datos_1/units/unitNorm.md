# Continuacion Teoria de normalizacion

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

### Basados en Dependencias de junta
