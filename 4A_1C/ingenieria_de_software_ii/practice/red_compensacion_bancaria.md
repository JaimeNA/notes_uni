# Red de compensacion bancaria 

## Requisitos 

### Funcionales 

- Permitir transferencias entre bancos 
- Verificar que el banco prestador tenga las garantias
- Si la verificacion es negativa, la trasferencia es rechazada y se 
  notifica al banco que tiene saldos insuficientes
- Operacion sincronica Online 
- Permitir que los bancos tengan saldo negativo 
- Al finalizar el dia el sistema verificara lo saldos finales y acreditara 
  en caso de saldo positivo o solicitara al banco un deposito para finalizar con 
  los saldos en 0
- Proveer interfaz grafica para gestionar datos, manitorear su saldo y generar 
  nuevas garantias

### No funcionales 

- Soportar una interfaz asincronica 
- Operable en horario bancario(10 a 15hs)
- La operacion de transaccion debe tardar menos de 5 seg
- Utiliza un metodo de criptografia asimetrica  de claves publicasy privadas 
- La UI debe ser una web 2.0.
- La operacion de verificar saldos finales debe coenzar entre las 18h y 19hs y 
  terminar antes de las 12hs del mismo dia
- Solo soporta transacciones entre bancos de la misma red, debe evitar que bancos 
  fuera de la red ejecuten transferencias 
- Debe poder ser implementado en diferentes paises de la region, debe adaptarse a 
  culturas, leyes y reglamentos.

## Atributos  

- Security 
- Availability 
- Customizability 
- Performance 

Top: 

1. Security 
2. Availability  
3. Performance 
4. Customizability

## Arbol de utilidad

```
Utility 
    |- Security  
    |   |- Access control 
    |   |- Data confidentiality
    |
    |- Performance
    |   |- Data latency
    |   |- Transaction thoughput
    |
    |- Availability
    |   |- Multiple instances 
    |   |- Alternative sources 
    |
    |- Customizacbility
        |- Adapt to different cultures 
        |- Adapt to different laws
     

```

## Como se solucionan los atributos 

### Security 

- Encriptacion punta a punta 
- MFA para los usuarios 
- ACL, control de acceso 

### Performance 

- Base de datos OLTP con replicas secundarias
- Multiples instancias con un load balancer(webapp) 

### Availability

- Base de datos OLTP con replicas secundarias 
- Multiples instancias con load balancer 
- Multiples proveedores 

### Customizability

- Internacionalizacion
- Probar con distintos usuarios 

## Riesgos 

- Se cae el load balancer 
- Se caen todas las instancias 

## No riesgos 

- Gran cantidad de transacciones 
- Usuarios con distintos idiomas 
- Usuarios con distinta cultura 

## Supuestos 

- Los bancos tienen conexion a internet
- Se haran mas lecturas que escrituras 

## Tradeoffs tomados

- Security por usabilidad
- Performance por costo 

2/6 


