package com.tbank.learn.carsharing.exception

class NotFoundInRepository(private val msg :String) : RuntimeException(msg) {
}