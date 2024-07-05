package com.songify.library.spotify.internal.di

import androidx.paging.PagingConfig
import com.songify.library.di.AppScope
import com.songify.library.spotify.internal.SpotifyService
import com.songify.library.spotify.internal.paging.SpotifyPagingSource
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
@ContributesTo(AppScope::class)
interface SpotifyModule {
    companion object {
        @Provides
        fun providesSpotifyService(retrofit: Retrofit): SpotifyService = retrofit.create()

        @Provides
        fun providesPagingConfig(): PagingConfig = PagingConfig(
            pageSize = SpotifyPagingSource.DEFAULT_PAGE_SIZE,
            initialLoadSize = SpotifyPagingSource.DEFAULT_PAGE_SIZE
        )
    }
}
