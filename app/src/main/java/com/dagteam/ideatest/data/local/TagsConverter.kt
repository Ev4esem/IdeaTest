package com.dagteam.ideatest.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TagsConverter {

    @TypeConverter
    fun fromTags(tagsJson: String): List<String> {
        val tags = Gson().fromJson<List<String>>(tagsJson, object : TypeToken<List<String>>() {}.type)
        return tags
    }

    @TypeConverter
    fun toTags(tags: List<String>): String {
        val gson = Gson().toJson(tags)
        return gson
    }

}