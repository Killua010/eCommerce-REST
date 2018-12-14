package br.com.ecommerce.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecommerce.domain.Categoria;
import br.com.ecommerce.domain.Cidade;

@Repository
public interface CidadeDAO extends JpaRepository<Cidade, Integer>{

}
