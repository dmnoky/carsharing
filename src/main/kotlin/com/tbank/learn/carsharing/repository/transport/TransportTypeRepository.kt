package com.tbank.learn.carsharing.repository.transport

import com.tbank.learn.carsharing.model.transport.TransportType
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransportTypeRepository : CrudRepository<TransportType, UUID>, PagingAndSortingRepository<TransportType, UUID> {
    @Modifying
    @Query("delete from carsharing.transport_type where id = :id")
    override fun deleteById(@Param("id") id: UUID)

}