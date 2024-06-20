package com.songify.feature.nowplaying.internal

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.songify.library.spotify.model.SpotifyModel.Track

sealed interface NowPlayingEvent : CircuitUiEvent {
    data object TappedBack : NowPlayingEvent
}

sealed interface NowPlayingState : CircuitUiState {
    data object Loading : NowPlayingState

    data class Success(
        val track: Track,
        val eventSink: (NowPlayingEvent) -> Unit,
    ) : NowPlayingState

    data class Error(val message: String) : NowPlayingState
}
