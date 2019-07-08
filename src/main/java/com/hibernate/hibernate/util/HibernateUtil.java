package com.hibernate.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

import com.hibernate.hibernate.entity.User;

@org.springframework.context.annotation.Configuration
public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	@Bean
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(User.class);
			return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Error while building session factory");
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
