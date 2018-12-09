package br.com.modelagemconceitual.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.modelagemconceitual.domain.Produto;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Integer>{

}
