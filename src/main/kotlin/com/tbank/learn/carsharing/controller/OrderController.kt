package com.tbank.learn.carsharing.controller;

import com.tbank.learn.carsharing.model.Order
import com.tbank.learn.carsharing.service.OrderService
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RequestMapping("/order")
@RestController
@Validated
class OrderController (val orderService: OrderService) {

    @GetMapping(value = ["/{id}"], produces = [APPLICATION_JSON_VALUE])
    fun getOrder(@PathVariable("id") id: Long): Order? {
        return orderService.getById(id)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun createOrder(@Valid @RequestBody order: Order): Order? {
        return orderService.create(order)
    }

    @PutMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun updateOrder(@Valid @RequestBody order: Order): Order? {
        return orderService.update(order)
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteOrder(@PathVariable("id") id: Long) {
        orderService.deleteById(id)
    }

    @GetMapping(value = ["/list"], produces = [APPLICATION_JSON_VALUE])
    fun getListOrder(@RequestParam(name = "pageNumber", defaultValue = "0")  pageNumber: Int,
                     @RequestParam(name = "pageSize"  , defaultValue = "10") pageSize: Int)
            : List<Order> {
        return orderService.getList(Pageable.ofSize(pageSize).withPage(pageNumber))
    }
}
