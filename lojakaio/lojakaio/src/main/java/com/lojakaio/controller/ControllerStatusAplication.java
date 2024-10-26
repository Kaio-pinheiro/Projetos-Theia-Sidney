package com.lojakaio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Controler é a ponte entre a interface e o banco de dados
// O Controller é a camada responsável por lidar com as requisições HTTP,
// processar os dados recebidos, e determinar as respostas apropriadas. 
// Ele atua como uma ponte entre a interface do usuário (UI) e a camada de serviço.

@RestController
@RequestMapping("/controllerStatus")
public class ControllerStatusAplication {
    
    @RequestMapping("/ok")
    public String statusOK(){
        return "Teste para saber se está funcionando!";
    }
}
