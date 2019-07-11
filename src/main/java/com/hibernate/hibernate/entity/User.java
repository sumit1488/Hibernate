package com.hibernate.hibernate.entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import com.hibernate.hibernate.enums.LastNameEnum;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "FINANCES_USER", uniqueConstraints = {
		@UniqueConstraint(name = "User_first_name_key", columnNames = { "FIRST_NAME" }) })
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "FIRST_NAME", nullable = false, columnDefinition = "ENUM('Sumit', 'Anu')")
	private String firstName;

	@Column(name = "LAST_NAME")
	@Enumerated(EnumType.STRING)
	private LastNameEnum lastName;

	@Column(name = "BIRTH_DATE", updatable = false)
	private LocalDate birthDate;

	@Basic(optional = false)
	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;
	
	@OneToOne(mappedBy = "user") // name of refernce at Credentials.java side // never ise joincolumn on both sides and never use cascade on both sides
	private Credentials credentials;
	
	@ElementCollection
	@CollectionTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(referencedColumnName = "ID"))
	@AttributeOverrides({ @AttributeOverride(name = "addressLine1", column = @Column(name = "USER_ADDRESS_LINE_1")),
			@AttributeOverride(name = "addressLine2", column = @Column(name = "USER_ADDRESS_LINE_2")),
			@AttributeOverride(name = "city", column = @Column(name = "USER_CITY")),
			@AttributeOverride(name = "state", column = @Column(name = "USER_STATE")),
			@AttributeOverride(name = "zipCode", column = @Column(name = "USER_ZIPCODE")) })
	private List<Address> address;
	
//	// for unidirectional
//	@OneToMany
//	@JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
//	@Cascade(CascadeType.ALL)
//	private List<Vehicle> vehicleList;
	
	// for bidirectional
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Vehicle> vehicleList;

	// for bidirectional
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "users") //mappedby contains the name of reference in Account entity
	List<Account> accountList = new ArrayList<Account>();
	
	@Builder
	public User(Long id, String createdBy, LocalDate createdDate, String lastUpdatedBy, Instant lastUpdatedDate,
			String password, String firstName, LastNameEnum lastName, LocalDate birthDate, String emailAddress,
			List<Address> address, Credentials credentials, List<Vehicle> vehicleList, List<Account> accountList) {
		super(id, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.emailAddress = emailAddress;
		this.address = address;
		this.credentials = credentials;
		this.vehicleList = vehicleList;
		this.accountList = accountList;
	}

}
