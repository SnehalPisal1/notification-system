package de.notification.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class CodeChallengeApplication

fun main(args: Array<String>) {
	runApplication<CodeChallengeApplication>(*args)
}
