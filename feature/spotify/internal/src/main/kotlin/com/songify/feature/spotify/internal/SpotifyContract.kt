package com.songify.feature.spotify.internal

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.songify.library.spotify.response.NewReleasesResponse

sealed interface SpotifyEvent : CircuitUiEvent {
    data object TappedBack : SpotifyEvent
}

sealed interface SpotifyState : CircuitUiState {
    data object Loading : SpotifyState

    data class Success(
        val newReleasesResponse: NewReleasesResponse,
        val eventSink: (SpotifyEvent) -> Unit,
    ) : SpotifyState

    data class Error(val message: String) : SpotifyState
}
