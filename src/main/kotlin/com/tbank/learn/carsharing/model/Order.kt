package com.tbank.learn.carsharing.model

import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import java.util.*

data class Order(
        @Id
        val id: Long?,
        @field:NotNull
        val clientId: Long,
        @field:NotNull
        val transportId: Long,
        @field:NotNull
        val status :Status,
        val dateOpen  :Date?=Date(),
        val dateClose :Date?=null,
        @Version
        val version: Long,
)
