package com.tbank.learn.carsharing.model.transport

import com.tbank.learn.carsharing.model.AnyReadWriteModel
import com.tbank.learn.carsharing.model.AnyUUID
import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table
class Transport (
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
		/** М:1 к [TransportModel], т.к. у 1 транспорта только 1 модель, но модели присущи множеству траспортов */
		@field:NotBlank
		var modelId: UUID,
) : AnyReadWriteModel, AnyUUID {
		
		//@MappedCollection(idColumn = "transport_id")
		//@Transient
		//val orders: Set<Order>?=null,
		
		override fun hashCode(): Int {
				return id.hashCode()
		}
		
		override fun equals(other: Any?): Boolean {
				if (this === other) return true
				if (other == null || other !is Transport) return false
				return this.id == other.id
		}
}