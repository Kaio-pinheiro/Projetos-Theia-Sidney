package com.carro.dto;

public class ModeloDTO {
    private Long idModelo;
    private String modelo;
    private FabricanteDTO fabricante; //MUDAR PRA DTO SE DER ALGUM PROBLEMA UM DIA
    
    public ModeloDTO(Long idModelo, String modelo, FabricanteDTO fabricante) {
        
        this.idModelo = idModelo;
        this.modelo = modelo;
        this.fabricante = fabricante;
    }

    public Long getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Long idModelo) {
        this.idModelo = idModelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public FabricanteDTO getFabricante() {
        return fabricante;
    }

    public void setFabricante(FabricanteDTO fabricante) {
        this.fabricante = fabricante;
    }

  
    
    
   
    

}
