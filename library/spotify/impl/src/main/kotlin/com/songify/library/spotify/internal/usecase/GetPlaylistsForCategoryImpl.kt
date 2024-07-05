package com.songify.library.spotify.internal.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.songify.library.di.AppScope
import com.songify.library.session.SongifySession
import com.songify.library.spotify.internal.SpotifyService
import com.songify.library.spotify.internal.paging.PlaylistsForCategoryPagingSource
import com.songify.library.spotify.model.SpotifyModel
import com.songify.library.spotify.usecase.GetPlaylistsForCategory
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class GetPlaylistsForCategoryImpl @Inject constructor(
    private val pagingConfig: PagingConfig,
    private val songifySession: SongifySession,
    private val spotifyService: SpotifyService,
) : GetPlaylistsForCategory {
    override operator fun invoke(categoryId: String): Flow<PagingData<SpotifyModel>> =
        Pager(pagingConfig) {
            PlaylistsForCategoryPagingSource(
                categoryId = categoryId,
                songifySession = songifySession,
                spotifyService = spotifyService,
            )
        }.flow
}
