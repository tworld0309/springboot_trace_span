
package com.example.config

import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.trace.Span
import io.opentelemetry.api.trace.Tracer
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.MDC
import org.springframework.stereotype.Component

@Aspect
@Component
class TracingAspect(private val tracingComponent: TracingComponent) {

    @Around("execution(* com.example..service.*.*(..)) || execution(* com.example..repository.*.*(..)) || execution(* com.example..controller.*.*(..))")
    fun traceMethod(joinPoint: ProceedingJoinPoint): Any? {
        val span = tracingComponent.startSpan(joinPoint.signature.toShortString())
        return try {
            joinPoint.proceed()
        } finally {
            tracingComponent.endSpan(span)
        }
    }
}

