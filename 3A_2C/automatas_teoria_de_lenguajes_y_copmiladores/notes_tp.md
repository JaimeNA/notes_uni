# Proyecto especial

## Etapa 2: Frontend

- No hay que hacer ningun informe.
- Clonar repo y tenerlo funcionarlo.
- El readme tiene que ser lo mas compacto posible, con lo minimo.
- En la wiki se va mas en detalle.
- Conviene que todo sea por variables de entorno y no por parametros, 
es mala practica.
- CMake es un builder de builders, te crea un makefile que despues podes usar 
para el build. Hace un makefile hecho especificamente para el sistema operativo en 
el que estas.
- Cambiar algo en la imagen para que no sea lo mismo para todos los grupos, poner 
nombres o algo asi.
- En los test va nuestro DSL.
- El log estructurado no esta escrito pasa el usuario, sino que se escribe como json, 
cada linea es un json estruturado que permite que otra aplicacion los analice.

## Flujo

### Frontend

- Flex --> Bison.
- Flex es el analizador lexico(lexer, scanner) y Bison es el analizador 
sintactico(parser).
- Cuidado con usar strtok, es ilegal.
- Cuando agreguemos el back puede pasar que test que pasaban van a fallar.
- Se puede entregar con el pipeline roto pues los errores pueden salir de no 
tener backend.
- TOKEN = Lexema + Atributos(o metadata).
- Sale un stream de tokens de Flex, es un stream porque no tiene inicio ni fin.
- Flex le pasa a Bison los tokens(pedazos) que luego se usan para armar un arbol.
- Flex escanea de izquierda a derecha y cada vez que encuentra una expresion, hace 
un corte.
- Un token se crea, se pushea y de destruye, es importante logear.
- El EOF es el fin para Bison. Probablemente tengamos todos la misma funcion.
- Siempre matchea con la regex que mas caracteres matchee.
- Los espacios son ignorados.
- Hay que poner las que queremos que salga primero antes.
- Cada vez que entra a una expresion, entra a un DFA.
- Hay que tener cuidado con los contextos. Hay un contexto por defecto, el 
`initial` que es el 0, por eso nuestros contextos empiezan con 1.
- El DFA es mucho mas eficiente que uno de pila. Uno es O(n) mientras que una 
libre de contexto es O(n^2), por eso se usan ambos.
- En Bison definimos nuestra gramatica.
- Union es que todo esta en la misma posicion de memoria, se pisa si se escribe con 
otro tipo.
- **IMPORTANTE**: Lambda se representa como `%empty`
- Cuando termina de parsear usa destructores
- Los conflictos son shift/reduce y reduce/reduce, indican cuando la gramatica es 
ambigua. Shift es leer un nuevo simbolo y reduce es aplicar una regla de produccion. 
El punto indica donde esta parado `.`. Se resuelven con reglas de 
precedencia definidas en bison.
- Si no soluciono los conflictos, bison va a hacer shift, pero en la entrega 
no puede ser asi. No hay que dejar que el analizador decida.
- Los reduce/reduce indican que hay cosas ambiguas en la gramatica, hay dos 
caminos para derivar la misma estructura. Son mas dificiles de resolver, hay 
que reestructurar la gramatica.

### Backend

- Pasa a ser analisis semantico.
- Recibe el arbol armado en el frontend(AST).
- 
