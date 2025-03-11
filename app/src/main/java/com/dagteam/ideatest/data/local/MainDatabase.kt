package com.dagteam.ideatest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dagteam.ideatest.data.PhoneEntity

@Database(entities = [PhoneEntity::class], version = 1)
@TypeConverters(TagsConverter::class)
abstract class MainDatabase: RoomDatabase() {

    abstract fun mainDao(): MainDao

}