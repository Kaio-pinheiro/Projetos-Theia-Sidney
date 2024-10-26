package com.carro.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carro.dto.ClienteDTO;
import com.carro.service.ClienteService;

//Anotação que define esta classe como um controlador REST
@RestController
@RequestMapping("/cliente") //Define o caminho base para os endpoints deste controlador
public class ControllerCliente {

    // Injeção de dependencia do serviço de clientes
    @Autowired
    private ClienteService clienteService;

    //Criar um novo cliente usando ClienteDTO
    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO clienteDTO){
        return clienteService.criarCliente(clienteDTO);
    }

    @GetMapping
    public List<ClienteDTO> getNome(){
        return clienteService.obterTodosClientes();

    }
   
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obterClientesById(@PathVariable Long id) {
        return clienteService.obterClientePorId(id).map(clienteDTO -> ResponseEntity.ok(clienteDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long id) {
        boolean itemDeletado = clienteService.deleteCliente(id);
        if (itemDeletado) {
            return ResponseEntity.noContent().build(); // 204 exclusão com sucesso
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }
    /*
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Optional<ClienteDTO> atualizarCliente = clienteService.atualizarCliente(id, clienteDTO);
        return atualizarCliente(id, clienteDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    */

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
    Optional<ClienteDTO> atualizarCliente = clienteService.atualizarCliente(id, clienteDTO);
    return atualizarCliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
}



}
