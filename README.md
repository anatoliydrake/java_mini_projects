# Java Project Collection
This repository contains a collection of Java projects developed for practical implementation of key software engineering concepts such as web development, concurrency, ORM, and testing.

---

## 📁 Projects Overview

---
### 1. FibonacciWebService
   A Spring Boot application that calculates and returns the n-th Fibonacci number.

**Features:**
- REST API to fetch Fibonacci numbers.
- Caches results in PostgreSQL.
- Full test suite using JUnit 5, Mockito, Testcontainers, and MockMvc.

**Technologies:** Java 17+, Spring Boot, Spring Data JPA, PostgreSQL, Docker, Maven

---
### 2. NewsFeedManager
A Spring Boot RESTful web app to manage news articles and categories.

**Features:**
- Full CRUD API for news and categories.
- PostgreSQL for persistence.
- Liquibase for DB versioning.
- OpenAPI (Swagger) documentation.

**Technologies:** Java 20+, Spring Boot, PostgreSQL, Spring Data JPA, Liquibase, Docker, Maven

---
### 3. WebsiteLinkMapWriter (Website Crawler)
   A ForkJoinPool-based crawler that recursively scans a website and outputs a sitemap file with indentation representing URL hierarchy.

**Features:**
- Domain-restricted, parallel crawling.
- Delay between requests for polite scraping.
- Outputs indented .txt sitemap.

**Technologies:** Java 20+, JSoup, ForkJoinPool, Maven

---
### 4. SQLHibernatePurchaseList
   A Hibernate-based project working with MySQL to link purchases, students, and courses.

**Features:**
- Reads purchase data and populates a link table using entity relationships.
- Composite key mapping with @EmbeddedId.

**Technologies:** Java 20+, Hibernate ORM, MySQL, JPA, JDBC, Maven

---
### 5. DataCollector
A Java console application that collects and merges data from:
- HTML pages (Moscow Metro),
- JSON and CSV files in nested folders.

**Output:**
- metro.json: structured metro lines and stations.
- stations.json: enriched station info (opening date, depth, etc.).

**Technologies:** Java 20, Jsoup, Jackson, Maven

---

## 🛠 How to Use

Each project is self-contained and can be built and run independently.

### General Steps:

1. Clone the repository.
2. Navigate to the specific project folder.
3. Follow its README or pom.xml to build and run.