package com.tbank.learn.carsharing.model

import java.util.*

/** Интерфейс для обновляемых (не РО) моделей. Для метки и базовых полей.
 * @see com.tbank.learn.carsharing.dto.AnyUpdateDto мапит поля с сущностей этого итерфейса */
interface AnyReadWriteModel {
		var version: Long
		var createdBy: UUID?
		var createdDate: Date?
		var lastModifiedBy: UUID?
		var lastModifiedDate: Date?
}