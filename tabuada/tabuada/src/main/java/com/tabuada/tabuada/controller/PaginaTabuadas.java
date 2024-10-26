package com.tabuada.tabuada.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaginaTabuadas {
    
    @GetMapping("/tabuada")
    public String abrirTabuada(@RequestParam(name = "numero", required = false, defaultValue = "0") int numero, Model model){
        
        List<String> tabuada = new ArrayList<>();

        // Calcula a tabuada para o número digitado
        for(int i = 1; i <= 10; i++){
            int resultado = i * numero;
            // Adiciona a linha da tabuada na lista
            tabuada.add(numero + " x " + i + " = " + resultado);
        }

        model.addAttribute("tabuada", tabuada);
        return "tabuada";
    }


    
}
