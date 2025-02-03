package com.tbank.learn.carsharing.model.user

import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version

open class User(
        @Id
        open val id: Long,
        @NotBlank
        open val lastName: String,
        @NotBlank
        open val firstName: String,
        open val middleName: String?=null,
        open val email: String?=null,
        //@ValidPhoneNumber
        open val phone: String?=null,
        @Version
        val version: Long?=0,
)
