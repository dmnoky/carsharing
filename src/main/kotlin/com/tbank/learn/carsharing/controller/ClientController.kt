package com.tbank.learn.carsharing.controller

import com.tbank.learn.carsharing.model.user.Client
import com.tbank.learn.carsharing.service.ClientService
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RequestMapping("/client")
@RestController
@Validated
class ClientController (val clientService: ClientService/*, val clientMapper: ClientMapper*/) {

    @GetMapping(value = ["/{id}"], produces = [APPLICATION_JSON_VALUE])
    fun getClient(@PathVariable("id") id: Long): Client? {
        return clientService.getById(id)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun createClient(@Valid @RequestBody client: Client): Client? {
        //val client: Client = clientMapper.fromClientUpsertDto(clientDto)
        return clientService.create(client)
    }

    @PutMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun updateClient(@Valid @RequestBody client: Client): Client? {
        return clientService.update(client)
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteClient(@PathVariable("id") id: Long) {
        clientService.deleteById(id)
    }

    @GetMapping(value = ["/list"], produces = [APPLICATION_JSON_VALUE])
    fun getListClient(@RequestParam(name = "pageNumber", defaultValue = "0")  pageNumber: Int,
                     @RequestParam(name = "pageSize"  , defaultValue = "10") pageSize: Int)
    : List<Client> {
        return clientService.getList(Pageable.ofSize(pageSize).withPage(pageNumber))
    }
}