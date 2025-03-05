package com.tbank.learn.carsharing.dto

import com.tbank.learn.carsharing.model.AnyReadWriteModel

/** Мапит базовые поля [AnyReadWriteModel] */
interface AnyUpdateDto<T: AnyReadWriteModel> {
		val version: Long
		/*var createdBy: UUID?
		var createdDate: Date?
		var lastModifiedBy: UUID?
		var lastModifiedDate: Date?*/
		
		fun mapModel(dbModel: T): T {
				dbModel.version = version
				/*dbModel.createdBy = createdBy
				dbModel.createdDate = createdDate
				dbModel.lastModifiedBy = lastModifiedBy
				dbModel.lastModifiedDate = lastModifiedDate*/
				return dbModel
		}

}