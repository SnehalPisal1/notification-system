package de.api.notification.controller

import de.api.notification.model.NotificationDto
import de.api.notification.model.RegisterRequest
import de.api.notification.service.NotificationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid

@RestController
class NotificationController(private val notificationService: NotificationService) {

}
