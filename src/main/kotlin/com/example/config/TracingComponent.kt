package com.example.config

import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.trace.Span
import io.opentelemetry.api.trace.Tracer
import io.opentelemetry.context.Context
import org.slf4j.MDC
import org.springframework.stereotype.Component

@Component
class TracingComponent {

    private val tracer: Tracer = GlobalOpenTelemetry.getTracer("application-tracer")

    fun startSpan(spanName: String): Span {
        // 현재 컨텍스트에서 Span을 시작
        val currentSpan = Span.current()
        val span = if (currentSpan != null && currentSpan.spanContext.isValid) {
            tracer.spanBuilder(spanName).setParent(Context.current()).startSpan()
        } else {
            tracer.spanBuilder(spanName).startSpan()
        }

        MDC.put("trace_id", span.spanContext.traceId)
        MDC.put("span_id", span.spanContext.spanId)
        return span
    }

    fun endSpan(span: Span) {
        span.end()
        MDC.clear()
    }
}