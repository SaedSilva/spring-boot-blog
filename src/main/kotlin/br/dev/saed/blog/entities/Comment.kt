package br.dev.saed.blog.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Comment(
    @Id
    var id: String? = null,
    var postId: String,
    var author: String,
    var content: String,
    var date: LocalDateTime
)
