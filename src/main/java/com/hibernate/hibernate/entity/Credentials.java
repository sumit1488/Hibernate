package com.hibernate.hibernate.entity;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CREDENTIALS")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Credentials extends BaseEntity {

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private User user;

	@Builder
	public Credentials(String username, String password, Long id, String createdBy, LocalDate createdDate,
			String lastUpdatedBy, Instant lastUpdatedDate, User user) {
		super(id, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, password);
		this.username = username;
		this.password = password;
		this.user = user;
	}

}
