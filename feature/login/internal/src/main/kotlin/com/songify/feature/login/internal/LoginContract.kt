package com.songify.feature.login.internal

import com.slack.circuit.runtime.CircuitUiState

internal sealed interface LoginState : CircuitUiState {
    data object Loading : LoginState
}
