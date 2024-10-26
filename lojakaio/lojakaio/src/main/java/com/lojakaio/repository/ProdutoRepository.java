package com.lojakaio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojakaio.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
