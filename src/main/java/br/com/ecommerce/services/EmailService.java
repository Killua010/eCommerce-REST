package br.com.ecommerce.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.ecommerce.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido); 
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
	
}
