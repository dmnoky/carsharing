package com.tbank.learn.carsharing.dto.client

data class ClientUpsertDto (
        val id: Long?,
        val lastName :String,
        val firstName :String,
        val middleName :String?,
        val email: String?,
        val phone: String?,
        val version: Long?
)