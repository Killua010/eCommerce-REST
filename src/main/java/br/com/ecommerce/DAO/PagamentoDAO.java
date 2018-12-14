package br.com.ecommerce.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecommerce.domain.Pagamento;

@Repository
public interface PagamentoDAO extends JpaRepository<Pagamento, Integer>{

}
