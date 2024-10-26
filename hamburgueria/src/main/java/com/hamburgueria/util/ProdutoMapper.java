package com.hamburgueria.util;
/*
import com.hamburgueria.dto.FornecedorDTO;
import com.hamburgueria.dto.ProdutoDTO;
import com.hamburgueria.entities.Produto;

public class ProdutoMapper {
    // Método para converter Produto para ProdutoDTO
    public static ProdutoDTO toDTO(Produto produto) {
        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        if (produto.getFornecedor() != null) {
            fornecedorDTO.setId(produto.getFornecedor().getId());
            fornecedorDTO.setNome(produto.getFornecedor().getNome());
            fornecedorDTO.setNacionalidade(produto.getFornecedor().getNacionalidade());
        }

        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getFornecedor().getNacionalidade(),fornecedorDTO);
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
}
*/
