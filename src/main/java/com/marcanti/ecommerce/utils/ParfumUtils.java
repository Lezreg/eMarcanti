package com.marcanti.ecommerce.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.view.bean.UserSessionBean;

/**
 * 
 * @author RK
 *
 */
public class ParfumUtils {
	
	public static final String BEAN_SESSION_NAME = "USER_SESSION";
	
	public static final short PROFIL_MEMBRE  = 1;
	public static final short PROFIL_FILLEUL = 2;
	public static final short PROFIL_MANAGER = 3;
	public static final short PROFIL_ADMIN   = 4;

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
		return (UserSessionBean)session.getAttribute(BEAN_SESSION_NAME);
	}
	
	public static void setUserSessionBean(UserSessionBean userSession) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute(BEAN_SESSION_NAME,userSession);
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

	public static void sendMail(final String username, final String password, String smtHost, String port,
			String fromEmail, String toEmailList, String subject, String text) {

		// final String username = "rachid.khalifa@opcma.com";
		// final String password = "Mypwd2014";
		// smtHost : smtp.orange.com
		// mail=quicklypizza@orange.fr password=pizza1975

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		// props.put("mail.smtp.host", "mail.opcma.com");
		props.put("mail.smtp.host", smtHost);
		// props.put("mail.smtp.port", "25");
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(fromEmail));

			String email = "";
			ArrayList<InternetAddress> addresses = new ArrayList<InternetAddress>();
			InternetAddress address = null;
			StringTokenizer stk = new StringTokenizer(toEmailList, ";");
			while (stk.hasMoreTokens()) {
				email = stk.nextToken();
				address = new InternetAddress(email);
				addresses.add(address);
			}
			InternetAddress[] internetAddress = addresses.toArray(new InternetAddress[addresses.size()]);
			message.setRecipients(Message.RecipientType.TO, internetAddress);

			// Set Subject: header field
			message.setSubject(subject);
			message.setText(text);

			// Send message
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
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

	public static void main(String[] args) {
		System.out.println(ParfumUtils.checkPasswordFormat("A1ERTYuI"));
	}

}
