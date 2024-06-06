package com.songify.feature.home.internal

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.songify.library.spotify.model.HomeFeed

sealed interface HomeEvent : CircuitUiEvent {
    data object TappedBack : HomeEvent
    data object TappedCard : HomeEvent
}

sealed interface HomeState : CircuitUiState {
    data object Loading : HomeState

    data class Success(
        val homeFeed: HomeFeed,
        val eventSink: (HomeEvent) -> Unit,
    ) : HomeState

    data class Error(val message: String) : HomeState
}
