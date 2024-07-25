package br.dev.saed.blog.repositories

import br.dev.saed.blog.entities.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : MongoRepository<Post, String> {

    fun searchPostsByAuthorId(id: String, pageable: Pageable): Page<Post>
}