# CMS - Sin revisar

## Requisitos 

### Funcionales 

- Permite entrar para buscar informacion
- Varios tipos de usuarios: redactores, editorres, administradores y usuarios finales 
- La informacion debe ser relevante 
- La informacion debe ser buscada por cualquier palabra del documento, no solo el titulo 
- Leer, imprimir y marcar informacion como favorita 
- Comentarios en los documentos 
- Dar rating a los documentos 
- Disponible en dispositivos variados, kiosk, por ejemplo.
- Publicacion de contenido  
- Herramientas de correccion
- Herramientas de guardado de apuntes 
- Los archivos escritos pasan a un estado borrador y pueden ser aprobados para su 
  publicacion 
- El editor puede ver los articulos, aprobarlos o pedir cambios 
- Todo cambio debe registrarse 
- Moderacion 
- Reportes a distintos usuario de acuerdo a sus necesidades

### No Funcionales 

- La informacion debe ser precisa 
- La informacion debe ser encontrada inmediatamente 
- Presupuesto razonable 
- Los usuarios tendran resistencia al cambio 

## Atributos de calidad 

- Auditability 
- Precision 
- Performance 
- Usability 
- Security 
- Availability 

Top 4: 

1. Precision 
2. Availability 
3. Performance
4. Auditability 

## Arquitectura

### Precision 

- Usar Lucene para poder buscar por documento y su contenido 
- 

### Availability 

- Multiples instancias del backend con load balancer
- Multiples instnacias de la DB 

### Performance 

- openSearch para busquedas mas rapidas 
- Multiples instancias de la DB

### Auditability

- Base de datos OLAP para guardar logs
- Logs de todas las operaciones

## Riesgos 

- Que se caiga el load balancer
- Que se corte la luz(aumentaria mucho el costo) 
- Que se corte el internet(aumentaria mucho el costo) 

## No riesgos 

- Que se caiga una instancia del backend 
- Que se caiga una instancia de la DB

## Supuestos 

- Se haran mas lecturas que escrituras 
- Los resultados deben volver en menos de 5s 
- No fluctuara de manera considerable la cantidad de usuarios 

## Tradeoffs

- Availability por costos 
- Presicion por performance al tener que indexar los documentos 

