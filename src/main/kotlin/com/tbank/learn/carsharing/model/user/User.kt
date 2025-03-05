package com.tbank.learn.carsharing.model.user

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import java.util.*

//@Table
data class User(
        @Id
        val id: UUID?, //GeneratedValue == Client.Id
        @NotNull
        @Size(min = 5, message = "Не меньше 5 знаков")
        val username: String,
        @JsonIgnore
        @NotNull
        @Size(min = 5, message = "Не меньше 5 знаков")
        val password: String,
        @Transient
        val passwordConfirm: String,
        @Version
        val version: Long,
)
