package com.tbank.learn.carsharing.dto.validation

import com.tbank.learn.carsharing.dto.client.request.ClientUpsertDto
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class ClientUpsertDtoValidator : Validator {
		override fun supports(clazz: Class<*>): Boolean {
				return ClientUpsertDto::class == clazz
		}
		
		override fun validate(target: Any, errors: Errors) {
				val dto = target as ClientUpsertDto
				
		}
		
}