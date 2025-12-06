package de.api.notification.util

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class NotificationTypeSetConverter : AttributeConverter<MutableSet<String>, String> {
    override fun convertToDatabaseColumn(valueSet: MutableSet<String>?): String =
        valueSet.orEmpty()
            .joinToString(";")

    override fun convertToEntityAttribute(databaseString: String?): MutableSet<String> =
        databaseString.orEmpty()
            .split(";")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .toMutableSet()
}