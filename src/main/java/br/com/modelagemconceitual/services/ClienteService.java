package br.com.modelagemconceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.modelagemconceitual.DAO.ClienteDAO;
import br.com.modelagemconceitual.domain.Cliente;
import br.com.modelagemconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteDAO catDao;
	
	public Cliente buscar(Integer id) {		
		return catDao.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Cliente.class.getName()));
	}
	
}
