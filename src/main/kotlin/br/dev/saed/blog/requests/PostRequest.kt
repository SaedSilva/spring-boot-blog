package br.dev.saed.blog.requests

import br.dev.saed.blog.entities.Comment
import java.time.LocalDate

class PostRequest(
    var title: String,
    var content: String,
    var author: String,
    var tags: List<String>,
    var date: LocalDate,
    var comments: List<Comment>
)