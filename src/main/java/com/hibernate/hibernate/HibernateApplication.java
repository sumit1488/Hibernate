package com.hibernate.hibernate;

import java.time.LocalDate;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hibernate.hibernate.entity.User;
import com.hibernate.hibernate.util.HibernateUtil;

@SpringBootApplication
public class HibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		User user = User.builder().firstName("Sumit").emailAddress("sumitjobs1488@gmail.com").lastName("Pareek")
				.birthDate(LocalDate.of(1988, 9, 25)).lastUpdatedBy("Sumit").lastUpdatedDate(null).createdBy("ETL")
				.createdDate(LocalDate.now()).build();

		session.save(user);

		session.getTransaction().commit();

		session.close();
	}

}
