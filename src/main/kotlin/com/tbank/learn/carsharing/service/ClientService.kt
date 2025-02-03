package com.tbank.learn.carsharing.service

import com.tbank.learn.carsharing.exception.NotFoundInRepository
import com.tbank.learn.carsharing.model.user.Client
import com.tbank.learn.carsharing.repository.ClientRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClientService(val clientRepository: ClientRepository) {
    @Transactional(readOnly = true)
    fun getList(pageable: Pageable): List<Client> {
        return clientRepository.findAll(pageable).toList()
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): Client? {
        return clientRepository.findById(id).orElseThrow { NotFoundInRepository("Клиент не найден!") }
    }

    @Transactional
    fun create(client: Client): Client {
        return clientRepository.save(client)
    }

    @Transactional
    fun update(client: Client): Client {
        return clientRepository.save(client)
    }

    @Transactional
    fun deleteById(id: Long) {
        clientRepository.deleteById(id)
    }
}