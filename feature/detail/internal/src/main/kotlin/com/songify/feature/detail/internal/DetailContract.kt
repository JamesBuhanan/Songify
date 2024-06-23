package com.songify.feature.detail.internal

import androidx.paging.PagingData
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.songify.library.spotify.model.SpotifyModel
import com.songify.library.spotify.model.SpotifyModel.Track
import kotlinx.coroutines.flow.Flow

internal sealed interface DetailEvent : CircuitUiEvent {
    data object TappedBack : DetailEvent
    data class TappedTrack(val track: Track) : DetailEvent
}

internal sealed interface DetailState : CircuitUiState {
    data object Loading : DetailState

    data class Success(
        val spotifyModel: SpotifyModel,
        val tracks: Flow<PagingData<Track>>,
        val eventSink: (DetailEvent) -> Unit,
    ) : DetailState

    data class Error(val message: String) : DetailState
}
