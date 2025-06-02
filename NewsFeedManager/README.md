# NewsFeedManager

Spring Boot web application for managing news articles and their categories.

---

## Technologies Used

- Java 20+
- Spring Boot
- Spring Data JPA
- Liquibase
- PostgreSQL
- Swagger / OpenAPI
- Docker (for running the DB locally)
- Maven

---

## Features

- Manage news and categories via REST API
- Data stored in PostgreSQL
- Liquibase for managing database schema versions
- RESTful API documented with Swagger (OpenAPI)

---

## API Endpoints

### News
- GET /api/news – Get all news
- GET /api/news/{id} – Get news by ID
- GET /api/news/category/{id} – Get all news by category ID
- POST /api/news – Create a new news item
- PUT /api/news/{id} – Update an existing news item
- DELETE /api/news/{id} – Delete a news item

### Categories
- GET /api/categories – Get all categories
- GET /api/categories/{id} – Get category by ID
- POST /api/categories – Create a new category
- PUT /api/categories/{id} – Update a category
- DELETE /api/categories/{id} – Delete a category
---

## Getting Started

### Run PostgreSQL in Docker

```bash
docker run --name news-db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=newsdb \
  -p 5432:5432 \
  -d postgres:14
```

### API Documentation
Swagger UI is available at:
```
http://localhost:8080/swagger-ui/index.html
```

### Example `application.yml`:

```yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/yourdatabase
    username: user
    password: password
    driver-class-name: org.postgresql.Driver
  jpa:
    show-sql: true 
    hibernate.ddl-auto: create
  liquibase:
    change-log: classpath:db/changelog/db.changelog-master.xml
    enabled: true
    drop-first: false
```

### Example `db.changelog-master.xml`:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-latest.xsd">

    <changeSet  id="createNewsTable"  author="anatoliydrake">
        <createTable tableName="news">
            <column name="id" type="serial">
                <constraints primaryKey="true"/>
            </column>
            <column name="title" type="varchar(255)"/>
            <column name="text" type="varchar(255)"/>
            <column name="creation_time" type="timestamp(6)"/>
            <column name="category_id" type="BIGINT">
                <constraints nullable="false"/>
            </column>
        </createTable>
    </changeSet>

    <changeSet  id="createCategoryTable"  author="anatoliydrake">
        <createTable tableName="category">
            <column name="id" type="serial">
                <constraints primaryKey="true"/>
            </column>
            <column name="title" type="varchar(255)"/>
        </createTable>
    </changeSet>
</databaseChangeLog>
```