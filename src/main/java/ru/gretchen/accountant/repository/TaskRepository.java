package ru.gretchen.accountant.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.gretchen.accountant.hiber.HibernateSessionFactoryUtil;
import ru.gretchen.accountant.model.Report;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.model.User;

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

    public Task addUser(Long taskId, Long userId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Task newTask = session.get(Task.class, taskId);
        User user = session.get(User.class, userId);
        newTask.setUser(user);
        session.update(newTask);
        transaction.commit();
        session.close();
        return newTask;
    }

    public List<Task> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Task> tasks = session.createQuery("FROM Task t JOIN FETCH t.user", Task.class)
                .getResultList();
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
