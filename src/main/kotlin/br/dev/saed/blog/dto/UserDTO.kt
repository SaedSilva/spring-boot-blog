package br.dev.saed.blog.dto

import br.dev.saed.blog.entities.Role
import br.dev.saed.blog.entities.User

class UserDTO(
    var id: String? = null,
    var name: String,
    var email: String,
    var userPassword: String,
    var roles: MutableSet<Role> = mutableSetOf()

) {
    companion object {
        fun fromEntity(entity: User): UserDTO {
            return UserDTO(
                entity.id,
                entity.name,
                entity.email,
                entity.password,
                entity.roles
            )
        }
    }
}