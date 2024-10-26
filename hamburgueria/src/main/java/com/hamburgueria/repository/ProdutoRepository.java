package com.hamburgueria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hamburgueria.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
