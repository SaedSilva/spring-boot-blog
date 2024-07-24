package br.dev.saed.blog.controllers

import br.dev.saed.blog.dto.user.UserDTO
import br.dev.saed.blog.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/users"])
class UserController {

    @Autowired
    private lateinit var service: UserService

    @GetMapping
    fun listUsersPage(pageable: Pageable): ResponseEntity<Page<UserDTO>> = ResponseEntity.ok(service.listUsersPage(pageable))

    @PostMapping
    fun insertUser(@RequestBody dto: UserDTO): ResponseEntity<UserDTO> = ResponseEntity.ok().body(service.insertUser(dto))

    @DeleteMapping(value = ["/{id}"])
    fun deletePost(@PathVariable id: String): ResponseEntity<Unit> {
        service.deleteUser(id)
        return ResponseEntity.noContent().build()
    }
}