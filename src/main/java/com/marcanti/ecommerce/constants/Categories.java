package com.marcanti.ecommerce.constants;

public enum Categories {

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

	Categories(String code) {
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		this.code = code;
	}

}
