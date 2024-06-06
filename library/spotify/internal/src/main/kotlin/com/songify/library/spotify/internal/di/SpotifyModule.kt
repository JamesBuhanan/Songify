package com.songify.library.spotify.internal.di

import com.songify.common.di.AppScope
import com.songify.library.spotify.internal.SpotifyService
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
    }
}
