package com.carro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import com.carro.dto.ModeloDTO;
import com.carro.repository.ModeloRepository;
import com.carro.service.ModeloService;
import java.util.List;

@RestController
@RequestMapping("/modelo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ControllerModelo {

    @Autowired
    private ModeloService modeloService;

    public ControllerModelo(ModeloRepository modeloRepository){
        this.modeloService = modeloService;
    }

    @PostMapping
    public ResponseEntity<ModeloDTO> addModelo(@RequestBody ModeloDTO modelo){
        return modeloService.criarModelo(modelo);
    }

    @GetMapping
    public List<ModeloDTO> getFabricantes(){
        return modeloService.listarModelos();

    }
  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModeloById(@PathVariable Long id) {
        boolean itemDeletado = modeloService.deleteModelo(id);
        if (itemDeletado) {
            return ResponseEntity.noContent().build(); // 204 exclusão com sucesso
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModeloDTO> updateModelo(@PathVariable Long id,
            @RequestBody ModeloDTO modeloDTO) {
        Optional<ModeloDTO> updateModelo = modeloService.atualizarModelo(id, modeloDTO);
        return updateModelo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
}
