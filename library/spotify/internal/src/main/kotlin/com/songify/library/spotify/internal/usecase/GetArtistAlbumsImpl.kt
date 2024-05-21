package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.SpotifyService
import com.songify.library.spotify.response.AlbumsMetadataResponse
import com.songify.library.spotify.response.ArtistResponse
import com.songify.library.spotify.usecase.GetArtist
import com.songify.library.spotify.usecase.GetArtistAlbums
import com.squareup.anvil.annotations.ContributesBinding
import retrofit2.HttpException
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetArtistAlbumsImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetArtistAlbums {
    override suspend operator fun invoke(artistId: String, limit: Int, offset: Int, includeGroups: String?): Result<AlbumsMetadataResponse> {
        return try {
            val artist = spotifyService.getAlbumsOfArtistWithId(
                artistId = artistId,
                token = songifySession.requireAccessToken(),
                limit = limit,
                offset = offset,
                includeGroups = includeGroups




            )
            Result.success(artist)
        } catch (ex: HttpException) {
            Result.failure(ex)
        }
    }
}
