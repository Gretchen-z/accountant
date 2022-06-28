package ru.gretchen.accountant.hiber;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.gretchen.accountant.exception.GetSessionException;
import ru.gretchen.accountant.model.Report;
import ru.gretchen.accountant.model.Task;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Report.class);
                configuration.addAnnotatedClass(Task.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                throw new GetSessionException(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
