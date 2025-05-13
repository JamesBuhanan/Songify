package com.songify.feature.detail.internal

import com.slack.circuit.runtime.screen.Screen
import com.songify.feature.detail.DetailScreen
import com.songify.library.spotify.model.SpotifyModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StartScreenModule {
    companion object {
        @Provides
        fun providesStartScreen(): Screen = DetailScreen(
            SpotifyModel.Album(
                id = TODO(),
                caption = TODO(),
                imageUrlString = TODO(),
                artistsString = TODO(),
                yearOfRelease = TODO()
            )
        )
    }
}
