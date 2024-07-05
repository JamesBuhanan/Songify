package com.songify.library.spotify.usecase

import androidx.paging.PagingData
import com.songify.library.spotify.model.SpotifyModel
import kotlinx.coroutines.flow.Flow

interface GetNewReleases {
    operator fun invoke(): Flow<PagingData<SpotifyModel>>
}