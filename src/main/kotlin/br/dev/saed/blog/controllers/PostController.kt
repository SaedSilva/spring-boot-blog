package br.dev.saed.blog.controllers

import br.dev.saed.blog.entities.Post
import br.dev.saed.blog.requests.PostRequest
import br.dev.saed.blog.services.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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
    fun listAllPosts(): ResponseEntity<List<Post>> = ResponseEntity.ok(postService.listAllPosts())

    @PostMapping
    fun insertPost(@RequestBody request: PostRequest): ResponseEntity<Post> = ResponseEntity.ok().body(postService.insertPost(request))

}