package com.tbank.learn.carsharing.repository

import com.tbank.learn.carsharing.dto.client.response.ClientBaseView
import com.tbank.learn.carsharing.model.user.Client
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

//https://docs.spring.io/spring-data/relational/reference/jdbc/query-methods.html
@Repository
interface ClientRepository : CrudRepository<Client, UUID>, PagingAndSortingRepository<Client, UUID> {
    @Modifying
    @Query("delete from carsharing.client where id = :id")
    override fun deleteById(@Param("id") id: UUID)
    
    @Query("select * from carsharing.client where id = :id")
    fun findByIdOrderById(@Param("id") id: UUID) : ClientBaseView?
}