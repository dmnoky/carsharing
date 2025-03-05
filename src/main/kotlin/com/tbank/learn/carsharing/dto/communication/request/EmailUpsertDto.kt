package com.tbank.learn.carsharing.dto.communication.request

import com.tbank.learn.carsharing.model.communication.Email
import jakarta.validation.constraints.NotNull
import java.util.*

data class EmailUpsertDto(
		val version: Long = 0,
		
		@field:NotNull(groups = [Update::class])
		val id: UUID?,
		
		@field:NotNull(groups = [Insert::class, Update::class])
		val parentId: UUID,
		
		@field:jakarta.validation.constraints.Email(groups = [Insert::class, Update::class])
		val email: String,
		
		val isPrimary: Boolean = false
) {
		
		interface Insert {}
		interface Update {}
		
		/** Создает новый экземляр Email из этой ДТО с пустым айди */
		fun toEntity(parentId: UUID = this.parentId) : Email = Email(parentId, email, isPrimary)
		
		/** Обновляет значения полей у [dbEntity] на значения полей этой ДТО
		 * @return обновленный [dbEntity] */
		fun mapEntity(dbEntity: Email): Email {
				dbEntity.email = email
				dbEntity.isPrimary = isPrimary
				dbEntity.parentId = parentId
				return dbEntity
		}
}