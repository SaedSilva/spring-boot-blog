package br.dev.saed.blog.controllers.handlers

import br.dev.saed.blog.dto.CustomError
import br.dev.saed.blog.services.exceptions.PostNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(PostNotFoundException::class)
    fun postNotFound(e: PostNotFoundException, request: HttpServletRequest) : ResponseEntity<CustomError> {
        val status = HttpStatus.NOT_FOUND
        val error = CustomError(Instant.now(), status.value(), e.message!!, request.requestURI)
        return ResponseEntity.status(status).body(error)
    }

}