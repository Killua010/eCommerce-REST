package br.com.ecommerce.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.ecommerce.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido); 
	
	void sendEmail(SimpleMailMessage msg);
	
}
