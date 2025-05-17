package org.mosorgpay.apiResponse;

import lombok.Data;

@Data
public class TransferApiResponse {

	private String receiverEmail;
	private String status;
	private String receiverBalance;
}
