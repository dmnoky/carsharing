package com.tbank.learn.carsharing.model.order

import com.tbank.learn.carsharing.model.AnyReadWriteModel
import com.tbank.learn.carsharing.model.Status
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table
data class Order(
        @Id
        var id: UUID?,
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
        //@field:NotNull
        val clientId: Long?,
        //@field:NotNull
        val transportId: Long?,
        //@field:NotNull
        val status : Status,
        val dateOpen  :Date?=Date(),
        val dateClose :Date?=null,
) : AnyReadWriteModel {
        override fun hashCode(): Int {
                return id.hashCode()
        }
        
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || other !is Order) return false
                return this.id == other.id
        }
}
