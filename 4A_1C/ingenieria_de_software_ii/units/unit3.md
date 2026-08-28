# Unidad 3: Documentacion de Arquitecturas de Software

Las arquitecturas no sirven si no estan bien documentadas, sirven para validar que lo 
construido cumple con la idea original. La gente tiene que poder ver los principales 
elementos funcionales, como se conectan, que caracteristicas operacionales tinene, etc. 
Se deja registro de todas las decisiones tomadas. 

Es muy importante lograr el nivel de detalle corracto a documentar, se busca balancear: 

- Vision general 
- Detalle particular 

Se debe crear una descripcion de la arquitectura que incluya todo. La documentacion son 
documentos vivos pues debe estar actualizada. El nivel de detalle debe ser el minimo 
necesario. El uso de patrones y practicas estandar permite decir mucho con poco. 
Hay muchas maneras de documentar arquitecturas, vamos aa ver una como referencia: 4+1.

La idea es tener distintas vistas del sistema para que cada una ponga foco en 
cosas distintas. 

## Modelo 4+1 

- Vista logica(dominio, persistencia)
- Vista proceso(concurrencia, sincronizacion, en ejecucion)
- Vista de desarrollo(vision estatica, librerias, org. del software)
- Vista fisica(Mapeo del software con el hardware real, distribucion de hardware)

Cada vista ataca un problema en particular, "le habla" a stakeholders distintos. Van 
a tener distinto tipos de diagramas. Cada vista tiene un estandar UML. 

### El +1 

Esta es la vista faltante, son los escenarios. Es la vista que cubre las otras 4, su 
objetivo es validar la arquitectura y explicar como los componentes se comportan en 
runtime.

### Consideraciones

- El modelo se adecua a cada paso 
- Proceso iterarivo 
- Complejidad e interrelacion 

Es posible elegir y eliminar vistas, si la arquitectura cambia hay que mantener 
actualizada la documentaccion. 
Importante no tener inconsistencias, pues las vistas estan muy relacionadas, entonces 
puede ocurrir que haya contradicciones y esto esta mal. 

## C4 

Inspirado en 4+1, surge como alternativa C4. Compuesto por: 

- Context
- Containers 
- Components 
- Code 

No es que tiene otras maneras de ver la arquitectura, sino que van del nivel mas 
alto de abstraccion al mas bajo. Es independiente de la notacion, lo importante 
es que las decisiones se entiendan facil. 

### Context 

El sistema es una caja negra, lo que importa es documentar con que sistemas 
externos se conecta. 

### Containers 

Explora que hay adentro de la caja negra, busca las aplicaciones independiantes. 
Por ejemplo, API, Redis, DB, etc. Son cosas que vamos a poder deployar a un servidor. 

### Components 

Explora lo que hay adentro de los contenedores, pueden aparecer librerias, estructuras 
de codigo, etc. Como va a estar construido el contenedor. 

### Code 

Se baja a implementacion, diagramas de clase, EDRs, etc.  

## C4 vs 4+1 

El concepto de tailoring aplica por igual, muchos arquitectos usan un approach 
hibrido. 


