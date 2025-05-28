# Hibernate PurchaseList Project

This is a simple Java project using Hibernate ORM to work with a MySQL database containing information about students, courses, and their purchases.

## 🧩 Technologies Used

- Java 20+
- Hibernate ORM
- MySQL
- Maven
- JPA annotations
- JDBC (for raw SQL queries)

## ⚙️ How It Works

1. The `PurchaseList` entity maps to a table with a composite key (`PurchaseListKey`).
2. The program reads records from the `purchaselist` table.
3. It joins student and course names to fetch their corresponding IDs from `students` and `courses` tables.
4. It then populates the `linkedpurchaselist` table using these IDs via Hibernate.

## ▶️ How to Run

1. Create the required database schema in MySQL.
2. Configure the `hibernate.cfg.xml` file with your database connection info.
3. Populate the tables manually **or** import a SQL dump with test data.
4. Run the `Main.java` class.

### Example MySQL configuration:

```xml
<hibernate-configuration>
  <session-factory>
    <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/hibernatetask?useSSL=false</property>
    <property name="hibernate.connection.username">your_user</property>
    <property name="hibernate.connection.password">your_password</property>
    <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
    <property name="show_sql">true</property>
    <property name="hibernate.hbm2ddl.auto">validate</property>
    
    <mapping class="com.anatoliydrake.sqlhibernatepurchaselist.entities.Course"></mapping>
    <mapping class="com.anatoliydrake.sqlhibernatepurchaselist.entities.Teacher"></mapping>
    <mapping class="com.anatoliydrake.sqlhibernatepurchaselist.entities.Student"></mapping>
    <mapping class="com.anatoliydrake.sqlhibernatepurchaselist.entities.Subscription"></mapping>
    <mapping class="com.anatoliydrake.sqlhibernatepurchaselist.entities.PurchaseList"></mapping>
    <mapping class="com.anatoliydrake.sqlhibernatepurchaselist.entities.LinkedPurchaseList"></mapping>
  </session-factory>
</hibernate-configuration>
