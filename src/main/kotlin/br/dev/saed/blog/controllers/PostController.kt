package br.dev.saed.blog.controllers

import br.dev.saed.blog.dto.post.PostDTO
import br.dev.saed.blog.services.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/posts"])
class PostController {

    @Autowired
    private lateinit var service: PostService

    @GetMapping
    fun listPosts(pageable: Pageable): ResponseEntity<Page<PostDTO>> = ResponseEntity.ok(service.listPosts(pageable))

    @GetMapping(value = ["/{id}"])
    fun findPostById(@PathVariable(value = "id") id: String): ResponseEntity<PostDTO> {
        return ResponseEntity.ok().body(service.findPostById(id))
    }

    @PostMapping
    fun insertPost(@RequestBody postDTO: PostDTO): ResponseEntity<PostDTO> = ResponseEntity.ok().body(service.insertPost(postDTO))

    @PutMapping(value = ["/{id}"])
    fun updatePost(@PathVariable id: String, @RequestBody dto: PostDTO) : ResponseEntity<PostDTO> {
        return ResponseEntity.ok().body(service.updatePost(id, dto))
    }

    @DeleteMapping(value = ["/{id}"])
    fun deletePost(@PathVariable id: String): ResponseEntity<Unit> {
        service.deletePost(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    fun deleteAllPosts(): ResponseEntity<Unit> {
        service.deleteAllPosts()
        return ResponseEntity.noContent().build()
    }
}