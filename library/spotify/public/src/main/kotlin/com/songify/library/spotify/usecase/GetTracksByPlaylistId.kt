package com.songify.library.spotify.usecase

import androidx.paging.PagingData
import com.songify.library.spotify.model.SpotifyModel.Track
import kotlinx.coroutines.flow.Flow

interface GetTracksByPlaylistId {
    suspend operator fun invoke(playlistId: String): Flow<PagingData<Track>>
}
