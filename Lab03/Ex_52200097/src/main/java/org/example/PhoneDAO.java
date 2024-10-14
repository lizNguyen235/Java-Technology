package org.example;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PhoneDAO implements Repository<Phone> {
    private static final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public static Session getSession() {
        return session;
    }

    private static final Session session = sessionFactory.openSession();
    @Override
    public boolean add(Phone phone) {
        try {
            session.beginTransaction();
            session.persist(phone);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Phone get(int k) {
        try {
            Phone phone = session.get(Phone.class, k);
            return phone;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Phone> getAll() {
        try {
            Query query = session.createQuery("FROM Phone", Phone.class);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public boolean update(Phone phone) {
        try {
            session.beginTransaction();
            session.merge(phone);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(Phone k) {
        try {
            session.beginTransaction();
            session.remove(k);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(int t) {
        try {
            session.beginTransaction();
            Phone phone = session.get(Phone.class, t);
            session.remove(phone);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public int getHighestPrice() {
        Query query = session.createQuery("SELECT MAX(price) FROM Phone",  Integer.class);
        return (int) query.getSingleResult();
    }
    public List getPhonesWithPrice() {
        Query query = session.createQuery("FROM Phone p ORDER BY p.country, p.price DESC", Phone.class);
        return query.getResultList();
    }
    public boolean checkAboutPhonePrice() {
        Query query = session.createQuery("FROM Phone p WHERE p.price > :price", Phone.class);
        query.setParameter("price", 50000000);
        return !query.getResultList().isEmpty();
    }
    public Phone getPhoneCriteria() {
       Query query = session.createQuery("FROM Phone p WHERE p.price > :price and p.color = :color ", Phone.class);
       query.setParameter("price", 15000000);
       query.setParameter("color", "Pink");
       query.setMaxResults(1);
        try {
            return (Phone) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
