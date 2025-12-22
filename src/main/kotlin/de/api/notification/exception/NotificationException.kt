package de.api.notification.exception

class UserNotSubscribedException(message: String) : RuntimeException(message)

class UserRegistrationException(message: String) : RuntimeException(message)

class UserNotFoundException(message: String) : RuntimeException(message)

class RateLimitException(message: String) : RuntimeException(message)



