package br.dev.saed.blog.controllers

import br.dev.saed.blog.dto.post.PostDTO
import br.dev.saed.blog.services.PostService
import br.dev.saed.blog.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/posts"])
class PostController {

    @Autowired
    private lateinit var service: PostService

    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    fun listPosts(pageable: Pageable): ResponseEntity<Page<PostDTO>> = ResponseEntity.ok(service.listPosts(pageable))

    @GetMapping(value = ["/{id}"])
    fun findPostById(@PathVariable id: String): ResponseEntity<PostDTO> {
        return ResponseEntity.ok().body(service.findPostById(id))
    }

    @PostMapping
    fun insertPost(@RequestBody postDTO: PostDTO): ResponseEntity<PostDTO> {
        val authorId = userService.findUserById(postDTO.authorId).id
        return ResponseEntity.ok().body(service.insertPost(postDTO))
    }

    @PutMapping(value = ["/{id}"])
    fun updatePost(@PathVariable id: String, @RequestBody dto: PostDTO): ResponseEntity<PostDTO> {
        return ResponseEntity.ok().body(service.updatePost(id, dto))
    }

    @DeleteMapping(value = ["/{id}"])
    fun deletePost(@PathVariable id: String): ResponseEntity<Unit> {
        service.deletePost(id)
        return ResponseEntity.noContent().build()
    }
}