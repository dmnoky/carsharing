package com.tbank.learn.carsharing.dto.client.response

class ClientBaseView(
		var id: Long?,
		var lastName: String?,
		var firstName: String?,
		var version: Long,
		) {
		/*fun getId(): Long? = id
		fun getFirstName(): String? = lastName
		fun getLastName(): String? = firstName
		fun getVersion(): Long = version
		
		fun setId(id: Long) { this.id = id }
		fun setFirstName(lastName: String) { this.lastName = lastName}
		fun setLastName(firstName: String) { this.firstName = firstName}
		fun setVersion(version: Long) { this.version = version }*/
}