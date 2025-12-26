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
        val user = User(id = req.id, notifications = req.notifications.toMutableSet())
        // Validate each notification type in the array
        val invalidTypes = req.notifications.filter { !notificationTypeService.isValidType(it) }
        logger.info("invalidTypes: $invalidTypes")

        if (invalidTypes.isNotEmpty()) {
            throw UserRegistrationException("Invalid notification types: $invalidTypes")
        }

        val existingUser = userRepository.findById(user.id)
        if (existingUser.isPresent) {
            throw UserRegistrationException("User ${user.id} already registered.")
        }
        logger.info("Registering new user ${user.id} with notifications: ${user.notifications}")
        return userRepository.save(user)
    }


    /**
     * NOTE: The 'users' table stores types as a semicolon-separated string.
     * Ideally, it should be normalized (one row per user-type) to simplify adding new types and remove string parsing.
     * For now, the system handles new types dynamically based on categories so existing users
     * receive all notifications for all types in their subscribed categories.
     *
     * /notify endpoint
     * - checks rate limiter
     * - checks whether user is subscribed either explicitly to the type OR to any type in its category
     */

    fun sendNotification(notificationDto: NotificationDto) {

        val type = notificationDto.notificationType
        val userId = notificationDto.userId
        // Rate limiting
        val allowed = rateLimiterService.tryConsume(userId.toString())
        if (!allowed) {
            throw RateLimitException("Rate limit exceeded for user ${notificationDto.userId}")
// Load user from DB or not found user
            userRepository.findById(userId).orElseThrow { UserNotFoundException("User not found: $userId") }

// get cached subscriptions(i.e. notification types) as a Set<String>
            val subscriptions = subscriptionCacheService.getUserSubscriptions(userId)

        }

        }



