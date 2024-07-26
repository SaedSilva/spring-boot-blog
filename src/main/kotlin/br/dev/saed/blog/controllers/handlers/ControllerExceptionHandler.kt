package br.dev.saed.blog.controllers.handlers

import br.dev.saed.blog.dto.errors.CustomError
import br.dev.saed.blog.services.exceptions.ExistsByEmailException
import br.dev.saed.blog.services.exceptions.IllegalDeleteException
import br.dev.saed.blog.services.exceptions.InvalidTokenException
import br.dev.saed.blog.services.exceptions.ResourceNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun postNotFound(e: ResourceNotFoundException, request: HttpServletRequest) : ResponseEntity<CustomError> {
        val status = HttpStatus.NOT_FOUND
        val error = CustomError(Instant.now(), status.value(), e.message!!, request.requestURI)
        return ResponseEntity.status(status).body(error)
    }

    @ExceptionHandler(ExistsByEmailException::class)
    fun emailAlreadyExists(e: ExistsByEmailException, request: HttpServletRequest) : ResponseEntity<CustomError> {
        val status = HttpStatus.BAD_REQUEST
        val error = CustomError(Instant.now(), status.value(), e.message!!, request.requestURI)
        return ResponseEntity.status(status).body(error)
    }

    @ExceptionHandler(IllegalDeleteException::class)
    fun illegalDelete(e: IllegalDeleteException, request: HttpServletRequest) : ResponseEntity<CustomError> {
        val status = HttpStatus.UNAUTHORIZED
        val error = CustomError(Instant.now(), status.value(), e.message!!, request.requestURI)
        return ResponseEntity.status(status).body(error)
    }

    @ExceptionHandler(InvalidTokenException::class)
    fun invalidToken(e: InvalidTokenException, request: HttpServletRequest) : ResponseEntity<CustomError> {
        val status = HttpStatus.UNAUTHORIZED
        val error = CustomError(Instant.now(), status.value(), e.message!!, request.requestURI)
        return ResponseEntity.status(status).body(error)
    }

}