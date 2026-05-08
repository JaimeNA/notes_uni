# Tips y errores comunes para la primer entrega

- Tener un `messages.properties`, `messages_es.properties` y un 
`messages_en.properties`(vacio!!). Por un tema del browser, conviene hacer esto pues 
el de ingles hereda las propiedades del default.
- **Importante** no incluir dependencias que no son especificas de Spring 5, como
`spring-boot-started-mail` o `spring-boot-starter-thymeleaf`. 
- Toda la logica de envio de mails debe estar en service, no en controller. 
- Si un proceso requiere de chequear algo, tiene que ser una unica llamada a funcion a 
service que hace toda la logica por detras.
- Quien invoca al servicio de email nunca debe tener que conocer el template a usar, 
debe abstraerse.
- No omitir modificaciones de acceso, siempre minimizar toda la visibilidad al minimo.
- Las untility classes no pueden ser instanciables(error muy comun)
- No loggear a nivel DEBUG
- No loguear archivos genericos. Utilizar archivos unicos para el equipo
- Los logs minimo deben ser INFO, WARN o ERROR.

