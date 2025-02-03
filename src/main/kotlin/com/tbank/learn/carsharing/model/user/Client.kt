package com.tbank.learn.carsharing.model.user

import com.tbank.learn.carsharing.model.Order
import com.tbank.learn.carsharing.validation.ValidPhoneNumber
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("user_table")
data class Client (
        @Id
        val id: Long?,
        @field:NotBlank
        val lastName: String?,
        @field:NotBlank
        val firstName: String?,
        val middleName: String?=null,
        @field:Email
        val email: String?=null,
        @field:ValidPhoneNumber
        val phone: String?=null,
        @Version
        val version: Long,
        @MappedCollection(idColumn = "client_id")
        val orders: Set<Order>?=null,
) //: User(id, lastName, firstName, middleName, email, phone)
