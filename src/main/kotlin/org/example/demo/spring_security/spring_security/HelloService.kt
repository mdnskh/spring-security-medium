package org.example.demo.spring_security.spring_security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class HelloService {
    fun sayHello(): String {
        val userName = SecurityContextHolder.getContext().authentication.name
        return "Hello ${userName}! Welcome to spring security."
    }
}