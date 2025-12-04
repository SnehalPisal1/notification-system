package de.api.notification.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ReteLimiterConfig (
    @Value("\${spring.data.redis.host}") private val host: String,
    @Value("\${spring.data.redis.port}") private val port: Int,
    @Value("\${spring.data.redis.password:}") private val password: String
    ) {
    @Bean
    fun redissonClient(): RedissonClient {
        val config = Config()
        val server = config.useSingleServer()
        server.address = "redis://$host:$port"
        if (password.isNotBlank()) server.password = password
        return Redisson.create(config)
    }
}
