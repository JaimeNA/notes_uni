# Unidad 2: Algoritmos para textos

La pricipal motivacion de estos algoritmos es analizar y comparar textos, ya sea para detectar copias o para detectar similitudes. Esto se aplica en areas como en la politica y la biologia.
Otra razon mas simple, es para buscar typos y corregirlos. La matematica es muy util para formalizar todo esto.

## Definicion: Alfabeto

Se denota $\Sigma$ y es el conjunto de simbolos o caracteres. 

## Definicion: String

Dado un alfabeto $\Sigma$ y $k_{ \ge 0} \in N$, un string `S` es un elemento de $\Sigma^k$

La longitud del stt=ring es $k$ y si $k=0$ entonces se lo denota $\lambda$. 

## Por que se lo denota $\lambda$?

Oara evitar los problemas que tienen los compiladores, un meta-simbolo no deberia ser al mimso tiempo parte del alfabeta. Los lenguages de programacion violan estas relgas y ahi surgen los problemas.

Para evitar ambiguedades obliga a escapar al simbolo comillas dobles cuando participa del string, es decir, `"hola"que"` para el compilador es simplemente 'hola', pero `"hola\"que"` es 'hola"que'. Sin embargo, esto no soluciona la ambiguedad pues '\\' es parte del alfabeto. 
Si no nos presenta confucion, podemos hablar de `""` como el string vacio.

## Definicion: Conjunto de todos los string posibles

Dado un alfabeto $\Sigma$, $\Sigma^@ = \cup \Sigma^k$ con $k_{\ge 0} \in N$

> **Nota**: Deberia ser sigma^asterisco, lo reemplace con @

## Definicion: Concatenacion de strings

Dado un alfabeto $\Sigma$, y $u, w \in \Sigma^@$

Se llama **concatenacion** al string definido como $uw$ (uno a continuacion del otro).

## Definicion: Prefijos, Sufijos y Subtrings

Dado un alfabeto $\Sigma$ y los strings $x, w, z \in \Sigma^@$. Sea $p=xyz$. 
Se dice que $x$ es un **prefijo** de $p$. Se dice que $w$ es un **substring** de $p$. Se dice que $x$ es un **sufijo** de $p$.

> $\lambda$ representa vacio, entonces es sufijo, prefijo y borde.

## Definicion: Bordes

Dado un alfabeto $\Sigma$ y los string $x, y, z \in \Sigma^@$. Si $p = wz = zw$ donde $|x| = |z|$, se dice que $w$ es un **borde** de $p$.

> Un borde es prefijo y sufijo al mismo tiempo.

## Dara Quality - Matching

Busqueda aproximada, los buscadores pueden usar varias tecnicas para esto y suelen ser muy variadas. Algunas de ellas(las estrategias pueden ser combinaciones de uno o mas de estas), de menos a mas avanzadas:

- Busca palabras y si las palabras no estan en el corpus de los documentos indizados, encontrar las que mayor similitud posean y sugerirlas.
- Tomar el idioma que tiene configurado el browser para saber en que corpus buscar las palabras usadas. Hay reglas conocidas por el idioma en cuestion, lo cual permite su deteccion.
- Sabiendo que los usuarios buscan palabras y cuando fue un error de tipeo, no clickean nada del resultado e intentan realizar inmediatamente la busqueda arreglada, almacenan estas buscasdas erroneas para su uso posterior en caso de que otro usualrio haga algo similar.

### Minimas reglas que deberian aplicarse:

1. Sacar blancos de comienzo y final(trim) - Pero no es suficiente, podria haber blancos entre medio
2. Buscar pasando todo a mayuscula o minuscula
3. Si se conocen abreviaturas, usarlas - BA por Buenos Aires
4. Los simbolos de punctuacion, eliminarlos
5. Si se conocen sinonimos, usarlos

### Reglas especificas para un determinado tipo de datos

Este tipo de cosas se tienen que tener en cuenta:

- Fechas y sus formatos
- La hora y sus formatos
- Numeros
- Numeros decimales
- Strings correspondientes a nombres y apellidos (depende del orden)

## Algoritmos busqueda aproximada

### Soundex

