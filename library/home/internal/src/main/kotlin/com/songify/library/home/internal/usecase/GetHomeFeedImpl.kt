package com.songify.library.home.internal.usecase

import com.songify.library.home.model.HomeFeed
import com.songify.library.home.model.HomeFeedCarousel
import com.songify.library.home.usecase.GetHomeFeed
import com.songify.library.spotify.usecase.GetCategories
import com.songify.library.spotify.usecase.GetFeaturedPlaylists
import com.songify.library.spotify.usecase.GetNewReleases
import com.songify.library.spotify.usecase.GetPlaylistsForCategory
import retrofit2.HttpException
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetHomeFeedImpl @Inject constructor(
    private val getNewReleases: GetNewReleases,
    private val getFeaturedPlaylists: GetFeaturedPlaylists,
    private val getCategories: GetCategories,
    private val getPlaylistsForCategory: GetPlaylistsForCategory,
) : GetHomeFeed {
    private val cache = ConcurrentHashMap<Int, Result<HomeFeed>>()

    override suspend fun invoke(): Result<HomeFeed> =
        cache.getOrPut(0) {
            try {
                val carousels = mutableListOf<HomeFeedCarousel>()

                carousels.add(
                    HomeFeedCarousel(
                        id = "Newly Released Albums",
                        title = "Newly Released Albums",
                        spotifyModels = getNewReleases(),
                    )
                )
                carousels.add(
                    HomeFeedCarousel(
                        id = "Featured Playlists",
                        title = "Featured Playlists",
                        spotifyModels = getFeaturedPlaylists()
                    )
                )
                carousels.addAll(getPlaylists())

                Result.success(HomeFeed(carousels))
            } catch (ex: HttpException) {
                Result.failure(ex)
            }
        }

    private suspend fun getPlaylists(): List<HomeFeedCarousel> = getCategories().map { category ->
        HomeFeedCarousel(
            id = category.id,
            title = category.name,
            spotifyModels = getPlaylistsForCategory(category.id)
        )
    }
}

