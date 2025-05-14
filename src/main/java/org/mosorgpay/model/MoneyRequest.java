package org.mosorgpay.model;

import java.time.LocalDateTime;

import org.mosorgpay.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity
@Data
public class MoneyRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String lendeeId;
	private String requesteeId;
	private LocalDateTime time;
	private Status status = Status.PENDING;
}
