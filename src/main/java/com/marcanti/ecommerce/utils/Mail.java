package com.marcanti.ecommerce.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.marcanti.ecommerce.view.bean.ReferentielBean;

public class Mail {
	

	public static void send(String addresses, String topic, String textMessage) throws MessagingException {

		// Sender's email ID needs to be mentioned
		//String from = "rachid.khalifa@sfr.fr";

		// Assuming you are sending email from localhost
		//String host = "smtp.sfr.fr";

		// Get system properties
		Properties properties = new Properties();

		properties.setProperty("mail.smtp.host",ReferentielBean.getSmtpHost());
		properties.put("mail.smtp.port", ReferentielBean.getSmtpPort());

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		// Create a default MimeMessage object.
		MimeMessage message = new MimeMessage(session);

		// Set From: header field of the header.
		message.setFrom(new InternetAddress(ReferentielBean.getSmtpFrom()));

		// Set To: header field of the header.
		// message.addRecipient(Message.RecipientType.TO, new InternetAddress(addresses));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));

		// Set Subject: header field
		message.setSubject(topic);

		// Now set the actual message
		message.setText(textMessage);

		// Send message
		Transport.send(message);
	}
}
