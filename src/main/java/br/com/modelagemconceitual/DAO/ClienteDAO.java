package br.com.modelagemconceitual.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.modelagemconceitual.domain.Cliente;

@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Integer>{

}
