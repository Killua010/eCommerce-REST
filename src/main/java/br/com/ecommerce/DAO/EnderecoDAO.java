package br.com.ecommerce.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecommerce.domain.Endereco;

@Repository
public interface EnderecoDAO extends JpaRepository<Endereco, Integer>{

}
