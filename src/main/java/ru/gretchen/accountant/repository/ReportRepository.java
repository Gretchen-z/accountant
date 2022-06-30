package ru.gretchen.accountant.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.gretchen.accountant.hiber.HibernateSessionFactoryUtil;
import ru.gretchen.accountant.model.Report;

import java.time.LocalDate;
import java.util.List;

public class ReportRepository {

    public Report getById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Report.class, id);
    }

    public Report getByDate() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Report where date = :date");
        query.setParameter("date", LocalDate.now());
        List<Report> reports = query.list();
        return reports.get(0);
    }

    public Report save(Report report) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(report);
        transaction.commit();
        session.close();
        return report;
    }
}
