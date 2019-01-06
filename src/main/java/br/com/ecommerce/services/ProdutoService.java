package br.com.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ecommerce.DAO.CategoriaDAO;
import br.com.ecommerce.DAO.ProdutoDAO;
import br.com.ecommerce.domain.Categoria;
import br.com.ecommerce.domain.Produto;
import br.com.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoDAO prodDao;
	
	@Autowired
	private CategoriaDAO catDao;
	
	public Produto buscar(Integer id) {		
		return prodDao.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer numPagina, Integer linhasPorPaginas, String ordenarPor, String direcaoParaOrdenar){
		// prepara as informações para a consulta
		PageRequest pageRequest = PageRequest.of(numPagina, linhasPorPaginas, Direction.valueOf(direcaoParaOrdenar), ordenarPor);
		
		List<Categoria> categorias = catDao.findAllById(ids);
		
		return prodDao.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	
}
