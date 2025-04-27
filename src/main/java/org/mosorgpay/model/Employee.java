package org.mosorgpay.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import lombok.ToString;

@Entity
@Table(name = "Employees")
public class Employee {

	@Id
	private String id;
	private String firstName;
	private String secondName;
	private String emailAddress;
	private String phoneNumber;
	
	
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "organisationId")
	private Organisation  organisation;
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	
	private Set<Transaction> transactions = new HashSet<>() ;
	
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
		transaction.setEmployee(this);
	}
	
	public Set<Transaction> getTransactions(){
		return Collections.unmodifiableSet(transactions);
	}

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getSecondName() {
		return secondName;
	}



	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}



	public String getEmailAddress() {
		return emailAddress;
	}



	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Organisation getOrganisation() {
		return organisation;
	}



	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}



	@Override 
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", emailAddress="
				+ emailAddress + ", phoneNumber=" + phoneNumber + ", organisation=" + organisation + ", transactions="
				+ transactions + "]";
	}
	 
	
	
}
