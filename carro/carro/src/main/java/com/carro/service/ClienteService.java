package com.carro.service;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.carro.dto.ClienteDTO;
import com.carro.entities.Cliente;
import com.carro.repository.ClienteRepository;
import com.carro.util.ClienteMapper;
import com.carro.util.EmailValidator;

// Anotação que indica que esta classe é um serviço
@Service
public class ClienteService {

    // Injeção de dependencia do repositorio de clientes
    @Autowired
    private ClienteRepository clienteRepository;

    //Setter para injetar o repositório manualmente em testes
    public void setClienteRepository(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    // Método para criar um novo cliente
    public ResponseEntity<ClienteDTO> criarCliente(ClienteDTO clienteDTO){
        // Verificar se o email é válido
         if (!EmailValidator.validarEmail(clienteDTO.getEmail())){
            return ResponseEntity.status(422).build(); // ou.body(null); testar os dois
        }

        //Criar entidade Cliente
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        ClienteDTO clienteSalvoDTO = ClienteMapper.toDTO(clienteRepository.save(cliente));
        return ResponseEntity.ok(clienteSalvoDTO); // Retorna o DTO atualizado
    }

    // Método para obter todos os clientes
   public List<ClienteDTO> obterTodosClientes(){
    List<Cliente> clientes = clienteRepository.findAll(); // Retorna todos os clientes do banco de dados
    return clientes.stream()  // Usando stream para converter cada Cliente para ClienteDTO
        .map(ClienteMapper::toDTO)  // Usando o ClienteMapper para converter para DTO
        .toList(); // Convertendo para List
    }


    // Método para obter um cliente por ID
   public Optional<ClienteDTO> obterClientePorId(Long id){
    return clienteRepository.findById(id)
            .map(ClienteMapper::toDTO); // Converte Cliente para ClienteDTO, se presente
    }

    /*
    //Método para atualizar um cliente existente
    public Cliente atualizarCliente(Long id, Cliente cliente){
        Optional<Cliente> clienteExistente = clienteRepository.findById(id); // Busca o cliente existente
        if (clienteExistente.isPresent()){
            Cliente c = clienteExistente.get();
            c.setNome(cliente.getNome()); //Atualiza o nome
            c.setEmail(cliente.getEmail()); // Atualiza o email
            return clienteRepository.save(c); // Salva as alterações
        } else {
            return null; //Retorna null se o cliente nao for encontrado
        }
    }
    */

    public Optional<ClienteDTO> atualizarCliente(Long id, ClienteDTO clienteDTO) {
    return clienteRepository.findById(id).map(clienteExistente -> {
        clienteExistente.setNome(clienteDTO.getNome());
        if (!EmailValidator.validarEmail(clienteDTO.getEmail())){
            return null;
        }
        clienteExistente.setEmail(clienteDTO.getEmail());
        // Atualize outras propriedades conforme necessário
        Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
        return ClienteMapper.toDTO(clienteAtualizado);
    });
    }


    // Método para deletar um cliente
    public boolean deleteCliente(Long id) {
        Optional<Cliente> clienteExiste = clienteRepository.findById(id);
        if (clienteExiste.isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    } 

    
}
