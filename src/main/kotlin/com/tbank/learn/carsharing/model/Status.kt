package com.tbank.learn.carsharing.model

import com.fasterxml.jackson.annotation.JsonValue

enum class Status (@get:JsonValue val value: String) {
    OPEN("Open"),
    IN_PROGRESS("In progress"),
    CLOSED("Closed"),
}