# Bibliotecas nacionales 

## Requerimientos 

### Funcionales 

- Sistema de catalogo 
- Busqueda de libros 
- Sistemas remotos y on-site 
- Reserva de libros 
- Interfaz de carga de datos
- Extraer reportes de libros consultados

### No funcionales 

- No se puede caer o la biblioteca cierra todo el dia
- Minimo denominador tecnologico posible
- Mayor espectro de clientes y usuarios 
- Buscar libros rapidamente(pocos segundos)
- Si el libro no se encuentra en la biblioteca, debe sugerir otra donde si este 
- Reservas solo se pueden hacer en persona y realizadas por un bibliotecario 
- Conexion con otras fuentes de datos(internacionales)
- No se conocen las API de cada sistema 
- Las API son en tiempo real 
- En corto plazo se hara una API propia para que las bibliotecas extrangeras puedan conectar 
- Los reportes deben poder cruzar datos de todas las bibliotecas alcanzadas 

## Atributos de calidad 

- Fault tolerance(Si se cae, cierra todo el dia)
- Portability(Mayor espectro de sistema de usuarios posible) 
- Performance(Buscar libros rapidamente)
- Usability(usado por bibliotecarios y clientes) 
- Interoperability(Conexion con otras fuentes de datos) 
- Maintainability(No se conocen las API y en corto plazo se hara una propia)
- Scalability(Se ofreceran servicios internacionales en corto plazo) 
- Availability(Punto obligado de entrada)

## Atributos de calidad mas importantes y su cuantificacion

Saco: 

- Usability

Me quedo con: 

- Fault tolerance 
- Performance 
- Portability 
- Interoperability

> No puedo cuantificar 

## Sistemas externos 2

- Otras fuentes de informacion con APIs propias 

## Arquitectura inicial 

(En papel) 

## Analisis de riesgos 

### Fault tolerance 

- Escenario 1: Falla una API externa -> Se asume el riesgo 
- Escenario 2: 

### Performance 

Para mejorar performance se puede tener mas de un servidor atendiendo clientes, pero genera el 
problema de tener un load balancer. El otro problema de load balancer es que habria que mantener 
las conexiones o hacer que sea stateless. Se hace stateless pues son pedidos unicos de informacion 
generalmente. 

Luego, pueden fallar los load balancers, pero se asume el riesgo pues tardan muy poco en 
reiniciarce. 

Que pasa si la conexion es muy lenta? Podria cambiar de proveedor, usar mas de uno al mismo tiempo 
o implementar edge computing. Poniendo servidores cache para consultas. 

### Portability

Que pasa si el usuario tiene una computadora muy vieja, entonces se debe implementar en 
un lenguage como C, C++ o Java para abarcar los dispositivos mas antiguos. Trae como 
problema que va a estar muy limitado, se asume ese riesgo. 

### Interoperability 

Debe poder funcionar con muchos servicios externos, pero aparte de una buena conexion a internet 
se debe asumir el riesgo.
