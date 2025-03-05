package com.tbank.learn.carsharing.controller

import com.tbank.learn.carsharing.dto.client.request.ClientUpsertDto
import com.tbank.learn.carsharing.dto.client.response.ClientResponse
import com.tbank.learn.carsharing.model.user.Client
import com.tbank.learn.carsharing.service.ClientService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/client")
@RestController
@Validated
class ClientController (val clientService: ClientService) {

    @GetMapping(value = ["/{id}"], produces = [APPLICATION_JSON_VALUE])
    fun getClient(@PathVariable("id") id: UUID): ClientResponse? {
        return clientService.getById(id)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun createClient(@Validated(ClientUpsertDto.Insert::class) @RequestBody client: ClientUpsertDto): Client? {
        return clientService.create(client)
    }

    @PutMapping(value = ["/update"], consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun updateClient(@Validated(ClientUpsertDto.Update::class) @RequestBody client: ClientUpsertDto): Client? {
        return clientService.update(client)
    }
    
    /** Апдейт без зависимотей */
    @PutMapping(value = ["/update/light"], consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun updateClientLight(@Validated(ClientUpsertDto.UpdateLight::class) @RequestBody client: ClientUpsertDto): Client? {
        return clientService.update(client)
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteClient(@PathVariable("id") id: UUID) {
        clientService.deleteById(id)
    }

    @GetMapping(value = ["/list"], produces = [APPLICATION_JSON_VALUE])
    fun getListClient(@RequestParam(name = "pageNumber", defaultValue = "0")  pageNumber: Int,
                     @RequestParam(name = "pageSize"  , defaultValue = "10") pageSize: Int)
    : List<Client> { //todo dto
        return clientService.getList(Pageable.ofSize(pageSize).withPage(pageNumber))
    }
}