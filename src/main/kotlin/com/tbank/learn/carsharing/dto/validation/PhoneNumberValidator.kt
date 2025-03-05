package com.tbank.learn.carsharing.dto.validation

import com.tbank.learn.carsharing.dto.Utils
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.regex.Pattern

class PhoneNumberValidator : ConstraintValidator<ValidPhoneNumber, String> {
    
    private val phonePattern : Pattern = Pattern.compile(Utils.VALID_PHONE_RU)
    
    override fun isValid(phone: String?, context: ConstraintValidatorContext?): Boolean {
        return phone != null && phonePattern.matcher(phone).matches()
    }
}