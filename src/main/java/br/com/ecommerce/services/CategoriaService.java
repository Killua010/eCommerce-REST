package br.com.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.DAO.CategoriaDAO;
import br.com.ecommerce.domain.Categoria;
import br.com.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaDAO catDao;
	
	public Categoria buscar(Integer id) {		
		return catDao.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Categoria.class.getName()));
	}
	
}
