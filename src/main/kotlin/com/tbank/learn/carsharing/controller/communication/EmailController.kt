package com.tbank.learn.carsharing.controller.communication

import com.tbank.learn.carsharing.dto.communication.request.EmailUpsertDto
import com.tbank.learn.carsharing.model.communication.Email
import com.tbank.learn.carsharing.service.communication.EmailService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/email")
@RestController
@Validated
class EmailController(open val emailService: EmailService) {
    @GetMapping(value = ["/{id}"], produces = [APPLICATION_JSON_VALUE])
    fun getEmail(@PathVariable("id") id: UUID): Email? {
        return emailService.getById(id)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun createEmail(@Validated(EmailUpsertDto.Insert::class) @RequestBody email: EmailUpsertDto): Email {
        return emailService.create(email)
    }

    @PutMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun updateEmail(@Validated(EmailUpsertDto.Update::class) @RequestBody email: EmailUpsertDto): Email {
        return emailService.update(email)
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteEmail(@PathVariable("id") id: UUID) {
        emailService.deleteById(id)
    }

    @GetMapping(value = ["/list"], produces = [APPLICATION_JSON_VALUE])
    fun getListEmail(@RequestParam(name = "pageNumber", defaultValue = "0") pageNumber: Int,
                        @RequestParam(name = "pageSize"  , defaultValue = "10") pageSize: Int)
            : List<Email> {
        return emailService.getList(Pageable.ofSize(pageSize).withPage(pageNumber))
    }
}