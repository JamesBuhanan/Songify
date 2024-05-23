package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.SpotifyService
import com.songify.library.spotify.response.AlbumResponse
import com.songify.library.spotify.response.FeaturedPlaylistsResponse
import com.songify.library.spotify.usecase.GetAlbum
import com.songify.library.spotify.usecase.GetFeaturedPlaylists
import com.squareup.anvil.annotations.ContributesBinding
import retrofit2.HttpException
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetFeaturedPlaylistsImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetFeaturedPlaylists {
    override suspend operator fun invoke(locale: String,
                                         timestamp: String,
                                         limit: Int,
                                         offset: Int): Result<FeaturedPlaylistsResponse> {
        return try {
            val artist = spotifyService.getFeaturedPlaylists(
                locale = locale,
                timestamp = timestamp,
                token = songifySession.requireAccessToken(),
                limit = limit,
                offset = offset,
            )
            Result.success(artist)
        } catch (ex: HttpException) {
            Result.failure(ex)
        }
    }
}
//    @Header("Authorization") token: String,
//        @Query("locale") locale: String = "", // ISO 639-1 language code and an uppercase ISO 3166-1 alpha-2 country code, joined by an underscore.
//        @Query("timestamp") timestamp: String = "", // A timestamp in ISO 8601 format: yyyy-MM-ddTHH:mm:ss
//        @Query("limit") limit: Int = 20,
//        @Query("offset") offset: Int = 0