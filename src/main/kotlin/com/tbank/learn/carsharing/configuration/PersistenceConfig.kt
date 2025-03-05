package com.tbank.learn.carsharing.configuration

import com.tbank.learn.carsharing.model.AnyUUID
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback
import java.util.*

@Configuration
class PersistenceConfig {
		/*@Bean
		fun idGenerator(): ApplicationListener<BeforeSaveEvent<*>>? {
				return ApplicationListener<BeforeSaveEvent<*>> { event: BeforeSaveEvent<*> ->
						val entity = event.entity
						if (entity is AnyUUID) {
								entity.setUUID(UUID.randomUUID())
						}
				}
		}*/
		
		@Bean
		fun beforeConvertCallback(): BeforeConvertCallback<AnyUUID>? {
				return BeforeConvertCallback<AnyUUID> {
						entity: AnyUUID ->
						if (entity.uuid == null) entity.setId(UUID.randomUUID())
						entity
				}
		}
}