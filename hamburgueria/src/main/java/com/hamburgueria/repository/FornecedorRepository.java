package com.hamburgueria.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hamburgueria.entities.Fornecedor;

// Recebe o objeto inteiro fornecedor ou o Long que é o id
@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
    
    //Método para encontrar fornecedores por nacionalidade, equivale ao where do sql
    List<Fornecedor> findByNacionalidade(String nacionalidade);
    //findByNacionalidadeOrderByNomeAsc
    //findByNacionalidadeOrderByNomeDesc

    //Método para encontrar fornecedores por nacionalidade usando LIKE
    List<Fornecedor> findByNacionalidadeContaining(String nacionalidade);

    //Método para encontrar todos os fornecedores, exceto os que possuem uma nacionalidade específica
     List<Fornecedor> findByNacionalidadeNot(String nacionalidade);

    //Método para contar a quantidade de fornecedores por nacionalidade
    Long countByNacionalidade(String nacionalidade);

    
}
