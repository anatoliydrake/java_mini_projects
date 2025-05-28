package com.anatoliydrake.sqlhibernatepurchaselist;

import com.anatoliydrake.sqlhibernatepurchaselist.entities.LinkedPurchaseList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Connection connection = session.doReturningWork(conn -> conn);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         "SELECT pl.student_name, pl.course_name, s.id, c.id course_id " +
                                 "FROM purchaselist pl " +
                                 "JOIN students s on pl.student_name = s.name " +
                                 "JOIN courses c on c.name = pl.course_name;")) {
                while (resultSet.next()) {
                    LinkedPurchaseList purchase = new LinkedPurchaseList();
                    SubscriptionKey key = new SubscriptionKey();
                    key.setStudentId(resultSet.getInt("id"));
                    key.setCourseId(resultSet.getInt("course_id"));
                    purchase.setId(key);
                    session.persist(purchase);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
