package com.tbank.learn.carsharing.dto.client.response

import com.tbank.learn.carsharing.model.communication.Email
import com.tbank.learn.carsharing.model.communication.Phone
import com.tbank.learn.carsharing.model.order.Order
import com.tbank.learn.carsharing.model.user.Client
import java.util.*

data class ClientAllResponse(
		val id: UUID?,
		val version: Long,
		val lastName: String?,
		val firstName: String?,
		val middleName: String?=null,
		var emails: Set<Email>?=null,
		var phones: Set<Phone>?=null,
		var orders: Set<Order>?//LinkedList<Order>?=null,
) : ClientResponse
{
		constructor(client: Client) : this(
				client.uuid,
				client.version,
				client.lastName,
				client.firstName,
				client.middleName,
				null,
				null,
				null,
		)

		fun setOrders(orders: Set<Order>) : ClientAllResponse {
				this.orders = orders
				return this
		}
		
		fun setEmails(emails: Set<Email>) : ClientAllResponse {
				this.emails = emails
				return this
		}
		
		fun setPhones(phones: Set<Phone>) : ClientAllResponse {
				this.phones = phones
				return this
		}
}