Es un algoritmo fonetico, es decir, codifica una palabra segun como suena. Intenta solucionar problemas de pronunciacion, fue creado para el alfabeta ingles, pero existen implementacinoes para otros idiomas.
Primer algorimo fonetico inventado, de la decada de 1930 y fue utilizado hasta la decada de 1990.

Idea, aquellas palabras que suenen igual, aunque no se escriban igual, deben ser codificadas de la misma manera. Lo que hicieron fue agrupar letras que sonaban parecido.

![Categorizacion de letras del algorimo Soundex](graphics/soundex.png)

Soundex siempre devuelve un `OUT` de exactamente 4 caracteres formados por:

```
 [Letra][Digito][Digito][Digito]
```

#### Pasos

1. Pasar todo a mayuscula y dejar solo las letras(digitos, simbolos de punctuacion, espacio, etc., se eliminan)
2. Colocar `OUT[0] = IN[0]` - La primera se la quedan pues es la mas importante
3. Se calcula la variable `last` como el peso fonetico de `IN[0]`
    3,1 Calcular variable `current` con un peso fonetico de `iter`. Si es diferente a 0 y no concide con `last`.
    3.2 `last = current`
4. Para cada letra en `IN`, iterar hasta completar 3 digitos o terminar de procesar `IN`. 

Un caso, si un producto no se encuentra en la categoria esperada

#### Como usarlo

Soundex no es una metrica, hay que definir como obtener una metrica a partir de Soundex.

#### Definicion de similitud

Comparo caracter a caracter y cuento cuantas veces coicidieron. Si ninguno concide, entonces da 0. Si coinciden 3 caracteres, entonces da 0.75, es decir, la metrica va de 0 a 1.

> **Nota**: Un Hashmap es la mejor opcion para hacer un lookup, pero para que sea O(1) tiene que cumplir con ondiciones especiales. Para el caso de Soundex, un hashmap encubierto. 

### Metaphone

Aparece en 1990 y presenta mejoras en relacion a Soundex, no hace la clasificacion, sino que la salida son letras que representan los osnidos.
Genera un encoding de longitud arbitraria. En wikipedia esta bien documentado el algoritmo, pero recomiendad usar la version en ingles pues es la que esta mejor explicada.

> Se recomienda ver la implementacion en `apache/commons-codec` una vez implementaste tu version, ahi hay muchos algoritmos conocidos.

#### Definicion de similitud

La similitud entre 2 textos puede pensarse como la proporcion de caracteres coincidentes entre los encodings respecto de la misma longitud, pero cuando no tienen la misma longitud, no es trivial. Para ello se utiliza **Levenshtein Distance**.

### Levenshtein Distance

No es un encoding, calcula la **minima** cantidad de operaciones necesarias para transformar un string en otro. Las operaciones validas son:

- Insertar
- Borrar 
- Sustituir un caracter

> **Nota**: Esto es la version canonina, hay alternativas con mas operaciones.

Aquellos strings iguales deben tener distancia 0. Basicamente hay muchas maneras de ir a un string a otro, como podemos obtener la manera con la menor cantidad de pasos?

La forma manual de hacerla es con una matriz, arriba va una palabra y al costado otra, cada palabra debe comenzar con un meta-caracter pues debe ser un simbolo que no esa en el lenguaje.

|        | meta-c | B | I | G |   | D | A | T | A |
| ------ | ------ | - | - | - | - | - | - | - | - |
| meta-c | 0      | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
| B      | 1      |   |   |   |   |   |   |   |   |
| I      | 2      |   |   |   | x |   |   |   |   |
| G      | 3      |   |   |   |   |   |   |   |   |
| D      | 4      |   |   |   |   |   |   |   |   |
| A      | 5      |   |   |   |   |   |   |   |   |
| T      | 6      |   |   |   |   |   |   |   |   |
| A      | 7      |   |   |   |   |   |   |   |   |

Para calcular el valor de x, esa celda representa `L('BIG', 'BI')`. Pero hay varias posibilidades para decidir el valor de x en base a los anteriores, el objetivo es obtener el menor. Los tres valores candidatos:

