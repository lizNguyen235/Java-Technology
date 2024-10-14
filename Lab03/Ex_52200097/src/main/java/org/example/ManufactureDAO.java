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
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Manufacture get(int k) {
        try {
            Manufacture manufacture = session.get(Manufacture.class, k);
            return manufacture;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Manufacture> getAll() {
        try {
            List<Manufacture> manufactures = session.createQuery("FROM Manufacture", Manufacture.class).getResultList();
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
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(int t) {
        try {
            session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, t);
            session.remove(manufacture);
            session.getTransaction().commit();
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

    public Manufacture getManufactureLast() throws InvalidOperationException {
        String hql = "FROM Manufacture m WHERE m.location = :location ORDER BY m.id DESC";
        Query query = session.createQuery(hql, Manufacture.class);
        query.setParameter("location", "US");
        query.setMaxResults(1);
        if(query.getResultList().isEmpty()) {
            throw new InvalidOperationException("No result");
        }
        return (Manufacture) query.getSingleResult();
    }
}


