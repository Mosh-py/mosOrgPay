package org.mosorgpay.model;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "organisations")
@Getter
@Setter 
public class Organisation {

	
	 
	@Id
	@Nonnull
	private String companyCode;
	private String name;
	
	private String password;
	
	@OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL)
	private Set<Employee> employees = new HashSet<>();
	
	public Set<Employee> getEmployees(){
		return Collections.unmodifiableSet(employees);
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee)	;
		}
}
