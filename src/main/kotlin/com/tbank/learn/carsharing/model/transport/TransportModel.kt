package com.tbank.learn.carsharing.model.transport

import com.tbank.learn.carsharing.model.AnyReadWriteModel
import com.tbank.learn.carsharing.model.AnyUUID
import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

/** Справочник. Модель транспорта
 * @param model r7, a6, q5, x4, model 3
 * M:M к [TransportBrand], т.к. наименования моделей могут совпадать у разных брендов
 * 1:M к [Transport], т.к. у 1 транспорта только 1 модель, но модели присущи множеству траспортов */
@Table
class TransportModel (
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
		@field:NotBlank
		var brandId: UUID, //мб тут можно и MappedCollection юзать, т.к. к модели всё равно нужен бренд, НО это М:М
		@field:NotBlank
		var model: String,
) : AnyReadWriteModel, AnyUUID {

		override fun hashCode(): Int {
				return id.hashCode()
		}
		
		override fun equals(other: Any?): Boolean {
				if (this === other) return true
				if (other == null || other !is TransportModel) return false
				return this.id == other.id
		}
}