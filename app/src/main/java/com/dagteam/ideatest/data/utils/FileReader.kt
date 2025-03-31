package com.dagteam.ideatest.data.utils

import android.content.Context
import com.dagteam.ideatest.data.PhoneEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun readJsonFromAssets(context: Context, filename: String): String {
    return context.assets.open(filename).bufferedReader().use { it.readText() }
}

fun parsePhonesJson(json: String): List<PhoneEntity> {
    val type = object : TypeToken<List<PhoneEntity>>() {}.type
    return Gson().fromJson(json, type)
}

fun isDataInitialized(context: Context): Boolean {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    return prefs.getBoolean("data_initialized", false)
}

fun setDataInitialized(context: Context) {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    prefs.edit().putBoolean("data_initialized", true).apply()
}