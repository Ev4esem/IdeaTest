package com.dagteam.ideatest.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dagteam.ideatest.domain.MainRepository
import com.dagteam.ideatest.presentation.main.mvi.DialogState
import com.dagteam.ideatest.presentation.main.mvi.MainIntent
import com.dagteam.ideatest.presentation.main.mvi.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            init()
        }
    }

    fun handleIntent(intent: MainIntent) {
        viewModelScope.launch {
            when (intent) {
                is MainIntent.RemovePhone -> {
                    removePhone(intent.id)
                }

                is MainIntent.UpdatePhone -> {
                    updatePhone(intent.id, intent.amount)
                }

                is MainIntent.SearchQuery -> {
                    search(intent.query)
                }

                is MainIntent.DismissDialog -> {
                    dismissDialog()
                }

                is MainIntent.ShowDialog -> {
                    showDialog(intent.dialogState)
                }
            }
        }
    }

    private fun dismissDialog() {
        _uiState.update { currentState ->
            currentState.copy(
                dialogState = DialogState.None,
            )
        }
    }

    private fun showDialog(dialogState: DialogState) {
        _uiState.update { currentState ->
            currentState.copy(
                dialogState = dialogState,
            )
        }
    }

    private suspend fun init() {
        mainRepository.getPhones()
            .onEach {
                _uiState.update { currentState ->
                    currentState.copy(
                        loading = true,
                        error = null,
                    )
                }
            }
            .catch { throwable ->
                _uiState.update { currentState ->
                    currentState.copy(
                        error = throwable.message,
                        loading = false,
                    )
                }
            }
            .collect { phones ->
                _uiState.update { currentState ->
                    currentState.copy(
                        loading = false,
                        error = null,
                        phones = phones,
                    )
                }
            }
    }

    private suspend fun search(query: String) {
        _uiState.update { currentState ->
            currentState.copy(
                query = query,
            )
        }
        mainRepository.search(query).collect { filterList ->
            _uiState.update { currentState ->
                currentState.copy(
                    phones = filterList,
                )
            }
        }
    }

    private suspend fun removePhone(id: Int) {
        mainRepository.removePhone(id)
        _uiState.update { currentState ->
            currentState.copy(
                dialogState = DialogState.None
            )
        }
    }

    private suspend fun updatePhone(id: Int, amount: Int) {
        mainRepository.updatePhone(id, amount)
        _uiState.update { currentState ->
            currentState.copy(
                dialogState = DialogState.None
            )
        }
    }
}