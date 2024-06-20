package com.songify.feature.home.sharedtest

import androidx.paging.PagingData
import com.songify.feature.home.sharedtest.TestData.homeFeed
import com.songify.library.home.model.HomeFeed
import com.songify.library.home.model.HomeFeedCarousel
import com.songify.library.home.usecase.GetHomeFeed
import com.songify.library.spotify.model.SpotifyModel
import kotlinx.coroutines.flow.flowOf

object TestData {
    const val NEW_RELEASES = "New Releases"
    val album = SpotifyModel.Album(
        "id",
        "caption",
        null,
        "artistsString",
        "yearOfRelease"
    )

    val homeFeed = HomeFeed(
        carousels = listOf(
            HomeFeedCarousel(
                id = "id",
                title = NEW_RELEASES,
                spotifyModels = flowOf(
                    PagingData.from(
                        listOf(
                            album
                        )
                    )
                )
            )
        )
    )
}


class FakeGetHomeFeed : GetHomeFeed {
    override suspend operator fun invoke() = Result.success(homeFeed)
}
