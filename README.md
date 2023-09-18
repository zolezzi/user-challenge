# user-challenge

========================
### Ejecutar con docker

 * Debe tener [docker](https://www.docker.com/) instalado
 * Ejecutar ```gradle clean build docker```
 * Ejecutar ```cd docker```
 * Ejecutar ```docker-compose up```
 * Ir a http://localhost:8080/swagger-ui/index.html para ver la documentación de api con swagger
 * Ejecutar ```gradle test jacocoTestReport``` ir a /build/jacoco/index.html

# Diagrama
![Diagrama de servicio](/documentation/userchallege-diagram.drawio.png "Diagrama del proyecto userchallenge")

# Backend
El Backend se utiliza el modelo de Arquitectura SOA orientado a servicios, debido a la prolijidad, constancia y desacoplamiento que nos facilita y garantiza. Las tecnologias aplicadas son:

* Java: como lenguaje principal.
* Spring Boot: como framework principal.
* H2: base de datos en memoria
* JUnit: como herramienta de testing unitario.
* Swagger: Documentación de API.
* JWT: como herramienta de autenticacion.
