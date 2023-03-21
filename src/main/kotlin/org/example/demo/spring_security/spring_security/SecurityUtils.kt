package org.example.demo.spring_security.spring_security

import org.springframework.core.env.Environment
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component

@Component
class SecurityUtils(private val environment: Environment) {

    fun getCurrentUser(): User {
        if (environment.activeProfiles.contains("dev")
            && !environment.activeProfiles.contains("live")) {
            return User("testUserName", "testPassword", listOf(SimpleGrantedAuthority("USER")))
        }
        return SecurityContextHolder.getContext().authentication.details as User
    }
}