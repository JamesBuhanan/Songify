package com.songify.library.detail.fake.usecase

import androidx.paging.PagingData
import com.songify.library.detail.fake.TestData
import com.songify.library.detail.usecase.GetTracks
import com.songify.library.spotify.model.SpotifyModel
import com.songify.library.spotify.model.SpotifyModel.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object FakeGetTracks : GetTracks {
    override suspend fun invoke(spotifyModel: SpotifyModel): Flow<PagingData<Track>> {
        return flowOf(PagingData.from(TestData.tracks))
    }
}
