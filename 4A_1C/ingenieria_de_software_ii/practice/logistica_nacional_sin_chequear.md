# Logisitica nacional 

## Requisitos 

### Funcionales 

- Provea informacion posicional de moviles en tiempo real
- Vista del mapa de la Republica Argentina y paises limitrofes
- Facil filtrado por conductor, carga, valor transportado, tiempo a destino y condiciones del 
contrato
- Cada movil se vera como un punto en el mapa, que cambia de color segun su estado
- El operario debe poder imprimir una hoja de ruta para el chofer al momento de autorizar la 
salida del cargamento
- Cada operario asentara en el sistema el pasaje del movil 
- Las alarmas y mas novedades son informadas al operador adecuado 
- Alarmas por movil, que sean configurables para casos diversos
- El gerente de operaciones debe poder obtener un reporte diario con las novedades de importancia 
del dia anterior. 
- Una vez al mes exportar al sistema de nomina los tiempos de movimiento para pago de horas extra
- Obtencion de ubicacion de carga mediante servicio JSON

### No funcionales

- El reporte se debe poder abrir en excel
- Operar con sistema de nomina 
- Operar con sistema interno de ventas
- Operar con sistema de aduana argentina
- COntrolar moviles por equipo de operadores en simultaneo 
- COnfigurar alarmas de movil 

## Atributos de calidad 

- Interaperability: Interactuar con sistema de aduana y de GPS 
- Security: La posicion de los cargamentos es informacion sensible 
- Usability: Debe poder ser usado por operarios, administradores
- Availability: Sin el sistema, se frenan todas las operaciones 
- Fault tolerance: Sin el sistema de aduana deberia seguir funcionando el transporte interior 
- Customizability: El sistema debe poder configurar senales 
- Performance: Debe ser en real time 
- Portability: Debe ser usado en oficina, en aduana y en la ruta 
- Scalability: Siendo transporte de bienes, en epocas como navidad o simil se esperaria un aumento 
en transporte de bienes.


## Top 4(en orden) 

1. Interoperability  
2. Availability
3. Presicion 
4. Auditability 

## Arquitectura 

### Interoperability

- Routers con 5G, pues si se cae la conexion no funciona y no se investigo a fondo 
- Generadores no pues no le podriamos pedir a la aduana que ponga uno 

### Availability 

- Multiples bases de datos, muchas lecturas, SQL con muchas secundarias 
- Multiples servidores con LB para manejo de carga, API stateless.
- ...

### Precision 

### Auditability

## Riesgos 

- 
- 

## No riesgos 

- 
- 

## Supuestos 

- 
- 

## Tradeoffs

- 
- 

