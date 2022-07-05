package ru.gretchen.accountant.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.gretchen.accountant.hiber.HibernateSessionFactoryUtil;
import ru.gretchen.accountant.model.Report;


public class ReportRepository {

    public Report save(Report report) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(report);
        transaction.commit();
        session.close();
        return report;
    }
}
