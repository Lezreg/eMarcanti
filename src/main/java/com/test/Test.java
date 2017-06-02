package com.test;

import org.apache.commons.codec.digest.DigestUtils;

public class Test {

	private static String hashPassword(String password, String alg) {
		String newPass;
		if (alg == null || alg.equals("MD5")) {
			newPass = DigestUtils.md5Hex(password);
		} else if (alg.equals("NONE")) {
			newPass = "password";
		} else if (alg.equals("SHA-256")) {
			byte[] resu  = DigestUtils.sha256(password);
			newPass=resu.toString();
		} else if (alg.equals("SHA-512")) {
			newPass = DigestUtils.sha512Hex(password);
		} else {
			newPass = DigestUtils.shaHex(password);
		}
		return newPass;
	}

	public static void main(String[] args) {
		System.out.println(hashPassword("parfum123","SHA-512"));
	}

}
