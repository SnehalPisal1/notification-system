package de.api.notification.kafka

import de.api.notification.model.NotificationDto
import de.api.notification.service.NotificationService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class NotificationSubscriber(private val notificationService: NotificationService) {


}