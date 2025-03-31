package com.dagteam.ideatest.di

import android.content.Context
import com.dagteam.ideatest.data.MainRepositoryImpl
import com.dagteam.ideatest.data.local.MainDao
import com.dagteam.ideatest.domain.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun provideMainRepositoryImplToMainRepository(
        @ApplicationContext context: Context,
        mainDao: MainDao
    ): MainRepository = MainRepositoryImpl(
        mainDao,
        context
    )

}