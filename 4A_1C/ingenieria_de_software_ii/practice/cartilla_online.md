# Cartilla online 

## Requisitos

### Funcionales

- Catalogo de prestadores donde el prestador brinda una o mas prestacion 
- Clasificar las prestaciones por catalogo 
- Libre acceso con excepcion de algunas funcionalidades 
- Busqueda por varios criterios
- Geo-localizacion de profesionales e instituciones desde dispositivo movil 
- Posibilidad de dar votos a un prestador 
- Ver mapa, solo para aplicacion web 
- Prestadores favoritos 
- Calendario

### No funcionales 

- Acceso desde la web y desde dispositibos moviles
- Actualizacion constante a partir del sistema de prestadores 
- El sistema se debe ir convirtiendo poco a poco en un centro de comunicacion con los asociados
- Disponibilidad suficiente para Servicio de Guardia 24hs
- Autenticacion para prestadores favoritos, calendarios, etc.


## Atributos de calidad 

- Availability
- Security 
- Mantainability 
- Portability 
- Accesibility 
- Interoperability
- 

### Top 4 

1. Availability
2. Security 
3. Interoperability
4. Mantainability

## Arquitectura 

### Availability

- Multiples proveedores de internet, router con 5G
- Grupos electrogenos
- Multiples instancias con LB por una posible caida del backend
- Replicacion de DB 

### Security

- Encriptacion con HTTPS para prevenir ataques MITM 
- Firewall para prevenir ataque DDoS 
- Multifactor autentication para evitar robo de credenciales

### Interoperability

- Patron adapter
- Uso de ETL 

### Mantainability

- Programacion contra interfaces
- Microservicios con Kubernetes

## Riesgos 

- Performance si los servicios externos son lentos 
- Se cae el load balancer

## No riesgos 

- Corte de luz 
- Corte de internet 
- El usuario solo tiene una computadora y no un celular o viceversa 
- Robo de credenciales de usuario 

## Supuestos 

- Los usuarios tienen acceso a internet 
- Los sistemas externos siempre funcionan
- Los dispositivos de los usuarios soportan una SPA 

## Tradeoffs

- Availability por costo de implementar 
- Security por performance, el usuario debe loggearse antes de acceder a calendario antes 
  de acceder al calendario u otras funciones 
- Microservicios, mantainability por simplicidad 
