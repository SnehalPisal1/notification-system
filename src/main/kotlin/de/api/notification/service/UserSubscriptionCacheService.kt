package de.api.notification.service

import de.api.notification.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserSubscriptionCacheService(
    private val userRepository: UserRepository
) {

    @Cacheable(cacheNames = ["userSubscriptions"], key = "#userId")
    fun getUserSubscriptions(userId: UUID): Set<String> {

    }
}
