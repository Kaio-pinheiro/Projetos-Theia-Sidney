package com.hamburgueria.util;

public class ValidaCpf {
    public static boolean validarCpf(Long cpf){

        // Verifica se o cpf é nulo
        if(cpf == null){
            return false;
        } 

        // Converte o CPF para String
        String cpfString = String.valueOf(cpf);
        
        // Verifica se o CPF tem 11 dígitos
        return cpfString.length() == 11;

    }
}
