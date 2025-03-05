package com.tbank.learn.carsharing.repository.communication

import com.tbank.learn.carsharing.model.communication.Email
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmailRepository : CrudRepository<Email, UUID>, PagingAndSortingRepository<Email, UUID> {
    @Modifying
    @Query("delete from carsharing.email where id = :id")
    override fun deleteById(@Param("id") id: UUID)
    
    @Query("select * from carsharing.email where parent_id = :parentId order by is_primary")
    fun findAllByParentId(@Param("parentId") parentId: UUID) : LinkedHashSet<Email>
    
    @Query("select * from carsharing.email where parent_id = :parentId and is_primary = true")
    fun findPrimaryByParentId(@Param("parentId") parentId: UUID) : Email
}