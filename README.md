# ORDERS BACKEND

**1. Description**

Тhis application is built with Spring Boot 3, Java 17 and uses MySQL for database.
There are Integration and Unit tests of some basic functionalities.

**2. Tech stack**
- Spring Boot 3
- Java 17
- MySQL
- Maven
- JUnit, Mockito
- [MapStruct](https://mapstruct.org)
- [Swagger and OpenAPI](https://swagger.io/)

**3. Design**

- no

**4. Features**

- Swagger UI

**5. How to run the project**

- Clone the application

```bash
git clone https://github.com/yoananaydenova/orders-backend.git
```

- Change mysql username and password as per your installation

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation
- Run the app using maven as run the following command in a terminal window (in the project directory):

```bash
./mvnw spring-boot:run
```

The app will start running at <http://localhost:8080>.

**6. Explore Rest APIs**

The app defines following CRUD APIs.

```bash 
POST /item
```

```bash
GET /all-items
 ```

```bash
GET /available-items
```

```bash
GET /item/{id}
```

```bash
PUT /item/{id}
```

```bash
DELETE /item/{id}
```

```bash
POST /order
```

```bash
GET /orders
```

```bash
GET /order/{id}
```

```bash
PUT /order/{id}
```

```bash
DELETE /order/{id}
```

You can test them using postman, Swagger or any other rest client.

Once the application is running, you can access the Swagger UI at <http://localhost:8080/swagger-ui/index.html>.  