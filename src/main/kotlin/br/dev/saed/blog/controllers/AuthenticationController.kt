package br.dev.saed.blog.controllers

import br.dev.saed.blog.configs.security.TokenService
import br.dev.saed.blog.dto.user.AuthenticationDTO
import br.dev.saed.blog.dto.user.LoginResponseDTO
import br.dev.saed.blog.dto.user.RegisterDTO
import br.dev.saed.blog.entities.User
import br.dev.saed.blog.services.AuthenticationService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/auth"])
class AuthenticationController {

    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    private lateinit var authenticationService: AuthenticationService

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @PostMapping(value = ["/login"])
    fun login(@Valid @RequestBody dto: AuthenticationDTO): ResponseEntity<Any> {
        val emailPassword = UsernamePasswordAuthenticationToken(dto.email, dto.userPassword) // Cria um token de autenticação
        val auth = authenticationManager.authenticate(emailPassword)
        val token = tokenService.generateToken(auth.principal as User)
        return ResponseEntity.ok(LoginResponseDTO(token))
    }

    @PostMapping(value = ["/register"])
    fun register(@Valid @RequestBody dto: RegisterDTO): ResponseEntity<Any> {

        val encryptedPassword = BCryptPasswordEncoder().encode(dto.userPassword)
        dto.userPassword = encryptedPassword

        authenticationService.save(RegisterDTO.toEntity(dto))
        return ResponseEntity.ok().build()
    }
}