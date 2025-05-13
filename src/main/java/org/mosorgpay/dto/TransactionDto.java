package org.mosorgpay.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransactionDto {
	
	private String receiverEmail;
	private BigDecimal amount;
}
