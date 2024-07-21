package br.dev.saed.blog.dto.post

import br.dev.saed.blog.entities.Comment
import br.dev.saed.blog.entities.Post
import java.time.LocalDateTime

data class PostDTO(
    var id: String? = null,
    var title: String,
    var content: String,
    var author: String,
    var tags: List<String>,
    var date: LocalDateTime? = null,
    var comments: List<Comment>
) {
    companion object {
        fun fromEntity(entity: Post): PostDTO {
            return PostDTO(
                entity.id,
                entity.title,
                entity.content,
                entity.author,
                entity.tags,
                entity.date,
                entity.comments
            )
        }
    }
}