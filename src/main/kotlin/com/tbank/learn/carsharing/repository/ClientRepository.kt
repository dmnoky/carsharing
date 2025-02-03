package com.tbank.learn.carsharing.repository

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
interface ClientRepository : CrudRepository<Client, Long>, PagingAndSortingRepository<Client, Long> {
    @Modifying
    @Query("delete from cars.user_table where id = :id")
    override fun deleteById(@Param("id") id: Long)
}