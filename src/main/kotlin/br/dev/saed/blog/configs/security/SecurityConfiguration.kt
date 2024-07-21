package br.dev.saed.blog.configs.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity // Habilita a segurança web
class SecurityConfiguration {

    // Configura o codificador de senha
    @Bean
    fun getPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


    // Configura o filtro de segurança
    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf { csrf -> csrf.disable() } // Desabilita o CSRF (Cross-Site Request Forgery)
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) } // Define a política de criação de sessão como STATELESS
            .authorizeHttpRequests { auth -> auth.anyRequest().permitAll() } // Permite todas as requisições
            .build()
    }

    // Configura o gerenciador de autenticação
    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager =
        authenticationConfiguration.authenticationManager

}