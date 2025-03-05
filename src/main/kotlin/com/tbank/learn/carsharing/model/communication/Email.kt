package com.tbank.learn.carsharing.model.communication

import com.fasterxml.jackson.annotation.JsonProperty
import com.tbank.learn.carsharing.model.AnyReadWriteModel
import com.tbank.learn.carsharing.model.AnyUUID
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

/** Способ связи (например, с клиентом).
 * @param parentId внешний ключ М:1 */
@Table
class Email (
		@Id @Column("id") @JsonProperty("id")
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
		@field:jakarta.validation.constraints.Email
		var email: String,
		var isPrimary: Boolean = false
) : AnyReadWriteModel, AnyUUID {
		
		constructor(parentId :UUID, email: String, isPrimary: Boolean) : this(
				null, 0, null, null, null, null,
				parentId, email, isPrimary
		)
		
		override fun hashCode(): Int {
				return uuid.hashCode()
		}
		
		override fun equals(other: Any?): Boolean {
				if (this === other) return true
				if (other == null || other !is Email) return false
				return this.uuid == other.uuid
		}
}