package com.tbank.learn.carsharing.dto.client.response

interface ClientProjection {
		fun getId(): Long?
		fun getFirstName(): String?
		fun getLastName(): String?
		fun getVersion(): Long
}