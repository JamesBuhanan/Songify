package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.SpotifyService
import com.songify.library.spotify.response.AlbumsMetadataResponse
import com.songify.library.spotify.response.ArtistResponse
import com.songify.library.spotify.response.TracksWithAlbumMetadataListResponse
import com.songify.library.spotify.usecase.GetArtist
import com.songify.library.spotify.usecase.GetTopTracks
import com.squareup.anvil.annotations.ContributesBinding
import retrofit2.HttpException
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetTopTracksImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetTopTracks {
    override suspend operator fun invoke(artistId: String): Result<TracksWithAlbumMetadataListResponse> {
        return try {
            val artist = spotifyService.getTopTenTracksForArtistWithId(
                artistId = artistId,
                token = songifySession.requireAccessToken(),
            )
            Result.success(artist)
        } catch (ex: HttpException) {
            Result.failure(ex)
        }
    }
}
