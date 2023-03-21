package org.example.demo.spring_security.spring_security

import org.springframework.stereotype.Service

@Service
class HelloService(private val securityUtils: SecurityUtils) {
    fun sayHello(): String {
        val userName = securityUtils.getCurrentUser().username
        return "Hello ${userName}! Welcome to spring security."
    }
}