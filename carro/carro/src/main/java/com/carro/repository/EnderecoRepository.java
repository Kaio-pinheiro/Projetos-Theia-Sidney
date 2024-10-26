package com.carro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.carro.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}


