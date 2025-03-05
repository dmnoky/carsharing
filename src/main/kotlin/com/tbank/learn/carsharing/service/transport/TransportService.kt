package com.tbank.learn.carsharing.service.transport

import com.tbank.learn.carsharing.exception.NotFoundInRepository
import com.tbank.learn.carsharing.model.transport.Transport
import com.tbank.learn.carsharing.repository.transport.TransportRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TransportService(
    val transportRepository: TransportRepository
) {
    @Transactional(readOnly = true)
    fun getList(pageable: Pageable): List<Transport> {
        return transportRepository.findAll(pageable).toList()
    }

    @Transactional(readOnly = true)
    fun getById(id: UUID): Transport? {
        return transportRepository.findById(id).orElseThrow { NotFoundInRepository("Транспорт не найден!") }
    }

    @Transactional
    fun create(transport: Transport): Transport {
        return transportRepository.save(transport)
    }

    @Transactional
    fun update(transport: Transport): Transport {
        return transportRepository.save(transport)
    }

    @Transactional
    fun deleteById(id: UUID) {
        transportRepository.deleteById(id)
    }
}