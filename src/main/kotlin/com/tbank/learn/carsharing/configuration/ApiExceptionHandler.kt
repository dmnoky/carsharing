package com.tbank.learn.carsharing.configuration

import com.tbank.learn.carsharing.dto.ApiErrorResponse
import com.tbank.learn.carsharing.dto.DtoNotValidException
import io.micrometer.tracing.Span
import io.micrometer.tracing.Tracer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.lang.NonNull
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ApiExceptionHandler(val tracer: Tracer) : ResponseEntityExceptionHandler() {
    
    private val apiLogger: Logger = LoggerFactory.getLogger(ApiExceptionHandler::class.java)
    
    @ExceptionHandler(value = [DtoNotValidException::class])
    fun handleCustomValidException(ex: DtoNotValidException?, request: WebRequest): ResponseEntity<Any> {
        val errors: MutableList<ApiErrorResponse> = ArrayList(ex!!.errMap.size)
        for (error in ex.errMap) {
            errors.add(ApiErrorResponse(
                error.key,
                String.format("Поле %s - %s", error.value.objectName, error.value.defaultMessage),
                getCurrentTraceId()
            ))
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors)
    }
    
    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errMsg: String = ex.message ?: "Ошибка"
        apiLogger.error("${getCurrentTraceId()}: $errMsg")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ApiErrorResponse(
                HttpStatus.BAD_REQUEST.name,
                errMsg,
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
    
    //https://www.baeldung.com/exception-handling-for-rest-with-spring
    @ExceptionHandler(value = [Throwable::class])
    fun handleConflict(ex: Throwable?, request: WebRequest): ResponseEntity<Any> {
        val errMsg: String = ex?.message ?: "Ошибка"
        apiLogger.error("${getCurrentTraceId()}: $errMsg")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ApiErrorResponse(
                HttpStatus.BAD_REQUEST.name,
                errMsg,
                getCurrentTraceId()
            ))
    }

    private fun getCurrentTraceId(): String {
        val span: Span? = tracer.currentSpan()
        return span?.context()?.traceId() ?: System.currentTimeMillis().toString()
    }
}