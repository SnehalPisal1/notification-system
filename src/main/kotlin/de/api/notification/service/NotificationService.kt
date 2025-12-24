package de.api.notification.service

import de.api.notification.exception.RateLimitException
import de.api.notification.exception.UserNotFoundException
import de.api.notification.exception.UserNotSubscribedException
import de.api.notification.exception.UserRegistrationException
import de.api.notification.model.NotificationDto
import de.api.notification.model.RegisterRequest
import de.api.notification.model.User
import de.api.notification.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val userRepository: UserRepository,
    private val notificationTypeService: NotificationTypeService,
    private val rateLimiterService: RateLimiterService,
    private val subscriptionCacheService: UserSubscriptionCacheService
    ) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    /**
     * /register endpoint
     * - checks invalid notification types
     * - checks user registering first time
     * - save data in table
     */

    fun registerUser(req: RegisterRequest): User {
    }

    fun sendNotification(notificationDto: NotificationDto) {
    }
}



