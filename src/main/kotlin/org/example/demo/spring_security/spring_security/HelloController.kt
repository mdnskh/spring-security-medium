package org.example.demo.spring_security.spring_security

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(private val service:HelloService) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun sayHello(): Map<String, Any> {
        return mapOf("text" to service.sayHello())
    }
}