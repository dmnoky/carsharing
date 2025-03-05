package com.tbank.learn.carsharing.dto.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PhoneUpsertDtoValidator::class])
annotation class ValidPhoneUpsertDto(
        val message: String = "невалидный номер телефона",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)