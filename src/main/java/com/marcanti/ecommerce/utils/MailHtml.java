package com.marcanti.ecommerce.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.marcanti.ecommerce.view.bean.ReferentielBean;

public class MailHtml {
	

	public static void send(String addresses, String topic, String textMessage) throws MessagingException, IOException {

		// Sender's email ID needs to be mentioned
		//String from = "rachid.khalifa@sfr.fr";

		// Assuming you are sending email from localhost
		//String host = "smtp.sfr.fr";

		// Get system properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host",ReferentielBean.getSmtpHost());
		properties.put("mail.smtp.port", ReferentielBean.getSmtpPort());

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);
		
		// Create a default MimeMessage object.
        Message message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(ReferentielBean.getSmtpFrom()));

        // Set To: header field of the header.
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));

        // Set Subject: header field
        message.setSubject(topic);

        // This mail has 2 part, the BODY and the embedded image
        MimeMultipart multipart = new MimeMultipart("related");

        // first part (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
		String text = Files.toString(new File(ReferentielBean.getMailFolderPath()+File.separator+"message_mail_1.html"), Charsets.UTF_8);
        messageBodyPart.setContent(text, "text/html");
        multipart.addBodyPart(messageBodyPart);

        // second part (the image)
        MimeBodyPart imagePart = new MimeBodyPart();
        DataSource fds = new FileDataSource(ReferentielBean.getMailFolderPath()+File.separator+"parfumelogo-message.jpg");
        imagePart.setDataHandler(new DataHandler(fds));
        //imagePart.setHeader("Content-ID", "<image1>");
        imagePart.setContentID("<image1>");
        imagePart.setDisposition(MimeBodyPart.INLINE);
        
        // add image to the multipart
        multipart.addBodyPart(imagePart);
        
        MimeBodyPart image2Part = new MimeBodyPart();
        DataSource fds2 = new FileDataSource(ReferentielBean.getMailFolderPath()+File.separator+"banner.jpg");
        image2Part.setDataHandler(new DataHandler(fds2));
        //imagePart.setHeader("Content-ID", "<image1>");
        image2Part.setContentID("<image2>");
        image2Part.setDisposition(MimeBodyPart.INLINE);
        
        // add image to the multipart
        multipart.addBodyPart(image2Part);
        
        // put everything together
        message.setContent(multipart);
        // Send message
        Transport.send(message);

	}
	
	public static void main(String[] args) throws Exception{
        System.out.println("Sending mail...");
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.sfr.fr");
        //props.setProperty("mail.user", "myuser");
        //props.setProperty("mail.password", "mypwd");

        Session mailSession = Session.getDefaultInstance(props, null);
        mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject("HTML  mail with images");
        message.setFrom(new InternetAddress("rachid.khalifa@sfr.fr"));
        message.addRecipient(Message.RecipientType.TO,
             new InternetAddress("rachid.khalifa@gmail.com"));

        //
        // This HTML mail have to 2 part, the BODY and the embedded image
        //
        MimeMultipart multipart = new MimeMultipart("related");

        // first part  (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
        messageBodyPart.setContent(htmlText, "text/html");

        // add it
        multipart.addBodyPart(messageBodyPart);
        
        // second part (the image)
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource("C:\\RK\\independant\\Parfum\\mail\\bestswomen2.png");
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID","<image>");

        // add it
        multipart.addBodyPart(messageBodyPart);

        // put everything together
        message.setContent(multipart);

        transport.connect();
        transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
        transport.close();
        }
}
