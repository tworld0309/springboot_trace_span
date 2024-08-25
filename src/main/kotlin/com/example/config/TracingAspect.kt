
package com.example.config

import io.opentelemetry.api.trace.Span
import io.opentelemetry.api.trace.Tracer
import io.opentelemetry.context.Context
import io.opentelemetry.context.Scope
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.MDC
import org.springframework.stereotype.Component

@Aspect
@Component
class TracingAspect(private val tracer: Tracer) {

    @Around("execution(* com.example..controller.*.*(..))")
    fun traceController(joinPoint: ProceedingJoinPoint): Any? {
        val span = tracer.spanBuilder(joinPoint.signature.toShortString()).startSpan()
        val scope: Scope = span.makeCurrent()

        MDC.put("trace_id", span.spanContext.traceId)
        MDC.put("span_id", span.spanContext.spanId)

        return try {
            joinPoint.proceed()
        } finally {
            scope.close()
            span.end()
            MDC.clear()
        }
    }

    @Around("execution(* com.example..service.*.*(..))")
    fun traceService(joinPoint: ProceedingJoinPoint): Any? {
        // 서비스에서는 기존 trace_id를 사용하고 새로운 span_id만 생성
        val parentSpan = Span.current()
        val span = tracer.spanBuilder(joinPoint.signature.toShortString()).setParent(Context.current().with(parentSpan)).startSpan()
        val scope: Scope = span.makeCurrent()

        MDC.put("trace_id", span.spanContext.traceId)
        MDC.put("span_id", span.spanContext.spanId)

        return try {
            joinPoint.proceed()
        } finally {
            scope.close()
            span.end()
            MDC.clear()
        }
    }
}

