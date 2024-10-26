package com.lojakaio.controller;

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

import com.lojakaio.dto.CategoriaDTO;
import com.lojakaio.service.CategoriaService;

//Controler é a ponte entre a interface e o banco de dados
// O Controller é a camada responsável por lidar com as requisições HTTP,
// processar os dados recebidos, e determinar as respostas apropriadas. 
// Ele atua como uma ponte entre a interface do usuário (UI) e a camada de serviço.

@RestController
@RequestMapping("/categoria")
public class ControllerCategoria {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaDTO> addCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.addCategoria(categoriaDTO);
    }

    @GetMapping
    public List<CategoriaDTO> getCategorias() {
        return categoriaService.getAllCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id) {
        return categoriaService.getCategoriaById(id).map(categoriaDTO -> ResponseEntity.ok(categoriaDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoriaById(@PathVariable Long id) {
        boolean itemDeletado = categoriaService.deleteCategoria(id);
        if (itemDeletado) {
            return ResponseEntity.noContent().build(); // 204 exclusão com sucesso
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Long id,
            @RequestBody CategoriaDTO categoriaDTO) {
        Optional<CategoriaDTO> updateCategoria = categoriaService.updateCategoria(id, categoriaDTO);
        return updateCategoria.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Método para encontrar categorias por descricao, equivale ao where do sql
    @GetMapping("/descricao/{descricao}")
    public List<CategoriaDTO> getCategoriasByDescricao(@PathVariable String descricao) {
        return categoriaService.getCategoriasByDescricao(descricao);
    }

    // Método para encontrar categorias por descricao usando LIKE
    @GetMapping("/descricao/contendo/{descricao}")
    public List<CategoriaDTO> getCategoriasByDescricaoContaining(@PathVariable String descricao) {
        return categoriaService.getCategoriasByDescricaoContaining(descricao);
    }

    // Método para contar a quantidade de categorias por descricao
    @GetMapping("/descricao/quantidade/{descricao}")
    public ResponseEntity<Long> countCategoriasByDescricao(@PathVariable String descricao) {
        Long count = categoriaService.countCategoriasByDescricao(descricao);
        return ResponseEntity.ok(count);
    }

    // Método para encontrar todos as categorias, exceto os que possuem uma
    // descrição específica
    @GetMapping("/descricao/exceto/{descricao}")
    public List<CategoriaDTO> getCategoriasByDescricaoNot(@PathVariable String descricao) {
        return categoriaService.getCategoriasByDescricaoNot(descricao);
    }
}
