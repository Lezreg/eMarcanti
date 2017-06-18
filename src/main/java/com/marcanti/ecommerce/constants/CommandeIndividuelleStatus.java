package com.marcanti.ecommerce.constants;

public enum CommandeIndividuelleStatus {

	CDE_INDIVID_NON_CONFIRMEE("CDE_INDIVID_NON_CONFIRMEE"),
	CDE_INDIVID_CONFIRMEE("CDE_INDIVID_CONFIRMEE"), 
	CDE_INDIVID_PAYEE("CDE_INDIVID_PAYEE"),
	CDE_INDIVID_A_LIVRER("CDE_INDIVID_A_LIVRER"),
	CDE_INDIVID_LIVREE("CDE_INDIVID_LIVREE"),
	/**
	 * 
	 */
	CDE_INDIVID_ANNULEE("CDE_INDIVID_ANNULEE");

	private String code;

	CommandeIndividuelleStatus(String code) {
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		this.code = code;
	}
}
