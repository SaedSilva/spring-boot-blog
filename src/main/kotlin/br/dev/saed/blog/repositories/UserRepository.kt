package br.dev.saed.blog.repositories

import br.dev.saed.blog.entities.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findByEmail(email: String): UserDetails

    fun existsByEmail(email: String): Boolean
}