1. `L('BI', 'B')`(Celda anterior, diagonalmente) + `G == I?0:1`
2. `L('BIG',' B')`(Celda anterior, verticalmente) + 1
3. `L('BI',' BI')`(Celda anterior, horizontalmente) + 1

La ultima celda sera el resultado.

**Ejempo casi-resuelto**:

|        | meta-c | B | I | G |   | D | A | T | A |
| ------ | ------ | - | - | - | - | - | - | - | - |
| meta-c | 0      | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
| B      | 1      | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
| I      | 2      | 1 | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
| G      | 3      | 2 | 1 | 0 | 1 | 2 | 3 | 4 | 5 |
| D      | 4      | 3 | 2 | 1 | 1 | 1 | 2 | 3 | 4 |
| A      | 5      | 4 | 3 | 2 | 2 | 2 | 1 | 2 | 3 |
| T      | 6      | 5 | 4 | 3 | 3 | 3 | 2 | 1 | 2 |
| A      | 7      | 6 | 5 | 4 | 4 | 4 | 3 | 2 |**1**|

> Este algoritmo es caracteristico, pues el minimo tomado en algun lugar intermedio llega al minimo global. Minimo local coincide con minimo global(no siempre ocurre).

#### Normalizacion

La distancia es un numero cualquiero, si quiero un numero entre 0 y 1, se obtiene de:

$$
    LevenshteinNormalize(str1, str2) = 1 - \frac{Levenshtein(str1, str2)}{\max{(str1.len, str2.len)}}
$$

#### Propiedades matematicas

El algoritmo cumple con las siguientes propiedades.

- **Simetria**: $L(s1, s2) = L(s2, s1)$
- **Desigualdad**: $L(s1, s2) + L(s2, s3) >= L(s1, s3)$

#### Programacion dinamica

Es una tecnica que consiste en rausar valores previamente calculados para no tener que recalcularlos repetidamente. Sirve, si para cierto calcula, pueden reusarce valores previos y tiene la desventaja de que estoy cambiando tiempo por espacio(`cache`). 

Para buscar estos valores necesitamos una estructura de datos que permita buscar valores lo mas rapido posible -> matriz, vector, etc.

De esta manera, por ejemplo, el algoritmo `fibonacci` pasa de ser $O(2^N)$ a $O(N)$.

#### Variantes

Otras variantes no consideran que las operaciones valos todas igual. Algunas es mas cara que otra y cambia la formula de distancia. Algunas agregan operaciones.

**Ejemplo**: Se agrega la operacion transposicion. Contempla el tipico error de escritura en el cual se invierten las letras.

#### Complejidad temporal

Notar que a pesar de ser dos `if` anidados, la complejidad no sera $O(N^2)$ pues la matriz no sera cuadrada, tendra una complejidad de $O(N \cdot M)$.

#### Complejidad espacial

Similar a la temporal ya que mantenemos la matriz, sera un orden de $O(N \cdot M)$

### Optimizacion - Version compacta

Mas util si solo necesito conocer la similitud. Notar que no necesitamos todas las filas anteriores para seguir con la siguiente fila, entonces se propone un cambio de punteros, basicamente utilizando solo dos filas en vez de la matriz. 

Solo hay que hacer un cambio de variables, no conviene copiar todo un arreglo. Notar, que tendra otra complejidad espacial. 
De todas formas, los dos algoritmos sirven, para distintas cosas. El menos compacto sirve para obtener los pasos que tendria que hacer para transformar los strings(haciendo el camino inverso).

### Q-Grams(o N-Grams)

Consiste en generar los pedazos que componen un string. La distancia entre 2 strings estara dada por la cantidad de componentes que tengan en comun. No es un econding sino que simplementte realiza la comparacion.

Si Q es 1 se generan componentes de longitud 1. Si Q es 2 se generan **bi-gramas**, si Q es 3 se generan **tri-gramas**. Nosotros vamos a  completar con $Q-1$ al comienzo y al final para que todas aparezcan la misma contidad de veces.

**Por ejemplo**: `Q-grams(JOHN) = {J, O, H, N, #J, JO, OH, HN, N#, ##J, #JO, JOH, OHN, HN#, N##}`

#### String matching

