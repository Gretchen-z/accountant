package ru.gretchen.accountant.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.gretchen.accountant.hiber.HibernateSessionFactoryUtil;
import ru.gretchen.accountant.model.User;

public class UserRepository {

    public User getById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
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
