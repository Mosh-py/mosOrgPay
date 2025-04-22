package org.mosorgpay.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Empoyees")
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String firstName;
	private String secondName;
	private String emailAddress;
	private String phoneNumber;
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "organisationId")
	private Organisation organisation;
	
//	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//	
//	private List<Transaction> transactions;
}
