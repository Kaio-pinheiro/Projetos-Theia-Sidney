package com.carro.service;

import com.carro.dto.FabricanteDTO;
import com.carro.entities.Fabricante;
import com.carro.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODA REGRA DE NEGOCIOTEM QUE ETSRA DENTRO DE SERBVICE O CONTROLELR SO CHAMA O ENDPOINT
@Service
public class FabricanteService {

    // Injeção de dependência do repositório de fabricantes
    @Autowired
    private FabricanteRepository fabricanteRepository;

    // Método setter para o repositório (geralmente não é necessário se você usar @Autowired)
    public void setFabricanteRepository(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    // Método para adicionar um novo fabricante
    public ResponseEntity<FabricanteDTO> addFabricante(FabricanteDTO fabricanteDTO) {

        // IMPLEMENTAÇÃO PARA CONVERTER DE FABRICANTE DTO EM FABRICANTE ENTITIES
        Fabricante fabricante = new Fabricante();  // Criando uma nova instância de Fabricante (entidade)
        fabricante.setNome(fabricanteDTO.getNome());// Definindo o nome do fabricante
        fabricante.setNacionalidade(fabricanteDTO.getNacionalidade());// Definindo a nacionalidade do fabricante

        Fabricante fabricanteSalvo = new Fabricante();

        try {
            // Salvando o fabricante no repositório e obtendo a entidade salva
            fabricanteSalvo = fabricanteRepository.save(fabricante);
        } catch (Exception e) {
            // Lançando uma exceção em caso de erro durante a operação de salvamento
            throw new RuntimeException(e);
        }

        // Fabricante fabricanteSalvo = fabricanteRepository.save(fabricante);
        // FabricanteDTO fabricanteDTOSalvo = new FabricanteDTO(fabricanteSalvo.getId(),
        // fabricanteSalvo.getNome(), fabricanteSalvo.getNacionalidade());
        fabricanteDTO.setId(fabricanteSalvo.getId());

        return ResponseEntity.ok(fabricanteDTO);
    }

    public List<FabricanteDTO> getAllFabricantes() {
        List<Fabricante> fabricantes = fabricanteRepository.findAll();
        // Converter a lista de Fabricante para FabricanteDTO
        return fabricantes.stream().map(fabricante -> new FabricanteDTO(fabricante.getId(), fabricante.getNome(),
                fabricante.getNacionalidade())).collect(Collectors.toList());

    }

    public Optional<FabricanteDTO> getFabricanteById(Long id) {
        return fabricanteRepository.findById(id).map(fabricante -> new FabricanteDTO(fabricante.getId(),
                fabricante.getNome(), fabricante.getNacionalidade()));
    }

    public boolean deleteFabricante(Long id) {
        Optional<Fabricante> fabricanteExiste = fabricanteRepository.findById(id);
        if (fabricanteExiste.isPresent()) {
            fabricanteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<FabricanteDTO> updateFabricante(Long id, FabricanteDTO fabricanteDTO) {
        return fabricanteRepository.findById(id).map(fabricante -> {
            fabricante.setNome(fabricanteDTO.getNome());
            fabricante.setNacionalidade(fabricanteDTO.getNacionalidade());
            fabricanteRepository.save(fabricante);
            return new FabricanteDTO(fabricante.getId(), fabricante.getNome(), fabricante.getNacionalidade());
        });
    }

    // Método para encontrar fabricantes por nacionalidade
    public List<FabricanteDTO> getFabricantesByNacionalidade(String nacionalidade) {
        List<Fabricante> fabricantes = fabricanteRepository.findByNacionalidade(nacionalidade);
        return fabricantes.stream().map(fabricante -> new FabricanteDTO(fabricante.getId(), fabricante.getNome(),
                fabricante.getNacionalidade())).collect(Collectors.toList());
    }

    // Método para encontrar fabricantes por nacionalidade usando LIKE
    public List<FabricanteDTO> getFabricantesByNacionalidadeContaining(String nacionalidade){
        List<Fabricante> fabricantes = fabricanteRepository.findByNacionalidadeContaining(nacionalidade);
        return fabricantes.stream().map(fabricante -> new FabricanteDTO(fabricante.getId(), fabricante.getNome(),
        fabricante.getNacionalidade())).collect(Collectors.toList());
    }

    //Método para contar a quantidade de fabricantes por nacionalidade
    public Long countFabricantesByNacionalidade(String nacionalidade){
        return fabricanteRepository.countByNacionalidade(nacionalidade);
    }

    //Método para encontrar todos os fabricantes, exceto os que possuem uma nacionalidade específica
    public List<FabricanteDTO> getFabricantesByNacionalidadeNot(String nacionalidade){
        List<Fabricante> fabricantes = fabricanteRepository.findByNacionalidadeNot(nacionalidade);
        return fabricantes.stream().map(fabricante -> new FabricanteDTO(fabricante.getId(), fabricante.getNome(),
        fabricante.getNacionalidade())).collect(Collectors.toList());
    }

}
