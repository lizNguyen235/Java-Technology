package org.example;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PhoneDAO implements Repository<Phone> {
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private Session session = sessionFactory.openSession();
    @Override
    public boolean add(Phone phone) {
        try {
            session.persist(phone);
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
            session.merge(phone);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(Phone k) {
        try {
            session.remove(k);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(int t) {
        try {
            Phone phone = session.get(Phone.class, t);
            session.remove(phone);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getHighestPrice() {
        Query query = session.createQuery("SELECT MAX(Price) FROM Phone",  Long.class);
        return (int) query.getSingleResult();
    }
    public List<Phone> getPhonesWithPrice(int price) {
        Query query = session.createQuery("FROM Phone p ORDER BY p.name, p.price DESC", Phone.class);
        return query.getResultList();
    }
    public boolean checkAboutPhonePrice(String name) {
        Query query = session.createQuery("FROM Phone p WHERE p.price = :price", Phone.class);
        query.setParameter("price", 50000000);
        return query.getResultList().size() > 0;
    }
    public Phone getPhone() {
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
