package br.dev.saed.blog.dto.user

import br.dev.saed.blog.entities.Role

import br.dev.saed.blog.entities.User

data class RegisterDTO(
    val email: String,
    var userPassword: String,
    val name: String,
    val roles: MutableSet<Role> = mutableSetOf(Role("ROLE_USER"))
) {
    companion object {
        fun toEntity(dto: RegisterDTO): User {
            return User(
                email = dto.email,
                userPassword = dto.userPassword,
                name = dto.name,
                roles = dto.roles
            )
        }
    }
}
