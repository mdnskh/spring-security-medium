package org.example.demo.spring_security.spring_security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component

@Component
class SecurityUtils {
    fun getCurrentUser() : User {
        return SecurityContextHolder.getContext().authentication.details as User
    }
}