package com.dagteam.ideatest.data.mapper

import com.dagteam.ideatest.data.PhoneEntity
import com.dagteam.ideatest.domain.Phone
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun PhoneEntity.toPhone(): Phone = Phone(
    id = id,
    name = name,
    time = time.toTime(),
    tags = tags,
    amount = amount,
)

fun List<PhoneEntity>.toPhones(): List<Phone> {
   return map { it.toPhone() }
}

fun Long.toTime(): String {
    val date = Date(this)
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return dateFormat.format(date)
}