package de.api.notification.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFound(ex: UserNotFoundException) =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))

    @ExceptionHandler(UserNotSubscribedException::class)
    fun handleNotSubscribed(ex: UserNotSubscribedException) =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to ex.message))


    @ExceptionHandler(UserRegistrationException::class)
    fun handleRegistrationError(ex: UserRegistrationException) =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("error" to ex.message))

    @ExceptionHandler(RateLimitException::class)
    fun handleRateLimit(ex: RateLimitException) =
        ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(mapOf("error" to ex.message))

}

