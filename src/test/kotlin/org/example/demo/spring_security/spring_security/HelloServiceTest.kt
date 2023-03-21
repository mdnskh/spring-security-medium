package org.example.demo.spring_security.spring_security

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.authentication.TestingAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

class HelloServiceTest {

    @BeforeEach
    fun setup(){
        SecurityContextHolder.getContext().authentication =
            TestingAuthenticationToken("testUserName", "testPassword", "ROLE_USER")
    }

    @Test
    fun testSayHello() {
        val service = HelloService()
        val expectedUserName = "testUserName"

        assertEquals("Hello ${expectedUserName}! Welcome to spring security.", service.sayHello())
    }

}