package org.example.demo.spring_security.spring_security

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@ActiveProfiles("dev")
@AutoConfigureMockMvc
@SpringBootTest
class HelloIntegrationTest {

    @Autowired
    private lateinit var mockMvc:MockMvc

    @Test
    fun sayHello(){

        mockMvc.get("/")
            .andDo { print() }
            .andExpect {
                status { is2xxSuccessful() }
                jsonPath("$.text"){ isNotEmpty() }
                jsonPath("$.text") {  value("Hello testUserName! Welcome to spring security.") }
            }
    }

}