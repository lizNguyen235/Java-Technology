package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        // Load the configuration and build the SessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        // Open a new session
        Session session = sessionFactory.openSession();

        // Close the session
        session.close();

        // Shut down the SessionFactory
        HibernateUtils.shutdown();
    }
}