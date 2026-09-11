# Gestor de documentos 

## Requerimientos

### Funcionales 

- Los documentos deben poder clasificarse o agruparse de manera que ciertos usuarios 
  o roles puedan o no, tener permisos sobre ellos. Cada usuario tiene permitido un 
  conjunto de operaciones sobre los distintos documentos
- Debe permitirse la asignacion de roles a usuarios. Un rol permite definir un conjunto 
  de operaciones sobre todos o algunos de los datos.
- Deberan existir al menos un rol de administracion general, que permite administrar 
  documentos, roles, usuarios y grupos de documentos dentro de toda la red. 
- Algunos usuarios o roles pueden tener el permiso especial para deshacer cambios sobre 
  uno o mas documentos. El administrador general siempre puede hacerlo.
- Buscar documentos en base a atributos tales como: creador del documento, 
  fecha de creacion, fecha de ultima modificacion, grupo y en ciertos casos, por 
  contenido. 
- Si el usuario esta desconectado, puede sincronizar los cambios en cualquier momento 
  que se encuentre conectado a la red. 
- El sistema debe permitir al usuario definir una cantidad predeterminada de espacio 
  de almacenamiento en su estacion de trabajo. 
- La historia de operaciones realizadas sobre un documento siempre debe estar accesible, 
  junto con la fecha y usuario que la realizo.
- Identificar cuando un dato haya sido adulterado 

### No funcionales 

- Las estaciones de trabajo pueden ser equipos de PC de escritorio o tambien 
  dispositivos moviles con conexiones de peor calidad.
- Todos deben ver los mismos datos, dentro de un cierto periodo umbral.
- El sistema debe permitir que un usuario trabaje con documentos gestinados por la 
  aplicacion sin verse afectados por intermitencias en la conexion a la red. 
- Redes P2P
- Desempeno razonable en redes cerradas y abieras 
- Otras aplicaciones debe poder utilizarla

## Atributos de calidad 

- Availability 
- Security 
- Portability
- Auditability 

## Arquitectura candidata

PWA con API stateless, la API usa varios servidores con un load balancer, se utiliza una 
base de datos redis en cluster, NoSQL para el manerjo de documentos. Cada PWA maneja 
su DBinterna para poder trabajar off-line. 

Hay grupos electrogenos en las instalaciones y doble proveedor de internet. El 
trafico es encriptado con HTTPS y se utilizan firewalls para proteger de posibles 
ataques. 

### Auditability

- Redis en cluster con un elasticSearch para cache 
- Registro de todos las operaciones

### Security 
 
- HTTPS 
- Firewall

### Portability 

- PWA 
- API 
- La mayoria de las operaciones las maneja el servidor

### Availability

- Grupos de electrogeno 
- Multiples servidores 
- DB redis

## Conclusion 

### Riesgos 

- Que el usuario no vuelva a tener conexion y no se actualice 
- Que alguien externo uso ingenieria social para obtener una cuenta de admin 
- Robo de credenciales 

### No Riesgos

- Ataques MITM 
- Ataques DDoS 
- Perdida de energia 

### Supuestos 

- Los usuarios tienen dispositivos capaces de correr la PWA 
- Los usuarios tienen conexion a internet 
- Las futuras aplicacion podran usar la API 

### Tradeoffs

- Performance por seguridad en encriptacion 
- Availability por costos 
- Seguridad sobre usabilidad, cada usuario debera tener una cuenta e iniciar sesion 

