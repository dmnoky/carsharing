package com.tbank.learn.carsharing.repository

import com.tbank.learn.carsharing.model.transport.Car
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransportRepository : CrudRepository<Car, Long>, PagingAndSortingRepository<Car, Long> {
    @Modifying
    @Query("delete from cars.transport where id = :id")
    override fun deleteById(@Param("id") id: Long)

}