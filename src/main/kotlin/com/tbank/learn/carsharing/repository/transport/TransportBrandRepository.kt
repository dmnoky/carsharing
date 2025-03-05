package com.tbank.learn.carsharing.repository.transport

import com.tbank.learn.carsharing.model.transport.TransportBrand
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransportBrandRepository : CrudRepository<TransportBrand, UUID>, PagingAndSortingRepository<TransportBrand, UUID> {
    @Modifying
    @Query("delete from carsharing.transport_brand where id = :id")
    override fun deleteById(@Param("id") id: UUID)

}