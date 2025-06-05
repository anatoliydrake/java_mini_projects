# Fibonacci Web Service

A simple Spring Boot web application that returns the nth Fibonacci number on request. The application includes database integration and full test coverage using JUnit 5, Mockito, Testcontainers, and MockMvc.

---
## 📌 Features

- Calculates Fibonacci numbers recursively.
- Caches results in a PostgreSQL database.
- REST API for fetching Fibonacci numbers by index.
- Comprehensive test suite:
    - Unit tests with JUnit 5
    - Mocking with Mockito
    - Data layer testing with Spring Data JPA & Testcontainers
    - API testing with MockMvc
---
## 🔧 Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker + Testcontainers
- JUnit 5
- Mockito
- MockMvc
- Maven
---
## 🚀 How to Run

1. Clone the repository.
2. Start PostgreSQL using the run-postgres.sh.
3. Build and run the application.
---
## ✅ Test Coverage
The project includes four layers of testing:

### 1. Unit Tests for Calculator
- Validate correct Fibonacci calculation.
- Handle invalid input (index < 1). 
- Use @ParameterizedTest with @CsvSource.

### 2. Unit Tests for Service
- Use Mockito to mock repository and calculator. 
- Validate correct use of cache and calculation logic. 
- Ensure proper saving behavior and exception handling.

### 3. Repository Tests with Testcontainers
- Use @DataJpaTest and PostgreSQL container. 
- Test: Saving Fibonacci numbers, Retrieving by index, Avoiding duplicates.

### 4. Controller API Tests
- Use @SpringBootTest and @AutoConfigureMockMvc. 
- Validate: 200 OK and correct JSON on valid input, 400 Bad Request and error message on invalid input.
