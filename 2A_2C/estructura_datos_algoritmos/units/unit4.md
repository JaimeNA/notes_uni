# Unidad 4: Hashing

Es una alternativa para solucionar varios problemas, se utiliza para hacer lookup rapidos de alguna componente. Sin embargo, problemas que soluciona:

- Representar la clase `Bag`
- Representar la clase `Set`
- En algoritmos como fibonacci, se puede implemenar un cache

No sirve para obtener el mayor, el menor, un rango, un arreglo ordenado. Sirve para hacer busqueda puntual, toma la idea de arreglo y la aprovecha al maximo, por eso su imlementacion siempre tiene un arreglo. El problema es que el lugar que ocupa una componente no depende del orden de llegada, la posicion es fuertemente dependiente de los valores de las otras componentes. 

## Hash table

Estrucutra de datos que utiliza un arreglo(lookup table) para almacenar pares key/value de una forma muy especial. No mantiene contiguedad, ni orden de las componentes.
Si $\Omega$ es el universo de los items que queremos almacenar y tenemos un arreglo Lookup subjacente, entonces, utiliza una funcion de hashing $hash: \Omega \rightarrow [0, |lookup| - 1]$, donde $|lookup|$ es la cantidad de ranuras.

Prioriza la busqueda, tratando de que el algoritmo tenga complejidad cercana a O(1) en la busqueda. Si para distintos elementos devuelve el mismo indie, hay que solucionarlo, eso lo veremos mas adelante.

### Primer escenario

Suponemos que hay suficiente espacio, o sea $| \Omega | << |lookup|$.
Sea $\Omega$ el conjunto de claves a hashear, y sea LookUp el arreglo para albergar los pares key/value. Asumimos $| \Omega | \le | Lookup |$, es decir, hay posibilidad de que a cada key se le asigne una ranura de LookUp. 

Como los keys que proporciona el usuario son tipos opacos(TAD, Objetos) es bueno solicitarle que proporcione una funcino de:

$prehash: \Omega \rightarrow N$ Eso es lo que nos hace implementar Java, son numeros naturales en general, no le vamos a pedir al usuario que acomode el numero a la cantidad de ranuras que nosotros reservamos.

Y nosotros, el que implementa el hashing, tenemos que garantizar que $hash: \Omega \rightarrow [0, | lookup | - 1]$ (cantidad de ranuras) porque la ranura tiene que ser valida, hacemos:
$$
    hash(key) = prehash(key) \% | Lookup |
$$

### Definicion: Funcion hash perfecta

Una funcion hash perfecta es dificil de encontrar, pero existe, donde no hay colisiones. Por ejemplo, en Soundex implementamos una tabla de hash perfecta pues era un hashing fijo. Por lo tanto, nuestro objetivo es crear una funcion hash que minimice las coliciones.

### Definicion: Colision

Se dice que 2 claves key1 <> key2 **colisionan** si hash(key1) = hash(key2), es decir, se le asigna la misma ranura.

### Factor de carga(current load factor)

Se define el factor de carga como:
$$
    CLF = \frac{| \text{Keys usadas} |}{|lookup|}
$$

El algoritmos de insercion **provisorio**(porque todavia no manejamos colisiones) consiste en ir a la ranura correspondiente y proceder segun el caso:

1. Si esta ocupada por el mismo key $\Rightarrow$ Era un update. Lo actualiza.
2. Si esta ocupada por otra key $\Rightarrow$ Era insercion con colision. Esta insercion no es exitosa porque todavia no manejamos colisiones, lanzamos una excepcion.
3. Si esta vacia $\Rightarrow$ Era insercion exitosa. Primero insertar allo. Luego, chequear si el factor de carga supera un cierto umbral predefinido(**Load Factor Threshold**) y si eso ocurre se duplica el espacio y se rehashean todas las claves.

Entonces, la mayoria de los casos se va a comportar como orden 1, pero cada tanto va a haber que reordenar pues cada tanto se degrada. En realidad es O(1) en promedio, por lo que realmente es $\Omega(1)$.

