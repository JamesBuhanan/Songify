package com.songify.feature.home.app.di

import androidx.paging.PagingData
import com.slack.circuit.runtime.screen.Screen
import com.songify.feature.nowplaying.NowPlayingScreen
import com.songify.library.home.model.HomeFeed
import com.songify.library.home.model.HomeFeedCarousel
import com.songify.library.home.usecase.GetHomeFeed
import com.songify.library.spotify.model.SpotifyModel.Playlist
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.flowOf

@Module
@InstallIn(SingletonComponent::class)
interface HomeAppModule {
    companion object {
        @Provides
        fun providesStartScreen(): Screen = NowPlayingScreen

        @Provides
        fun providesGetHomeFeed(): GetHomeFeed = object : GetHomeFeed {
            override suspend fun invoke(): Result<HomeFeed> {
                return Result.success(
                    HomeFeed(
                        listOf(
                            HomeFeedCarousel(
                                "1", "Thing", flowOf(
                                    PagingData.from(
                                        listOf(
                                            Playlist(
                                                "1a",
                                                "caption",
                                                null,
                                                "name",
                                                "ownerName",
                                                "totalNumberOfTracks"
                                            ),
                                            Playlist(
                                                "1b",
                                                "caption",
                                                null,
                                                "name",
                                                "ownerName",
                                                "totalNumberOfTracks"
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            }
        }
    }
}
