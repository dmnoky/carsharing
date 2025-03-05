package com.tbank.learn.carsharing.dto

internal object Utils {
		const val VALID_ONLY_CHARACTERS_AND_SPACE: String = "^([а-яА-Я]| )+$"
		const val VALID_PHONE_RU: String = "^(((\\+7)|8)[0-9]{10})$"
}