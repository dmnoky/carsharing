package com.tbank.learn.carsharing.dto

import jakarta.validation.ConstraintViolation
import org.springframework.stereotype.Component
import org.springframework.validation.ObjectError

class DtoNotValidException : Exception("Ошибка валидации") {
		private var inited = false
		
		val errMap by lazy {
				inited = true
				LinkedHashMap<String, ObjectError>()
		}
		
		fun inited() = inited
		
		fun putError(key: String, err: Set<ConstraintViolation<*>>) {
				for (constraintViolation in err) {
						val propertyPath = constraintViolation.propertyPath.toString()
						val message = constraintViolation.message
						errMap[key] = ObjectError(propertyPath, message)
				}
		}
}