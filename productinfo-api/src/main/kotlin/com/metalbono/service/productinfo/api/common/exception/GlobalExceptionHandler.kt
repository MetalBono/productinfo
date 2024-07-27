package com.metalbono.service.productinfo.api.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = ex.message ?: ""
            ),
            HttpStatus.INTERNAL_SERVER_ERROR,
        )
    }
}

data class ErrorResponse(
    val status: Int,
    val message: String
)