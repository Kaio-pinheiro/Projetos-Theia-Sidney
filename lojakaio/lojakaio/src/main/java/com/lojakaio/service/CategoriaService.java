package com.lojakaio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lojakaio.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.lojakaio.entities.Categoria;
import com.lojakaio.repository.CategoriaRepository;

// A camada Service é responsável por implementar a lógica de negócio da aplicação.
// O Controller recebe as requisições, delega a lógica para o Service, que processa a regra de negócio
// e, em seguida, interage com o Repository para persistir ou buscar os dados necessários.

// Ela serve como intermediária entre os controllers e os repositórios, garantindo que as
// regras de negócio sejam aplicadas antes dos dados serem persistidos ou recuperados do banco.

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public ResponseEntity<CategoriaDTO> addCategoria(CategoriaDTO categoriaDTO) {
        // IMPLEMENTAÇÃO PARA CONVERTER DE categoria DTO EM categoria ENTITIES
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());

        Categoria categoriaSalvo = new Categoria();

        try {
            categoriaSalvo = categoriaRepository.save(categoria);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        categoriaDTO.setId(categoriaSalvo.getId());

        return ResponseEntity.ok(categoriaDTO);
    }

    public List<CategoriaDTO> getAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao()))
                .collect(Collectors.toList());
    }

    public Optional<CategoriaDTO> getCategoriaById(Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao()));
    }

    public boolean deleteCategoria(Long id) {
        Optional<Categoria> categoriaExiste = categoriaRepository.findById(id);
        if (categoriaExiste.isPresent()) {
            categoriaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<CategoriaDTO> updateCategoria(Long id, CategoriaDTO categoriaDTO) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNome(categoriaDTO.getNome());
            categoria.setDescricao(categoriaDTO.getDescricao());
            categoriaRepository.save(categoria);
            return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
        });
    }

    // Método para encontrar categorias por descrição
    public List<CategoriaDTO> getCategoriasByDescricao(String descricao) {
        List<Categoria> categorias = categoriaRepository.findByDescricao(descricao);
        return categorias.stream().map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNome(),
                categoria.getDescricao())).collect(Collectors.toList());
    }

    // Método para encontrar categorias por descrição usando LIKE
    public List<CategoriaDTO> getCategoriasByDescricaoContaining(String descricao) {
        List<Categoria> categorias = categoriaRepository.findByDescricaoContaining(descricao);
        return categorias.stream().map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNome(),
                categoria.getDescricao())).collect(Collectors.toList());
    }

    // Método para contar a quantidade de categorias por descrição
    public Long countCategoriasByDescricao(String descricao) {
        return categoriaRepository.countByDescricao(descricao);
    }

    // Método para encontrar todos as categorias, exceto os que possuem uma
    // descrição específica
    public List<CategoriaDTO> getCategoriasByDescricaoNot(String descricao) {
        List<Categoria> categorias = categoriaRepository.findByDescricaoNot(descricao);
        return categorias.stream().map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNome(),
                categoria.getDescricao())).collect(Collectors.toList());
    }

}
