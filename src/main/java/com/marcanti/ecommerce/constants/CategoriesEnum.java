package com.marcanti.ecommerce.constants;

public enum CategoriesEnum {

	PARFUM_HOMME("PARFUM.HOMME"),

	PARFUM_FEMME("PARFUM.FEMME"),

	PARFUM_ENFANT("PARFUM.ENFANT"),

	TESTER("TESTER"),

	COFFRET("COFFRET"),

	MINIATURE("MINIATURE"),

	CREME("CREME"),

	PILE_ACCESSOIRE("PILE.ACCESSOIRE"),

	LOTS("LOTS");

	private String code;

	CategoriesEnum(String code) {
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		this.code = code;
	}

}
