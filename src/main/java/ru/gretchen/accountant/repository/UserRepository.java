package ru.gretchen.accountant.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.gretchen.accountant.hiber.HibernateSessionFactoryUtil;
import ru.gretchen.accountant.model.User;

import java.util.List;

public class UserRepository {

    public User getById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public User getByUsername(String username) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM User where username = :username");
        query.setParameter("username", username);
        List<User> users = query.list();
        return users.get(0);
    }

    public User save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return user;
    }
}
