# Cheatsheet

## Base de datos 

### NoSQL

Para una cantidad masiva de datos.

### SQL OLTP

Es CA, util para funciones de agregacion.

### SQL OLAP

Para uso analitico(reporting), siempre agregando y no eliminando datos. 

### Cache

Aumenta tiempo de respuestas, pero muy caras.

### Cassandra

Columnar, permite cantidades masivas de lectura y escritura. Corre en cluster, mantiene 
comunicacion entre nodos con Gossip, no sirve cuando se requiere transaccionalidad.

### MongoDB 

Base de datos NoSQL, no brinda alta disponibilidad. Sirve cuando se requiere flexibilidad y 
proporciona alto volumen de escrituras.

### Objetos 

Base de datos NoSQL, guarda objetos complejos en cualquier lenguaje de programacion. 
Automatiza las operaciones mas comunes(CRUD) y facilita programacion con el tradeoff de 
performance y complejidad. 

## Esquemas de base de datos 

### Primario-Secundario 

Busca escalar operaciones de lectura y solo se aplican las escrituras sobre el nodo primario. 
Si se cae el nodo primario, uno de los secundarios es elegido para tomar su rol.

### Primario-Primario 

Busca escalar operaciones de escritura, pero trae problemas de concurrencia y por eso no todos 
los motores RDBM soportan ese modo de operacion. MySQL lo tolera con Group Replication, para 
Postgres se requieren extensiones. Se sacrifica performance. 

## Hosting 

### Cloud 

Todo el sistema se encuentra en la nube, como en AWS. Solucion mas barata y permite aumentar 
el alcance(expandirse a otros paises) de manera eficiente. Cortes de luz/internet ya contemplados.

### On premise 

Los servidores son propios de la empresa, pueden estar tanto en un datacenter compartido como en 
el mismo edificio. Aumenta el ownership del sistema, deben contemplarce los casos de cortes de 
luz/internet. 

### Salas cofre

Salas para almacenar servidores, similar al anterior. 

## Seguridad y conexiones 

### HTTPS 

Version cifrada de HTTP 

### JWT 

Firma digital para verificar la identidad del que manda los datos. Util cuando se tiene 
comunicacion con APIs.

### WAF 

Servicio de seguridad perimetral que analiza, filtra y bloquea el trafico HTTP/HTTPS 
malisioso dirigido hacia la webapp. Protege de SQL Injection, XSS, DDoS(solo de capa 7).

### VPN 

Conexion cifrada y segura mediante un tunel sobre infraestructura publica, permitiendo que 
los sistemas se comuniquen como si estuvieran en una red privada local. 

### Websockets 

Protocolo de comunicacion bidireccional, full-duplex y persistente sobre unica conexion TCP. 
Permite una interaccion fluida y en tiempo real entre cliente y servidor sin la sobrecarga 
del modelo tradicional peticion-respuesta. Traw problemas de availability si se usa con un 
backend que corre en multiples instancias pues no hay forma de reestablecer la conexion si se 
cae un servidor del backend. 

### Webhooks 

Mecanismo de comunicacion asincronicos basado en eventos mediando una aplicacion evia 
automaticamente datos en tiempo real a otra a traves de un HTTP POST. No se puede usar con una 
SPA. 

## Autenticacion 

### 2FA 

Subconjunto de MFA, exige dos evidencias de distinta categoria para validar la identidad de un 
usuario. 

### MFA 

Mas general, requiere dos o mas factores de verificacion independiente de distinta categoria. 

## Multiples instancias de backend 

Brinda escalamiento horizontal, aumentando la capacidad de computo bajo demando, aumenta 
disponibilidad y permite realizar procesamiento en paralelo aumentando la performance. 
Sin embargo, tiene el riesgo de la caida del load balancer, pero no el riesgo de la caida de 
una instancia del backend. 

## Problemas de calidad y su solucion 

### Availability

- Ataque DDoS -> Web Application Firewall (WAF)
- Caída Backend -> Múltiples Instancias + Load Balancer
- Caída Base de Datos -> Replicación Primaria-Secundaria si es relacional o Funcionamiento en Clúster para no relacionales.
- Corte de Internet -> Múltiples Proveedores
- Caída de la cola -> Asegurarse de especificar que corra en clúster
- Corte de luz -> Generador o doble servicio de luz
- Caída de Internet -> Doble proveedor

### Security

- Ataque DDoS -> Web Application Firewall
- Ataque MItM -> Conexiones HTTPS
- Ataque a operadores con info privilegiada -> Usar VPNs en sistemas internos
- Robo de credenciales -> Multi Factor Authentication (MFA)
- Gestión de Accesos -> Access Control List (ACL) en el Backend
- SQL Injections -> Sanitizar entradas en la SPA.
- Ingresos maliciosos a la BD -> Encryption at rest, la data se guarda encriptada.
- Datos muy sensibles -> Hosting privado en una sala cofre (solamente se accede con llave)

### Performance

- Las lecturas tardan mucho -> Caché en el back. Importante definir qué se guarda y cuándo se borra.
- Demasiadas solicitudes -> Proceso en paralelo (sirve tener muchas instancias) y multithreading.
- Reportes Real Time -> Réplica de sólo lectura de la base de datos
- Reportes No Real Time -> Se copian los datos de la BD SQL OLTP a una OLAP mediante ETL (Extract, Transform, Load). Hacer el reporte en horarios de baja demanda.
-  Caída del elemento activo -> Tener un heartbeat entre instancias para saber cuándo usar las pasivas. 
- Lectura en real time -> websocket 
- Búsquedas en múltiples fuentes -> procesamiento asincrónico y en paralelo con un timeout.

### Precision

- Error de Punto Flotante -> punto fijo
- Sensor puede medir mal -> dos en activo-activo
- Propagación de errores en cálculos intermedios -> métodos numéricos
- Faltan mediciones -> interpolación de datos
- Mediciones varían mucho -> Intervalos de confianza
- Guardar datos como string en la base de datos

### Scalability

- Picos Recurrentes -> Load balancer
- Muchos accesos a la SPA desde distintos puntos -> hosting en CDN
- Bases de datos que permitan sharding y, por ende, escalamiento horizontal.
- Crecen las lecturas -> read replicas

### Interoperabilidad

- **Patrón adapter**: patrón de diseño estructural que permite la colaboración entre objetos con interfaces incompatibles, actuando como un traductor que convierte la interfaz de una clase existente en otra interfaz que el cliente espera.
- Exponer una API con requests que sólo toleren lecturas para que otros servicios puedan integrar.
Fault Tolerance
- Fallo en la carga de un archivo necesario para generar un reporte -> el sistema sigue funcionando normalmente basándose en la información disponible.
- Proceso ETL(Extraction, Transformation, Load), combina, limpia y organiza datos de 
multiples fuentes en un unico conjunto de datos coherente.

### Accessibility

- Probar con distintos usuarios
- Internacionalización
- Diseños HCI
- Alt en imagénes para lectores de pantalla
- Sistemas de lectores de pantalla

### Tradeoffs Comunes

- Availability vs Costos al tener escalamiento horizontal
- Performance vs Security al tener encryption at rest
- Precision vs Performance si se tienen operaciones de punto fijo
- Performance vs Availability si se tienen websockets, ya que si se cae un nodo, ningún otro toma la conexión y se muere.


