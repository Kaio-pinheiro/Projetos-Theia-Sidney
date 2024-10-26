package com.securityLogin.securityLogin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class publicos {

    @GetMapping
    public String teste(){
        return "Passou no controller com acesso público!";
    }
}

