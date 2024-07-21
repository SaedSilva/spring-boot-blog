package br.dev.saed.blog.services

import br.dev.saed.blog.entities.Post
import br.dev.saed.blog.repositories.PostRepository
import br.dev.saed.blog.dto.post.PostDTO
import br.dev.saed.blog.services.exceptions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.time.LocalDateTime

@Service
class PostService {

    @Autowired
    private lateinit var repository: PostRepository

    @Transactional(readOnly = true)
    fun listPosts(pageable: Pageable): Page<PostDTO> {
        val pagePost = repository.findAll(pageable)
        val pagePostDTO = pagePost.map { PostDTO.fromEntity(it) }
        return pagePostDTO
    }

    @Transactional(readOnly = true)
    fun findPostById(id: String): PostDTO {
        try {
            return PostDTO.fromEntity(repository.findById(id).get())
        } catch (e: NoSuchElementException) {
            throw ResourceNotFoundException("Post not found")
        }
    }

    @Transactional
    fun insertPost(dto: PostDTO): PostDTO {
        var entity = Post(null, dto.title, dto.content, dto.author, dto.tags, LocalDateTime.now(), dto.comments)
        entity = repository.save(entity)
        return PostDTO.fromEntity(entity)
    }

    @Transactional
    fun updatePost(id: String, dto: PostDTO): PostDTO {
        try {
            val entity = repository.findById(id).get()
            entity.title = dto.title
            entity.content = dto.content
            entity.author = dto.author
            entity.tags = dto.tags
            entity.comments = dto.comments
            repository.save(entity)
            return PostDTO.fromEntity(entity)
        } catch (e: NoSuchElementException) {
            throw ResourceNotFoundException("Post not found")
        }
    }

    @Transactional
    fun deletePost(id: String) {
        if (!repository.existsById(id)) {
            throw ResourceNotFoundException("Post not found")
        }
        repository.deleteById(id)
    }

}