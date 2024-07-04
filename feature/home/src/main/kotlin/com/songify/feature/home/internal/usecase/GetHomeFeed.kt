package com.songify.feature.home.internal.usecase

import com.songify.feature.home.internal.model.HomeFeed
import com.songify.feature.home.internal.model.HomeFeedCarousel
import com.songify.library.di.FeatureScope
import com.songify.library.spotify.usecase.GetCategories
import com.songify.library.spotify.usecase.GetFeaturedPlaylists
import com.songify.library.spotify.usecase.GetNewReleases
import com.songify.library.spotify.usecase.GetPlaylistsForCategory
import com.squareup.anvil.annotations.ContributesBinding
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

interface GetHomeFeed {
    suspend operator fun invoke(): Result<HomeFeed>
}

@ContributesBinding(FeatureScope::class)
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
            } catch (ex: Exception) {
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
