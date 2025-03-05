package com.tbank.learn.carsharing.dto.client.request

import com.tbank.learn.carsharing.dto.Utils
import com.tbank.learn.carsharing.dto.communication.request.EmailUpsertDto
import com.tbank.learn.carsharing.dto.communication.request.PhoneUpsertDto
import com.tbank.learn.carsharing.model.user.Client
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import java.util.*

data class ClientUpsertDto (
		val version: Long,
		
		@field:NotNull(groups = [Update::class, UpdateLight::class])
		val id: UUID?,
		
		//@field:NotBlank(groups = [Insert::class, Update::class, UpdateLight::class])
		@field:Pattern(groups = [Insert::class, Update::class, UpdateLight::class],
				regexp = Utils.VALID_ONLY_CHARACTERS_AND_SPACE,
				message = "{validation.field.only.chars.space}"
		)
		val lastName: String,
		
		//@field:NotBlank(groups = [Insert::class, Update::class, UpdateLight::class])
		@field:Pattern(groups = [Insert::class, Update::class, UpdateLight::class],
				regexp = Utils.VALID_ONLY_CHARACTERS_AND_SPACE,
				message = "{validation.field.only.chars.space}"
		)
		val firstName: String,
		
		@field:Pattern(groups = [Insert::class, Update::class, UpdateLight::class],
				regexp = "^.{0}$|"+Utils.VALID_ONLY_CHARACTERS_AND_SPACE,
				message = "{validation.field.only.chars.space}"
		)
		val middleName: String?=null,
		
		//@field:NotNull(groups = [Insert::class, Update::class])
		@Valid
		val email: ArrayList<EmailUpsertDto>?=null,
		
		//@field:NotNull(groups = [Insert::class, Update::class])
		@Valid
		//@field:ValidPhoneUpsertDto(groups = [Insert::class, Update::class])
		val phone: ArrayList<PhoneUpsertDto>?=null
		
) {
		constructor(fromClient: Client) : this (
				fromClient.version,
				fromClient.uuid,
				fromClient.lastName,
				fromClient.firstName,
				fromClient.middleName
		)
		
		init {
				//ConstraintViolationImpl{interpolatedMessage='должно иметь формат адреса электронной почты', propertyPath=email,
				// rootBeanClass=class com.tbank.learn.carsharing.dto.communication.request.EmailInsertDto, messageTemplate='{jakarta.validation.constraints.Email.message}'}
				/*val validateEmail = Validation.buildDefaultValidatorFactory().validator.validate(email)
				//
				val dtoNotValidException = DtoNotValidException()
				if (validateEmail.isNotEmpty()) dtoNotValidException.putError("email", validateEmail) //throw ConstraintViolationException(validateEmail) //
				
				val validatePhone = Validation.buildDefaultValidatorFactory().validator.validate(phone)
				if (validatePhone.isNotEmpty()) dtoNotValidException.putError("phone", validatePhone)
				if (dtoNotValidException.inited()) throw dtoNotValidException*/
				
				/*if (validateEmail.isNotEmpty()) { //throw ConstraintViolationException(validateEmail)
						val result = BeanPropertyBindingResult(email, "email")
						for (constraintViolation in validateEmail) {
								val propertyPath = constraintViolation.propertyPath.toString()
								val message = constraintViolation.message
								result.addError(ObjectError(propertyPath, message))
						}
						throw MethodArgumentNotValidException(
								MethodParameter(
										this.javaClass.getConstructor(ClientUpsertDto::class.java), 5
								), result
						)
				}*/
		}
		
		interface Insert {}
		interface Update {}
		interface UpdateLight {}
		
		/** Создает нового клиента из этой ДТО
		 * @return клиент с пустым айди */
		fun toEntity(): Client = Client(
				lastName,
				firstName,
				middleName
		)
		
		/** Обновляет значения полей у клиента [dbClient] на значения полей этой ДТО
		 * @param dbClient предположительно, клиент из БД
		 * @return обновленный [dbClient] - для удобства */
		fun mapClient(dbClient: Client): Client {
				//return dbClient.copy(lastName = lastName, firstName = firstName, middleName = middleName, version = version)
				dbClient.lastName = lastName
				dbClient.firstName = firstName
				dbClient.middleName = middleName
				return dbClient
		}
}