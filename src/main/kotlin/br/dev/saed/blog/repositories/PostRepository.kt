package br.dev.saed.blog.repositories

import br.dev.saed.blog.entities.Post
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : MongoRepository<Post, String> {

}