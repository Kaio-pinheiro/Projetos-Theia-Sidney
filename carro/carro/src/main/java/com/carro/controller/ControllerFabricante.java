package com.carro.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.carro.dto.FabricanteDTO;
import com.carro.service.FabricanteService;

@RestController
@RequestMapping("/fabricante")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ControllerFabricante {

    @Autowired
    private FabricanteService fabricanteService;

    @PostMapping
    public ResponseEntity<FabricanteDTO> addFabricante(@RequestBody FabricanteDTO fabricanteDTO) {
        return fabricanteService.addFabricante(fabricanteDTO);
    }

    @GetMapping
    public List<FabricanteDTO> getFabricantes() {
        return fabricanteService.getAllFabricantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FabricanteDTO> getFabricanteById(@PathVariable Long id) {
        return fabricanteService.getFabricanteById(id).map(fabricanteDTO -> ResponseEntity.ok(fabricanteDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFabricanteById(@PathVariable Long id) {
        boolean itemDeletado = fabricanteService.deleteFabricante(id);
        if (itemDeletado) {
            return ResponseEntity.noContent().build(); // 204 exclusão com sucesso
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FabricanteDTO> updateFabricante(@PathVariable Long id,
            @RequestBody FabricanteDTO fabricanteDTO) {
        Optional<FabricanteDTO> updateFabricante = fabricanteService.updateFabricante(id, fabricanteDTO);
        return updateFabricante.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Método para encontrar fabricantes por nacionalidade, equivale ao where do sql
    @GetMapping("/nacionalidade/{nacionalidade}")
    public List<FabricanteDTO> getFabricantesByNacionalidade(@PathVariable String nacionalidade) {
     return fabricanteService.getFabricantesByNacionalidade(nacionalidade);
    }

    //Método para encontrar fabricantes por nacionalidade usando LIKE
    @GetMapping("/nacionalidade/contendo/{nacionalidade}")
    public List<FabricanteDTO> getFabricantesByNacionalidadeContaining(@PathVariable String nacionalidade){
        return fabricanteService.getFabricantesByNacionalidadeContaining(nacionalidade);
    }

    //Método para contar a quantidade de fabricantes por nacionalidade
    @GetMapping("/nacionalidade/quantidade/{nacionalidade}")
    public ResponseEntity<Long> countFabricantesByNacionalidade(@PathVariable String nacionalidade){
        Long count = fabricanteService.countFabricantesByNacionalidade(nacionalidade);
        return ResponseEntity.ok(count);
    }
    
    //Método para encontrar todos os fabricantes, exceto os que possuem uma nacionalidade específica
    @GetMapping("/nacionalidade/exceto/{nacionalidade}")
    public List<FabricanteDTO> getFabricantesByNacionalidadeNot(@PathVariable String nacionalidade){
        return fabricanteService.getFabricantesByNacionalidadeNot(nacionalidade);
    }




    /*
    @GetMapping("/nacionalidade")
    public ResponseEntity<List<Fabricante>> getFabricantesByNacionalidade(@RequestParam String nacionalidade) {
    List<Fabricante> fabricantes = fabricanteService.findFabricantesByNacionalidade(nacionalidade);
    return ResponseEntity.ok(fabricantes);
    }*/
}

// Esse exemplo retorn NULL quando nao localida o id
/*
 * @GetMapping("/{id}")
 * public Optional<Fabricante> getFabricanteById(@PathVariable Long id) {
 * return fabricanteService.getFabricanteById(id);
 * }
 */
