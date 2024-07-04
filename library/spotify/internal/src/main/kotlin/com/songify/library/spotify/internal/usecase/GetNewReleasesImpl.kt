package com.songify.library.spotify.internal.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.songify.library.di.AppScope
import com.songify.library.spotify.internal.paging.NewReleasesPagingSource
import com.songify.library.spotify.model.SpotifyModel
import com.songify.library.spotify.usecase.GetNewReleases
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@AppScope
@ContributesBinding(AppScope::class)
class GetNewReleasesImpl @Inject constructor(
    private val pagingConfig: PagingConfig,
    private val newReleasesPagingSource: NewReleasesPagingSource
) : GetNewReleases {
    override operator fun invoke(): Flow<PagingData<SpotifyModel>> =
        Pager(pagingConfig) { newReleasesPagingSource }.flow
}
