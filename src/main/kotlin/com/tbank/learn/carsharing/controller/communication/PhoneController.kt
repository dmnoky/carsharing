package com.tbank.learn.carsharing.controller.communication

import com.tbank.learn.carsharing.dto.communication.request.PhoneUpsertDto
import com.tbank.learn.carsharing.model.communication.Phone
import com.tbank.learn.carsharing.service.communication.PhoneService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/phone")
@RestController
@Validated
class PhoneController(open val phoneService: PhoneService) {
    @GetMapping(value = ["/{id}"], produces = [APPLICATION_JSON_VALUE])
    fun getPhone(@PathVariable("id") id: UUID): Phone? {
        return phoneService.getById(id)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun createPhone(@Validated(PhoneUpsertDto.Insert::class) @RequestBody email: PhoneUpsertDto): Phone {
        return phoneService.create(email)
    }

    @PutMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun updatePhone(@Validated(PhoneUpsertDto.Update::class) @RequestBody email: PhoneUpsertDto): Phone {
        return phoneService.update(email)
    }

    @DeleteMapping(value = ["/{id}"])
    fun deletePhone(@PathVariable("id") id: UUID) {
        phoneService.deleteById(id)
    }

    @GetMapping(value = ["/list"], produces = [APPLICATION_JSON_VALUE])
    fun getListPhone(@RequestParam(name = "pageNumber", defaultValue = "0") pageNumber: Int,
                        @RequestParam(name = "pageSize"  , defaultValue = "10") pageSize: Int)
            : List<Phone> {
        return phoneService.getList(Pageable.ofSize(pageSize).withPage(pageNumber))
    }
}