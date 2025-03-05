package com.tbank.learn.carsharing.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource

@Configuration
//@PropertySource("classpath:props/error-msg.properties")
class ApiAnyBeanConfig {
		/** Подятигвает проперти файлы */
		@Bean
		fun messageSource(): ReloadableResourceBundleMessageSource {
				val messageSource = ReloadableResourceBundleMessageSource()
				messageSource.setBasename("classpath:props/error-msg")
				messageSource.setDefaultEncoding("UTF-8")
				return messageSource
		}
		
		/*@Bean
		fun validatorFactoryBean(messageSource :ReloadableResourceBundleMessageSource, applicationContext: ApplicationContext): LocalValidatorFactoryBean? {
				val validatorFactoryBean = LocalValidatorFactoryBean()
				validatorFactoryBean.setValidationMessageSource(messageSource)
				validatorFactoryBean.setApplicationContext(applicationContext)
				return validatorFactoryBean
		}*/
}