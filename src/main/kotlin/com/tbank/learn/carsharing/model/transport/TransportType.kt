package com.tbank.learn.carsharing.model.transport

import com.tbank.learn.carsharing.model.AnyReadWriteModel
import com.tbank.learn.carsharing.model.AnyUUID
import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

/** Справочник. Тип транспорта
 * @param type Кар, Електрокар, Мото, Скутер, Велосипед...
 * M:M к [TransportBrand], т.к. бренд AUDI может быть и Кар и Мото */
@Table
class TransportType (
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
		var type: String,
) : AnyReadWriteModel, AnyUUID {
		
		override fun hashCode(): Int {
				return id.hashCode()
		}
		
		override fun equals(other: Any?): Boolean {
				if (this === other) return true
				if (other == null || other !is TransportType) return false
				return this.id == other.id
		}
}