package br.com.modelagemconceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.modelagemconceitual.DAO.CategoriaDAO;
import br.com.modelagemconceitual.domain.Categoria;
import br.com.modelagemconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaDAO catDao;
	
	public Categoria buscar(Integer id) {		
		return catDao.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Categoria.class.getName()));
	}
	
}
