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

## Sistemas externos 2

- Otras fuentes de informacion con APIs propias 

## Arquitectura inicial 

(En papel) 

## Analisis de riesgos 


