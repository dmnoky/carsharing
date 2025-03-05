package com.tbank.learn.carsharing.dto.client.request

import com.tbank.learn.carsharing.dto.AnyUpdateDto
import com.tbank.learn.carsharing.dto.Utils
import com.tbank.learn.carsharing.model.user.Client
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.util.*

data class ClientUpdateDto (
		val id: UUID?,
		override var version: Long,
		/*override var createdBy: UUID?,
		override var createdDate: Date?,
		override var lastModifiedBy: UUID?,
		override var lastModifiedDate: Date?,*/
		@field:NotBlank
		@field:Pattern(
				regexp = Utils.VALID_ONLY_CHARACTERS_AND_SPACE,
				message = "{validation.field.only.chars.space}"
		)
		val lastName: String,
		
		@field:NotBlank
		@field:Pattern(
				regexp = Utils.VALID_ONLY_CHARACTERS_AND_SPACE,
				message = "{validation.field.only.chars.space}"
		)
		val firstName: String,
		
		@field:Pattern(
				regexp = Utils.VALID_ONLY_CHARACTERS_AND_SPACE,
				message = "{validation.field.only.chars.space}"
		)
		val middleName: String?=null,
		
		/*@field:Email
		val email: String?=null,
		
		@field:ValidPhoneNumber
		val phone: String?=null,*/
		
) : AnyUpdateDto<Client> {
		
		/** Обновляет значения полей у клиента [dbClient] на значения полей этой ДТО
		 * @param dbClient предположительно, клиент из БД
		 * @return обновленный [dbClient] - для удобства */
		fun mapClient(dbClient: Client): Client {
				//return dbClient.copy(lastName = lastName, firstName = firstName, middleName = middleName, version = version)
				//super.mapModel(dbClient)
				dbClient.lastName = lastName
				dbClient.firstName = firstName
				dbClient.middleName = middleName
				/*dbClient.email = email
				dbClient.phone = phone*/
				return dbClient
		}
}