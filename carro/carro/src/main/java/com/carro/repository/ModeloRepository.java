package com.carro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carro.entities.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    







    
    //Método para encontrar fabricantes por nacionalidade, equivale ao where do sql
    //List<Fabricante> findByNacionalidade(String nacionalidade);
    //findByNacionalidadeOrderByNomeAsc
    //findByNacionalidadeOrderByNomeDesc

    //Método para encontrar fabricantes por nacionalidade usando LIKE
    //List<Fabricante> findByNacionalidadeContaining(String nacionalidade);

    //Método para contar a quantidade de fabricantes por nacionalidade
    //Long countByNacionalidade(String nacionalidade);

    //Método para encontrar todos os fabricantes, exceto os que possuem uma nacionalidade específica
    //List<Fabricante> findByNacionalidadeNot(String nacionalidade);
}
