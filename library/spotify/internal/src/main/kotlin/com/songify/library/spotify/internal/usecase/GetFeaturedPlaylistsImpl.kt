package com.songify.library.spotify.internal.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.songify.library.di.AppScope
import com.songify.library.spotify.internal.paging.FeaturedPlaylistsPagingSource
import com.songify.library.spotify.model.SpotifyModel
import com.songify.library.spotify.usecase.GetFeaturedPlaylists
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class GetFeaturedPlaylistsImpl @Inject constructor(
    private val pagingConfig: PagingConfig,
    private val featuredPlaylistsPagingSource: FeaturedPlaylistsPagingSource
) : GetFeaturedPlaylists {
    override operator fun invoke(): Flow<PagingData<SpotifyModel>> =
        Pager(pagingConfig) { featuredPlaylistsPagingSource }.flow
}
