package com.tbank.learn.carsharing

import io.micrometer.observation.ObservationRegistry
import io.micrometer.tracing.Tracer
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.TransactionStatus

@Configuration
class BaseConfigTest {
		
		/** Создаёт пустой бин под логирование
		 * @see [com.tbank.learn.carsharing.configuration.ApiExceptionHandler]
		 * @errorFix App failed to start with exception: Field tracing required a bean of type 'brave.Tracing' that could not be found **/
		@ConditionalOnProperty(prefix = "management.tracing", name = ["enabled"], havingValue = "false")
		object NoopTracingConfig {
				@Bean
				fun tracer(): Tracer = Tracer.NOOP
				
				@Bean
				fun observationRegistry(): ObservationRegistry = ObservationRegistry.NOOP
		}
		
		/** Пустышка TransactionManager */
		@Bean
		fun mockedTransactionManager() = object : PlatformTransactionManager {
				override fun getTransaction(definition: TransactionDefinition?): TransactionStatus = object : TransactionStatus {
						override fun createSavepoint(): Any = Any()
						
						override fun rollbackToSavepoint(savepoint: Any) {}
						
						override fun releaseSavepoint(savepoint: Any) {}
				}
				
				override fun commit(status: TransactionStatus) {}
				
				override fun rollback(status: TransactionStatus) {}
		}
}