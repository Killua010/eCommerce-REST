package br.com.ecommerce.resources.exception;

import java.io.Serializable;

public class MensagemDoCampo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nomeCampo;
	
	private String mensagem;
	
	public MensagemDoCampo() {
		
	}
	
	public MensagemDoCampo(String nomeCampo, String mensagem) {
		super();
		this.nomeCampo = nomeCampo;
		this.mensagem = mensagem;
	}

	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
