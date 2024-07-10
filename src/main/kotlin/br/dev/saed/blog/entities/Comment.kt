package br.dev.saed.blog.entities

import java.time.LocalDate


class Comment(
    var author: String,
    var content: String,
    var date: LocalDate
)
