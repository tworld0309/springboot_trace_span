
package com.example.config

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class TracingAspect(private val tracingComponent: TracingComponent) {

    @Around("execution(* com.example.*.service.*.*(..)) || execution(* com.example.*.repository.*.*(..)) || execution(* com.example.*.controller.*.*(..))")
    fun traceMethod(joinPoint: ProceedingJoinPoint): Any? {
        val span = tracingComponent.startSpan(joinPoint.signature.toShortString())
        return try {
            joinPoint.proceed()
        } finally {
            tracingComponent.endSpan(span)
        }
    }
}


//@Aspect
//@Component
//class TracingAspect {
//
//    private val tracer: Tracer = GlobalOpenTelemetry.getTracer("application-tracer")
//
//    @Around("execution(* com.example.*.service.*.*(..)) || execution(* com.example.*.repository.*.*(..)) || execution(* com.example.*.controller.*.*(..))")
//    //@Around("execution(* com.example..*(..))")  // 모든 패키지의 메서드에 적용
//    @Throws(Throwable::class)
//    fun traceMethods(joinPoint: ProceedingJoinPoint): Any? {
//        val span: Span = tracer.spanBuilder(joinPoint.signature.toShortString()).startSpan()
//        return try {
//            MDC.put("spanId", span.spanContext.spanId)
//            joinPoint.proceed()
//        } finally {
//            span.end()
//            MDC.remove("spanId")
//        }
//    }
//}