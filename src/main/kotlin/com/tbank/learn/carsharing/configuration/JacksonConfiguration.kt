package com.tbank.learn.carsharing.configuration

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder


@Configuration
class JacksonConfiguration {
    @Bean
    fun jackson2ObjectMapperBuilderCustomizer(): Jackson2ObjectMapperBuilderCustomizer? {
        return Jackson2ObjectMapperBuilderCustomizer {
            jacksonObjectMapperBuilder: Jackson2ObjectMapperBuilder -> jacksonObjectMapperBuilder.failOnUnknownProperties(false)
        }
    }
}