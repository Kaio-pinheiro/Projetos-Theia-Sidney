package com.lojakaio.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lojakaio.dto.ProdutoDTO;
import com.lojakaio.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ControllerProduto {

    @Autowired
    private ProdutoService produtoService;

    // CREATE - Adicionar novo produto
    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.criarProduto(produtoDTO);
    }

    // READ - Listar todos os produtos
    @GetMapping
    public List<ProdutoDTO> listarProdutos() {
        return produtoService.listarProdutos();
    }

    // READ - Buscar produto por ID
    // @GetMapping("/{id}")
    // public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable Long id) {
    // Optional<ProdutoDTO> produtoDTO = produtoService.buscarProdutoPorId(id);
    // return produtoDTO.map(ResponseEntity::ok).orElseGet(() ->
    // ResponseEntity.notFound().build());
    // }

    // UPDATE - Atualizar produto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        Optional<ProdutoDTO> produtoAtualizado = produtoService.atualizarProduto(id, produtoDTO);
        return produtoAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE - Remover produto pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        boolean deletado = produtoService.deleteProduto(id);
        if (deletado) {
            return ResponseEntity.noContent().build(); // Sucesso: No Content
        } else {
            return ResponseEntity.notFound().build(); // Produto não encontrado
        }
    }
}