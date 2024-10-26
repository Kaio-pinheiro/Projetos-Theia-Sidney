package com.carro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controllerStatus")
public class ControllerStatusAplication {


    @RequestMapping("/ok")
    public String statusOK(){
        return "OK";
    }
}
