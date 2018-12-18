package br.com.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.ecommerce.DAO.CategoriaDAO;
import br.com.ecommerce.domain.Categoria;
import br.com.ecommerce.services.exceptions.DataIntegrityException;
import br.com.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaDAO catDao;
	
	public Categoria buscar(Integer id) {		
		return catDao.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Categoria.class.getName()));
	}
	
	public Categoria salvar(Categoria categoria) {
		categoria.setId(null);
		return catDao.save(categoria);
	}

	public Categoria alterar(Categoria categoria) {
		buscar(categoria.getId());
		return catDao.save(categoria);
	}
	
	public void deletar(Integer id) {
		buscar(id);
		try {
			catDao.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	
}
