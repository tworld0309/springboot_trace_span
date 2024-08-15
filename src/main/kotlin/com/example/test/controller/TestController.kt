package com.example.test.controller

import com.example.test.service.TestService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
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