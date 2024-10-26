package com.hamburgueria.util;

import com.hamburgueria.dto.ClienteDTO;
import com.hamburgueria.dto.EnderecoDTO;
import com.hamburgueria.entities.Cliente;
import com.hamburgueria.entities.Endereco;

//Classe utilitaria para mapear Cliente para ClienteDTO e vice-versa
public class ClienteMapper {

    //Método para converter Cliente para ClienteDTO
    public static ClienteDTO toDTO(Cliente cliente){

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setSexo(cliente.getSexo());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setEmail(cliente.getEmail());
        
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(cliente.getEndereco().getId());
        enderecoDTO.setCep(cliente.getEndereco().getCep());
        enderecoDTO.setLogradouro(cliente.getEndereco().getLogradouro());
        enderecoDTO.setNumero(cliente.getEndereco().getNumero());
        enderecoDTO.setComplemento(cliente.getEndereco().getComplemento());
        enderecoDTO.setCidade(cliente.getEndereco().getCidade());
        enderecoDTO.setUf(cliente.getEndereco().getUf());

        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getSexo(), cliente.getCpf(), cliente.getEmail(), enderecoDTO);
    }

    //Método para converter ClienteDTO para Cliente
    public static Cliente toEntity(ClienteDTO clienteDTO){

        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setSexo(clienteDTO.getSexo());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());

        if(clienteDTO.getEnderecoDTO() != null){
            Endereco endereco = new Endereco();
            endereco.setId(clienteDTO.getEnderecoDTO().getId());
            endereco.setCep(clienteDTO.getEnderecoDTO().getCep());
            endereco.setLogradouro(clienteDTO.getEnderecoDTO().getLogradouro());
            endereco.setNumero(clienteDTO.getEnderecoDTO().getNumero());
            endereco.setComplemento(clienteDTO.getEnderecoDTO().getComplemento());
            endereco.setCidade(clienteDTO.getEnderecoDTO().getCidade());
            endereco.setUf(clienteDTO.getEnderecoDTO().getUf());
            cliente.setEndereco(endereco);
        }
        return cliente;
    }
 
}
