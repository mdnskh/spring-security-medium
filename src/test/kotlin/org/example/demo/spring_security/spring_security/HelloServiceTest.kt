package org.example.demo.spring_security.spring_security

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.security.core.userdetails.User

class HelloServiceTest {


//    @BeforeEach
//    fun setup(){
//        SecurityContextHolder.getContext().authentication =
//            TestingAuthenticationToken("testUserName", "testPassword", "ROLE_USER")
//    }

    @Test
    fun testSayHello() {
        val utils: SecurityUtils = mockk()
        val user: User = mockk()

        val service = HelloService(utils)
        val expectedUserName = "testUserName"

        every { utils.getCurrentUser() } returns user
        every { user.username } returns expectedUserName

        assertEquals("Hello ${expectedUserName}! Welcome to spring security.", service.sayHello())
    }

}