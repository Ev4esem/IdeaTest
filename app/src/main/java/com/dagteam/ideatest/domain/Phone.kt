package com.dagteam.ideatest.domain

data class Phone(
    val id: Int,
    val name: String,
    val time: String,
    val tags: List<String>,
    val amount: Int,
)
