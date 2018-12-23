package br.com.ecommerce.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ecommerce.DAO.ClienteDAO;
import br.com.ecommerce.DAO.EnderecoDAO;
import br.com.ecommerce.domain.Cidade;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.enums.TipoCliente;
import br.com.ecommerce.dto.ClienteDTO;
import br.com.ecommerce.dto.ClienteNovoDTO;
import br.com.ecommerce.services.exceptions.DataIntegrityException;
import br.com.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteDAO clieDao;
	
	@Autowired
	private EnderecoDAO enderecoDAO;
	
	public Cliente buscar(Integer id) {		
		return clieDao.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Cliente.class.getName()));
	}
	
	@Transactional 
	public Cliente salvar(Cliente cliente) {
		cliente.setId(null);
		cliente = clieDao.save(cliente);
		enderecoDAO.saveAll(cliente.getEnderecos());
		return cliente;
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
	
	public Cliente fromDTO(ClienteNovoDTO dto) {
		Cliente cliente =  new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipoCliente()));
		Cidade cidade = new Cidade(dto.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(),
				dto.getBairro(), dto.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(dto.getTelefone1());
		if(null != dto.getTelefone2()) {
			cliente.getTelefones().add(dto.getTelefone2());
		}
		if(null != dto.getTelefone3()) {
			cliente.getTelefones().add(dto.getTelefone3());
		}
		return cliente;
	}
	
	private void atualizarDados(Cliente novoCliente, Cliente cliente) {
		novoCliente.setEmail(cliente.getEmail());
		novoCliente.setNome(cliente.getNome());
	}
	
}
