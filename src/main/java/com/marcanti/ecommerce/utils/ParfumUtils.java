package com.marcanti.ecommerce.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.ApplicationConfig;

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

	public ParfumUtils() {

	}

	public static List<SelectItem> getListeFichierRequest() {
		return getListeFichier(ParfumUtils.getAppConfig().getRequestRepFile());
	}

	private static List<SelectItem> getListeFichier(String urlFile) {
		List<SelectItem> listeFichiersItems = Collections.emptyList();
		listeFichiersItems = new ArrayList<SelectItem>();
		File f = new File(urlFile);
		File[] listfichier = f.listFiles();
		for (int i = 0; i < listfichier.length; i++) {
			File fichier = listfichier[i];
			// si c'est un fichier on l'ajoute � la liste d 'item
			if (!fichier.isDirectory() && fichier.getName().toLowerCase().matches(".*\\.xml")) {
				listeFichiersItems.add(new SelectItem(fichier.getAbsolutePath(), fichier.getName()));
			}
			// si c'est un r�pertoire on regarde � l'interieur
			else if (fichier.isDirectory()) {
				listeFichiersItems.addAll(getListeFichier(fichier.getAbsolutePath()));
			}
		}

		return listeFichiersItems;
	}

	public static boolean checkPasswordFormat(String password) {

		//au moins 1 majuscule
		boolean resu1 = Pattern.matches(".*[A-Z]{1,}.*", password);
		//au moins 1 minuscule
		boolean resu2 = Pattern.matches(".*[a-z]{1,}.*", password);
		//au moins 1 chiffre
		boolean resu3 = Pattern.matches(".*[0-9]{1,}.*", password);
		//au moins 8 caractères
		boolean resu4 = Pattern.matches(".{8,}", password);
		
		return (resu1 && resu2 && resu3 && resu4);

	}

	public static ApplicationConfig getAppConfig() {
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		ApplicationConfig appBean = (ApplicationConfig) servletContext.getAttribute(ApplicationConfig.BEAN_KEY);
		return appBean;
	}

	public static Object getBean(String beanName) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		Object bean = (Object) request.getSession().getAttribute(beanName);
		return bean;
	}

	/*
	 * public static UserSessionBean getUserSessionBean() throws InesException {
	 * HttpServletRequest request =
	 * (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext(
	 * ).getRequest(); UserSessionBean userSessionBean =
	 * (UserSessionBean)request.getSession().getAttribute(UserSessionBean.
	 * BEAN_KEY); if(userSessionBean == null) throw new
	 * InesException(bundleApplication.getString("Code_GetUserSessionBean"),
	 * bundleApplication.getString("Libelle_GetUserSessionBean")); return
	 * userSessionBean; }
	 */

	public static Object getSessionObject(String objName) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext extCtx = ctx.getExternalContext();
		Map<String, Object> sessionMap = extCtx.getSessionMap();
		return sessionMap.get(objName);
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
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request;
	}

	public static ResourceBundle getBundleApplication() {
		return bundleApplication;
	}

	public static void main(String[] args) {
		System.out.println(ParfumUtils.checkPasswordFormat("A1ERTYuI"));
	}

}
