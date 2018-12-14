package br.com.ecommerce.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecommerce.domain.ItemPedido;

@Repository
public interface ItemPedidoDAO extends JpaRepository<ItemPedido, Integer>{

}
