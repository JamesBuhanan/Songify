package com.songify.feature.spotify.internal

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

sealed interface SpotifyEvent : CircuitUiEvent {
    data object TappedBack : SpotifyEvent
}

sealed interface SpotifyState : CircuitUiState {
    data object Loading : SpotifyState

    data class Success(
//        val Spotify: Flow<PagingData<Post>>,
        val eventSink: (SpotifyEvent) -> Unit,
    ) : SpotifyState
}