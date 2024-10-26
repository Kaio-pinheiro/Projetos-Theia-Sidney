package com.hamburgueria.dto;

public class ProdutoDTO {
    private Long idProduto;
    private String nome;
    private FornecedorDTO fornecedor;

    public ProdutoDTO(String nome, FornecedorDTO fornecedor, Long idProduto) {
        this.nome = nome;
        this.fornecedor = fornecedor;
        this.idProduto = idProduto; 
    }
    
    public ProdutoDTO() {
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public FornecedorDTO getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorDTO fornecedor) {
        this.fornecedor = fornecedor;
    }
}
