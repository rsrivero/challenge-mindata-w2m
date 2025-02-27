# challenge-mindata-w2m


### See api documentation

Swagger: http://localhost:8080/swagger-ui.html

### Start LocalStack

`docker-compose up`

### 📌 SWAGGER
🔗 **URL:** [Swagger UI](http://localhost:8080/swagger-ui.html)

### 📌 Ejemplo de Consulta
Puedes probar la API con la siguiente solicitud:
```http
curl -X 'GET' \
  'http://localhost:8080/v1/spaceship/searchByName?name=fal' \
  -H 'accept: */*'
  ```

### 📌 Authentication
The API uses basic authentication. Use the following credentials to authenticate:

Username: user

Password: user123

### 📌 Authentication
Para correr los test ejecutar: (tener localstack levantado)
```http
mvn test
  ```
