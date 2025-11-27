package de.api.notification.repository

import de.api.notification.model.NotificationTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationTypeRepository : JpaRepository<NotificationTypeEntity, String> {
    fun findAllByCategory(category: String): List<NotificationTypeEntity>
}
