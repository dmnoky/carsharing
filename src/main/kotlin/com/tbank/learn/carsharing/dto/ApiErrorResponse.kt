package com.tbank.learn.carsharing.dto

data class ApiErrorResponse(
        val errorCode: String,
        val errorMessage: String,
        val errorId: String,
)
