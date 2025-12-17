package de.api.notification.service

import de.api.notification.repository.NotificationTypeRepository
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class NotificationTypeService(private val repo: NotificationTypeRepository) {



}

