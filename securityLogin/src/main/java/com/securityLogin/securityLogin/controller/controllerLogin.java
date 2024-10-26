package com.securityLogin.securityLogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class controllerLogin {

    @GetMapping
    public String showLoginPage() {
        return "login";
    }

    // ABRE A TELA PRINCIPAL APÓS LOGIN
    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

}
