package de.api.notification.service

import de.api.notification.repository.NotificationTypeRepository
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class NotificationTypeService(private val repo: NotificationTypeRepository) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    // Cached: type -> category
    @Cacheable(cacheNames = ["typeCategory"], unless = "#result == null")
    fun getCategoryForType(type: String): String? {
        return repo.findById(type.trim())
            .map { it.category }
            .orElse(null)
    }

    // Cached: category -> types list
    @Cacheable(cacheNames = ["typesForCategory"])
    fun getAllTypesForCategory(category: String): List<String> {
        return repo.findAllByCategory(category.trim())
            .map { it.type }
    }


    fun isValidType(type: String): Boolean {
        val isValid = repo.existsById(type)
        logger.info("type = $type is= $isValid")

        return isValid
    }

}

