package br.com.ecommerce.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecommerce.domain.Cliente;

@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Integer>{

}
