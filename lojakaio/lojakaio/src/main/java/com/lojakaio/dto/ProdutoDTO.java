package com.lojakaio.dto;

public class ProdutoDTO {

    private Long id;
    private String nome;
    private Long idCategoria;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Long id, String nome, Long idCategoria) {
        this.id = id;
        this.nome = nome;
        this.idCategoria = idCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
}