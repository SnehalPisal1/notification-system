package de.api.notification.model

import java.util.UUID

data class NotificationDto(
    val userId: UUID,
    val notificationType: String,
    val message: String,
)