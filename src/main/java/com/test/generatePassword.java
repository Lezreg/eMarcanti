package com.test;

import java.util.Random;

public class generatePassword

{
	private static final Random rand = new Random();
	private static final String Xsi = "abcdefghijklmnopqrstuvwxyz";
	private static final String Gamm = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String Iot = "1234567890";
	// private static final String Zeta = "&~#|`-_)('/?,;:.";
	private static String demo = "";
	private static double i = 0;

	public static void main(String[] args) {

		{

			for (i = 0; i < 5000; i++) {
				demo = "";
				// randomisation des caractères selon leur nombre par type
				// définis à 8 caratères
				while (demo.length() != 8) {

					int rPick = rand.nextInt(4);
					if (rPick == 0) {
						int erzat = rand.nextInt(25);
						demo += Xsi.charAt(erzat);
					} else if (rPick == 1) {
						int erzat = rand.nextInt(9);
						demo += Iot.charAt(erzat);
					} else if (rPick == 2) {
						int erzat = rand.nextInt(25);
						demo += Gamm.charAt(erzat);
					}// else if (rPick == 3) {
						//int erzat = rand.nextInt(15);
						// demo += Zeta.charAt(erzat);
					//}
				}
				System.out.println(demo);
			}
		}
	}
}
