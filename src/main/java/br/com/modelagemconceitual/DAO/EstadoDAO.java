package br.com.modelagemconceitual.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.modelagemconceitual.domain.Estado;

@Repository
public interface EstadoDAO extends JpaRepository<Estado, Integer>{

}
