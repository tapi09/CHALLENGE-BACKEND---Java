package com.alkemyAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String to) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("registromailalkemy@gmail.com");
		message.setTo(to);
		message.setSubject("Registro Satisfactorio");
		message.setText("Bienvenido. Se registró satisfactoriamente!!!!");

		mailSender.send(message);

	}
}