Para comparar dos strings, se compararn los arreglos de dos palabras y se cuenta cuantos elementos tienen en comun. Luego, la distancia sera la cantidad de elementos en comun. La implementacion que es mas usada, es tri-grama y esa es la que vamos a usar nosottros. Si al comparar aparece el mismo patron dos veces en uno y una vez en el otro, solo se cuenta una vez, es decir, la comparacion es 1 en 1.<D-p>

#### Normalizacion

$$
  Q-grams(str1, str2) = \frac{\#TG(str1) + \#TG(str2) - \#TGNoShared(str1, str2)}{\#TG(st1) + \#TG(str2)}  
$$

> **Nota**: En general, nuca se usa un algoritmo individual, sino que se usa una combinacion de varios. Utilizando una formula que los combine.

## Algoritmos para busqueda exacta

### Fuerza bruta o Naive

``` java
    public static int indexOf(String str, String pattern) {
        
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();

        int j = 0;

        for (int i = 0; i < s.length; i++) {
            if (s[i] == p[j]) {
                j++;
            } else {
                i -= j;
                j = 0;  // Puede estar mal, pues tengo que ir para atras de nuevo
            }

            if (j == p.length) {
                return i - j + 1;
            }
        }
        return -1;
    }
```

Voy comparando char a char y verificando si coinciden, recorriendo todo el array. El peor caso es que el query no encuentre el target.

- **Complejidad temporal** = O(n\*m), donde `n = target.length` y `m = query.length`
- **Complejidad espacial**: O(1)

Este algoritmo no es el mejor ya que no aprovecha lo que aprendio en el recorrrido.

### Knuth-Morris-Pratt

Examina el **target** de izquierda a derehca, pero usa conocimiento sobre los caracteres comparados antes de determinar la proxima posicion del patron a usar. Preprocesa el query antes de la busqueda una vez, con el objetivo de analizar la estructura(las caracteristicas del patrton query). Para ello contruye una tabla **Next** del mismo `length` que el query.
No vuelve para atras, eso es muy importante ya que reduce la complejidad temporal. La tabla tiene en cada posicion `i` la longitud del **borde propio** mas grande para el substring desde 0 hasta `i`.

Implementacion de la computacion de Next:

``` java
    private static int[] nextComputation1(char[] query) {
		int[] next = new int[query.length];

	    int border=0;  // Length of the current border

	    int rec=1;  
	    while(rec < query.length){
	        if(query[rec]!=query[border]){  
	           if(border!=0)  
	                border=next[border-1]; 
               else  
                    next[rec++]=0;  
           }
           else{ 
          	    border++;  
                next[rec]=border;  
	            rec++;  
	        }
	    }
	    return next;
	}
```

Es muy astuta la implementacion y tiene complejidad temporal O(n).

Para calcular el `search` sin el **backtracking**, avanza entre la tabla del `query` y el `target`, cuando no haya coincidencias se **shifttea** el `query` de la siguiente manera:

...(Falta completar)

Finalmente, la complejidad temporal sera O(n+m)

## Fulltext Retrieval

El problema es que KMP realiza la busqueda en un documento, pero si quisiera buscar sobre un grupo de documentos, nos conviene generar un indice de dicha coleccion para luego buscar a traves del indice.
Lo que ocurre es que generalmente la **query** no siempre es la misma, pero los documentos no cambian, entonces conviene preprocesar los documentos(similar a lo que hace google). Esto se conoce como contruir el **corpus**. Eso es lo que hacen las bibliotecas de **Fulltext Retrieval**. 
Existe una biblioteca de codigo abierto para esto: **Lucene**. Tomamos ventaja de los indices obtenigos. 

Basicamente, esta biblioteca nos permmite armar nuestro propio "search engine", tiene hasta el manejo de sinonimos. 

### Indices

Un indice es una estrucutra que permite llegar rapidamente al dato buscado, pero si se modifica un documento en la coleccion debe actualizarse. Es decir, su ventaja esta en la busqueda y tiene la desventaja de nececsitar actualizar la estructura cada vez que se modifique un documento.

Lucene usa un **archivo invertido** (conjnuto de terminos que dicen a que documentos pertenecen). Es un mapping:

Termino -> Documento

**Por ejemplo**:

```
    MapReduce -> {Doc1, Doc2}
    Grid -> {Doc2}
    ...
```

De esa manera evita tener que ir a mirar los documentos. 

### Definicion: Documento

Un documento en Lucene es una secuencia de **campos**(fields). Cuando se ingresa un documento en Lucene, automaticamente se asociara un ID(docid).

### Definicion: Campo(Field)

Un campo en Lucene es un par nombre y secuencia de 1 o mas terminos.

``` java
    // Como crearlo
    FieldType aField = new FieldType();

    // Como decidir se se almacena o indexa
    aField.setStored(true);

    // Como especificar como indexar(depende cuanto espacio queres que ocupe)
    aField.setIndexOptions(IndexOptions.NONE); 
    // - NONE(no se indexa)
    // - DOCS(se indiza solo con los doc ids en los que esta)
    // - DOCS_AND_FREQS(similar a DOCS, y ademas se especifica la frecuencia)
    // - DOCS_AND_FREQ_AND_POSITIONS(Idem a anterior, ademas de las posiciones ordinales)
    // - DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS(No lo vamos a usar, solo para frontend)
```

Para evitar escribir `DOCS_AND_FREQ_AND_POSITIONS` todo el tiempo, se usa `TextField`, el cual es simplemente un alias. 
La opcion de almacenar solo se podra hacer si no viene de un **stream**, sino lo estaria almacenando 3 veces: en el stream(file system), en el indice y ademas otra vez literal fuera del archivo. 

### Definicion: Termino(Term)

Un termino Lucene es una secuencia de bytes(Podrian interpretarse como Strings, numeros, etc. asociada a cierto campo. Dos secuencias de bytes con igual contenido pero asociadas a 2 campor diferentes se consideran diferentes.

> **Nota**: Nosotros solo vamos a usar Strings como terminos.

### Almacenamiento

Lucene es peculiar ya que tiene una zona donde guarda informacion que no es el archivo invertido. 
Al crear un campo(nombre y terminos) y asociarlo a cirto documento puedo optar por:

- Almacenarlo en Lucene pero fuera del archivo invertido. Eso significa que se lo almacena literal(sin procesamiento, no se lo separa en tokans, no se pasa a minuscula, etc.). Es decir, esta en Lucene pero no esta indexado, entonces no participa de las busquedas.
- Indexarlo en el archivo invertido Lucene. Eso significa que esta tokenizado o no(segun lo configure) forma parte del archivo invertido y sus terminos(to kens), entonces participan de las busquedas.

> **Nota**: Uno busca palabras especificas, no busca signos de punctuacion, preposiciones, etc. Solo nos interesan las palabras clave.

Un field puede ser solo almacenable, solo indexable o ambas cosas. Es obligatoria que sea, por lo menos, una de ellas. La informacion se almacena de distintas maneras dependiendo de la opcion que eleji para los fields. Es una tabla ordenada alfabeticamente por token.

| Value term | Freq en docs | [docid:freqs in docif:[position]] |
| ---------- | ------------ | --------------------------------- |
| ...        | ...          | ...                               |

![Ejemplo almacenamiento Lacene](graphics/lacene.png)

Como se le asigna el id al doc? Si quiero el nombre y no el id? Para eso sirve el almacenamiento fuera del index, el nombre del path lo puedo almacenar en el almacenamiento aparte. Es un exelente uso para el almacenamiento fuera del indice en Lucene. 

### Ranking de documentos

Si busco un **query** y trae varios documentos, cual documento aparece primero? cual es mas relevante que otro? Esto se decide a partir de una formula.
$$
    \text{Score}(DOC_i, query) = \text{FormulaLocal}(DOC_i, query) \cdot \text{FormulaGlobal}(DOC, query)
$$

**Formula local**: Quiere calcular que tan relevante es esa query respecto a un documento en particular a rankear. Por lo tanto, si busco un paper en Java, me va a importar mas el que mencione mas dicho lenguaje. Si dos documentos tienen la misma cantidad de apariciones, se elije el de mayor volumen.
$$
    \text{FormulaLocal}(DOC_i, query) = \sqrt{\frac{\#\text{frec term en }DOC_i}{\#\text{term existentes en }DOC_i}}
$$

**Formula global**: Quiere calcular que tan relevante es esa query respecto a la coleccion de documentos existente. Tampoco se quiere linealidad, sino que tiene escala logaritmica.
$$
    \text{FormulaGlobal}(DOC, query) = 1 + \log_e (\frac{1 + \# \text{docs en la coleccion }}{1 + \# \text{docs que contienen la query}})
$$

#### Multi-Query

Si es una query multi termino, el score queda: 
$$
    \text{Score}(DOC_i, query) = \Sigma_{\text{term in query, without NOT}} \text{FormulaLocal}(DOC_i, term) \cdot \text{FormulaGlobal}(DOC, term)
$$

Si es con `AND` es solo los documentos con los dos cumplidos, pero se siguen sumando los scores.:3


### Queries

Vamos a usar dos aplicaciones: IndexBuilder y TheSearcher(se encarga de aceptar consultas, utiliza el indice, solo devuelve los ids). **SOLO** usar las versiones de las teoricas, sino no nos va a funcionar porque cambio la API.

Hay dos maneras para hacer **queries**(Una mas alto nivel que otra):

- **API**
    - `TermQuery`
    - `PrefixQuery`
    - `PhraseQuery`
    - `WildcardQuery`
    - `FuzzyQuery` <- Usa Damerau-Levenstein(Con transposicion) con un MaxEdit=2
    - `BooleanQuery`
    - etc
- **Lenguaje lado cliente**
    - `TermQuery`: Con `content:game`.
    - `PrefixQuery`: Con `content:ga*`.
    - `TermRangeQuery`: Con `content:{gaming TO gum]`, donde `{` es abierto y `]` cerrado.
    - `PraseQuery`: Con `content:\"store game\"`, busca frase donde la primer palabra sea 'store' y la segundo 'game'.
    - `WildcardQuery`: Con `content:g??e`.
    - `FuzzyQuery`: Con `content:gno~2`, no se toman mas de 2, estaria muy lejos.
    - etc.

El  `QueryParser` es una extension que permite simplificar los query. Ejemplo de uso:

``` java
    String queryStr= "content:game";
    
    QueryParser queryparser = new QueryParser(null, new StandardAnalyzer() );
    Query query= queryparser.parse(queryStr);
```

Si busco 'ga' con `content:ga` no va a encontrar 'game', pero si quiero buscar el analogo a `PrefixQuery` busco prefijo `ga` con `content:ga*`. Si ya se que las queries siempre van a ser en `content`, se podria pasar como default y no tener que poner el prefijo.

``` java
    String queryStr= "game";
    
    QueryParser queryparser = new QueryParser("content", new StandardAnalyzer() );
    Query query= queryparser.parse(queryStr);
```


> **Nota**: Si pones `content:store content:game` lo va a tomar como `content:store OR content:game`, hay que tener cuidado. Tambien acepta `||` como **or**.

### Analyzers

Si buscamos 'Game' no lo va a encontrar, esto lo solucionamos con un analyzer, el cual se va a encargar de pasar a minuscula, sacar numeros, etc. Permite separar los tokens, en el ejemplo anterior usamos el `StandardAnalyzer`, pero hay muchos mas, permitionme filtrar con precision lo que quiero que se indexe y lo que no.
Si ninguno te sirve, podes usar un `CustomAnalyzer` y personalizarlo como quieras.

``` java
Analyzer a = CustomAnalyzer.builder()
            .withTokenizer("standard")
            .addTokenFilter("lowercase")
            .addTokenFilter("stop")
            .addTokenFilter("porterstem") // Word family
            .addTokenFilter("capitalization")
            .build();
```

El `SpanishAnalyzer` busca la raiz de la palabra ya que podria ser similar al del castellano(Stemmer Algorithm).

### Solr y Elasticsearch

Lucene no ofrece escabilidad, para ello se utiliza Solr o Elasticsearch.
Para realizar bsuquedas de gran escala, se ditribuye la busqueda en varias computadoras. Una compuradora dirije y pide la busqueda y el resto realiza la busqueda en diferentes documentos.

