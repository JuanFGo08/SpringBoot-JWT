# Spring Boot JWT Authentication

This is a secure REST API built using **Spring Boot**, **Spring Security**, and **JWT (JSON Web Tokens)**. It supports authentication and role-based authorization with protected endpoints.

## 🔐 Features

- User registration and login with encrypted passwords (BCrypt)
- JWT-based authentication
- Role-based access control (e.g., `USER`, `ADMIN`)
- Custom exception handling
- JPA integration with a relational database (MySQL/PostgreSQL)

## 🛠 Technologies Used

- Spring Boot
- Spring Security
- JWT (jjwt)
- Spring Data JPA
- Hibernate
- Lombok
- Maven
- MySQL/PostgreSQL

## 📦 Setup Instructions

1. **Clone the repository**

    ```bash
    git https://github.com/JuanFGo08/SpringBoot-JWT.git
    cd SpringBoot-JWT
    ```

2. **Configure the database**

   - Open `src/main/resources/application.properties` or `application.yml`.
   - Update the database connection properties (URL, username, password).
   - Make sure to create the database before running the application.

3. **Build the project**

    ```bash
    mvn clean install
    ```

4. **Run the application**

    ```bash
    mvn spring-boot:run
    ```

5. **Test the API**

   - Use tools like Postman or curl to test the endpoints.
   - Register a new user and log in to get a JWT token.
   - Use the token to access protected resources.

## 📜 API Endpoints

| Method | Endpoint                     | Description                         |
|--------|------------------------------|-------------------------------------|
| POST   | `/api/auth/register`         | Register a new user                 |
| POST   | `/api/auth/login`            | Authenticate user and get JWT token |
| POST   | `/api/role/create`           | Create a new role                   |
| GET    | `/api/role/list`             | List all roles                      |
| GET    | `/api/auth/check-token`      | Check if the token is valid         |


## Future Improvements

- Add refresh token functionality
- Implement password reset feature
- Add Swagger documentation
- Implement unit and integration tests
- Dockerize the application
