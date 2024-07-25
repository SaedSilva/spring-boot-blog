package br.dev.saed.blog.dto.user

import br.dev.saed.blog.entities.User

data class UserMinDTO(
    var name: String,
    var email: String
) {
    companion object {
        fun fromEntity(entity: User): UserMinDTO {
            return UserMinDTO(
                entity.name,
                entity.email
            )
        }
    }
}