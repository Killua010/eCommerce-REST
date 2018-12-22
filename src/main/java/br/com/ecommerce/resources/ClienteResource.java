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

import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.dto.ClienteDTO;
import br.com.ecommerce.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService ClienteService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id ) {
		
		return ResponseEntity.ok().body(ClienteService.buscar(id));
	}
	
	/*@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody ClienteDTO ClienteDto){
		Cliente Cliente =  ClienteService.fromDTO(ClienteDto);
		Cliente = ClienteService.salvar(Cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}*/
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@Valid @RequestBody ClienteDTO ClienteDto, @PathVariable Integer id){
		Cliente Cliente = ClienteService.fromDTO(ClienteDto);
		Cliente.setId(id);
		Cliente = ClienteService.alterar(Cliente);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		ClienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> buscarTodos() {
		List<ClienteDTO> listDto = ClienteService.buscarTodos().stream().map(cat -> new ClienteDTO(cat)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/paginado", method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> buscarPaginado(
			@RequestParam(value="numPagina", defaultValue="0") Integer numPagina, 
			@RequestParam(value="linhasPorPaginas", defaultValue="24") Integer linhasPorPaginas, 
			@RequestParam(value="ordenarPor", defaultValue="nome") String ordenarPor, 
			@RequestParam(value="direcaoParaOrdenar", defaultValue="ASC") String direcaoParaOrdenar) {
		Page<ClienteDTO> listDto = ClienteService.buscarPagina(numPagina, linhasPorPaginas, ordenarPor, direcaoParaOrdenar)
				.map(cat -> new ClienteDTO(cat));
		return ResponseEntity.ok().body(listDto);
	}
	
}
