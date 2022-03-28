package com.kotlin.desafiotinnova.controllers.exceptions

import com.kotlin.desafiotinnova.services.exceptions.DatabaseException
import com.kotlin.desafiotinnova.services.exceptions.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun entityNotFound(e: ResourceNotFoundException, request: HttpServletRequest): ResponseEntity<StandardError> {
       val erro =
           StandardError(timestamp = LocalDateTime.now(),
               status = HttpStatus.NOT_FOUND.value(),
                message= e.message.toString(),
               path = request.requestURI)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro)
    }

    @ExceptionHandler(DatabaseException::class)
    fun entityNotFound(e: DatabaseException, request: HttpServletRequest): ResponseEntity<StandardError> {
        val erro =
            StandardError(timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                message= e.message.toString(),
                path = request.requestURI)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro)
    }
}