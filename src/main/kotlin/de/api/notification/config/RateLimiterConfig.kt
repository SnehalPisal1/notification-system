package de.api.notification.config

import org.springframework.context.annotation.Configuration

@Configuration
class ReteLimiterConfig (
    @Value("\${spring.data.redis.host}") private val host: String,
    @Value("\${spring.data.redis.port}") private val port: Int,
    @Value("\${spring.data.redis.password:}") private val password: String
    )
}
