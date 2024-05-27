package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.SpotifyService
import com.songify.library.spotify.response.HomeFeed
import com.songify.library.spotify.response.PlaylistsForSpecificCategoryResponse
import com.songify.library.spotify.usecase.GetHomeFeed
import com.squareup.anvil.annotations.ContributesBinding
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
    override suspend fun invoke(): Result<HomeFeed> = coroutineScope {
        try {
            val newReleasesTask =
                async { spotifyService.getNewReleases(songifySession.requireAccessToken()) }

            val featuredPlaylistsTask = async {
                spotifyService.getFeaturedPlaylists(
                    token = songifySession.requireAccessToken(),
                    locale = "",
                    timestamp = ""
                )
            }
            val playlistsForCategoryTask = async { getPlaylists() }

            awaitAll(newReleasesTask, featuredPlaylistsTask, playlistsForCategoryTask)

            val newReleasesResponse = newReleasesTask.getCompleted()
            val featuredPlaylistsResponse = featuredPlaylistsTask.getCompleted()
            val playlistsForSpecificCategoryResponses = playlistsForCategoryTask.getCompleted()

            Result.success(
                HomeFeed(
                    newReleasesResponse = newReleasesResponse,
                    featuredPlaylistsResponse = featuredPlaylistsResponse,
                    playlistsForSpecificCategoryResponses = playlistsForSpecificCategoryResponses,
                )
            )
        } catch (ex: HttpException) {
            Result.failure(ex)
        }
    }

    private suspend fun getPlaylists(): List<PlaylistsForSpecificCategoryResponse> =
        coroutineScope {
            val categories = spotifyService.getBrowseCategories(songifySession.requireAccessToken())
                .categories
                .items

            // instead of fetching playlists for each category in a sequential manner
            // fetch it in parallel
            val playlistsMap = categories.map { huh ->
                async {
                    spotifyService.getPlaylistsForCategory(
                        token = songifySession.requireAccessToken(),
                        categoryId = huh.id,
                    )
                }
            }

            playlistsMap.awaitAll().map { it }
        }
}