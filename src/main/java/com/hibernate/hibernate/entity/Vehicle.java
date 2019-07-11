package com.hibernate.hibernate.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VEHICLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Vehicle extends BaseEntity{

	@Column(name = "VIN")
	private String vin;
	
	@Column(name = "PRICE")
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private User user;

	@Builder
	public Vehicle(String vin, BigDecimal price,// List<Transaction> transactions,
			Long id, String createdBy, LocalDate createdDate, String lastUpdatedBy, Instant lastUpdatedDate, String password) {
		super(id, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, password);
		this.vin = vin;
		this.price = price;
	//	this.transactions = transactions;
	}
	
	
}
