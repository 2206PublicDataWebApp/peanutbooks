package com.books.peanut.member.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {
	private JavaMailSenderImpl mailSender;
	
	private String getKey(int size) {
		return "622354";
	}
	
	public String sendAuthMail(String mEmail) throws MessagingException{
		String authKey = getKey(6);
		MimeMessage mailMessage = mailSender.createMimeMessage();
		String mailContent = "인증번호: " + authKey;
			mailMessage.setSubject("땅콩북스에서 보내는 이메일", "utf-8");
			mailMessage.setText(mailContent, "utf-8", "html");
			mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mEmail));
			mailSender.send(mailMessage);
			
		return authKey;
	}
}
