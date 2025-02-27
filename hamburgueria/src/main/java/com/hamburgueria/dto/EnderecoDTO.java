package com.hamburgueria.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.hamburgueria.entities.Cliente;

public class EnderecoDTO {
    private Long id;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    // Para nao retornar o client = null no json do postman
    @JsonIgnore 
    private ClienteDTO clienteDTO;

    // Não sei se é o ClienteDTO ou o Cliente entao dexei comentado aqui caso precise mudar futuramente
    // private Cliente cliente;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    /*
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    */
    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }
    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }
}
