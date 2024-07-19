package br.dev.saed.blog.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Post(
    @Id
    val id: String? = null,
    var title: String,
    var content: String,
    var author: String,
    var tags: List<String>,
    var date: LocalDateTime,
    var comments: List<Comment>
)

/*
JSON DE TESTE:

{


*/