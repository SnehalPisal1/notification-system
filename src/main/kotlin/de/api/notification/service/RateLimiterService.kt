package de.api.notification.service


import org.redisson.api.RRateLimiter
import org.redisson.api.RateIntervalUnit
import org.redisson.api.RateType
import org.redisson.api.RedissonClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

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
