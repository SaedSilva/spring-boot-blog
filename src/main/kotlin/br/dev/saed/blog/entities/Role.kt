package br.dev.saed.blog.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority

data class Role(
    val role: String
) : GrantedAuthority {

    override fun getAuthority(): String {
        return role
    }
}
