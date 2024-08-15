package com.example.config

import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.trace.Tracer
import jakarta.annotation.PostConstruct
import org.slf4j.MDC
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenTelemetryConfig {

    @Bean
    fun tracer(): Tracer {
        // OpenTelemetry를 초기화하고 글로벌 트레이서를 설정합니다.
        val openTelemetry = GlobalOpenTelemetry.get()
        return openTelemetry.getTracer("application-tracer")
    }

    @PostConstruct
    fun initTracing() {
        // 요청의 시작점에서 Trace ID를 생성합니다.
        val tracer = GlobalOpenTelemetry.getTracer("application-tracer")
        val rootSpan = tracer.spanBuilder("rootSpan").startSpan()

        // MDC에 Trace ID를 저장하여 로깅에 포함할 수 있습니다.
        MDC.put("traceId", rootSpan.spanContext.traceId)
        System.out.println("traceId : {} " + rootSpan.spanContext.traceId)
        System.out.println("rootSpan : {} " + rootSpan)
    }
}