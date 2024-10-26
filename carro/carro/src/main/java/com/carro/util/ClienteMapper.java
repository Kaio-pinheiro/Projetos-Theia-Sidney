package com.carro.util;

import com.carro.dto.ClienteDTO;
import com.carro.dto.EnderecoDTO;
import com.carro.entities.Cliente;
import com.carro.entities.Endereco;

//Classe utilitaria para mapear Cliente para ClienteDTO e vice-versa
public class ClienteMapper {

    //Método para converter Cliente para ClienteDTO
    public static ClienteDTO toDTO(Cliente cliente){

       /* ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setEmail(cliente.getEmail());
        */


        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(cliente.getEndereco().getId());
        enderecoDTO.setCep(cliente.getEndereco().getCep());
        enderecoDTO.setNumero(cliente.getEndereco().getNumero());
        enderecoDTO.setComplemento(cliente.getEndereco().getComplemento());

        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf(), enderecoDTO);
    }

    

    //Método para converter ClienteDTO para Cliente
    public static Cliente toEntity(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setCpf(clienteDTO.getCpf());

        if(clienteDTO.getEnderecoDTO() != null){
            Endereco endereco = new Endereco();
            endereco.setId(clienteDTO.getEnderecoDTO().getId());
            endereco.setCep(clienteDTO.getEnderecoDTO().getCep());
            endereco.setNumero(clienteDTO.getEnderecoDTO().getNumero());
            endereco.setComplemento(clienteDTO.getEnderecoDTO().getComplemento());
            cliente.setEndereco(endereco);
        }

        return cliente;

    }
    
}
