package com.dagteam.ideatest.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.dagteam.ideatest.presentation.main.composable.AmountDialog
import com.dagteam.ideatest.presentation.main.composable.DeleteDialog
import com.dagteam.ideatest.presentation.main.composable.ErrorScreen
import com.dagteam.ideatest.presentation.main.composable.LoadingScreen
import com.dagteam.ideatest.presentation.main.composable.MainScreenContent
import com.dagteam.ideatest.presentation.main.mvi.DialogState
import com.dagteam.ideatest.presentation.main.mvi.MainIntent

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    when(val currentDialogState = uiState.dialogState) {
        is DialogState.Delete -> {
            DeleteDialog(
                onDismiss = {
                    viewModel.handleIntent(MainIntent.DismissDialog)
                },
                onConfirm = {
                    viewModel.handleIntent(MainIntent.RemovePhone(currentDialogState.phone.id))
                },
            )
        }
        is DialogState.Edit -> {
            AmountDialog(
                currentAmount = currentDialogState.phone.amount,
                onDismiss = {
                    viewModel.handleIntent(MainIntent.DismissDialog)
                },
                onConfirm = { newAmount ->
                    viewModel.handleIntent(
                        MainIntent.UpdatePhone(
                            id = currentDialogState.phone.id,
                            amount = newAmount,
                        )
                    )
                }
            )
        }
        is DialogState.None -> { /* no op */ }
    }

    if (uiState.error != null) {
        ErrorScreen(
            message = uiState.error ?: ""
        )
    } else if (uiState.loading) {
        LoadingScreen()
    } else {
        MainScreenContent(
            query = uiState.query,
            phones = uiState.phones,
            onIntent = {
                viewModel.handleIntent(it)
            }
        )
    }

}