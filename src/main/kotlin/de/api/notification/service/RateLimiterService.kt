package de.api.notification.service


import org.springframework.stereotype.Service

@Service
class RateLimiterService(
    private val redissonClient: RedissonClient,

    @Value("\${ratelimit.capacity}") private val capacity: Long,
    @Value("\${ratelimit.refill-duration-seconds}") private val refillSeconds: Long
    ) {

    fun tryConsume(userId: String): Boolean {
        val limiter: RRateLimiter = redissonClient.getRateLimiter("user:$userId")

        if (!limiter.isExists) {
            limiter.trySetRate(
                RateType.OVERALL,
                capacity,
                refillSeconds.toLong(),
                RateIntervalUnit.SECONDS
            )
        }

        return limiter.tryAcquire(1, 0, TimeUnit.SECONDS)
    }
}
