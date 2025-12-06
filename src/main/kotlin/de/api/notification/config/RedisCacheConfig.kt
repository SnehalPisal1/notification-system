package de.api.notification.config

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.beans.factory.annotation.Value
import java.time.Duration

@Configuration
@EnableCaching
class RedisCacheConfig(
    @Value("\${cache.ttl-minutes:10}") private val ttlMinutes: Long,
    @Value("\${cache.key-prefix:user}") private val keyPrefix: String
) {
    @Bean
    fun cacheManager(connectionFactory: RedisConnectionFactory): CacheManager {
        val cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(ttlMinutes))
            .prefixCacheNameWith("$keyPrefix:")
            .disableCachingNullValues()
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer()
                )
            )

        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(cacheConfig)
            .build()
    }
}
