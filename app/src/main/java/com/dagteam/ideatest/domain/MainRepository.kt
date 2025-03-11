package com.dagteam.ideatest.domain

import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getPhones(): Flow<List<Phone>>

    suspend fun removePhone(id: Int)

    suspend fun updatePhone(id: Int, amount: Int)

    fun search(query: String): Flow<List<Phone>>

}