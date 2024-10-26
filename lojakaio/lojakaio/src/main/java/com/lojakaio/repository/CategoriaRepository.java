package com.lojakaio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lojakaio.entities.Categoria;

import java.util.List;

// Repository é uma interface que o jpa usa pra gravar os dados no banco, pra conectar
// é igual a camada DAO, onde terá os metodos create read update delete

// Repository é uma interface do Spring Data JPA que fornece métodos para operações CRUD (Create, Read, Update, Delete)
// Ela substitui a necessidade de implementar manualmente a camada DAO, oferecendo métodos prontos para manipulação de dados.

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Método para encontrar categorias por descrição, equivale ao where do sql
    List<Categoria> findByDescricao(String descricao);
    // findByDescricaoOrderByNomeAsc
    // findByDescricaoOrderByNomeDesc

    // Método para encontrar categorias por descrição usando LIKE
    List<Categoria> findByDescricaoContaining(String descricao);

    // Método para contar a quantidade de categorias por descrição
    Long countByDescricao(String descricao);

    // Método para encontrar todos as categorias, exceto os que possuem uma
    // descrição específica
    List<Categoria> findByDescricaoNot(String descricao);
}
