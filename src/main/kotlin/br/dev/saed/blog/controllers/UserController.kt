package br.dev.saed.blog.controllers

import br.dev.saed.blog.dto.UserDTO
import br.dev.saed.blog.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/users"])
class UserController {

    @Autowired
    private lateinit var service: UserService

    @PostMapping
    fun insertUser(@RequestBody dto: UserDTO): ResponseEntity<UserDTO> = ResponseEntity.ok().body(service.insertUser(dto))

    @DeleteMapping(value = ["/{id}"])
    fun deletePost(@PathVariable id: String): ResponseEntity<Unit> {
        service.deleteUser(id)
        return ResponseEntity.noContent().build()
    }
}