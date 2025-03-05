package com.tbank.learn.carsharing.model.communication

import com.tbank.learn.carsharing.dto.validation.ValidPhoneNumber
import com.tbank.learn.carsharing.model.AnyReadWriteModel
import com.tbank.learn.carsharing.model.AnyUUID
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

/** Способ связи (например, с клиентом).
 * @param parentId внешний ключ М:1 */
@Table
class Phone (
		@Id @Column("id")
		override var uuid: UUID?,
		@Version
		override var version: Long,
		@CreatedBy
		override var createdBy: UUID?,
		@CreatedDate
		override var createdDate: Date?,
		@LastModifiedBy
		override var lastModifiedBy: UUID?,
		@LastModifiedDate
		override var lastModifiedDate: Date?,

		var parentId :UUID, //client
		@field:ValidPhoneNumber
		var phone: String,
		var isPrimary: Boolean = false
) : AnyReadWriteModel, AnyUUID {
		
		constructor(parentId :UUID, phone: String, isPrimary: Boolean) : this(
				null, 0, null, null, null, null,
				parentId, phone, isPrimary
		)
		
		override fun hashCode(): Int {
				return uuid.hashCode()
		}
		
		override fun equals(other: Any?): Boolean {
				if (this === other) return true
				if (other == null || other !is Phone) return false
				return this.uuid == other.uuid
		}
}