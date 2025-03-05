package com.tbank.learn.carsharing.repository.communication

import com.tbank.learn.carsharing.model.communication.CommType
import com.tbank.learn.carsharing.model.communication.Phone
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PhoneRepository : CrudRepository<Phone, UUID>, PagingAndSortingRepository<Phone, UUID> {
    @Modifying
    @Query("delete from carsharing.phone where id = :id")
    override fun deleteById(@Param("id") id: UUID)
    
    @Query("select * from carsharing.phone where parent_id = :parentId order by is_primary")
    fun findAllByParentId(@Param("parentId") parentId: UUID) : LinkedHashSet<Phone>
    
    @Query("select * from carsharing.phone where parent_id = :parentId and is_primary = true")
    fun findPrimaryByParentId(@Param("parentId") parentId: UUID) : Phone
}