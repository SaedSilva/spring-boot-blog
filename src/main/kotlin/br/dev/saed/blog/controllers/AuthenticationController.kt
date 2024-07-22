package br.dev.saed.blog.controllers

import br.dev.saed.blog.dto.user.AuthenticationDTO
import br.dev.saed.blog.dto.user.RegisterDTO
import br.dev.saed.blog.repositories.UserRepository
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
    private lateinit var repository: UserRepository

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @PostMapping(value = ["/login"])
    fun login(@RequestBody @Valid data: AuthenticationDTO): ResponseEntity<Unit> {
        val emailPassword = UsernamePasswordAuthenticationToken(data.email, data.userPassword) // Cria um token de autenticação
        val auth = authenticationManager.authenticate(emailPassword)

        return ResponseEntity.ok().build()
    }

    @PostMapping(value = ["/register"])
    fun register(@RequestBody @Valid data: RegisterDTO): ResponseEntity<Unit> {
        if(repository.existsByEmail(data.email)) {
            return ResponseEntity.badRequest().build()
        }

        val encryptedPassword = BCryptPasswordEncoder().encode(data.userPassword)
        data.userPassword = encryptedPassword

        repository.save(RegisterDTO.toEntity(data))
        return ResponseEntity.ok().build()
    }
}