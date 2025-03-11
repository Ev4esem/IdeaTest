package com.dagteam.ideatest.presentation.main.mvi

sealed interface MainIntent {

    data class ShowDialog(val dialogState: DialogState): MainIntent

    data object DismissDialog: MainIntent

    data class RemovePhone(val id: Int): MainIntent

    data class UpdatePhone(val id: Int, val amount: Int): MainIntent

    data class SearchQuery(val query: String): MainIntent

}