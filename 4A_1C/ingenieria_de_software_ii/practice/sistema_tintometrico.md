# Sistema tintometrico 

## Requisitos 

### Funcionales 

- Administra las formulas de colores para una fabrica de pinturas 
- Los laboratorios de cada tipo de producto(Hogar y Obra, Industrial y automotor) 
  deben poder registrar las formular en el sistema
- Poder importar los productos que se utilizan para las formulas, con sus respectivos precios
- Los usuarios deben poder ingresar al sistema el producto y el color, y el sistema debera 
  devolver la formula que indica que productos debe utilizar y las cantidades
- Debe calcular el precio del producto. Contempla el costo del producto base y los productos 
  utilizados para generar el color
- Ingresar el porcentaje de ganancia por parte de las pinturerias 
- Proveer el costo de los productos
- Permitir a las tinturerias generar y guardar sus propias formulas de color, sin que afecten a la 
  lista de formulas que genera la fabrica y sin que sean vistas por otras pinturerias.

### No funcionales 

- Es necesario un esquema de seguridad para poder dar permisos de acceso por rol
- Devolver las cantidades necesarias, es necesario obtener la formula correcta
- Las formulas propias de cada pintureria no deben ser vistas por otras pinturerias 

## Atributos 

- Security
- Availability 
- Precision 
- Usability

### Top 

1. Segurity, es necesario un esquema de seguridad para poder dar permisos de acceso por rol 
2. Availability, debe estar disponible o no se podra producir 
3. Presicion, las formulas deben ser correctas 
4. Usability, debe ser usado en entornos industriales donde no se deben cometer errores

### Security 

- Firewall para evitar ataques DDoS 
- HTTPS para evitar ataques MITM 
- 2FA para garantizar la identidad del usuario 
- ACL, control de acceso para limitar acceso a formulas 

### Availability 

- Multiples instancias con load balancer 
- Base de datos local SQLite 
- Replicacion solo lectura de la base de datos 

### Precision 

- Uso de punto fijo en cambio de punto flotante 
- Utilizacion de metodos numericos para evitar la propagacion de errores 

### Usability 

- Diseno HCI 
- Testeo con usuarios para detectar errores 

## Arbol de utilidad 

### Security 

- Access control 
- Diferentes roles de usuario 

### Availability

- Multiple instances 
- Local database 

### Presicion 

- Valor fijo 
- ...

### Usability 

- Diseno HCI 
- Testeo con usuarios previo al lanzamiento

## Arquitectura 

(en papel)

## Riesgos 

- Caida del load balancer 
- Usuario nunca recupera conexion a internet y no recibe las formulas 
- Usuarios no entiendan como usar la aplicacion y cometan un error 

## No riesgos

- Caida del backend 
- Corte de conexion a internet 
- Ataques MITM y DDoS 

## Supuestos 

- Se requiere se una disponibilidad de 99% 
- Se haran mas lecturas que escrituras 
- Cualquier error cometido producira consecuencias graves 

## Tradeoffs

- Usabilidad por security debido a 2FA y autenticacion 
- Availability por costos debido a las multiples intancias 
- Presicion por performance al operar con punto fijo 

