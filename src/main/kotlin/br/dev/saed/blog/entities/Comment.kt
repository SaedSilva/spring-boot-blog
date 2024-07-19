package br.dev.saed.blog.entities

import java.time.LocalDate
import java.time.LocalDateTime


class Comment(
    var author: String,
    var content: String,
    var date: LocalDateTime
)
