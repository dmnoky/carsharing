package com.tbank.learn.carsharing.controller.transport

import com.tbank.learn.carsharing.model.transport.Transport
import com.tbank.learn.carsharing.service.transport.TransportService
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/transport")
@RestController
@Validated
open class TransportController(open val transportService: TransportService) {
    @GetMapping(value = ["/{id}"], produces = [APPLICATION_JSON_VALUE])
    fun getTransport(@PathVariable("id") id: UUID): Transport? {
        return transportService.getById(id)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun createTransport(@Valid @RequestBody transport: Transport): Transport {
        return transportService.create(transport)
    }

    @PutMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun updateTransport(@Valid @RequestBody transport: Transport): Transport {
        return transportService.update(transport)
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteTransport(@PathVariable("id") id: UUID) {
        transportService.deleteById(id)
    }

    @GetMapping(value = ["/list"], produces = [APPLICATION_JSON_VALUE])
    fun getListTransport(@RequestParam(name = "pageNumber", defaultValue = "0") pageNumber: Int,
                        @RequestParam(name = "pageSize"  , defaultValue = "10") pageSize: Int)
            : List<Transport> {
        return transportService.getList(Pageable.ofSize(pageSize).withPage(pageNumber))
    }
}