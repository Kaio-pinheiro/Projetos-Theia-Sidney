package com.lojakaio.service;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.lojakaio.dto.ProdutoDTO;
import com.lojakaio.entities.Categoria;
import com.lojakaio.entities.Produto;
import com.lojakaio.repository.CategoriaRepository;
import com.lojakaio.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // CREATE
    public ResponseEntity<ProdutoDTO> criarProduto(ProdutoDTO produtoRecebido) {
        if (produtoRecebido.getIdCategoria() == null) {
            throw new IllegalArgumentException("idCategoria não pode ser nulo.");
        }

        Categoria categoria = categoriaRepository.findById(produtoRecebido.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Produto produto = new Produto();
        produto.setCategoria(categoria);
        produto.setNome(produtoRecebido.getNome());

        Produto produtoSalvo = produtoRepository.save(produto);
        produtoRecebido.setId(produtoSalvo.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRecebido);
    }

    // READ
    public List<ProdutoDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(produto -> new ProdutoDTO(produto.getId(), produto.getNome(), produto.getCategoria().getId()))
                       .collect(Collectors.toList());
    }

    // DELETE
    public boolean deleteProduto(Long id) {
        Optional<Produto> produtoExiste = produtoRepository.findById(id);
        if (produtoExiste.isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    } 

    // UPDATE
    public Optional<ProdutoDTO> atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setNome(produtoDTO.getNome());
            produto.setCategoria(categoriaRepository.findById(produtoDTO.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada")));
            Produto produtoAtualizado = produtoRepository.save(produto);
            return Optional.of(new ProdutoDTO(produtoAtualizado.getId(), produtoAtualizado.getNome(), produtoAtualizado.getCategoria().getId()));
        }
        return Optional.empty();
    }
}





/*package com.lojakaio.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lojakaio.dto.ProdutoDTO;
import com.lojakaio.dto.CategoriaDTO;
import com.lojakaio.entities.Categoria;
import com.lojakaio.entities.Produto;
import com.lojakaio.repository.CategoriaRepository;
import com.lojakaio.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    /*
    // CREATE
    public ResponseEntity<ProdutoDTO> criarProduto(ProdutoDTO produtoRecebido) {
        // Buscar a categoria pelo ID informado no DTO
        Categoria categoria = categoriaRepository.findById(produtoRecebido.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Produto produto = new Produto();
        produto.setCategoria(categoria);
        produto.setNome(produtoRecebido.getNome()); 

        Produto produtoSalvo = produtoRepository.save(produto);

        // Atualizando o DTO com informações da categoria
        CategoriaDTO categoriaDTO = new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
        produtoRecebido.setCategoria(categoriaDTO);
        produtoRecebido.setId(produtoSalvo.getId());

        return ResponseEntity.ok(produtoRecebido);
    }
    
    public ResponseEntity<ProdutoDTO> criarProduto(ProdutoDTO produtoRecebido) {
        // Buscar a categoria pelo ID informado no DTO
        if (produtoRecebido.getIdCategoria() == null) {
            return ResponseEntity.badRequest().body(null); // Retorna um erro se idCategoria for nulo
        }
    
        Categoria categoria = categoriaRepository.findById(produtoRecebido.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    
        Produto produto = new Produto();
        produto.setCategoria(categoria);
        produto.setNome(produtoRecebido.getNome());
    
        Produto produtoSalvo = produtoRepository.save(produto);
    
        // Atualizando o DTO com o ID do produto salvo
        produtoRecebido.setId(produtoSalvo.getId());
    
        return ResponseEntity.ok(produtoRecebido);
    }



    //  READ
    public List<ProdutoDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(produto -> new ProdutoDTO(produto.getId(),produto.getNome(),convertCategoriaDTO(produto.getCategoria())))
                      .collect(Collectors.toList());
    }
    
    private CategoriaDTO convertCategoriaDTO(Categoria categoria){
        CategoriaDTO categoriaDTO = new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
        return categoriaDTO;
    }

    //  DELETE
    public boolean deleteProduto(Long id) {
        Optional<Produto> produtoExiste = produtoRepository.findById(id);
        if (produtoExiste.isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    } 

    // UPDATE
    public Optional<ProdutoDTO> atualizarProduto(Long id, ProdutoDTO produtoDTO){
        Optional<Produto> produtoExistente = produtoRepository.findById(id); // Busca o produto existente
        if (produtoExistente.isPresent()){
            Produto produto = produtoExistente.get();
            produto.setNome(produtoDTO.getNome()); // Atualiza o nome
            produto.setCategoria(categoriaRepository.findById(produtoDTO.getIdCategoria()).orElseThrow(() -> new RuntimeException("Categoria não encontrada")));
            Produto produtoAtualizado = produtoRepository.save(produto);
            CategoriaDTO categoriaDTO = convertCategoriaDTO(produtoAtualizado.getCategoria());
            ProdutoDTO produtoAtualizadoDto = new ProdutoDTO(produtoAtualizado.getId(), produtoAtualizado.getNome(), categoriaDTO);
            return Optional.of(produtoAtualizadoDto);
        } else {
            return Optional.empty(); // Retorna Optional vazio se o produto não for encontrado
        } 
    }

}
*/

