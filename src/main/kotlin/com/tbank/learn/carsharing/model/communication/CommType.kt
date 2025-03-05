package com.tbank.learn.carsharing.model.communication

import com.fasterxml.jackson.annotation.JsonValue

enum class CommType (@get:JsonValue val value: String) {
    EMAIL("eMail"),
    PHONE("Phone"),
}