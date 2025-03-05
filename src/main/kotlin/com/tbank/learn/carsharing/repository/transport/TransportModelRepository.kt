package com.tbank.learn.carsharing.repository.transport

import com.tbank.learn.carsharing.model.transport.TransportModel
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransportModelRepository : CrudRepository<TransportModel, UUID>, PagingAndSortingRepository<TransportModel, UUID> {
    @Modifying
    @Query("delete from carsharing.transport_model where id = :id")
    override fun deleteById(@Param("id") id: UUID)

}