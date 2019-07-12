package com.hibernate.hibernate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hibernate.hibernate.entity.Account;
import com.hibernate.hibernate.entity.Address;
import com.hibernate.hibernate.entity.Bank;
import com.hibernate.hibernate.entity.Budget;
import com.hibernate.hibernate.entity.Credentials;
import com.hibernate.hibernate.entity.Transaction;
import com.hibernate.hibernate.entity.User;
import com.hibernate.hibernate.entity.Vehicle;
import com.hibernate.hibernate.enums.LastNameEnum;
import com.hibernate.hibernate.util.HibernateUtil;

@SpringBootApplication
//@EnableJpaAuditing
public class HibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		User user = User.builder().firstName("Sumit").emailAddress("sumitjobs1488@gmail.com")
				.lastName(LastNameEnum.PAREEK).birthDate(LocalDate.of(1988, 9, 25)).lastUpdatedBy("Sumit")
				.lastUpdatedDate(null).createdBy("ETL").createdDate(LocalDate.now()).build();

		session.save(user);

		Address userAddress1 = Address.builder().addressLine1("user_addrLine1").addressLine2("user_addressLine2")
				.city("user_Pune").state("user_Maharashtra").zipCode("user_411027").build();
		Address userAddress2 = Address.builder().addressLine1("user_addrLine2").addressLine2("user_addressLine2")
				.city("user_Pune").state("user_Maharashtra").zipCode("user_411027").build();

		Vehicle vehicle1 = Vehicle.builder().vin("DL2CG4859").price(new BigDecimal(12123791)).build();
		Vehicle vehicle2 = Vehicle.builder().vin("DDW351").price(new BigDecimal(431322)).build();

		User user1 = User.builder().firstName("Anu").emailAddress("sumitjobs1488@gmail.com")
				.lastName(LastNameEnum.PAREEK).birthDate(LocalDate.of(1988, 9, 25)).lastUpdatedBy("Sumit")
				.lastUpdatedDate(null).createdBy("ETL").createdDate(LocalDate.now())
				.address(Arrays.asList(userAddress1, userAddress2)).build();

		// one to many
		user1.setVehicleList(Arrays.asList(vehicle1, vehicle2));
		vehicle1.setUser(user1);
		vehicle2.setUser(user1);

		Credentials credentials = Credentials.builder().username("userName").password("pass").lastUpdatedBy("Sumit")
				.lastUpdatedDate(null).createdBy("ETL").createdDate(LocalDate.now()).user(user1).build();

		// bidirectional relationship save
		user1.setCredentials(credentials);
		session.save(credentials);

		// fetch to prove bidirectional

		User userFetch = session.get(User.class, user1.getId());
		Credentials credentialFetch = session.get(Credentials.class, credentials.getId());
		System.out.println(userFetch.getCredentials().getUsername());
		System.out.println(credentialFetch.getUser().getFirstName());

		// fetch to prove onetomany bidirectional
		Vehicle vehicleFetch1 = session.get(Vehicle.class, vehicle1.getId());
		Vehicle vehicleFetch2 = session.get(Vehicle.class, vehicle2.getId());
		System.out.println(userFetch.getVehicleList().get(0).getVin());
		System.out.println(userFetch.getVehicleList().get(1).getVin());
		System.out.println(vehicleFetch1.getUser().getFirstName());
		System.out.println(vehicleFetch2.getUser().getFirstName());

		Address address = Address.builder().addressLine1("addrLine1").addressLine2("addressLine2").city("Pune")
				.state("Maharashtra").zipCode("411027").build();

		Map<String, String> positionMap = new HashMap<>();
		positionMap.put("BRANCH MANAGER", "RAHUL");
		positionMap.put("MANAGER", "MANAYAN");
		positionMap.put("TRAINEE", "SUMIT");
		positionMap.put("PO", "AJAY");

		// bank example for collection embedded for contacts and positionName and also
		// address for object type embedding.
		Bank bank = Bank.builder().name("HDFC BANK").international(true).lastUpdatedBy("Sumit").lastUpdatedDate(null)
				.createdBy("ETL").createdDate(LocalDate.now()).address(address)
				.contacts(Arrays.asList("mail", "phone", "email")).positionNameMap(positionMap).build();

		session.save(bank);

		Account account1 = Account.builder().accNum("accName").balance(new BigDecimal(123123123.90)).build();
		account1.setUsers(Arrays.asList(user, user1));
		Account account2 = Account.builder().accNum("accName222").balance(new BigDecimal(87364239.90)).build();
		account2.setUsers(Arrays.asList(user, user1));

		// for bidirectional relation add accounts to user
		user.setAccountList(Arrays.asList(account1, account2));
		user1.setAccountList(Arrays.asList(account1, account2));

		session.save(account1);
		session.save(account2);

		// test many to many bidirectional
		System.out.println("Account== " + user.getAccountList().get(0).getAccNum());
		System.out.println("Account== " + user.getAccountList().get(1).getAccNum());
		System.out.println("Account== " + user1.getAccountList().get(0).getAccNum());
		System.out.println("Account== " + user1.getAccountList().get(1).getAccNum());

		System.out.println("USER== " + account1.getUsers().get(0).getFirstName());
		System.out.println("USER== " + account1.getUsers().get(1).getFirstName());
		System.out.println("USER== " + account2.getUsers().get(0).getFirstName());
		System.out.println("USER== " + account2.getUsers().get(1).getFirstName());

		Transaction transaction1 = Transaction.builder().closingBal(new BigDecimal(123123123.90))
				.initialBal(new BigDecimal(123123123.90)).build();
		Transaction transaction2 = Transaction.builder().closingBal(new BigDecimal(123123123.90))
				.initialBal(new BigDecimal(123123123.90)).build();
		transaction1.setAccount(account1);
		transaction2.setAccount(account1);
		transaction1.setAccount(account2);
		transaction2.setAccount(account2);

		// @JoinTable example for onetomany
		Budget budget = Budget.builder().goalAmount(new BigDecimal(7239793286.00)).name("YOYObudget").build();
		budget.setTransactions(Arrays.asList(transaction1, transaction2));

		session.save(budget);

//		session.save(transaction2);
//		session.save(transaction1);

		session.getTransaction().commit();

		session.beginTransaction();

		User userFetched = (User) session.get(User.class, user.getId());

		userFetched.setBirthDate(LocalDate.now());

		session.update(userFetched);

		session.getTransaction().commit();

		session.close();
	}

}
