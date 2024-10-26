package com.securityLogin.securityLogin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class securityConfig {
 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            //.csrf(csrf -> csrf.disable()) // Desabilita CSRF temporariamente
            .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // Configura o repostório de token CSRF

            .authorizeHttpRequests(authorize -> authorize.requestMatchers("/public/**","/css/**", "/js/**", "/images/**","login", "home").permitAll() // Permite acesso à página de login
            .anyRequest().authenticated()) // Demais páginas necessitam de autenticação
                                                                            
            .formLogin(formLogin -> formLogin.loginPage("/login")// Especifica a página de login
            .defaultSuccessUrl("/login/home", true).permitAll())// Redireciona para /home após login bem-sucedido

            .rememberMe(Customizer.withDefaults());
        return http.build();

    }
    
}




