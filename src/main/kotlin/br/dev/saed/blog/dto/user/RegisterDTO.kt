package br.dev.saed.blog.dto.user

import br.dev.saed.blog.entities.Role
import br.dev.saed.blog.entities.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterDTO(
    @field:Email(message = "Email não válido", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    val email: String,
    @field:Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres")
    var userPassword: String,
    @field:NotBlank(message = "Campo obrigatório")
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
