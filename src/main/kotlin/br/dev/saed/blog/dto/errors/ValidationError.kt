package br.dev.saed.blog.dto.errors

import java.time.Instant

class ValidationError(
    timestamp: Instant,
    status: Int,
    error: String,
    path: String
) : CustomError(timestamp, status, error, path) {
    private val errors: MutableList<FieldMessage> = mutableListOf()

    fun addError(fieldName: String, message: String) {
        errors.add(FieldMessage(fieldName, message))
    }

    fun getErrors(): List<FieldMessage> {
        return errors
    }
}