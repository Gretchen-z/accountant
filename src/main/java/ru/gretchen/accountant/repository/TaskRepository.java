package ru.gretchen.accountant.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.gretchen.accountant.hiber.HibernateSessionFactoryUtil;
import ru.gretchen.accountant.model.Task;

import java.time.LocalDate;
import java.util.List;

public class TaskRepository {

    public Task getById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Task.class, id);
    }

    public Task save(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(task);
        transaction.commit();
        session.close();
        return task;
    }

    public List<Task> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Task> tasks = session.createQuery("FROM Task").list();
        session.close();
        return tasks;
    }

    public List<Task> getByDate() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Task where date = :date");
        query.setParameter("date", LocalDate.now());
        return query.list();
    }
}
