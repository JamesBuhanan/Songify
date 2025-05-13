package com.songify.feature.premium.app.di

import com.slack.circuit.runtime.screen.Screen
import com.songify.feature.detail.DetailScreen
import com.songify.library.spotify.model.SpotifyModel.Album
import com.songify.library.spotify.model.SpotifyModel.Playlist
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DetailAppModule {
    companion object {


        @Provides
        fun providesStartScreen(): Screen = DetailScreen(playlist)
    }
}
