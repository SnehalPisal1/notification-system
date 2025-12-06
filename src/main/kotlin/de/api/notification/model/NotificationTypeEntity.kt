package de.api.notification.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "notification_types")
data class NotificationTypeEntity(
    @Id
    @Column(name = "type", nullable = false, length = 255)
    val type: String = "",

    @field:NotNull
    @Column(name = "category", nullable = false, length = 255)
    val category: String = ""
)
