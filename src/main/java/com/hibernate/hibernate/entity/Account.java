package com.hibernate.hibernate.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ACCOUNT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity {

	@Column(name = "ACCOUNT_NUMBER")
	private String accNum;

	@Column(name = "BALANCE")
	private BigDecimal balance;

	// private List<Transaction> transactions;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ACCOUNT_USER", joinColumns = @JoinColumn(referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(referencedColumnName = "ID"))
	List<User> users = new ArrayList<>();

	@Builder
	public Account(String accNum, BigDecimal balance, // List<Transaction> transactions,
			Long id, String createdBy, LocalDate createdDate, String lastUpdatedBy, Instant lastUpdatedDate,
			String password, List<User> users) {
		super(id, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, password);
		this.accNum = accNum;
		this.balance = balance;
		this.users = users;
		// this.transactions = transactions;
	}

}
