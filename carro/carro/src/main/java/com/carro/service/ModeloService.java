package com.carro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.carro.dto.FabricanteDTO;
import com.carro.dto.ModeloDTO;
import com.carro.entities.Fabricante;
import com.carro.entities.Modelo;
import com.carro.repository.FabricanteRepository;
import com.carro.repository.ModeloRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private FabricanteRepository fabricanteRepository;
    
    // Método para CRIAR MODELOS - CREATE
    public ResponseEntity<ModeloDTO> criarModelo(ModeloDTO modeloRecebido){
        Fabricante fabricante = fabricanteRepository.findById(modeloRecebido.getFabricante().getId()).orElseThrow(() -> new RuntimeException("Fabricante nao encontrado"));

        Modelo modelo = new Modelo();
        modelo.setFabricante(fabricante);
        modelo.setNome(modeloRecebido.getModelo());
        
        Modelo modeloSalvo = modeloRepository.save(modelo);

        FabricanteDTO fabricanteDTO = new FabricanteDTO(fabricante.getId(), fabricante.getNome(), fabricante.getNacionalidade());

        modeloRecebido.setFabricante(fabricanteDTO);
        modeloRecebido.setIdModelo(modeloSalvo.getId());

        return ResponseEntity.ok(modeloRecebido);

    }

    // Método para LISTAR TODOS OS MODELOS - READ
    public List<ModeloDTO> listarModelos() {
        List<Modelo> modelos = modeloRepository.findAll();
        // Converter a lista de Fabricante para FabricanteDTO
        return modelos.stream().map(modelo -> new ModeloDTO(modelo.getId(),modelo.getNome(),convertFabricanteDTO(modelo.getFabricante())))
                      .collect(Collectors.toList());

    }

   // public List<Modelo> obterModelosPorNacionalidadeDoFabricante(String nacionalidade){
   //     return modeloRepository.findModelosByFabricanteNacionalidade(nacionalidade);
   // }

    private FabricanteDTO convertFabricanteDTO(Fabricante fabricante){
        FabricanteDTO fabricanteDTO = new FabricanteDTO(fabricante.getId(), fabricante.getNome(), fabricante.getNacionalidade());
        return fabricanteDTO;
    }

    // Método para DELETAR OS MODELOS - DELETE
    public boolean deleteModelo(Long id) {
        Optional<Modelo> modeloExiste = modeloRepository.findById(id);
        if (modeloExiste.isPresent()) {
            modeloRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    } 

    // Método para EDITAR OS MODELOS - UPDATE
    public Optional<ModeloDTO> atualizarModelo(Long id, ModeloDTO modeloDTO){
        Optional<Modelo> modeloExistente = modeloRepository.findById(id); // Busca o cliente existente
        if (modeloExistente.isPresent()){
            Modelo modelo = modeloExistente.get();
            modelo.setNome(modeloDTO.getModelo()); //Atualiza o nome
            modelo.setFabricante(fabricanteRepository.findById(modeloDTO.getFabricante().getId())
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado")));
            Modelo modeloAtualizado = modeloRepository.save(modelo);
            FabricanteDTO fabricanteDTO = convertFabricanteDTO(modeloAtualizado.getFabricante());
            ModeloDTO modeloAtualizaDto = new ModeloDTO(modeloAtualizado.getId(), modeloAtualizado.getNome(),fabricanteDTO);
            return Optional.of(modeloAtualizaDto);
        } else {
            return Optional.empty(); //Retorna null se o cliente nao for encontrado
        } 
    }

    
}
