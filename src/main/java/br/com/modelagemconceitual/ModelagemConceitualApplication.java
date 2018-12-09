package br.com.modelagemconceitual;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.modelagemconceitual.DAO.CategoriaDAO;
import br.com.modelagemconceitual.DAO.CidadeDAO;
import br.com.modelagemconceitual.DAO.ClienteDAO;
import br.com.modelagemconceitual.DAO.EnderecoDAO;
import br.com.modelagemconceitual.DAO.EstadoDAO;
import br.com.modelagemconceitual.DAO.ProdutoDAO;
import br.com.modelagemconceitual.domain.Categoria;
import br.com.modelagemconceitual.domain.Cidade;
import br.com.modelagemconceitual.domain.Cliente;
import br.com.modelagemconceitual.domain.Endereco;
import br.com.modelagemconceitual.domain.Estado;
import br.com.modelagemconceitual.domain.Produto;
import br.com.modelagemconceitual.domain.enums.TipoCliente;

@SpringBootApplication
public class ModelagemConceitualApplication implements CommandLineRunner{

	@Autowired
	private CategoriaDAO categoriaDAO;
	@Autowired
	private ProdutoDAO produtoDAO;
	@Autowired
	private EstadoDAO estadoDAO;
	@Autowired
	private CidadeDAO cidadeDAO;
	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	private EnderecoDAO enderecoDAO;
	
	public static void main(String[] args) {
		SpringApplication.run(ModelagemConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));	
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		categoriaDAO.saveAll(Arrays.asList(cat1, cat2));
		produtoDAO.saveAll(Arrays.asList(p1, p2, p3));
		
		estadoDAO.saveAll(Arrays.asList(est1, est2));
		cidadeDAO.saveAll(Arrays.asList(c1, c2, c3));
		
		clienteDAO.saveAll(Arrays.asList(cli1));
		enderecoDAO.saveAll(Arrays.asList(e1, e2));
		
	}
}
