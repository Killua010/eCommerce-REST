package br.com.modelagemconceitual.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.modelagemconceitual.domain.Endereco;

@Repository
public interface EnderecoDAO extends JpaRepository<Endereco, Integer>{

}
