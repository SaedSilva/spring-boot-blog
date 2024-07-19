package br.dev.saed.blog.services

import br.dev.saed.blog.entities.Post
import br.dev.saed.blog.repositories.PostRepository
import br.dev.saed.blog.dto.PostDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService {

    @Autowired
    private lateinit var postRepository: PostRepository

    @Transactional(readOnly = true)
    fun listPosts(pageable: Pageable): Page<PostDTO> {
        val pagePost = postRepository.findAll(pageable)
        val pagePostDTO = pagePost.map { PostDTO.fromEntity(it) }
        return pagePostDTO
    }

    @Transactional(readOnly = true)
    fun findPostById(id: String): PostDTO {
        return PostDTO.fromEntity(postRepository.findById(id).get())
    }

    @Transactional
    fun insertPost(dto: PostDTO): PostDTO {
        val entity = Post(null, dto.title, dto.content, dto.author, dto.tags, dto.date, dto.comments)
        postRepository.save(entity)
        return PostDTO.fromEntity(entity)
    }

    @Transactional
    fun updatePost(id: String, dto: PostDTO): PostDTO {
        val entity = postRepository.findById(id).get()
        entity.title = dto.title
        entity.content = dto.content
        entity.author = dto.author
        entity.tags = dto.tags
        entity.date = dto.date
        entity.comments = dto.comments
        postRepository.save(entity)
        return PostDTO.fromEntity(entity)
    }

    @Transactional
    fun deletePost(id: String) {
        postRepository.deleteById(id)
    }

}