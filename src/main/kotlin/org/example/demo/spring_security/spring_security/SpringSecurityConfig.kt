package org.example.demo.spring_security.spring_security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class SpringSecurityConfig {

    @Bean
    fun securityFilterChain(http:HttpSecurity) : SecurityFilterChain {
        http.authorizeHttpRequests()
            .anyRequest().hasAuthority("ROLE_USER")
            .and()
            .formLogin().and()
            .httpBasic().disable()
            .userDetailsService(userDetailsService())
            .logout().permitAll()
        return http.build()
    }

    @Bean
    fun userDetailsService() : UserDetailsService {
        val user: UserDetails = User.withDefaultPasswordEncoder()
            .username("testUser")
            .password("testPassword")
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }
}