# Ejercicio parcial 

## Requerimientos 

### Funcionales 

- Procesar valores de los sensores de diversas formas 
- Disparar alertas cuando algun parametro este fuera de lo aceptable 
- Generar reportes
- No todos los reportes son accesibles para todos los usuarios

### No Funcionales

- Frecuencia de sensado lo mas alta posible 
- Consultas veloces 
- Debe funcionar por varios anios 
- Adaptarse a distintos tipos de sensores

## Atributos de calidad 

1. Presicion 
2. Availability
3. Performance 
4. Mantainability

## Arbolito 

### Precision 

- Sensor failure  
- Data types 

### Availability 

- Hardware failure
- Power failure

### Performance 

- Data lantency
- Data recovery 

### Mantainability

- Hardware change 
- Precision change 

## Arquitectura 
 

## Riesgos 

- Fallen todos los sensores 
- 

## No riesgos 

- Falle un sensor
- Caiga una instancia del backend 
- Caiga una instancia de la DB 

## Supuestos 

- Disponibilidad 99.99
- Mayor cantidad de lecturas 

## Tradeoffs

- Availability por costos 
- Presicion por performance 

--- Hecho por sotuyo --- 

## Atributos de calidad

- Security - ACL 
- Performance - Freq. de sensado 
- Interoperability - Cambio de sensores 
- Reliability - Duradero en el tiempo 
- Precision - Calculos complejos
- Fault tolerance - Rotura de sensores 

1. Reliability 
2. Precision 
3. Interoperability
4. Fault tolerance

## Arquitectura 

- **Factores externos**: Sensores(varios), reporters, alarmas(varias). 
- Una webapp, con ACL
- Base de datos OLAP, se llena con cosas que fueron pasando por el dia, pero jamas tener una OLAP sin OLTP, conectadas con ETL(sistema batch, cada una hora). 
- Computadora local que recopila la informacion de los sensores y la manda a la base de 
  datos OLTP.
- Luego, se pasa a la OLAP por ETL donde puede visualizarse la data  en la webapp 
- La compu on-premise hace el procesamiento de la informacion, para poder activar las 
  alarmas si hace falta. 
- Todo es on-premise 
- Los reporters se conectan mediante internet a la webapp 
- Una computadora mas con otro juego de sensores, pero conectada a las mismas alarmas, 
  sistema Activo-Pasivo. 
- Base de datos secudaria para la OLTP
- Punto fijo en vez de punto flotante para evitar errores de conversino de tipos 

--- gran cambio --- 

Saco las dos instancias, hago divide and conquer, tengo varias computadoras(pollers) con 
uno o dos sensores cada uno. Estas estan conectadas a una computadora principal, la 
cual se encarga de procesar los datos, conectarse a las alarmas y de escribir a la 
base de datos. Con esto se obtiene reliabilitty y un aumento importante el la velocidad 
de lectura. 

Todos los pollers se conectan con la principal con eventos, productores y consumidores 
clasico, con un queue, un kafka pues garantiza que lleguen en orden, que se procesen 
todos los mensajes, que tengan TTL. 

## Escenarios 

### Reliability 

- Se rompe un sensor --> pongo dos de cada uno --> la compu solo tiene un puerto --> 
  uso dos computadoras PDE(Process Data Engine) --> Detecto si un sensor falla con un 
  timeout porque la falla mas comun es que se pierda la conexion --> Debe ser un timeout 
  chico pues va por polling y bloquea al resto, pero no puede ser muy corto porque 
  despues si responde tarde pisa la siguiente comunicacion --> se define empiricamente
- Falla la DB --> DB secundaria 
- Se corta la luz --> grupo de electrogeno
- Inundacion -> Asumo riesgo 
- Fuego -> Asumo riesgo 
- Se llena el queue mas rapido de lo que se lo procesa --> asumido
- Se cae el servidor --> Escalamiento horizontal --> Problemas en la DB, pues son varios 
  escribiendo. 

### Precision 

Depende de: 

- Precision del sensor 
- Tipo de dato(punto flotante) 
- Metodos numericos aplicados 
- Intervalo de confienzo 
- Propagacion de errores 

Por lo tanto, se usa punto fijo en vez de punto flotante, para asegurar el mismo nivel 
de precision. 
Resolver precision, termina asumiendo muchas como riesgos. 
