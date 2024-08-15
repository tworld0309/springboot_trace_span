package com.example.pain.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PainService {

    private val log = LoggerFactory.getLogger(PainService::class.java)

    fun callService1() {
        log.info("log-info - call painService1")
    }

    fun callService2() {
        log.info("log-info - call painService2")
    }
}