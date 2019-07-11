package com.hibernate.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

import com.hibernate.hibernate.entity.Account;
import com.hibernate.hibernate.entity.Bank;
import com.hibernate.hibernate.entity.Budget;
import com.hibernate.hibernate.entity.Credentials;
import com.hibernate.hibernate.entity.Transaction;
import com.hibernate.hibernate.entity.User;
import com.hibernate.hibernate.entity.Vehicle;

@org.springframework.context.annotation.Configuration
public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	@Bean
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Bank.class);
			configuration.addAnnotatedClass(Credentials.class);
			configuration.addAnnotatedClass(Account.class);
			configuration.addAnnotatedClass(Transaction.class);
			configuration.addAnnotatedClass(Vehicle.class);
			configuration.addAnnotatedClass(Budget.class);
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
