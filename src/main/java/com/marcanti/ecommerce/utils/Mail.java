package com.marcanti.ecommerce.utils;

public class Mail {
// 
//    @Resource(name = "java:jboss/mail/parfumMail")
//    private static Session session;
//    
//    public static void sendMail(String addresses, String topic, String textMessage) throws MessagingException {
// 
// 
//            Message message = new MimeMessage(session);
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
//            message.setSubject(topic);
//            message.setText(textMessage);
// 
//            Transport.send(message);
// 
//    }
//    
//    public static void send(String addresses, String topic, String textMessage) throws MessagingException {    
//        // Recipient's email ID needs to be mentioned.
//        //String to = "rachid.khalia@gmail.com";
//
//        // Sender's email ID needs to be mentioned
//        //String from = "quicklypizza@orange.fr";
//    	String from = "rachid.khalifa@sfr.fr";
//
//        // Assuming you are sending email from localhost
//        String host = "smtp.sfr.fr";
//
//        // Get system properties
//        Properties properties = System.getProperties();
//
//        // Setup mail server
//        properties.setProperty("mail.smtp.host", host);
//        
//        //properties.setProperty("mail.user", "quicklypizza@orange.fr");
//        //properties.setProperty("mail.password", "pizza1975");
//
//        // Get the default Session object.
//        Session session = Session.getDefaultInstance(properties);
//
//           // Create a default MimeMessage object.
//           MimeMessage message = new MimeMessage(session);
//
//           // Set From: header field of the header.
//           message.setFrom(new InternetAddress(from));
//
//           // Set To: header field of the header.
//           //message.addRecipient(Message.RecipientType.TO, new InternetAddress(addresses));
//           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
//
//           // Set Subject: header field
//           message.setSubject(topic);
//
//           // Now set the actual message
//           message.setText(textMessage);
//
//           // Send message
//           Transport.send(message);
//     }
}
