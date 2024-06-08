package com.songify.feature.search.internal

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.songify.feature.search.internal.model.Genre

sealed interface SearchEvent : CircuitUiEvent {
    data object TappedBack : SearchEvent
    data object TappedCard : SearchEvent
}

sealed interface SearchState : CircuitUiState {
    data object Loading : SearchState

    data class Success(
        val genres: List<Genre>,
        val eventSink: (SearchEvent) -> Unit,
    ) : SearchState

    data class Error(val message: String) : SearchState
}
