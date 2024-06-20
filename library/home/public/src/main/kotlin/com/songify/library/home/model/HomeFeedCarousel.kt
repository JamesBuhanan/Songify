package com.songify.library.home.model

import androidx.paging.PagingData
import com.songify.library.spotify.model.SpotifyModel
import kotlinx.coroutines.flow.Flow

data class HomeFeedCarousel(
    val id: String,
    val title: String,
    val spotifyModels: Flow<PagingData<SpotifyModel>>,
)
