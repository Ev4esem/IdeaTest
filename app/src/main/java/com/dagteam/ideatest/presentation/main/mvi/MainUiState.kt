package com.dagteam.ideatest.presentation.main.mvi

import com.dagteam.ideatest.domain.Phone

data class MainUiState(
    val dialogState: DialogState = DialogState.None,
    val phones: List<Phone> = listOf(),
    val loading: Boolean = false,
    val error: String? = null,
    val query: String = "",
)
