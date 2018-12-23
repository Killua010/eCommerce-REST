package br.com.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ecommerce.DAO.CategoriaDAO;
import br.com.ecommerce.domain.Categoria;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.dto.CategoriaDTO;
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
	
	public List<Categoria> buscarTodos() {		
		return catDao.findAll();
	}
	
	public Categoria salvar(Categoria categoria) {
		categoria.setId(null);
		return catDao.save(categoria);
	}
	
	public Categoria alterar(Categoria categoria) {
		Categoria novaCategoria = buscar(categoria.getId());
		atualizarDados(novaCategoria, categoria);
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
	
	public Page<Categoria> buscarPagina(Integer numPagina, Integer linhasPorPaginas, String ordenarPor, String direcaoParaOrdenar){
		// prepara as informações para a consulta
		PageRequest pageRequest = PageRequest.of(numPagina, linhasPorPaginas, Direction.valueOf(direcaoParaOrdenar), ordenarPor);
		return catDao.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO dto) {
		return new Categoria(dto.getId(), dto.getNome());
	}
	
	private void atualizarDados(Categoria novaCategoria, Categoria categoria) {
		novaCategoria.setNome(categoria.getNome());
	}
	
}
