package com.mycom.utils;

import com.mycom.entity.Doctor;
import com.mycom.entity.Patient;
import com.mycom.entity.Recipe;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
     private static SessionFactory sessionFactory;

    private HibernateSession() {
    }

    public static SessionFactory getSessionFactory() throws Exception {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Doctor.class);
                configuration.addAnnotatedClass(Patient.class);
                configuration.addAnnotatedClass(Recipe.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
               throw e;
            }
        }
        return sessionFactory;
    }
}
