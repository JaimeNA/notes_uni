# Gestor de documentos 

## Requerimientos

### Funcionales 

- Permite trabajar de manera colaborativa
- Se puede acceder mediante internet
- Los usuarios pueden buscar o listar recursos disponibles 
- El usuario puede pedir un documento para editar 
- Modificaciones de modo off-line 
- Cuando el usuario quiera, se actualiza o publica el archivo 
- Usuarios con privilegios de admin pueden deshacer cambios
- Comparten archicos de texto, imagenes, xml, etc 
- Solo pueden buscarse archivos publicos 
- Definir cantidad de espacio de almacenamiento 
- Rol administrativo general 
- Asignacion de roles de usuarios 
- La histora de operaciones debe ser accesible 
- Debe ser indentificado si un usuario no identificado modifico algo 

Del doc: 

1. Funcional 
2. No funcional 
3. No funcional 
4. Funcional 
5. Funcional 
6. Funcional 
7. Funcional 
8. No funcional 
9. Funcional 
10. No funcional 
11. Funcional 
12. Funcional 
13. No funcional  
14. No funcional

### No funcionales 

- Redes P2P 
- Cada estacion de trabajo se comporta como consumidor y proveedor de manera simultanea
- Las estaciones de trabajo pueden ser Pc, celulares, laptops, etc. 
- Las actualizaciones deben ser vistas por todos los usuarios poco tiempo de haberse realizada
- El sistema debe funcionar con un desempeño razonable no solo en redes cerradas
  (privadas) sino también en ambientes abiertos como pueden serlo diferentes tipos de
  comunidades basadas en Internet
- El sistema debe desarrollarse de forma tal que en el futuro otras aplicaciones puedan
  utilizarlo como plataforma para almacenar datos.

## Atributos de calidad 

### Todos 

- Availability
- Performance 
- Portability 
- Reusability
- Security 
- Usability 
- Auditability
- Scalability 

### 4 mas importantes 

1. Availability
2. Performance
3. Security
4. Portability

## Arquitectura candidata

### Performance 

- Cluster redis 
- Cache de busqueda elasticSearch 
- Multiples servidores con load balancing

### Security 

- Tokens JWT
- MFA 
- HTTPS 
- Cloudflare firewall 

### Portability 

- Webapp, pero con PWA
- API 

### Availability

- Grupo de electrogenos 
- Proveedor de internet alternativos

## Conclusion 

### Riesgos 

- Desincronizacion en estacion de trabajo online 
- Usuario con dispositivo que no soporte PWA

### No Riesgos

- Aumento significativo en cantidad de usuarios 
- Ataques DDoS, usuario no identificado 
- Corte de luz

### Supuestos 

- Mas lecturas que escrituras, pues muchos documentos publicos no se pueden editar 
- 

### Tradeoffs

- Costo por performance 
- Performance por security 
- Usability por security
