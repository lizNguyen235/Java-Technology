package dao;

import Repository.Repository;
import Utils.HibernateUtils;
import jakarta.persistence.NoResultException;
import model.User;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.NoSuchElementException;

public class UserDAO implements Repository<User> {
    private final Session session;

    public UserDAO() {
        session = HibernateUtils.getSessionFactory().openSession();
    }

    @Override
    public void save(User user) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public User findById(String username) {
         try {
             String hql = "FROM User WHERE username = :username";
             Query query = session.createQuery(hql, User.class);
             query.setParameter("username", username);
             return (User) query.getSingleResult();
         }catch (NoSuchElementException | NoResultException e){
             return null;
         }

    }
}
