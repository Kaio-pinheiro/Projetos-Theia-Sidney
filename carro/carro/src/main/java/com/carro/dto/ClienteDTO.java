package com.carro.dto;

// Classe para trasnferir dados do Cliente
public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;
	private String cpf;
    private EnderecoDTO enderecoDTO;

    public EnderecoDTO getEnderecoDTO(){
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoDTO enderecoDTO){
        this.enderecoDTO = enderecoDTO;
    }

    public ClienteDTO() {
    }

	public ClienteDTO(Long id, String nome, String email, String cpf, EnderecoDTO enderecoDTO) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.enderecoDTO = enderecoDTO;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

   
    
    
}
