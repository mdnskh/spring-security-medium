package org.example.demo.spring_security.spring_security

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.User
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@ActiveProfiles("dev")
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig::class)
class HelloIntegrationTest {

    @Autowired
    private lateinit var securityUtils: SecurityUtils

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun sayHello(){
        val user: User = mockk()
        every { securityUtils.getCurrentUser() } returns user
        every { user.username } returns "testUserName"

        mockMvc.get("/")
            .andDo { print() }
            .andExpect {
                status { is2xxSuccessful() }
                jsonPath("$.text"){ isNotEmpty() }
                jsonPath("$.text") {  value("Hello testUserName! Welcome to spring security.") }
            }
    }
}


@TestConfiguration
class TestConfig {
    @Bean
    @Primary
    fun securityUtil() : SecurityUtils {
        return mockk()
    }
}