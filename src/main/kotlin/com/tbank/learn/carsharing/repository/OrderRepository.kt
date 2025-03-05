package com.tbank.learn.carsharing.repository

import com.tbank.learn.carsharing.model.order.Order
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : CrudRepository<Order, UUID>, PagingAndSortingRepository<Order, UUID> {
    @Modifying
    @Query("delete from carsharing.order where id = :id")
    override fun deleteById(@Param("id") id: UUID)
    
    @Query("select o.* from carsharing.order o where o.client_id = :clientId")
    fun findAllByClientId(@Param("clientId") clientId: UUID) : HashSet<Order>
}