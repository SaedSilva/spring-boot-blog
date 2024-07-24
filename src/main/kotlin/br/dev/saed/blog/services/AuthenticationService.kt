package br.dev.saed.blog.services

import br.dev.saed.blog.entities.User
import br.dev.saed.blog.repositories.UserRepository
import br.dev.saed.blog.services.exceptions.ExistsByEmailException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthenticationService {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun save(entity: User) {
        if(userRepository.existsByEmail(entity.email)) {
            throw ExistsByEmailException("Email already in use")
        }
        userRepository.save(entity)
    }
}