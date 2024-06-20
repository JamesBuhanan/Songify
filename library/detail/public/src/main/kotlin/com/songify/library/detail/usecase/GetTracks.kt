package com.songify.library.detail.usecase

import androidx.paging.PagingData
import com.songify.library.spotify.model.SpotifyModel
import com.songify.library.spotify.model.SpotifyModel.Track
import kotlinx.coroutines.flow.Flow

interface GetTracks {
    suspend operator fun invoke(spotifyModel: SpotifyModel): Flow<PagingData<Track>>
}