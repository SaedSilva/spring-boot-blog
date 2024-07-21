package br.dev.saed.blog.dto.errors

import java.time.Instant

open class CustomError(
    val timestamp: Instant,
    val status: Int,
    val error: String,
    val path: String
)