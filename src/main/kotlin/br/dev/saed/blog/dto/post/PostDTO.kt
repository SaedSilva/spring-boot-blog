package br.dev.saed.blog.dto.post

import br.dev.saed.blog.entities.Post
import java.time.LocalDateTime

data class PostDTO(
    var title: String,
    var content: String,
    var author: String? = null,
    var authorId: String? = null,
    var tags: List<String>,
    var date: LocalDateTime? = null,
) {
    companion object {
        fun fromEntity(entity: Post): PostDTO {
            return PostDTO(
                entity.title,
                entity.content,
                entity.author,
                entity.authorId,
                entity.tags,
                entity.date
            )
        }
    }
}