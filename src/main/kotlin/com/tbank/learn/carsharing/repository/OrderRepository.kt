package com.tbank.learn.carsharing.repository

import com.tbank.learn.carsharing.model.Order
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, Long>, PagingAndSortingRepository<Order, Long> {
    @Modifying
    @Query("delete from cars.order where id = :id")
    override fun deleteById(@Param("id") id: Long)
}