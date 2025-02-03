package com.tbank.learn.carsharing.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PhoneNumberValidator::class])
annotation class ValidPhoneNumber(
        val message: String = "невалидный номер телефона",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)