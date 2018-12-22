package br.com.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ecommerce.DAO.ClienteDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.dto.ClienteDTO;
import br.com.ecommerce.services.exceptions.DataIntegrityException;
import br.com.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteDAO clieDao;
	
	public Cliente buscar(Integer id) {		
		return clieDao.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Cliente.class.getName()));
	}
	

	public List<Cliente> buscarTodos() {		
		return clieDao.findAll();
	}

	public Cliente alterar(Cliente cliente) {
		Cliente novoCliente = buscar(cliente.getId());
		atualizarDados(novoCliente, cliente);
		return clieDao.save(novoCliente);
	}
	
	public void deletar(Integer id) {
		buscar(id);
		try {
			clieDao.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Cliente porque há entidades relacionadas");
		}
	}
	
	public Page<Cliente> buscarPagina(Integer numPagina, Integer linhasPorPaginas, String ordenarPor, String direcaoParaOrdenar){
		// prepara as informações para a consulta
		PageRequest pageRequest = PageRequest.of(numPagina, linhasPorPaginas, Direction.valueOf(direcaoParaOrdenar), ordenarPor);
		return clieDao.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}
	
	private void atualizarDados(Cliente novoCliente, Cliente cliente) {
		novoCliente.setEmail(cliente.getEmail());
		novoCliente.setNome(cliente.getNome());
	}
	
}
