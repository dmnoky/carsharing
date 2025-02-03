package com.tbank.learn.carsharing.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

import java.util.regex.Pattern

class PhoneNumberValidator : ConstraintValidator<ValidPhoneNumber, String> {

    private val phonePattern : Pattern = Pattern.compile("^(((\\+7)|8)[0-9]{10})$")

    override fun isValid(phone: String?, context: ConstraintValidatorContext?): Boolean {
        return phone != null && phonePattern.matcher(phone).matches()
    }
}