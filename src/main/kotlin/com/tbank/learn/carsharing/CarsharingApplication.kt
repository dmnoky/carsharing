package com.tbank.learn.carsharing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
@PropertySource("classpath:props/error-msg.properties")
class CarsharingApplication

fun main(args: Array<String>) {
    runApplication<CarsharingApplication>(*args)
}
