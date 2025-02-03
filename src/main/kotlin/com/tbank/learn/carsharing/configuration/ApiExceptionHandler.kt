package com.tbank.learn.carsharing.configuration

import com.tbank.learn.carsharing.dto.ApiErrorResponse
import io.micrometer.tracing.Span
import io.micrometer.tracing.Tracer
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.lang.NonNull
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ApiExceptionHandler(val tracer: Tracer) : ResponseEntityExceptionHandler() {

    //https://www.baeldung.com/exception-handling-for-rest-with-spring
    @ExceptionHandler(value = [Exception::class])
    fun handleConflict(ex: RuntimeException?, request: WebRequest?): ResponseEntity<Any>? {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiErrorResponse(
                        HttpStatus.BAD_REQUEST.name,
                        ex?.message ?: "Ошибка",
                        getCurrentTraceId()
        ))
    }

    override fun handleMethodArgumentNotValid(
            ex: MethodArgumentNotValidException,
            @NonNull headers: HttpHeaders,
            @NonNull status: HttpStatusCode,
            @NonNull request: WebRequest)
    : ResponseEntity<Any>? {
        val errors: MutableList<ApiErrorResponse> = ArrayList(ex.fieldErrors.size)
        for (error in ex.fieldErrors) {
            errors.add(ApiErrorResponse(
                    error.field,
                    String.format("Поле %s - %s", error.field, error.defaultMessage),
                    getCurrentTraceId()
            ))
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors)
    }

    private fun getCurrentTraceId(): String {
        val span: Span? = tracer.currentSpan()
        return span?.context()?.traceId() ?: ""
    }
}