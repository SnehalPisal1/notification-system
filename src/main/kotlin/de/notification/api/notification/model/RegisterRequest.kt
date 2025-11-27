package de.notification.api.notification.model

import java.util.UUID

data class RegisterRequest(
    val id: UUID,
    val notifications: Set<String> = emptySet()
)
