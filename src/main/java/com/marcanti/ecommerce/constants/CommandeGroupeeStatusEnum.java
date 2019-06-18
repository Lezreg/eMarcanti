package com.marcanti.ecommerce.constants;

public enum CommandeGroupeeStatusEnum {

	CDE_GROUPEE_NON_CONFIRMEE("CDE_GROUPEE_NON_CONFIRMEE"), CDE_GROUPEE_CONFIRMEE(
			"CDE_GROUPEE_CONFIRMEE"), CDE_GROUPEE_PAYEE("CDE_GROUPEE_PAYEE"), CDE_GROUPEE_A_LIVRER(
					"CDE_GROUPEE_A_LIVRER"), CDE_GROUPEE_LIVREE(
							"CDE_GROUPEE_LIVREE"), CDE_GROUPEE_ANNULEE("CDE_GROUPEE_ANNULEE");
	private String code;

	CommandeGroupeeStatusEnum(String code) {
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		this.code = code;
	}
}
