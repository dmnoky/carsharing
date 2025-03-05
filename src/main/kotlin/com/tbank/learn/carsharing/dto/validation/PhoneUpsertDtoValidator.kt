package com.tbank.learn.carsharing.dto.validation

import com.tbank.learn.carsharing.dto.communication.request.PhoneUpsertDto
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Validation

class PhoneUpsertDtoValidator : ConstraintValidator<ValidPhoneUpsertDto, ArrayList<PhoneUpsertDto>> {

    override fun isValid(phone: ArrayList<PhoneUpsertDto>?, context: ConstraintValidatorContext?): Boolean {
        val validatePhone = Validation.buildDefaultValidatorFactory().validator.validate(phone?.get(0))
        return validatePhone.isEmpty()
    }
}