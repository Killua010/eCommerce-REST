package br.com.ecommerce.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecommerce.domain.Categoria;

@Repository
public interface CategoriaDAO extends JpaRepository<Categoria, Integer>{

}
