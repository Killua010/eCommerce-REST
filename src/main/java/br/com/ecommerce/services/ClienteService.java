package br.com.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.DAO.ClienteDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteDAO catDao;
	
	public Cliente buscar(Integer id) {		
		return catDao.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Cliente.class.getName()));
	}
	
}
