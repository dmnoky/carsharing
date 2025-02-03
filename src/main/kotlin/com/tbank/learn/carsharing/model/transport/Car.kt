package com.tbank.learn.carsharing.model.transport

import com.tbank.learn.carsharing.model.Order
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("transport")
data class Car (
        @Id
        val id: Long?,
        @field:NotNull
        val brand: CarBrand,
        @MappedCollection(idColumn = "transport_id")
        val orders: Set<Order>?=null,
        @Version
        val version: Long,
) //: Transport ()