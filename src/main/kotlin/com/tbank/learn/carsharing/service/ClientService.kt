package com.tbank.learn.carsharing.service

import com.tbank.learn.carsharing.dto.client.request.ClientUpsertDto
import com.tbank.learn.carsharing.dto.client.response.ClientAllResponse
import com.tbank.learn.carsharing.dto.client.response.ClientResponse
import com.tbank.learn.carsharing.exception.NotFoundInRepository
import com.tbank.learn.carsharing.model.user.Client
import com.tbank.learn.carsharing.repository.ClientRepository
import com.tbank.learn.carsharing.repository.communication.EmailRepository
import com.tbank.learn.carsharing.repository.communication.PhoneRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ClientService(
    val clientRepository: ClientRepository,
    val emailRepository: EmailRepository,
    val phoneRepository: PhoneRepository
) {
    @Transactional(readOnly = true)
    fun getList(pageable: Pageable): List<Client> {
        return clientRepository.findAll(pageable).toList()
    }

    @Transactional(readOnly = true)
    fun getById(id: UUID): ClientResponse {
        val client = clientRepository.findById(id).orElseThrow { NotFoundInRepository("Клиент не найден!") }
        val emails = emailRepository.findAllByParentId(id)
        val phones = phoneRepository.findAllByParentId(id)
        return ClientAllResponse(client).setEmails(emails).setPhones(phones)
    }

    @Transactional
    fun create(clientUpsertDto: ClientUpsertDto): Client {
        //бизнес-валидация где-то тут или до Transactional
        val newEntity = clientRepository.save(clientUpsertDto.toEntity())
        val newId = newEntity.id!!
        
        clientUpsertDto.email?.forEach { emailRepository.save(it.toEntity(newId)) }
        clientUpsertDto.phone?.forEach { phoneRepository.save(it.toEntity(newId)) }
        //if (clientUpsertDto.phone != null) phoneRepository.save(clientUpsertDto.phone.toEntity(newId))
        return newEntity
    }

    @Transactional
    fun update(clientUpsertDto: ClientUpsertDto): Client {
        //бизнес-валидация где-то тут или до Transactional
        val client = clientRepository.findById(clientUpsertDto.id!!).get()
        return clientRepository.save(clientUpsertDto.mapClient(client))
    }
    
    /*@Transactional
    fun update(clientUpdDto: ClientUpdateFIODto): Client {
        val client = clientRepository.findById(clientUpdDto.id).get()
        return clientRepository.save(clientUpdDto.mapClient(client))
    }*/

    @Transactional
    fun deleteById(id: UUID) {
        clientRepository.deleteById(id)
    }
}