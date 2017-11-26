package com.marcanti.ecommerce.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.view.bean.ReferentielBean;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

/**
 * 
 * @author RK
 *
 */
public class ParfumUtils {
	
	/**
	 * Gestion des Logs.
	 */
	private final static Logger logger = LoggerFactory.getLogger(ParfumUtils.class);

	private static ResourceBundle bundleApplication = ResourceBundle.getBundle("ressources.ApplicationMessage");

	private static ResourceBundle bundleLibelle = ResourceBundle.getBundle("ressources.parfumLibelle");

	public ParfumUtils() {

	}

	public static boolean checkPasswordFormat(String password) {

		// au moins 1 majuscule
		boolean resu1 = Pattern.matches(".*[A-Z]{1,}.*", password);
		// au moins 1 minuscule
		boolean resu2 = Pattern.matches(".*[a-z]{1,}.*", password);
		// au moins 1 chiffre
		boolean resu3 = Pattern.matches(".*[0-9]{1,}.*", password);
		// au moins 8 caractères
		boolean resu4 = Pattern.matches(".{8,}", password);

		return (resu1 && resu2 && resu3 && resu4);

	}

	public static UserSessionBean getUserSessionBean() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session!=null)
			return (UserSessionBean)session.getAttribute(ReferentielBean.BEAN_SESSION_NAME);
		else return null;
	}
	
	public static void setUserSessionBean(UserSessionBean userSession) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute(ReferentielBean.BEAN_SESSION_NAME,userSession);
	}	

	public static boolean checkPattern(String chaine, String regex) {

		Pattern pattern = null;
		Matcher m = null;
		pattern = Pattern.compile(regex);
		if (chaine != null) {
			m = pattern.matcher(chaine);
			return m.matches();
		}
		return false;

	}

	public static int getCode() {
		return (new Random().nextInt(9999 - 1000) + 1000);
	}

	public static String getPassword() {

		final Random rand = new Random();
		final String Xsi = "abcdefghijklmnopqrstuvwxyz";
		final String Gamm = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String Iot = "1234567890";
		// private static final String Zeta = "&~#|`-_)('/?,;:.";

		String password = "";
		// randomisation des caractères selon leur nombre par type
		// définis à 8 caratères
		while (password.length() != 8) {

			int rPick = rand.nextInt(4);
			if (rPick == 0) {
				int erzat = rand.nextInt(25);
				password += Xsi.charAt(erzat);
			} else if (rPick == 1) {
				int erzat = rand.nextInt(9);
				password += Iot.charAt(erzat);
			} else if (rPick == 2) {
				int erzat = rand.nextInt(25);
				password += Gamm.charAt(erzat);
			} // else if (rPick == 3) {
				// int erzat = rand.nextInt(15);
				// demo += Zeta.charAt(erzat);
				// }
		}
		return password;
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request;
	}

	public static ResourceBundle getBundleApplication() {
		return bundleApplication;
	}

	public static ResourceBundle getBundleLibelle() {
		return bundleLibelle;
	}

	public static String getDateNowEN() {

		Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(dt);
	}

	public static String getDateNowFR() {

		Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		return sdf.format(dt);
	}
	
	public static Object getSessionObject(String objName) {
	    FacesContext ctx = FacesContext.getCurrentInstance();
	    ExternalContext extCtx = ctx.getExternalContext();
	    Map<String, Object> sessionMap = extCtx.getSessionMap();
	    return sessionMap.get(objName);
	}	
	
	public static boolean isUniqueName(String pathFolder, String nameFile) {

		boolean resu = true;
		File folder = new File(pathFolder);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			//System.out.println("file name : " + listOfFiles[i].getName());
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().equals(nameFile)) {
				resu=false;
				break;
			} 
		}
		return resu;
	}
	
	public static String getUniqueName(String pathFolder, String nameFile) {

		int i = 0;
		StringTokenizer st = new StringTokenizer(nameFile, ".");
		String copyNameFile = new String(st.nextToken());
		String extFile = new String(st.nextToken());

		while(!isUniqueName(pathFolder,nameFile)){
			i++;
			nameFile = new String(copyNameFile);
			nameFile = nameFile + "_" + i + "." + extFile;
		}
		return nameFile;
	}	
	
	
	public static void replace(String oldstring, String newstring, File in, File out) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(in));
		PrintWriter writer = new PrintWriter(new FileWriter(out));
		String line = null;
		while ((line = reader.readLine()) != null) {
			writer.println(line.replaceAll(oldstring,newstring));
		}
		reader.close();
		writer.close();
		reader=null;
		writer=null;
	}

	public static void main(String[] args) {
		//System.out.println(ParfumUtils.checkPasswordFormat("A1ERTYuI"));
		//System.out.println(isUniqueName("C:\\RK\\independant\\Parfum\\upload","passede.gif"));
		System.out.println(getUniqueName("C:\\RK\\independant\\Parfum\\upload","passed.gif"));
	}
	
	


}
