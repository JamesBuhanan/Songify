package com.songify.feature.premium.app.di

import com.slack.circuit.runtime.screen.Screen
import com.songify.feature.premium.DetailScreen
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
        private val album = Album(
            id = "1Mo4aZ8pdj6L1jx8zSwJnt",
            caption = "THE TORTURED POETS DEPARTMENT",
            imageUrlString = "https://i.scdn.co/image/ab67616d00001e025076e4160d018e378f488c33",
            artistsString = "THE TORTURED POETS DEPARTMENT",
            yearOfRelease = "2024",
        )

        private val playlist = Playlist(
            id = "37i9dQZF1DXcBWIGoYBM5M",
            caption = "Today’s Top Hits",
            imageUrlString = "https://i.scdn.co/image/ab67706f000000028695a5a2512e4c614fdbfc39",
            name = "Today’s Top Hits",
            ownerName = "Spotify",
            totalNumberOfTracks = "50",
        )

        @Provides
        fun providesStartScreen(): Screen = DetailScreen(playlist)
    }
}
