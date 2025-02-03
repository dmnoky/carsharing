package com.tbank.learn.carsharing.dto.client

data class ClientLightDto (
        val id: Long,
        val lastName :String,
        val firstName :String,
        val middleName :String,
        val version: Long
)