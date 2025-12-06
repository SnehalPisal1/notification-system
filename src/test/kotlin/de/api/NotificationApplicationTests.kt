package de.api

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest
class NotificationApplicationTests {

	companion object {
		@Container
		val postgres = PostgreSQLContainer("postgres:15")

		@Container
		val redis = GenericContainer("redis:7-alpine").apply {
			withExposedPorts(6379)
		}


		@JvmStatic
		@DynamicPropertySource
		@Suppress("unused", "UsePropertyAccessSyntax")
		fun registerPgProperties(registry: org.springframework.test.context.DynamicPropertyRegistry) {
			registry.add("spring.datasource.url", postgres::getJdbcUrl)
			registry.add("spring.datasource.username", postgres::getUsername)
			registry.add("spring.datasource.password", postgres::getPassword)

			// Redis
			registry.add("spring.data.redis.host") { redis.host }
			registry.add("spring.data.redis.port") { redis.getMappedPort(6379) }
		}
	}

	init {
	    postgres.start()
	}

	@Test
	fun contextLoads() {
	}

}
