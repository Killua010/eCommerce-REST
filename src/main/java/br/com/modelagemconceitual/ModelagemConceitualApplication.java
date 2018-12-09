package br.com.modelagemconceitual;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.modelagemconceitual.DAO.CategoriaDAO;
import br.com.modelagemconceitual.DAO.CidadeDAO;
import br.com.modelagemconceitual.DAO.EstadoDAO;
import br.com.modelagemconceitual.DAO.ProdutoDAO;
import br.com.modelagemconceitual.domain.Categoria;
import br.com.modelagemconceitual.domain.Cidade;
import br.com.modelagemconceitual.domain.Estado;
import br.com.modelagemconceitual.domain.Produto;

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
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		categoriaDAO.saveAll(Arrays.asList(cat1, cat2));
		produtoDAO.saveAll(Arrays.asList(p1, p2, p3));
		
		estadoDAO.saveAll(Arrays.asList(est1, est2));
		cidadeDAO.saveAll(Arrays.asList(c1, c2, c3));
		
	}
}
