package br.com.ecommerce.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecommerce.domain.Estado;

@Repository
public interface EstadoDAO extends JpaRepository<Estado, Integer>{

}
