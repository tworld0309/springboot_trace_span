package com.example.pain.controller

import com.example.pain.service.PainService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PainController(val painService: PainService) {
    private val log = LoggerFactory.getLogger(PainController::class.java)

    @GetMapping("/pain")
    fun testEndpoint(): String {
        log.info("log-info - painController call ")
        painService.callService1()
        painService.callService2()

        return "painController called "
    }
}