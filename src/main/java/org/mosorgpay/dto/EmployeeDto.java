package org.mosorgpay.dto;

import java.util.UUID;

import org.mosorgpay.model.Organisation;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
	
	private String id;
	private String firstName;
	private String secondName;
	private String emailAddress;
	private String phoneNumber;
	private String password;
	private String organisationCode;
}
