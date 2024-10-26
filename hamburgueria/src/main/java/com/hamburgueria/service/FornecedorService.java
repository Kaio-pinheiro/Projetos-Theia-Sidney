package com.hamburgueria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.hamburgueria.dto.FornecedorDTO;
import com.hamburgueria.entities.Fornecedor;
import com.hamburgueria.repository.FornecedorRepository;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    // CONSTRUTOR
    public void setFornecedorRepository(FornecedorRepository fornecedorRepository){
        this.fornecedorRepository = fornecedorRepository;
    }
    
    // CREATE retornando um ResponseEntity
    public ResponseEntity <FornecedorDTO> addFornecedor(FornecedorDTO fornecedorDTO){ // Recebe o DTO
        //PARA CONVERTER DE DTO PARA ENTITY
        Fornecedor fornecedor = new Fornecedor(); // Cria o objeto de entidade
        fornecedor.setNome(fornecedorDTO.getNome()); // Poem os dados do DTO dentro da ENTIDADE
        fornecedor.setNacionalidade(fornecedorDTO.getNacionalidade());

        // Cria um objeto dos dados gravados
        Fornecedor fornecedorSalvo = new Fornecedor();

        try {
            fornecedorSalvo = fornecedorRepository.save(fornecedor); // Grava a ENTIDADE
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Pega os dados gravados cria um novo DTO 
        // FornecedorDTO fornecedorDTOSalvo = new FornecedorDTO(fornecedorSalvo.getId(),fornecedorSalvo.getNome(),fornecedorSalvo.getNacionalidade());
        // Se fosse usar este, o return seria return fornecedorDTOSalvo;

        // Ou se for retornar o mesmo objeto recebido
        fornecedorDTO.setId(fornecedorSalvo.getId());

        return ResponseEntity.ok(fornecedorDTO); // Retorna pra tela um DTO
    }

    // READ retornando uma Lista
    public List<FornecedorDTO> getFornecedores(){
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        //Converter a lista de fornecedor para FornecedorDTO
        return fornecedores.stream().map(fornecedor -> new FornecedorDTO(fornecedor.getId(), fornecedor.getNome(), fornecedor.getNacionalidade())).collect(Collectors.toList());
    }

    // READ BY ID  retornando um Optional
    // ESSE EXEMPLO RETORNA NULL QUANDO NÃO LOCALIZA O ID
    // OPTIONAL: Verifica se vai retornar um dado ou não, por exemplo se vier NULL, False, Vazio, ou se não existe,
    // ai não precisa fazer o tratamento de exceções, ele ja faz essa parte e retorna o objeto vazio
    public Optional <FornecedorDTO> getFornecedorById(Long id){
        return fornecedorRepository.findById(id).map(fornecedor -> new FornecedorDTO(fornecedor.getId(), fornecedor.getNome(), fornecedor.getNacionalidade()));
    }

    // UPDATE 
    public Optional<FornecedorDTO> updateFornecedor(Long id, FornecedorDTO fornecedorDTO){
        return fornecedorRepository.findById(id).map(fornecedor -> {
            fornecedor.setNome(fornecedorDTO.getNome());
            fornecedor.setNacionalidade(fornecedorDTO.getNacionalidade());
            fornecedorRepository.save(fornecedor);
            return new FornecedorDTO(fornecedor.getId(), fornecedor.getNome(), fornecedor.getNacionalidade());
        });
    }

     // DELETE retornando um boolean
    public boolean deleteFornecedor(Long id){
        Optional <Fornecedor> fornecedorExiste = fornecedorRepository.findById(id); // Verificando se o fornecedor existe, se sim coloca dentro do objeto Fornecedor
        if (fornecedorExiste.isPresent()){
            fornecedorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Método para encontrar fornecedores por nacionalidade - where
    public List<FornecedorDTO> findFornecedoresByNacionalidade(String nacionalidade){
        List<Fornecedor> fornecedores = fornecedorRepository.findByNacionalidade(nacionalidade);
        //Converter a lista de fornecedores para FornecedoresDTO
        return fornecedores.stream().map(fornecedor -> new FornecedorDTO(fornecedor.getId(), fornecedor.getNome(), fornecedor.getNacionalidade())).collect(Collectors.toList());
    }

    // Método para encontrar fornecedores que contém a letra ou conjunto de letras tal
    public List<FornecedorDTO> findByNacionalidadeContaining(String nacionalidade){
        List<Fornecedor> fornecedores = fornecedorRepository.findByNacionalidadeContaining(nacionalidade);
        //Converter a lista de fornecedores para FornecedoresDTO
        return fornecedores.stream().map(fornecedor -> new FornecedorDTO(fornecedor.getId(), fornecedor.getNome(), fornecedor.getNacionalidade())).collect(Collectors.toList());
    }
    
    // Método para encotrar fornecedores exceto tal
    public List<FornecedorDTO> findByNacionalidadeNot(String nacionalidade){
        List<Fornecedor> fornecedores = fornecedorRepository.findByNacionalidadeNot(nacionalidade);
        return fornecedores.stream().map(fornecedor -> new FornecedorDTO(fornecedor.getId(), fornecedor.getNome(), fornecedor.getNacionalidade())).collect(Collectors.toList());
    }

    // Método para contar a quantidade de fornecedores por nacionalidade
    public Long countByNacionalidade(String nacionalidade){
        return fornecedorRepository.countByNacionalidade(nacionalidade);
    }
}

// save Para salvar no repository
// findAll() Para listar tudo
// findById() Para listar por id
// deleteById() Para deletar por id
