package br.com.modelagemconceitual.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.modelagemconceitual.domain.Categoria;
import br.com.modelagemconceitual.domain.Cidade;

@Repository
public interface CidadeDAO extends JpaRepository<Cidade, Integer>{

}
