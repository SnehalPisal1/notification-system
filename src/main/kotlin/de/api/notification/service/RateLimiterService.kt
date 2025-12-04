package de.api.notification.service


import org.springframework.stereotype.Service

@Service
class RateLimiterService(
    private val redissonClient: RedissonClient,

    @Value("\${ratelimit.capacity}") private val capacity: Long,
    @Value("\${ratelimit.refill-duration-seconds}") private val refillSeconds: Long
    ) {
}
