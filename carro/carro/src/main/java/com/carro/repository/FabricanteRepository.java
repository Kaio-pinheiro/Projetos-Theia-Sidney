package com.carro.repository;

import com.carro.entities.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {

        //Método para encontrar fabricantes por nacionalidade, equivale ao where do sql
        List<Fabricante> findByNacionalidade(String nacionalidade);
        //findByNacionalidadeOrderByNomeAsc
        //findByNacionalidadeOrderByNomeDesc

        //Método para encontrar fabricantes por nacionalidade usando LIKE
        List<Fabricante> findByNacionalidadeContaining(String nacionalidade);
    
        //Método para contar a quantidade de fabricantes por nacionalidade
        Long countByNacionalidade(String nacionalidade);

        //Método para encontrar todos os fabricantes, exceto os que possuem uma nacionalidade específica
        List<Fabricante> findByNacionalidadeNot(String nacionalidade);
}
