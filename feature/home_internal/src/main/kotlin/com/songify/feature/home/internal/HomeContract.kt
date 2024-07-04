package com.songify.feature.home.internal

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.songify.feature.home.internal.model.HomeFeed
import com.songify.library.spotify.model.SpotifyModel

sealed interface HomeEvent : CircuitUiEvent {
    data object TappedBack : HomeEvent
    data class TappedCard(val spotifyModel: SpotifyModel) : HomeEvent
}

sealed interface HomeState : CircuitUiState {
    data object Loading : HomeState

    data class Success(
        val homeFeed: HomeFeed,
        val eventSink: (HomeEvent) -> Unit,
    ) : HomeState

    data class Error(val message: String) : HomeState
}
