package com.hamburgueria.controller;

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
import com.hamburgueria.dto.ClienteDTO;
import com.hamburgueria.service.ClienteService;

// Anotação que define esta classe como um controlador REST
@RestController
@RequestMapping("/cliente") //Define o caminho base para os endpoints deste controlador
public class ClienteController {

    // Injeção de dependência do serviço de clientes
    @Autowired
    private ClienteService clienteService;

    // CREATE
    @PostMapping
    public ResponseEntity <ClienteDTO> addCliente(@RequestBody ClienteDTO clienteDTO){
        return clienteService.addCliente(clienteDTO);
    }

    // Obter todos os clientes como lista de ClienteDTO
    //@GetMapping
    //public List<ClienteDTO> obterTodosClientes(){
    //}
    
    // READ
    @GetMapping
    public List<ClienteDTO> getClientes(){
        return clienteService.getClientes();

    }
   
    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id).map(clienteDTO -> ResponseEntity.ok(clienteDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
    Optional<ClienteDTO> atualizarCliente = clienteService.updateCliente(id, clienteDTO);
    return atualizarCliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long id) {
        boolean itemDeletado = clienteService.deleteCliente(id);
        if (itemDeletado) {
            return ResponseEntity.noContent().build(); // 204 exclusão com sucesso
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    // PUT
    /*
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Optional<ClienteDTO> atualizarCliente = clienteService.atualizarCliente(id, clienteDTO);
        return atualizarCliente(id, clienteDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    */

}
