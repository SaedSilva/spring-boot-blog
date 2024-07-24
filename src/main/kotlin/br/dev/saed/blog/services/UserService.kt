package br.dev.saed.blog.services

import br.dev.saed.blog.dto.user.UserDTO
import br.dev.saed.blog.entities.User
import br.dev.saed.blog.repositories.UserRepository
import br.dev.saed.blog.services.exceptions.ExistsByEmailException
import br.dev.saed.blog.services.exceptions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService {

    @Autowired
    private lateinit var repository: UserRepository

    @Transactional(readOnly = true)
    fun listUsersPage(pageable: Pageable): Page<UserDTO> {
        val pageUser = repository.findAll(pageable)
        val pageUserDTO = pageUser.map { UserDTO.fromEntity(it) }
        return pageUserDTO
    }

    @Transactional
    fun insertUser(dto: UserDTO): UserDTO {
        if(repository.existsByEmail(dto.email)) {
            throw ExistsByEmailException("Email already in use")
        }
        var entity = User(null, dto.name, dto.email, dto.userPassword, dto.roles)
        entity = repository.save(entity)
        return UserDTO.fromEntity(entity)
    }

    @Transactional
    fun deleteUser(id: String) {
        if (!repository.existsById(id)) {
            throw ResourceNotFoundException("User not found")
        }
        repository.deleteById(id)
    }

}