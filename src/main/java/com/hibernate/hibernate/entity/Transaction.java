package com.hibernate.hibernate.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRANSACTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Transaction extends BaseEntity {

	private BigDecimal initialBal;
	private BigDecimal closingBal;

	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID", nullable = false)
	@Cascade(CascadeType.ALL)
	private Account account;

	@Builder
	public Transaction(BigDecimal initialBal, BigDecimal closingBal, Long id, String createdBy, LocalDate createdDate,
			String lastUpdatedBy, Instant lastUpdatedDate, String password) {
		super(id, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, password);
		this.initialBal = initialBal;
		this.closingBal = closingBal;
	}

}
