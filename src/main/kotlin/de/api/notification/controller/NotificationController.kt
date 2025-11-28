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
    @PostMapping("/register")
    fun register(@RequestBody @Valid req: RegisterRequest): ResponseEntity<String> {
        val saved = notificationService.registerUser(req)
        return ResponseEntity.ok("User Registered - $saved")
    }

    @PostMapping("/notify")
    fun sendNotification(@RequestBody notificationDto: NotificationDto): ResponseEntity<Map<String, String>> {
        notificationService.sendNotification(notificationDto)
        return ResponseEntity.ok(mapOf("status" to "Notification processed"))
    }
}
