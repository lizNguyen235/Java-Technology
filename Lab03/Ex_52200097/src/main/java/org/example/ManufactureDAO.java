package org.example;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.management.openmbean.InvalidOpenTypeException;
import javax.naming.OperationNotSupportedException;
import java.util.List;

public class ManufactureDAO implements Repository<Manufacture> {
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    Session session = sessionFactory.openSession();
    @Override
    public boolean add(Manufacture manufacture) {
        try {
            session.beginTransaction();
            session.persist(manufacture);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Manufacture get(int k) {
        try {
            session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, k);
            session.getTransaction().commit();
            session.close();

            return manufacture;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Manufacture> getAll() {
        try {
            session.beginTransaction();
            List<Manufacture> manufactures = session.createQuery("FROM Manufacture", Manufacture.class).getResultList();
            session.getTransaction().commit();
            session.close();
            return manufactures;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(Manufacture manufacture) {
        try {
            session.beginTransaction();
            session.merge(manufacture);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(Manufacture k) {
        try {
            session.beginTransaction();
            session.remove(k);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(int t) {
        try {
            Manufacture manufacture = session.get(Manufacture.class, t);
            session.remove(manufacture);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkEmployeeCount() {
        List<Manufacture> manufactures = session.createQuery("FROM Manufacture m where m.employeeCount > :count", Manufacture.class)
                .setParameter("count", 100)
                .getResultList();
        return !manufactures.isEmpty();
    }

    public Long checkSumEmployeeCount() {
        return session.createQuery("SELECT SUM(m.employeeCount) FROM Manufacture m",  Long.class).getSingleResult();
    }

    public Manufacture getManufactureLast() {
        String hql = "FROM Manufacturer m WHERE m.country = :country ORDER BY m.id DESC";
        Query query = session.createQuery(hql, Manufacture.class);
        query.setParameter("country", "US");
        query.setMaxResults(1);
        if(query.getResultList().isEmpty()) {
            throw new IllegalArgumentException("No result");
        }
        return (Manufacture) query.getSingleResult();
    }
}
