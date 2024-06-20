package com.songify.library.spotify.internal.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.songify.library.spotify.internal.paging.NewReleasesPagingSource
import com.songify.library.spotify.model.SpotifyModel
import com.songify.library.spotify.usecase.GetNewReleases
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewReleasesImpl @Inject constructor(
    private val pagingConfig: PagingConfig,
    private val newReleasesPagingSource: NewReleasesPagingSource
) : GetNewReleases {
    override operator fun invoke(): Flow<PagingData<SpotifyModel>> =
        Pager(pagingConfig) { newReleasesPagingSource }.flow
}