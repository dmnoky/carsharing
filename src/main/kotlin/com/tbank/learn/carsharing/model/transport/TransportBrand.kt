package com.tbank.learn.carsharing.model.transport

import com.tbank.learn.carsharing.model.AnyReadWriteModel
import com.tbank.learn.carsharing.model.AnyUUID
import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

/** Справочник. Бренд транспорта
 * @param brand Audi, BMW, Mazda, Tesla, Suzuki...
 * M:M к [TransportType], т.к. бренд AUDI может быть и Кар и Мото
 * M:M к [TransportModel], т.к. наименования моделей могут совпадать у разных брендов */
@Table
class TransportBrand (
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
		var typeId: UUID,
		@field:NotBlank
		var brand: String,
) : AnyReadWriteModel, AnyUUID {
		
		override fun hashCode(): Int {
				return id.hashCode()
		}
		
		override fun equals(other: Any?): Boolean {
				if (this === other) return true
				if (other == null || other !is TransportBrand) return false
				return this.id == other.id
		}
}