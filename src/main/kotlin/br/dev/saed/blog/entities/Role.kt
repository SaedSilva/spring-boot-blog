package br.dev.saed.blog.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority

@Document
data class Role(
    @Id
    val id: String? = null,
    val role: String
) : GrantedAuthority {

    override fun getAuthority(): String {
        return role
    }
}
