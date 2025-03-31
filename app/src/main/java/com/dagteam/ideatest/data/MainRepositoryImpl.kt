package com.dagteam.ideatest.data

import android.content.Context
import com.dagteam.ideatest.data.local.MainDao
import com.dagteam.ideatest.data.mapper.toPhones
import com.dagteam.ideatest.data.utils.isDataInitialized
import com.dagteam.ideatest.data.utils.parsePhonesJson
import com.dagteam.ideatest.data.utils.readJsonFromAssets
import com.dagteam.ideatest.data.utils.setDataInitialized
import com.dagteam.ideatest.domain.MainRepository
import com.dagteam.ideatest.domain.Phone
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDao: MainDao,
    @ApplicationContext private val context: Context,
): MainRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        coroutineScope.launch {
            if (!isDataInitialized(context)) {
                val json = readJsonFromAssets(context, "phones.json")
                val phones = parsePhonesJson(json)
                mainDao.insertAll(phones)
                setDataInitialized(context)
            }
        }
    }

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