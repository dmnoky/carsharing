package com.tbank.learn.carsharing.service

import com.tbank.learn.carsharing.exception.NotFoundInRepository
import com.tbank.learn.carsharing.model.order.Order
import com.tbank.learn.carsharing.repository.ClientRepository
import com.tbank.learn.carsharing.repository.OrderRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class OrderService(
    val orderRepository: OrderRepository,
    val clientRepository: ClientRepository
) {
    @Transactional(readOnly = true)
    fun getList(pageable: Pageable): List<Order> {
        return orderRepository.findAll().toList()
    }
    
    @Transactional(readOnly = true)
    fun getListWithClient(pageable: Pageable): List<Order> { //List<Order> -> List<OrderWithClient>
        val orders = orderRepository.findAll().toList()
        return orders
    }
    
    @Transactional(readOnly = true)
    fun getById(id: UUID): Order? {
        return orderRepository.findById(id).orElseThrow { NotFoundInRepository("Заказ не найден!") }
    }
    
    @Transactional
    fun create(order: Order): Order {
        return orderRepository.save(order)
    }

    @Transactional
    fun update(order: Order): Order {
        return orderRepository.save(order)
    }

    @Transactional
    fun deleteById(id: UUID) {
        orderRepository.deleteById(id)
    }
}