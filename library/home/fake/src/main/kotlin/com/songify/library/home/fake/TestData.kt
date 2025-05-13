package com.songify.library.home.fake

import androidx.paging.PagingData
import com.songify.library.home.model.HomeFeed
import com.songify.library.home.model.HomeFeedCarousel
import com.songify.library.spotify.model.SpotifyModel
import com.songify.library.spotify.model.SpotifyModel.Playlist
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

    val fakeHomeFeed = HomeFeed(
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
}
