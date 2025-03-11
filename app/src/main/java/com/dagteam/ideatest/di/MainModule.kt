package com.dagteam.ideatest.di

import com.dagteam.ideatest.data.MainRepositoryImpl
import com.dagteam.ideatest.domain.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
@InstallIn(SingletonComponent::class)
interface MainModule {

    @Binds
    @Singleton
    fun bindMainRepositoryImplToMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

}