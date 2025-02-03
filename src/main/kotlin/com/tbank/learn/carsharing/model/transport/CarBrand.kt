package com.tbank.learn.carsharing.model.transport

import com.fasterxml.jackson.annotation.JsonValue

enum class CarBrand(@get:JsonValue val value: String) {
    AUDI("AUDI"),
    BMW("BMW"),
    Ford("Ford"),
    Mazda("Mazda"),
    Tesla("Tesla"),
    Volkswagen("Volkswagen");
}