# Compraventa de acciones 

## Requisitos

### Funcionales 

- Compra venta de acciones 
- Revisar los precios de las acciones en la bolsa 
- Debe poder utilizarse desde internet en todo el mundo 
- Conexion con la bolsa de comercio utilizando dos API
- Una compra consiste en el usuario que elige un activo en el mercado, 
  una cantidad, y un precio.
- El sistema debe validar la existencia de saldo, realizar la operatoria, e 
  informar del resultado

### No funcionales

## ADD 

- Availability - 99.99%, es una app global 
- Performance - Se deben ver las cotizaciones y cantidad de acciones real-time
- Security - Hay manejo de dinero 
- Accesibility - Traducciones 
- Scalability - Fluctuaciones de mercado
- Auditability - Entes regulatorios pueden pedir los datos para control
- Precision - % de acciones 

## Ranking 

1. Availability
2. Security 
3. Performance
4. Scalability

## Arquitectura 

- Ponemos DB SQL OLTP porque solo almacena usuarioes y operaciones, el resto de la 
  informacion viene de las API. 
- Ponemos varias instancias de la DB y de los servidores por disponibilidad.
- HTTPS para evitar un MITM 
- ACL para tener control de acceso 
- 2FA para los usuarios y garantizar su identidad
- WAF, mas availability pues evita ataques DDoS
- Por seguridad, se crea una aplicacion distinta que solo es para los 
  empleados y cargo de saldos. Evita exponer esos endpoints y permite que los 
  empleados usen un VPN, sino no sirve porque quedan formas de acceso sin VPN.
- Por security, se separan los endpoints admin de los endpoint users, entonces 
  crean dos aplicativos, uno para users que se conecta mediante internet, y otro para 
  admins que se conectan mediante VPN. Si estan ambos en un solo aplicativo no sirve 
  que tenga uno VPN y otro no. 
- Por performance podemos poner una cache, pero no para datos sensibles, solo para 
  preferencias de busqueda perfiles, etc. 
