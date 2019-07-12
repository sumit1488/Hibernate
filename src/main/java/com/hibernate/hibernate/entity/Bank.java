package com.hibernate.hibernate.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BANK")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Bank extends BaseEntity {

	@Column(name = "BANK_NAME")
	private String name;

	@Column(name = "IS_INTERNATIONAL")
	private boolean international;

	@Column(name = "ADDRESS")
	@Embedded
	private Address address;

	@ElementCollection
	@CollectionTable(name = "BANK_CONTACTS", joinColumns = @JoinColumn(referencedColumnName = "ID")) // column name in
																										// Bank table
	@Column(name = "CONTACT_NAME")
	private Collection<String> contacts = new ArrayList<String>();

	@ElementCollection
	@CollectionTable(name = "BANK_POSITIONS", joinColumns = @JoinColumn(referencedColumnName = "ID")) // column name in
																										// Bank table
	@Column(name = "NAME")
	@MapKeyColumn(name = "POSITION")
	private Map<String, String> positionNameMap = new HashMap<>();

	@Builder
	public Bank(Long id, String createdBy, LocalDate createdDate, String lastUpdatedBy, Instant lastUpdatedDate,
			String password, String name, boolean international, Address address, Collection<String> contacts,
			Map<String, String> positionNameMap) {
		super(id, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, password);
		this.name = name;
		this.international = international;
		this.address = address;
		this.contacts = contacts;
		this.positionNameMap = positionNameMap;
	}

}
