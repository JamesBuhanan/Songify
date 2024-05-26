package com.songify.feature.home.internal

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.songify.library.spotify.response.FeaturedPlaylistsResponse
import com.songify.library.spotify.response.NewReleasesResponse
import com.songify.library.spotify.response.PlaylistsForSpecificCategoryResponse

sealed interface HomeEvent : CircuitUiEvent {
    data object TappedBack : HomeEvent
}

sealed interface HomeState : CircuitUiState {
    data object Loading : HomeState

    data class Success(
        val newReleasesResponse: NewReleasesResponse,
        val featuredPlaylistsResponse: FeaturedPlaylistsResponse,
        val playlistsForSpecificCategoryResponse: PlaylistsForSpecificCategoryResponse,
        val eventSink: (HomeEvent) -> Unit,
    ) : HomeState

    data class Error(val message: String) : HomeState
}
