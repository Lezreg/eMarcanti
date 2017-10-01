package com.marcanti.ecommerce.constants;

public enum StatutTransactionFournissPaiement {

	TRANSACTION_NON_CONFIRMEE("TRANSACTION_NON_CONFIRMEE"), TRANSACTION_CONFIRMEE("TRANSACTION_CONFIRMEE");

	/**
	 * 
	 */
	private String code;

	private StatutTransactionFournissPaiement(String code) {
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
