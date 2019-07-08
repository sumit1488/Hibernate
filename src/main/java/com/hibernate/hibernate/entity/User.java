package com.hibernate.hibernate.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "FINANCES_USER")
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {

	/**
	 *  Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "BIRTH_DATE")
	private LocalDate birthDate;

	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;

	@Builder
	public User(Long id, String createdBy, LocalDate createdDate, String lastUpdatedBy, LocalDate lastUpdatedDate,
			String firstName, String lastName, LocalDate birthDate, String emailAddress) {
		super(id, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate);
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.emailAddress = emailAddress;
	}

}
