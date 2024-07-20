package br.dev.saed.blog.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Document
data class User(
    @Id
    val id: String? = null,
    val name: String,
    val email: String,
    val userPassword: String,
    val roles: MutableSet<Role> = mutableSetOf()

) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles
    }

    override fun getPassword(): String {
        return userPassword
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}

/*
JSON DE TESTE:
{
    "email": "",
    "name": "",
    "userPassword": "",
    "roles": [ROLE_USER, ROLE_ADMIN]
}
 */
