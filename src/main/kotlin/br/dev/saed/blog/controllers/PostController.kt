package br.dev.saed.blog.controllers

import br.dev.saed.blog.dto.PostDTO
import br.dev.saed.blog.services.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/posts"])
class PostController {

    @Autowired
    private lateinit var postService: PostService

    @GetMapping
    fun listPosts(pageable: Pageable): ResponseEntity<Page<PostDTO>> = ResponseEntity.ok(postService.listPosts(pageable))

    @GetMapping(value = ["/{id}"])
    fun findPostById(@PathVariable(value = "id") id: String): ResponseEntity<PostDTO> {
        return ResponseEntity.ok().body(postService.findPostById(id))
    }

    @PostMapping
    fun insertPost(@RequestBody postDTO: PostDTO): ResponseEntity<PostDTO> = ResponseEntity.ok().body(postService.insertPost(postDTO))

}