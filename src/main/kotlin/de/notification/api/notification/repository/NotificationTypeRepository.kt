package de.notification.api.notification.repository

import de.notification.api.notification.model.NotificationTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationTypeRepository : JpaRepository<NotificationTypeEntity, String> {
    fun findAllByCategory(category: String): List<NotificationTypeEntity>
}
