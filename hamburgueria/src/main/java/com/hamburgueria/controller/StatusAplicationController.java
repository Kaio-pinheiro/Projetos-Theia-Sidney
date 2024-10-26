package com.hamburgueria.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statusController")
public class StatusAplicationController {

    @RequestMapping("/ok")
    public String statusOk(){
        return "HELLO WORLD! TESTE DE CONEXÃO OK!";
    }
    
}
