package br.com.ecommerce.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ErroDeValidacao extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<MensagemDoCampo> lista = new ArrayList<>();
	
	public ErroDeValidacao(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<MensagemDoCampo> getErros() {
		return lista;
	}

	public void addErro(String campo, String mensagem) {
		this.lista.add(new MensagemDoCampo(campo, mensagem));
	}
	
	

}
