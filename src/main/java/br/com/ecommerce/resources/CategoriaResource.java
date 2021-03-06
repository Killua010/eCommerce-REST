package br.com.ecommerce.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ecommerce.domain.Categoria;
import br.com.ecommerce.dto.CategoriaDTO;
import br.com.ecommerce.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> buscar(@PathVariable Integer id) {
		return ResponseEntity.ok().body(categoriaService.buscar(id));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody CategoriaDTO categoriaDto){
		Categoria categoria =  categoriaService.fromDTO(categoriaDto);
		categoria = categoriaService.salvar(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@Valid @RequestBody CategoriaDTO categoriaDto, @PathVariable Integer id){
		Categoria categoria = categoriaService.fromDTO(categoriaDto);
		categoria.setId(id);
		categoria = categoriaService.alterar(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		categoriaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> buscarTodos() {
		List<CategoriaDTO> listDto = categoriaService.buscarTodos().stream().map(cat -> new CategoriaDTO(cat)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/paginado", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> buscarPaginado(
			@RequestParam(value="numPagina", defaultValue="0") Integer numPagina, 
			@RequestParam(value="linhasPorPaginas", defaultValue="24") Integer linhasPorPaginas, 
			@RequestParam(value="ordenarPor", defaultValue="nome") String ordenarPor, 
			@RequestParam(value="direcaoParaOrdenar", defaultValue="ASC") String direcaoParaOrdenar) {
		Page<CategoriaDTO> listDto = categoriaService.buscarPagina(numPagina, linhasPorPaginas, ordenarPor, direcaoParaOrdenar)
				.map(cat -> new CategoriaDTO(cat));
		return ResponseEntity.ok().body(listDto);
	}
	
}
