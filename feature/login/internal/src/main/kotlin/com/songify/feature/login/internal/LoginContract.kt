package com.songify.feature.login.internal

import com.slack.circuit.runtime.CircuitUiState

sealed interface LoginState : CircuitUiState {
    data object Loading : LoginState
}
