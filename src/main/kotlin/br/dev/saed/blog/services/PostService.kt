package br.dev.saed.blog.services

import br.dev.saed.blog.entities.Post
import br.dev.saed.blog.repositories.PostRepository
import br.dev.saed.blog.dto.PostDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PostService {

    @Autowired
    private lateinit var postRepository: PostRepository

    fun listPosts(pageable: Pageable): Page<PostDTO> {
        val pagePost = postRepository.findAll(pageable)
        val pagePostDTO = pagePost.map { PostDTO.fromEntity(it) }
        return pagePostDTO
    }

    fun findPostById(id: String): PostDTO {
        return PostDTO.fromEntity(postRepository.findById(id).get())
    }

    fun insertPost(request: PostDTO): PostDTO {
        val entity = Post(null, request.title, request.content, request.author, request.tags, request.date, request.comments)
        postRepository.save(entity)
        return PostDTO.fromEntity(entity)
    }
}