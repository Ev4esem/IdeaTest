package com.dagteam.ideatest.presentation.main.mvi

import com.dagteam.ideatest.domain.Phone

sealed class DialogState {
    data object None : DialogState()
    data class Edit(val phone: Phone) : DialogState()
    data class Delete(val phone: Phone) : DialogState()
}