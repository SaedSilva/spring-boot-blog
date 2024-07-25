package br.dev.saed.blog.services

import br.dev.saed.blog.dto.post.PostDTO
import br.dev.saed.blog.dto.user.UserDTO
import br.dev.saed.blog.dto.user.UserMinDTO
import br.dev.saed.blog.entities.Post
import br.dev.saed.blog.repositories.PostRepository
import br.dev.saed.blog.repositories.UserRepository
import br.dev.saed.blog.services.exceptions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class PostService {

    @Autowired
    private lateinit var postRepository: PostRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Transactional(readOnly = true)
    fun listPosts(pageable: Pageable): Page<PostDTO> {
        val pagePost = postRepository.findAll(pageable)
        val pagePostDTO = pagePost.map { PostDTO.fromEntity(it) }
        return pagePostDTO
    }

    @Transactional(readOnly = true)
    fun findPostById(id: String): PostDTO {
        try {
            return PostDTO.fromEntity(postRepository.findById(id).get())
        } catch (e: NoSuchElementException) {
            throw ResourceNotFoundException("Post not found")
        }
    }

    @Transactional
    fun insertPost(dto: PostDTO): PostDTO {
        val autheticated = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.searchUserByEmail(autheticated).get()
        var entity = Post(null, dto.title, dto.content, user.name, user.id!!, dto.tags, LocalDateTime.now())
        entity = postRepository.save(entity)
        return PostDTO.fromEntity(entity)
    }

    @Transactional
    fun updatePost(id: String, dto: PostDTO): PostDTO {
        try {
            val entity = postRepository.findById(id).get()
            val autheticated = SecurityContextHolder.getContext().authentication.name
            val user = userRepository.searchUserByEmail(autheticated).get()
            entity.title = dto.title
            entity.content = dto.content
            entity.author = user.name
            entity.authorId = user.id!!
            entity.tags = dto.tags
            entity.date = LocalDateTime.now()
            postRepository.save(entity)
            return PostDTO.fromEntity(entity)
        } catch (e: NoSuchElementException) {
            throw ResourceNotFoundException("Post not found")
        }
    }

    @Transactional
    fun deletePost(id: String) {
        if (!postRepository.existsById(id)) {
            throw ResourceNotFoundException("Post not found")
        }
        val autheticated = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.searchUserByEmail(autheticated).get()
        if (postRepository.findById(id).get().authorId != user.id) {
            throw ResourceNotFoundException("You can only delete your own posts")
        }
        postRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    fun findPostsByAuthorId(id: String, pageable: Pageable): Page<PostDTO> {
        val pagePost = postRepository.findPostsByAuthorId(id, pageable)
        val pagePostDTO = pagePost.map { PostDTO.fromEntity(it) }
        return pagePostDTO
    }

    @Transactional(readOnly = true)
    fun findPostsUserLogged(pageable: Pageable): Page<PostDTO> {
        val pagePost = postRepository.findPostsByAuthorId(getMe().id!!, pageable)
        if(pagePost.isEmpty) {
            throw ResourceNotFoundException("No posts found")
        }
        val pagePostDTO = pagePost.map { PostDTO.fromEntity(it) }
        return pagePostDTO
    }

    @Transactional(readOnly = true)
    fun getMe(): UserDTO {
        val email = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.searchUserByEmail(email)
        return UserDTO.fromEntity(user.get())
    }
}
