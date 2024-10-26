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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hamburgueria.dto.ProdutoDTO;
//import com.hamburgueria.entities.Produto;
import com.hamburgueria.repository.ProdutoRepository;
import com.hamburgueria.service.ProdutoService;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    // Injeção de depedencia
    @Autowired
    private ProdutoService produtoService;

    // REVER ESTE CONSTRUTOR O PARAMETRO DEVERIA SER ProdutoService OU O this.produtoService DEVERIA SER REPOSITORY??!! VER ISSO
    public ProdutoController(ProdutoRepository produtoRepository){
        this.produtoService = produtoService;
    }

    // CREATE
    // Passando um RequestBody que é a requisição no corpo da página, que é o JSON, toda vez que tem o RequestBody é o JSON que estamos passando
    @PostMapping
    public ResponseEntity<ProdutoDTO> addProduto(@RequestBody ProdutoDTO produto){ // Pasando como paramentro o produtoDTO e retornando um ResponseEntity ProdutoDTO
        // Usando o service para criar o produto
        return produtoService.addProduto(produto);
    }

    // READ
    @GetMapping
    public List<ProdutoDTO> getFornecedores(){
        return produtoService.getProdutos();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity <ProdutoDTO> getProdutoById(@PathVariable Long id){ // PathVariable: Toda vez que na URL agente bota /id ele coloca na variavel chamada Long id
        return produtoService.getProdutoById(id)
            .map(produtoDTO -> ResponseEntity.ok(produtoDTO)) //MAP: função que pega o conteúdo antes dele e faz o mapeamento desta informação
            .orElse(ResponseEntity.notFound().build()); // Quando ele for fazer isto **return produtoService.getProdutoById(id)** ele retornar um produto,
             // então ele vai pega este produto como paramentro e se ele achou o produto ele vai vir no ResponseEntity.ok e vai retornar este produto que ele achou
            // orElse: é o senão, senão encontrou vai retornar um responseEntity not found 404
    }

    // PUT
     @PutMapping("/{id}")
     public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO){
         Optional<ProdutoDTO> updateProduto = produtoService.updateProduto(id, produtoDTO);
         return updateProduto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
     }

     // DELETE BY ID
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteProdutoById(@PathVariable Long id){
         boolean itemDeletado = produtoService.deleteProduto(id);
         if (itemDeletado){
             return ResponseEntity.noContent().build(); // 204 exclusão com sucesso
         } else {
             return ResponseEntity.notFound().build(); // 404 não encontrado
         }
     }
 
    /*
    @GetMapping("/nacionalidade")
    public ResponseEntity<List<Produto>> getProdutoByNacionalidade(@RequestParam String nacionalidade){
        List<Produto> produto = produtoService.obterProdutosPorNacionalidadeDoFornecedor(nacionalidade);
        return ResponseEntity.ok(produto);
    }
    */
}
