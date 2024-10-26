package com.hamburgueria.dto;

public class ClienteDTO {
    
    private Long id;
    private String nome;
    private String sexo;
    private Long cpf;
    private String email;
    private EnderecoDTO enderecoDTO;

    public ClienteDTO(Long id, String nome, String sexo, Long cpf, String email, EnderecoDTO enderecoDTO) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.email = email;
        this.enderecoDTO = enderecoDTO;
    }
    
    public ClienteDTO() {
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
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public Long getCpf() {
        return cpf;
    }
    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public EnderecoDTO getEnderecoDTO() {
        return enderecoDTO;
    }
    public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }
}
