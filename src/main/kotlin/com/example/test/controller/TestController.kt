package com.example.test.controller

import com.example.test.service.TestService
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@OpenAPIDefinition(
    info = Info(
        title = "My API",
        version = "1.0",
        description = "TEST APi"
    )
)
class TestController(val testService: TestService) {
    private val log = LoggerFactory.getLogger(TestController::class.java)

    @GetMapping("/test")
    fun testEndpoint(): String {
        log.info("log-info - TestController call ")
        testService.callService1()
        testService.callService2()

        return "TestController called "
    }
}