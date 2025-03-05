package com.tbank.learn.carsharing.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.domain.Persistable
import java.util.*

/** Основа под праймари айди сущностей [uuid]
 * @see com.tbank.learn.carsharing.configuration.PersistenceConfig автогенерация айди */
interface AnyUUID : Persistable<UUID> {
		var uuid: UUID?
		
		fun setId(id: UUID) {
				this.uuid = id
		}
		
		@JsonIgnore
		override fun getId(): UUID? = uuid
		
		@JsonIgnore
		override fun isNew(): Boolean = uuid == null
}