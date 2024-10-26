package com.hamburgueria.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaEmail {
    
    public static boolean validarEmail(String email){

        if (email == null || email.isEmpty()){
         return false;
        }
 
         String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
     
         Pattern pattern = Pattern.compile(regex);
     
         Matcher matcher = pattern.matcher(email);
     
         return matcher.matches();
     }

    public static boolean validaCaracterArroba(String input){
        return input != null && input.contains("@");
    }
}
