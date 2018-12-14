package br.com.ecommerce.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecommerce.domain.Pedido;

@Repository
public interface PedidoDAO extends JpaRepository<Pedido, Integer>{

}
