package com.hibernate.hibernate.entity;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
	@SequenceGenerator(name = "book_generator", sequenceName = "book_seq", initialValue = 21, allocationSize = 50)
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "book_generator")
//	@TableGenerator(name="book_generator", table="id_generator")
	@Column(name = "ID")
	protected Long id;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@CreatedDate
	@Column(name = "CREATED_DATE", columnDefinition = "DATE")
	// @Temporal(TemporalType.DATE)
	private LocalDate createdDate;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@LastModifiedDate
	@Column(name = "LAST_UPDATED_DATE", columnDefinition = "DATE")
	private Instant lastUpdatedDate;

	@Transient
	private String password;

//	public BaseEntity(Long id, String createdBy, LocalDate createdDate, String lastUpdatedBy, Instant lastUpdatedDate,
//			String password) {
//		super();
//		this.id = id;
//		this.createdBy = createdBy;
//		this.createdDate = createdDate;
//		this.lastUpdatedBy = lastUpdatedBy;
//		this.lastUpdatedDate = lastUpdatedDate;
//		this.password = password;
//	}

}
