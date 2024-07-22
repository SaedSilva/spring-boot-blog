package br.dev.saed.blog.configs.security

import br.dev.saed.blog.entities.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService {

    @Value("\${api.security.token.secret}")
    private lateinit var secret: String

    fun generateToken(user: User): String {
        try {
            val algorithm = Algorithm.HMAC256(secret)
            val token = JWT
                .create()
                .withIssuer("auth-api")
                .withSubject(user.email)
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm)
            return token
        } catch (e: JWTCreationException) {
            throw RuntimeException("Error while generate token: $e")
        }
    }

    fun validateToken(token: String): String {
        try {
            val algorithm = Algorithm.HMAC256(secret)
            return JWT
                .require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .subject
        } catch (e: JWTVerificationException) {
            return ""
        }

    }

    private fun generateExpirationDate(): Instant {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
    }
}