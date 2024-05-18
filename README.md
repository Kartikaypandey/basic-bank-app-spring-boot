# Banking Application

This is a simple banking application built with Spring Boot, Spring Data JPA, and PostgreSQL. It provides functionalities for creating accounts, viewing account details, depositing and withdrawing funds, and transferring funds between accounts.

## Features

- Create a new account
- Retrieve details of all accounts
- Retrieve details of a single account
- Deposit funds into an account
- Withdraw funds from an account
- Transfer funds between accounts

## Technologies Used

- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker (for PostgreSQL container)

## Getting Started

### Prerequisites

- Java 11 or higher
- Docker

### Running the Application

1. **Clone the repository:**

    ```bash
    git clone https://github.com/Kartikaypandey/basic-bank-app-spring-boot.git
    cd banking-app
    ```

2. **Start PostgreSQL using Docker:**

   Ensure Docker is installed and running on your machine. Create a `docker-compose.yml` file with the following content:

    ```yaml
    version: '3.1'

    services:
      db:
        image: postgres
        ports:
          - "5432:5432"
        restart: always
        environment:
          POSTGRES_PASSWORD: changemeinprod!
    ```

   Then run:

    ```bash
    docker-compose up
    ```

3. **Configure application properties:**

   Update your `application.properties` or `application.yml` file to connect to the PostgreSQL database:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
    spring.datasource.username=postgres
    spring.datasource.password=changemeinprod!
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    ```

4. **Build and run the application:**

   Use Maven to build and run the application:

    ```bash
    ./mvnw spring-boot:run
    ```

## API Endpoints

### Account Endpoints

- **Create Account:**

    ```http
    POST /api/v1/account
    ```

  Request Body:

    ```json
    {
      "userName": "John Doe",
      "balance": 1000.0
    }
    ```

- **Get All Accounts:**

    ```http
    GET /api/v1/account
    ```

- **Get Single Account:**

    ```http
    GET /api/v1/account/{id}
    ```

- **Deposit Amount:**

    ```http
    PUT /api/v1/account/{id}/deposit
    ```

  Request Body:

    ```json
    {
      "amount": 500.0
    }
    ```

- **Withdraw Amount:**

    ```http
    PUT /api/v1/account/{id}/withdraw
    ```

  Request Body:

    ```json
    {
      "amount": 200.0
    }
    ```

- **Delete Account:**

    ```http
    DELETE /api/v1/account/{id}
    ```

- **Transfer Funds:**

    ```http
    POST /api/v1/account/transfer
    ```

  Request Body:

    ```json
    {
      "fromAccountId": 1,
      "toAccountId": 2,
      "amount": 100.0
    }
    ```

## Exception Handling

Custom exceptions are used to handle errors. For example, if an account is not found, an `AccountException` is thrown with a relevant message.

```java
public class AccountException extends RuntimeException {
    public AccountException(String message) {
        super(message);
    }
}
```