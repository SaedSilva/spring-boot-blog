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
    private lateinit var postService: PostService

    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    fun listPosts(pageable: Pageable): ResponseEntity<Page<PostDTO>> = ResponseEntity.ok(postService.listPosts(pageable))

    @GetMapping(value = ["/{id}"])
    fun findPostById(@PathVariable id: String): ResponseEntity<PostDTO> {
        return ResponseEntity.ok().body(postService.findPostById(id))
    }

    @PostMapping
    fun insertPost(@RequestBody postDTO: PostDTO): ResponseEntity<PostDTO> {
        userService.findUserById(postDTO.authorId).id
        return ResponseEntity.ok().body(postService.insertPost(postDTO))
    }

    @PutMapping(value = ["/{id}"])
    fun updatePost(@PathVariable id: String, @RequestBody dto: PostDTO): ResponseEntity<PostDTO> {
        return ResponseEntity.ok().body(postService.updatePost(id, dto))
    }

    @DeleteMapping(value = ["/{id}"])
    fun deletePost(@PathVariable id: String): ResponseEntity<Unit> {
        postService.deletePost(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping(value = ["/author/{id}"])
    fun findPostsByAuthorId(@PathVariable id: String, pageable: Pageable): ResponseEntity<Page<PostDTO>> {
        return ResponseEntity.ok(postService.findPostsByAuthorId(id, pageable))
    }
}