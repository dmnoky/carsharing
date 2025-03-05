package com.tbank.learn.carsharing.model.user

import com.fasterxml.jackson.annotation.JsonProperty
import com.tbank.learn.carsharing.model.AnyReadWriteModel
import com.tbank.learn.carsharing.model.AnyUUID
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table
class Client (
		@Id @Column("id") @JsonProperty("id")
		override var uuid: UUID?, //private var id: UUID?,
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
		//@field:NotBlank
		var lastName: String,
		//@field:NotBlank
		var firstName: String,
		var middleName: String?,
		//@field:Email
		//var email: String?=null, //todo композиция Communication
		//@field:ValidPhoneNumber
		//var phone: String?=null, //todo композиция Communication
		
		/*@MappedCollection(idColumn = "client_id")
		//var orders: Set<Order>?=null //LinkedList */
) : AnyReadWriteModel, AnyUUID {
		
		constructor(lastName: String, firstName: String, middleName: String?)
				: this(null, 0,null, null,null, null,
				lastName, firstName, middleName)
		
		/** или в AnyUUID нереализовывать setUUID и Persistable<UUID>, оставив на имплементеров +
		 * или uuid (id) делать val и всегда заполнять, но надо продумать isNew - */
		/*@Transient
		override var uuid: UUID? = id
				get() = id
				set(value) {
						field = value
						this.id = field
				}*/
		
		override fun hashCode(): Int {
				return uuid.hashCode()
		}
		
		override fun equals(other: Any?): Boolean {
				if (this === other) return true
				if (other == null || other !is Client) return false
				return this.uuid == other.uuid
		}
		
		override fun toString(): String {
				return "Client(id=$uuid, version=$version, createdBy=$createdBy, createdDate=$createdDate, lastModifiedBy=$lastModifiedBy, lastModifiedDate=$lastModifiedDate, lastName=$lastName, firstName=$firstName, middleName=$middleName)"
		}
}
