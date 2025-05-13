package org.mosorgpay.exception;

public class NotEnoughBalanceException extends RuntimeException {
	
	public NotEnoughBalanceException( String message) {
		super(message);
	}
}
