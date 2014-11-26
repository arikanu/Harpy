package com.harpy.hag.db.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
        	AnnotationConfiguration config = new AnnotationConfiguration();
        	config.configure("hibernate.cfg.xml");
        	sessionFactory = config.buildSessionFactory();
        	
            //sessionFactory = new Configuration().configure().buildSessionFactory();
        	
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}