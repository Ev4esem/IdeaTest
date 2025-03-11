package com.dagteam.ideatest.data.local

import androidx.room.Dao
import androidx.room.Query
import com.dagteam.ideatest.data.PhoneEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Query("SELECT * FROM phone_entity")
    fun getPhones(): Flow<List<PhoneEntity>>

    @Query("UPDATE phone_entity SET amount = :amount WHERE id = :id")
    suspend fun updatePhone(id: Int, amount: Int)

    @Query("DELETE FROM phone_entity WHERE id = :id")
    suspend fun removePhone(id: Int)

    @Query("SELECT * FROM phone_entity WHERE name LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<PhoneEntity>>

}