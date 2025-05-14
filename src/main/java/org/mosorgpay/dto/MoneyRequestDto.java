package org.mosorgpay.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MoneyRequestDto {
	
	private String lendeeId;
	private BigDecimal amount;
}
