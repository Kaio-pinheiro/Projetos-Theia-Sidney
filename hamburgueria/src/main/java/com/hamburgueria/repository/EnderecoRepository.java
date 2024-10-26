package com.hamburgueria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hamburgueria.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}
