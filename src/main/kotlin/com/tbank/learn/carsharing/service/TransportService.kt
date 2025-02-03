package com.tbank.learn.carsharing.service

import com.tbank.learn.carsharing.exception.NotFoundInRepository
import com.tbank.learn.carsharing.model.transport.Car
import com.tbank.learn.carsharing.repository.TransportRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransportService(val transportRepository: TransportRepository) {
    @Transactional(readOnly = true)
    fun getList(pageable: Pageable): List<Car> {
        return transportRepository.findAll(pageable).toList()
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): Car? {
        return transportRepository.findById(id).orElseThrow { NotFoundInRepository("Транспорт не найден!") }
    }

    @Transactional
    fun create(transport: Car): Car {
        return transportRepository.save(transport)
    }

    @Transactional
    fun update(transport: Car): Car {
        return transportRepository.save(transport)
    }

    @Transactional
    fun deleteById(id: Long) {
        transportRepository.deleteById(id)
    }
}