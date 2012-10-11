package knt.exceedvote.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SSLMail {

	
	public static void sendMail(String recevier, String password, String type){
		
		InputStream stream = SSLMail.class.getResourceAsStream( "mail.properties" );
		Properties properties = new Properties();
	
		String host = null;
		String port = null;
		String auth = null;
		 try {
			             properties.load(stream);          
			             host = properties.getProperty("smtp");
			             port= properties.getProperty("sslport");
			             auth = properties.getProperty("auth");

			         } catch (IOException e) {
			        	 System.out.println(e);
			         }
		
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.port", port);
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				private String user;
				private String password;

				protected PasswordAuthentication getPasswordAuthentication() {
					
					InputStream stream = SSLMail.class.getResourceAsStream( "mail.properties" );
					Properties properties = new Properties();
				
					 try {
						             properties.load(stream);          
						             this.user = properties.getProperty("mailuser");
						             this.password = properties.getProperty("password");

						         } catch (IOException e) {
						        	 System.out.println(e);
						         }
					
					
					return new PasswordAuthentication(user,password);
				}
			});
 
		if (type.equals("register")){
			String mailpath = null;
			try {
				properties.load(stream);
				mailpath = properties.getProperty("mailpath");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   
			
		try {
                   

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("system@no-spam.com"));
			
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recevier + mailpath));
			message.setSubject("eXceed Vote Registration");
			message.setText("Dear User," +
					"\n\n This is a message for eXceed Vote registration!" +
					"\n\n Your password is: " + password + 
					"\n\n At your first login you must change the password!" +
					"\n\n Thanks!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	}

	
}
