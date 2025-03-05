package com.tbank.learn.carsharing.dto.communication.request

import com.tbank.learn.carsharing.dto.validation.ValidPhoneNumber
import com.tbank.learn.carsharing.model.communication.Phone
import jakarta.validation.constraints.NotNull
import java.util.*

data class PhoneUpsertDto(
		val version: Long = 0,
		
		@field:NotNull(groups = [Update::class])
		val id: UUID?,
		
		@field:NotNull(groups = [Insert::class, Update::class])
		val parentId: UUID,
		
		@field:ValidPhoneNumber(groups = [Insert::class, Update::class])
		val phone: String,
		
		val isPrimary: Boolean = false
) {
		interface Insert {}
		interface Update {}
		
		/** Создает новый экземляр Email из этой ДТО с пустым айди */
		fun toEntity(parentId: UUID = this.parentId) : Phone = Phone(parentId, phone, isPrimary)
		
		/** Обновляет значения полей у [dbEntity] на значения полей этой ДТО
		 * @return обновленный [dbEntity] */
		fun mapEntity(dbEntity: Phone): Phone {
				dbEntity.phone = phone
				dbEntity.isPrimary = isPrimary
				dbEntity.parentId = parentId
				return dbEntity
		}
}