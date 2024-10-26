package com.hamburgueria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hamburgueria.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
