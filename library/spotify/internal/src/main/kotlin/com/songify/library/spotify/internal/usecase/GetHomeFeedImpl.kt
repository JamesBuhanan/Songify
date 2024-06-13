package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.internal.SpotifyService
import com.songify.library.spotify.internal.model.AlbumMetadataResponse
import com.songify.library.spotify.internal.model.FeaturedPlaylistsResponse
import com.songify.library.spotify.internal.model.NewReleasesResponse
import com.songify.library.spotify.internal.model.PlaylistMetadataResponse
import com.songify.library.spotify.internal.model.PlaylistsForSpecificCategoryResponse
import com.songify.library.spotify.model.CarouselCard
import com.songify.library.spotify.model.HomeFeed
import com.songify.library.spotify.model.HomeFeedCarousel
import com.songify.library.spotify.usecase.GetHomeFeed
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetHomeFeedImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetHomeFeed {
    override suspend fun invoke(): Result<HomeFeed> =
        try {
            coroutineScope {
                val carousels = mutableListOf<HomeFeedCarousel>()

                val newReleasesTask = async {
                    spotifyService.getNewReleases(songifySession.requireAccessToken())
                        .toHomeFeedCarousel()
                }
                val featuredPlaylistsTask: Deferred<HomeFeedCarousel> = async {
                    spotifyService.getFeaturedPlaylists(
                        token = songifySession.requireAccessToken(),
                        locale = "",
                        timestamp = ""
                    ).toHomeFeedCarousel()
                }
                val playlistsByCategoryTask = async { getPlaylists() }

                awaitAll(newReleasesTask, featuredPlaylistsTask, playlistsByCategoryTask)

                carousels.add(newReleasesTask.getCompleted())
                carousels.add(featuredPlaylistsTask.getCompleted())
                carousels.addAll(playlistsByCategoryTask.getCompleted())

                Result.success(HomeFeed(carousels))
            }
        } catch (ex: HttpException) {
            Result.failure(ex)
        }

    private suspend fun getPlaylists(): List<HomeFeedCarousel> =
        coroutineScope {
            val categories = spotifyService.getBrowseCategories(songifySession.requireAccessToken())
                .categories
                .items

            // instead of fetching playlists for each category in a sequential manner
            // fetch it in parallel
            val playlistsMap = categories.map { category ->
                async {
                    if (category.id == "0JQ5DAqbMKFJhFzxyONOIo") {
                        category to PlaylistsForSpecificCategoryResponse(
                            PlaylistsForSpecificCategoryResponse.PlaylistsForSpecificCategoryResponseItems(
                                emptyList()
                            )
                        )
                    } else {
                        category to spotifyService.getPlaylistsForCategory(
                            token = songifySession.requireAccessToken(),
                            categoryId = category.id,
                        )
                    }
                }
            }

            playlistsMap.awaitAll().map { pair ->
                val (browseCategoryResponse, playlistsForSpecificCategoryResponse) = pair

                HomeFeedCarousel(
                    id = browseCategoryResponse.id,
                    title = browseCategoryResponse.name,
                    cards = playlistsForSpecificCategoryResponse.toCards()
                )
            }
        }
}

private fun NewReleasesResponse.toHomeFeedCarousel() = HomeFeedCarousel(
    id = "Newly Released Albums",
    title = "Newly Released Albums",
    cards = this.albums.items.toAlbumCards()
)

private fun List<AlbumMetadataResponse>.toAlbumCards(): List<CarouselCard> {
    return map {
        CarouselCard.AlbumCard(
            id = it.id,
            imageUrlString = it.images.firstOrNull()?.url,
            caption = it.name,
            artistsString = it.artists.joinToString(", ") { it.name },
            name = it.name,
            yearOfReleaseString = it.releaseDate.substring(0..3) // yyyy-mm-dd
        )
    }
}

private fun FeaturedPlaylistsResponse.toHomeFeedCarousel() = HomeFeedCarousel(
    id = "Featured Playlists",
    title = "Featured Playlists",
    cards = this.playlists.items.toPlaylistCards()
)

private fun List<PlaylistMetadataResponse>.toPlaylistCards(): List<CarouselCard> {
    return map {
        CarouselCard.PlaylistCard(
            id = it.id,
            caption = it.name,
            imageUrlString = it.images.firstOrNull()?.url,
            name = it.name,
            ownerName = it.ownerName.value,
            totalNumberOfTracks = it.totalNumberOfTracks.value.toString(),
        )
    }
}

fun PlaylistsForSpecificCategoryResponse.toCards(): List<CarouselCard> {
    return playlists.items.map {
        CarouselCard.PlaylistCard(
            id = it.id,
            name = it.name,
            caption = it.name,
            ownerName = it.ownerName.value,
            totalNumberOfTracks = it.totalNumberOfTracks.value.toString(),
            imageUrlString = it.imageUrlList.firstOrNull()?.url
        )
    }
}
