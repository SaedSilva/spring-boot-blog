package br.dev.saed.blog.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
class Post(
    @Id
    var id: String?,
    var title: String,
    var content: String,
    var author: String,
    var tags: List<String>,
    var date: LocalDate,
    var comments: List<Comment>
)