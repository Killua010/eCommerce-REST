package br.com.modelagemconceitual.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.modelagemconceitual.domain.Pagamento;

@Repository
public interface PagamentoDAO extends JpaRepository<Pagamento, Integer>{

}
