package br.com.ecommerce.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.domain.Produto;
import br.com.ecommerce.dto.CategoriaDTO;
import br.com.ecommerce.dto.ProdutoDTO;
import br.com.ecommerce.resources.utils.URL;
import br.com.ecommerce.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id ) {
		return ResponseEntity.ok().body(produtoService.buscar(id));
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> buscarPaginado(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="numPagina", defaultValue="0") Integer numPagina, 
			@RequestParam(value="linhasPorPaginas", defaultValue="24") Integer linhasPorPaginas, 
			@RequestParam(value="ordenarPor", defaultValue="nome") String ordenarPor, 
			@RequestParam(value="direcaoParaOrdenar", defaultValue="ASC") String direcaoParaOrdenar) {
		
		List<Integer> ids = URL.decodeIntList(categorias);
		String nomeDecoded = URL.decodeParam(nome);
		
		Page<Produto> list = produtoService.search(nomeDecoded, ids, numPagina, linhasPorPaginas, ordenarPor, direcaoParaOrdenar);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
	}
	
}
