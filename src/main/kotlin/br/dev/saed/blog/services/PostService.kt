package br.dev.saed.blog.services

import br.dev.saed.blog.entities.Post
import br.dev.saed.blog.repositories.PostRepository
import br.dev.saed.blog.requests.PostRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostService {

    @Autowired
    private lateinit var postRepository: PostRepository

    fun listAllPosts(): List<Post> {
        return postRepository.findAll()
    }

    fun insertPost(request: PostRequest): Post {
        val entity = Post(null, request.title, request.content, request.author, request.tags, request.date, request.comments)
        return postRepository.save(entity)
    }
}