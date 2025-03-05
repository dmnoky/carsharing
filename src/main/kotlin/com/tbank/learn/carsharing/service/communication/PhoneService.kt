package com.tbank.learn.carsharing.service.communication

import com.tbank.learn.carsharing.dto.communication.request.PhoneUpsertDto
import com.tbank.learn.carsharing.exception.NotFoundInRepository
import com.tbank.learn.carsharing.model.communication.Phone
import com.tbank.learn.carsharing.repository.communication.PhoneRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class PhoneService(val phoneRepository: PhoneRepository) {
    @Transactional(readOnly = true)
    fun getList(pageable: Pageable): List<Phone> {
        return phoneRepository.findAll(pageable).toList()
    }

    @Transactional(readOnly = true)
    fun getById(id: UUID): Phone? {
        return phoneRepository.findById(id).orElseThrow { NotFoundInRepository("Phone не найден!") }
    }

    @Transactional
    fun create(phoneDto: PhoneUpsertDto): Phone {
        //todo уникальный праймари
        return phoneRepository.save(phoneDto.toEntity())
    }

    @Transactional
    fun update(phoneDto: PhoneUpsertDto): Phone {
        val dbEntity = phoneRepository.findById(phoneDto.id!!).get()
        return phoneRepository.save(phoneDto.mapEntity(dbEntity))
    }

    @Transactional
    fun deleteById(id: UUID) {
        phoneRepository.deleteById(id)
    }
}