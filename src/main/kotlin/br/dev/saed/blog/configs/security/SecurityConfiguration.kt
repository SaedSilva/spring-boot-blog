package br.dev.saed.blog.configs.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity // Habilita a segurança web
class SecurityConfiguration {

    @Autowired
    private lateinit var securityFilter: SecurityFilter

    // Configura o codificador de senha
    @Bean
    fun getPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


    // Configura o filtro de segurança
    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf { csrf -> csrf.disable() } // Desabilita o CSRF (Cross-Site Request Forgery)
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) } // Define a política de criação de sessão como STATELESS
            .authorizeHttpRequests { auth -> auth
                .requestMatchers( "/auth/**").permitAll()

                .requestMatchers( "/api/users/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/posts/").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/posts/").authenticated()

                .anyRequest().permitAll()
                }
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    // Configura o gerenciador de autenticação
    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager =
        authenticationConfiguration.authenticationManager

}