### Hashing sobre strings

- **Metodo 1**: ASCII del primer caracter
- **Metodo 2**: Suma de los ASCII, pero se le puede agregar un numero primo en la formula(como hace java), va a hashear mucho mejor, es decir, en la suma se le multiplica por un numero primo a cada valor asccii que se suma.

### Funcinoes para claves numericas

- **Division o Modulo**: $hash(x) = x \cdot mod m$ Donde m debe ser primo.
- **Mid-square**: Se calcula el cuadrado de un numero y se toman los bits centrales coo lugar donde hashear. Por ejemplo, si X = 14, X^2 = 23420 y hay que tomar los bits centrales para poder hashear. Si la tabla tubiera 11 elementos usaria el numero 34 % 11 (o 42 % 11).
- **Folding o Plegado**: Se divide el numero en zonas de la misma longitud. Se las suma y se toman los bytes necesarios. Por ejemplo, si X=20112241203123 y la tabla tiene 101 elementos, se arman grupos de a 3 digitos, se obtiene: 020 + 112 + 241 + 203 + 123 = 699 y se lo hashea a 699 % 101.
- **Analisis del Digito**: Implica un conocimiento de antemano de las caracteristicas de la poblacion. Se analiza los patrones de las claves, en busca de la informacion de la clave que menos se repite. Por ejemplo, si las claves para hashear los alumnos de una facultad en cierto ano fuera su DNNI, no convendria elegir suus primeros digitos porque todos los alumnos de un mismo ano comienzan con las mismas cifras de DNI.

## Manejo de colisiones

Hay dos maneras de resolverlo, la mas complicada es la primera:

1. **Open Addressing or Closed Hashing**: Cuando hace la colision, busca un lugar disponible dentro de la tabla. Guarda los elementtos que colisionaros denttro de la misma tabla.
2. **Open hashing or Closed Addressing or Chaining**: Fuera del hashing se almacenan los elementos que colisionaros, pueden ser listas o arboles. Comienza a degradarse e orden 1.

### Open Addressing

Cada ranura puede tener null(vacio o baja fisica). Aunque la ranura no este vacia puede ser que el elemento no este(Se haya borrado, pero no se lo quito), ya que hay que manejar el concepto de bajas logicas(ademos de las fisicas). Es decir, una ranura representa 3 estados. Formas de resolver:

#### Rehasheo Lineal(lineal probing)

 Si hay colision en la ranura i, entonces intentar con la ranura i+1, y asi siguiendo hasta encontrar que el elemento(se hace update) o encontrar un lugar vacio(baja fisica) y se la inserta alli. Con esta tecnique si hay lugar lo encuentra seguro. Este algorimo fue creado para disco, nosotros lo implementamos en RAM asi que los hacemos en RAM.

- **Borrado**: No se puede reemplazar al lugar borrado por una ranura vacia porque la busqueda de alguna clave puede necesitar "pasar sobre ella" si hubo colision. Se debe manejar dos tipos de borrado, fisico y logico. El borrado fisico se lo usa cuando la ranura que le sigue esta tambien borrado fisicamente, mientras que el borrado logico se lo usa en caso contrario, o sea cuando la ranura que le sigue esta ocupada o borrada logicamente.
- **Busqueda**: Se comienza buscando en la ranura calculada. Si el lugar esta con baja fisica seguro que no esta en otro lado. Caso contrario si esta amrcado como ocupado y coincide con el valor esperado, se ha encontrado. 
- **Insercion**: Si la ranura calculada esta marcada como baja fisica, el elemento se inserta alli. Caso contrario hay que comenzar a navegar con las sucesivas.

> **NOTA**: La baja fisica afecta la performace, para no rebotar de mas en las colisiones, lo mejor en el algoritmo de insercion es no insertar en el primer espacio libre que se encuentra, sino en la primera baja logica que se encontro en el camino hasta descubrir que se podia insertar(se hallo baja fisica).

#### Rehasheo Cuadratico(quadratic probing)

El intervalo entre ranuras a usar, si hubiera colision, sera cuadratica.

