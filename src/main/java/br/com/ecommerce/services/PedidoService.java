package br.com.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.DAO.PedidoDAO;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoDAO catDao;
	
	public Pedido buscar(Integer id) {		
		return catDao.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Pedido.class.getName()));
	}
	
}
