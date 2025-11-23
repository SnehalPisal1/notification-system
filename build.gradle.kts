plugins {
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("jvm") version "2.2.0"
	kotlin("plugin.spring") version "2.2.0"
	kotlin("plugin.jpa") version "2.2.0"

	id("com.autonomousapps.dependency-analysis") version "2.19.0"
}

group = "de.notification.api"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Core Kotlin dependencies
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Spring Boot dependencies
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// JSON serialization/deserialization
	runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

	// Database dependencies
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("org.liquibase:liquibase-core")

	// Jakarta Persistence API
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

	// Testing dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")
	testRuntimeOnly("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.springframework.kafka:spring-kafka-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Caching and Redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-cache")

	implementation("org.redisson:redisson-spring-boot-starter:3.27.2")

}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

// Configure bootJar explicitly
tasks.bootJar {
	archiveFileName.set("app.jar")
	enabled = true
	mainClass.set("de.dkb.api.codeChallenge.CodeChallengeApplicationKt")
}

// Disable plain jar
tasks.jar {
	enabled = false
	archiveClassifier.set("plain")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
