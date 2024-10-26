package com.hamburgueria.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.hamburgueria.dto.ClienteDTO;
import com.hamburgueria.entities.Cliente;
import com.hamburgueria.repository.ClienteRepository;
import com.hamburgueria.util.ClienteMapper;
import com.hamburgueria.util.ValidaCpf;
import com.hamburgueria.util.ValidaEmail;
import com.hamburgueria.util.ValidaSexo;

@Service
public class ClienteService {

    // Injeção de dependência do repositorio de Clientes
    @Autowired
    private ClienteRepository clienteRepository;

    // Setter para injetar o rpeositório manualmente em testes
    public void setClienteRepository(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    // CREATE
    public ResponseEntity <ClienteDTO> addCliente(ClienteDTO clienteDTO){
        // Verificar se o email tem arroba @
        if(!ValidaEmail.validaCaracterArroba(clienteDTO.getEmail())){
           return ResponseEntity.status(422).build();
        }
        // Verifica se o cpf tem 11 digitos
        if(!ValidaCpf.validarCpf(clienteDTO.getCpf())){
            return ResponseEntity.status(422).build();
        }
        // Verifica se o cliente colocou o sexo certo
        if(!ValidaSexo.validarSexo(clienteDTO.getSexo())){
            return ResponseEntity.status(422).build();
        }
        // Criar entidade Cliente
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        ClienteDTO clienteSalvoDTO = ClienteMapper.toDTO(clienteRepository.save(cliente));
        return ResponseEntity.ok(clienteSalvoDTO); // Retorna o DTO atualizado
    }

    // READ
    public List<ClienteDTO> getClientes(){
        List<Cliente> clientes = clienteRepository.findAll(); // Retorna todos os clientes do banco de dados
        return clientes.stream() // Usando stream para converter cada Cliente para ClienteDTO
        .map(ClienteMapper::toDTO) // Usando o ClienteMapper para converter para DTO
        .toList(); // Convertendo para List

    }

    // READ BY ID
   public Optional<ClienteDTO> getClienteById(Long id){
    return clienteRepository.findById(id)
            .map(ClienteMapper::toDTO); // Converte Cliente para ClienteDTO, se presente
    }

    // PUT
    public Optional<ClienteDTO> updateCliente(Long id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id).map(clienteExistente -> {
            clienteExistente.setNome(clienteDTO.getNome());
            clienteExistente.setSexo(clienteDTO.getSexo());
            clienteExistente.setCpf(clienteDTO.getCpf());
            if (!ValidaEmail.validarEmail(clienteDTO.getEmail())){
                return null;
            }
            clienteExistente.setEmail(clienteDTO.getEmail());
            // Atualize outras propriedades conforme necessário
            Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
            return ClienteMapper.toDTO(clienteAtualizado);
        });
        }
    
    // DELETE
    public boolean deleteCliente(Long id) {
        Optional<Cliente> clienteExiste = clienteRepository.findById(id);
        if (clienteExiste.isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    } 
    
    /*
    // READ BY ID
    public Optional<Cliente> obterClientePorId(Long id, Cliente cliente){
        Optional<Cliente> clienteExistente = clienteRepository.findById(id); // Busca o cliente existente
        if (clienteExistente.isPresent()){
            Cliente c = clienteExistente.get();
            c.setNome(cliente.getNome());  //Atualiza o nome
            c.setSexo(cliente.getSexo());  //Atualiza o sexo
            c.setCpf(cliente.getCpf());    //Atualiza o cpf
            c.setEmail(cliente.getEmail());//Atualiza o email
            return clienteRepository.save(c); //Salva as alterações
        } else {
            return null; // Retorna null se o cliente não for encontrado
        }
    }
    */
}
