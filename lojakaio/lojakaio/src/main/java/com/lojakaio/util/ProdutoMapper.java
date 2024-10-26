// ACHEI QUE FOSSE PRECISAR, POREM FUNCIONOU SEM E DEIXEI AQUI COMENTADO !!

/*
package com.lojakaio.util;

import com.lojakaio.dto.ProdutoDTO;
import com.lojakaio.dto.CategoriaDTO;
import com.lojakaio.entities.Produto;
import com.lojakaio.entities.Categoria;

// Classe utilitária para mapear Produto para ProdutoDTO e vice-versa
public class ProdutoMapper {

    // Método para converter Produto para ProdutoDTO
    public static ProdutoDTO toDTO(Produto produto) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        if (produto.getCategoria() != null) {
            categoriaDTO.setId(produto.getCategoria().getId());
            categoriaDTO.setNome(produto.getCategoria().getNome());
            categoriaDTO.setDescricao(produto.getCategoria().getDescricao());
        }

        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getCategoria().getId(), categoriaDTO);
    }

    // Método para converter ProdutoDTO para Produto
    public static Produto toEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setId(produtoDTO.getId());
        produto.setNome(produtoDTO.getProduto());

        if (produtoDTO.getCategoria() != null) {
            Categoria categoria = new Categoria();
            categoria.setId(produtoDTO.getCategoria().getId());
            categoria.setNome(produtoDTO.getCategoria().getNome());
            categoria.setDescricao(produtoDTO.getCategoria().getDescricao());
            produto.setCategoria(categoria);
        }

        return produto;
    }
    */

