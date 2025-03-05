package com.tbank.learn.carsharing.service.communication

import com.tbank.learn.carsharing.dto.communication.request.EmailUpsertDto
import com.tbank.learn.carsharing.exception.NotFoundInRepository
import com.tbank.learn.carsharing.model.communication.Email
import com.tbank.learn.carsharing.repository.communication.EmailRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class EmailService(val emailRepository: EmailRepository) {
    @Transactional(readOnly = true)
    fun getList(pageable: Pageable): List<Email> {
        return emailRepository.findAll(pageable).toList()
    }

    @Transactional(readOnly = true)
    fun getById(id: UUID): Email? {
        return emailRepository.findById(id).orElseThrow { NotFoundInRepository("Email не найден!") }
    }

    @Transactional
    fun create(emailDto: EmailUpsertDto): Email {
        //todo уникальный праймари
        return emailRepository.save(emailDto.toEntity())
    }

    @Transactional
    fun update(emailDto: EmailUpsertDto): Email {
        val dbEntity = emailRepository.findById(emailDto.id!!).get()
        return emailRepository.save(emailDto.mapEntity(dbEntity))
    }

    @Transactional
    fun deleteById(id: UUID) {
        emailRepository.deleteById(id)
    }
}