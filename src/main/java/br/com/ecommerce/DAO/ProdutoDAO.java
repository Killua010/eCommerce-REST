package br.com.ecommerce.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecommerce.domain.Produto;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Integer>{

}
