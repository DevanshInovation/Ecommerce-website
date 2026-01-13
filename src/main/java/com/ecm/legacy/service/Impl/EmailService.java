package com.ecm.legacy.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	 
	public void sendVerificationOtpEmail(String userEmail, String otp, String subject, String text) throws MessagingException {
		
		try {
			MimeMessage mimeMessage=javaMailSender.createMimeMessage();
			MimeMessageHelper mineMessageHelper=new MimeMessageHelper(mimeMessage, "utf-8");
			mineMessageHelper.setSubject(subject);
			mineMessageHelper.setText(text);
			mineMessageHelper.setTo(userEmail);
			javaMailSender.send(mimeMessage);
			
		}catch(MailException e) {
			throw new MailSendException("faild to send message");
		}
	}
}
