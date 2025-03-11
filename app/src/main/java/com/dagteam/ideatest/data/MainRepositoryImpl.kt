package com.dagteam.ideatest.data

import com.dagteam.ideatest.data.local.MainDao
import com.dagteam.ideatest.data.mapper.toPhones
import com.dagteam.ideatest.domain.MainRepository
import com.dagteam.ideatest.domain.Phone
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDao: MainDao
): MainRepository {

    override fun getPhones(): Flow<List<Phone>> = mainDao.getPhones().map {
        it.toPhones()
    }

    override suspend fun removePhone(id: Int) {
        mainDao.removePhone(id)
    }

    override suspend fun updatePhone(id: Int, amount: Int) {
        mainDao.updatePhone(id, amount)
    }

    override fun search(query: String): Flow<List<Phone>> = mainDao.search(query).map {
        it.toPhones()
    }

}