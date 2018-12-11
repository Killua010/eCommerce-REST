package br.com.modelagemconceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.modelagemconceitual.DAO.PedidoDAO;
import br.com.modelagemconceitual.domain.Pedido;
import br.com.modelagemconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoDAO catDao;
	
	public Pedido buscar(Integer id) {		
		return catDao.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Pedido.class.getName()));
	}
	
}
