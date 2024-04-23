package com.entity;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendigEmail {

	private String userEmail;
	private String myHash;
	
	
	public SendigEmail(String userEmail,String myHash) {
		this.userEmail=userEmail;
		this.myHash=myHash;
	}
	
	public void sendMail() {
		
		String email="nastya1200526@gmail.com";
		String password="gnkvtnefgqdtvgfh"
				+ "";
		
		Properties theProperties=new Properties();
		theProperties.put("mail.smtp.auth","true");
		theProperties.put("mail.smtp.starttls.enable", "true");
		theProperties.put("mail.smtp.host","smtp.gmail.com");
		theProperties.put("mail.smtp.port","587");
		
		Session session=Session.getDefaultInstance(theProperties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email,password);
			}
			
		});
		
		try {
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			message.setSubject("Email Verification Link");
			message.setText("Click this link to confirm your email address and complete setup for your account."
					+ "\n\nVerification Link: " + "http://localhost:8080/GamesCollection/ActivateAccount?key1=" + userEmail + "&key2=" + myHash);
			
			Transport.send(message);
			
			System.out.println("Successfully sent Verification Link");
			
		} catch (Exception e) {
			System.out.println("Error at SendingEmail.java: " + e);
		}
	}
	
}
