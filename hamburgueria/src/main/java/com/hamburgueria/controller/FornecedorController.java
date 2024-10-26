package com.hamburgueria.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hamburgueria.dto.FornecedorDTO;
//import com.hamburgueria.entities.Fornecedor;
import com.hamburgueria.service.FornecedorService;

@RestController
@RequestMapping("/fornecedor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    // CREATE
    @PostMapping
    public ResponseEntity <FornecedorDTO> addFornecedor(@RequestBody FornecedorDTO fornecedorDTO){ // Recebe um DTO, passa um DTO e retorna um DTO
        return fornecedorService.addFornecedor(fornecedorDTO); 
    }
    
    // READ
    @GetMapping
    public List<FornecedorDTO> lgetFornecedores(){
        return fornecedorService.getFornecedores();
    }
    
    // ESSE EXEMPLO RETORNA NULL QUANDO NÃO LOCALIZA O ID
    // READ BY ID
    /*@GetMapping("/{id}")
    public Optional <Fornecedor> getFornecedorById(@PathVariable Long id){ // PathVariable: Toda vez que na URL agente bota /id ele coloca na variavel chamada Long id
        return fornecedorService.getFornecedorById(id);
    }*/

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity <FornecedorDTO> getFornecedorById(@PathVariable Long id){ // PathVariable: Toda vez que na URL agente bota /id ele coloca na variavel chamada Long id
        return fornecedorService.getFornecedorById(id)
            .map(fornecedorDTO -> ResponseEntity.ok(fornecedorDTO)) //MAP: função que pega o conteúdo antes dele e faz o mapeamento desta informação
            .orElse(ResponseEntity.notFound().build()); // Quando ele for fazer isto **return fornecedorService.getFornecedorById(id)** ele retornar um fornecedor,
             // então ele vai pega este fornecedor como paramentro e se ele achou o fornecedor ele vai vir no ResponseEntity.ok e vai retornar este fornecedor que ele achou
            // orElse: é o senão, senão encontrou vai retornar um responseEntity not found 404
    }

      // PUT
    @PutMapping("/{id}")
    public ResponseEntity<FornecedorDTO> updateFornecedor(@PathVariable Long id, @RequestBody FornecedorDTO fornecedorDTO){
        Optional<FornecedorDTO> updateFornecedor = fornecedorService.updateFornecedor(id, fornecedorDTO);
        return updateFornecedor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFornecedorById(@PathVariable Long id){
        boolean itemDeletado = fornecedorService.deleteFornecedor(id);
        if (itemDeletado){
            return ResponseEntity.noContent().build(); // 204 exclusão com sucesso
        } else {
            return ResponseEntity.notFound().build(); // 404 não encontrado
        }
    }

    // Método para buscar fornecedores por nacionalidade
    // http://localhost:8080/fornecedor/nacionalidade?nacionalidade=brasil
    @GetMapping("/nacionalidade")
    public ResponseEntity<List<FornecedorDTO>> getFornecedoresByNacionalidade(@RequestParam String nacionalidade){
        List<FornecedorDTO> fornecedores = fornecedorService.findFornecedoresByNacionalidade(nacionalidade);
        return ResponseEntity.ok(fornecedores);
    }

    // Método para buscar fornecedores que contém a letra tal ou conjunto de letras
    // http://localhost:8080/fornecedor/nacionalidadeContem?nacionalidade=bra
    @GetMapping("/nacionalidadeContem")
    public ResponseEntity<List<FornecedorDTO>> getNacionalidadeContaining(@RequestParam String nacionalidade){
        List<FornecedorDTO> fornecedores = fornecedorService.findByNacionalidadeContaining(nacionalidade);
        return ResponseEntity.ok(fornecedores);
    }

    // Método para buscar fornecedores exceto o tal
    // http://localhost:8080/fornecedor/nacionalidadeExceto?nacionalidade=uruguai
    @GetMapping("/nacionalidadeExceto")
    public ResponseEntity<List<FornecedorDTO>> findByNacionalidadeNot(@RequestParam String nacionalidade){
        List<FornecedorDTO> fornecedores = fornecedorService.findByNacionalidadeNot(nacionalidade);
        return ResponseEntity.ok(fornecedores);
    }

     // Método para contar a quantidade de fornecedores por nacionalidade
     // http://localhost:8080/fornecedor/nacionalidadeQuantidade?nacionalidade=brasil usando @RequestParam
     // http://localhost:8080/fornecedor/nacionalidade/quantidade/uruguai usando @PathVariable
     @GetMapping("/nacionalidade/quantidade/{nacionalidade}")
     public ResponseEntity<Long> countByNacionalidade(@PathVariable String nacionalidade){
         Long count = fornecedorService.countByNacionalidade(nacionalidade);
         return ResponseEntity.ok(count);
     }

}
