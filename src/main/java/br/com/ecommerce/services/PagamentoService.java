package br.com.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.DAO.PagamentoDAO;
import br.com.ecommerce.domain.Pagamento;

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoDAO dao;
	
	public Pagamento salvar(Pagamento pagamento) {
		pagamento.setId(null);
		return dao.save(pagamento);
	}
	
}
