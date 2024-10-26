package com.hamburgueria.util;

public class ValidaSexo {
    public static boolean validarSexo(String sexo){
        
        if(!"Masculino".equals(sexo) && !"Feminino".equals(sexo) && !"masculino".equals(sexo) && !"feminino".equals(sexo)){
            return false;
        }
        return true;
    }
}


