package com.dagteam.ideatest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("phone_entity")
data class PhoneEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val time: Long,
    val tags: List<String>,
    val amount: Int,
)
