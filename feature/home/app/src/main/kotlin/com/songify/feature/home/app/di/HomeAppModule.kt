package com.songify.feature.home.app.di

import com.slack.circuit.runtime.screen.Screen
import com.songify.common.di.AppScope
import com.songify.feature.home.HomeScreen
import com.songify.library.spotify.model.CarouselCard
import com.songify.library.spotify.model.HomeFeed
import com.songify.library.spotify.model.HomeFeedCarousel
import com.songify.library.spotify.usecase.GetHomeFeed
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@ContributesTo(AppScope::class)
@Module
interface HomeAppModule {
    companion object {
        @Provides
        fun providesStartScreen(): Screen = HomeScreen

        @Provides
        fun providesGetHomeFeed(): GetHomeFeed = object : GetHomeFeed {
            override suspend fun invoke(): Result<HomeFeed> {
                return Result.success(
                    HomeFeed(
                        listOf(
                            HomeFeedCarousel(
                                "1", "Thing", listOf(
                                    CarouselCard.PlaylistCard(
                                        "1a",
                                        "caption",
                                        null,
                                        "name",
                                        "ownerName",
                                        "totalNumberOfTracks"
                                    ),
                                    CarouselCard.PlaylistCard(
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
            }
        }
    }
}