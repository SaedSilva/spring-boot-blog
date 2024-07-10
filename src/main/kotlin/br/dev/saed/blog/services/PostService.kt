package br.dev.saed.blog.services

import br.dev.saed.blog.entities.Post
import br.dev.saed.blog.repositories.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostService {

    @Autowired
    private lateinit var postRepository: PostRepository

    fun listAllPosts() : List<Post> {
        return postRepository.findAll()
    }

    fun insertPost(post: Post) {
        postRepository.save(post)
    }
}