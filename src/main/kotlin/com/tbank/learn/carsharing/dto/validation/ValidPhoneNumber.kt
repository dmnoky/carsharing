package com.tbank.learn.carsharing.dto.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PhoneNumberValidator::class])
annotation class ValidPhoneNumber(
        //@Value("\${validation.field.phone.format}")
        val message: String = "невалидный номер телефона",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)