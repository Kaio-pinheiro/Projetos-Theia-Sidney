package com.aula1.ola.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Paginas {

    @GetMapping("/pagina3")
    public String abrirPagina3(){
        return "pagina3";
    }

    @GetMapping("/pagina4")
    public String abrirPagina4(Model model){
        String meuTexto = "Você está na classe de controller na página 4";
        model.addAttribute("mensagem", meuTexto);
        return "pagina4";
    }

    @GetMapping("/pagina5")
    public String pagina5(@RequestParam(name = "nome", required = false, defaultValue = "Mundo") String nome, Model model){
       String meuTexto = "Olá " + nome + "!";
       model.addAttribute("mensagem", meuTexto);
        return "pagina4";
    }

    // varios modos de fazer a tabuada
    // @GetMapping("/tabuada")
    // public String tabuada(@RequestParam(name = "nome", required = true, defaultValue = "1")int numero, Model model){
    //    // String resultado = "";
    //    StringBuilder tabuada = new StringBuilder();
    //     for(int i = 1; i <= 10; i++){
    //     tabuada.append(numero).append(str: " x ").append(i).append(str: " = ").append(numero*i).append(str: "<br />");
    //        //   resultado += numero + " X " + i + " = " + numero * i;
    //     }
    //     model.addAttribute("resultado", tabuada.toString());
    //     return "tabuada";
    // }

   // @GetMapping("/tabuadaREST")
   // public String tabuadaREST(@RequestParam(name = "nome", required = true, defaultValue = "1")int numero){
    //    String resultado = "";
    //    for(int i = 1; i <= 10; i++){
    //        resultado += numero + " X " + i + " = " + numero * i;
    //    }
   //     return resultado;
  //s  }
}
