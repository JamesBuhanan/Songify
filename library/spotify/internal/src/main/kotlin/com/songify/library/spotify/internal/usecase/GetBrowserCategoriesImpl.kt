package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.SpotifyService
import com.songify.library.spotify.response.ArtistResponse
import com.songify.library.spotify.response.BrowseCategoriesResponse
import com.songify.library.spotify.usecase.GetArtist
import com.songify.library.spotify.usecase.GetBrowseCategories
import com.squareup.anvil.annotations.ContributesBinding
import retrofit2.HttpException
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetBrowserCategoriesImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetBrowseCategories {
    override suspend operator fun invoke(locale: String,
                                         limit: Int,
                                         offset: Int): Result<BrowseCategoriesResponse> {
        return try {
            val artist = spotifyService.getBrowseCategories(
                locale = locale,
                token = songifySession.requireAccessToken(),
                limit = limit,
                offset = offset
            )
            Result.success(artist)
        } catch (ex: HttpException) {
            Result.failure(ex)
        }
    }
}
//suspend fun getBrowseCategories(
//        @Query("locale") locale: String, // ISO 639-1 language code and an uppercase ISO 3166-1 alpha-2 country code, joined by an underscore.
//        @Query("limit") limit: Int = 20,
//        @Query("offset") offset: Int = 0
//    ): BrowseCategoriesResponse