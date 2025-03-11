package com.dagteam.ideatest.di

import android.content.Context
import androidx.room.Room
import com.dagteam.ideatest.data.local.MainDao
import com.dagteam.ideatest.data.local.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MainDatabase {
        return Room
            .databaseBuilder(
                context.applicationContext,
                MainDatabase::class.java,
                "main_database.db"
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(database: MainDatabase): MainDao = database.mainDao()

}