package com.hibernate.hibernate.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BUDGET")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Budget extends BaseEntity{

	@Column(name = "BUDGET_NAME")
	private String name;
	
	@Column(name = "GOAL_AMOUNT")
	private BigDecimal goalAmount;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "BUDGET_TRANSACTION", joinColumns = @JoinColumn(referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(referencedColumnName = "ID"))
	private List<Transaction> transactions;

	@Builder
	public Budget(String name, BigDecimal goalAmount, List<Transaction> transactions,
			Long id, String createdBy, LocalDate createdDate, String lastUpdatedBy, Instant lastUpdatedDate, String password) {
		super(id, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, password);
		this.name = name;
		this.goalAmount = goalAmount;
		this.transactions = transactions;
	}
	
	
}
