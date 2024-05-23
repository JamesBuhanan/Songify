package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.SpotifyService
import com.songify.library.spotify.SupportedSpotifyGenres
import com.songify.library.spotify.response.TracksWithAlbumMetadataListResponse
import com.songify.library.spotify.usecase.GetTracksForGenre
import com.squareup.anvil.annotations.ContributesBinding
import retrofit2.HttpException
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetTracksForGenreImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetTracksForGenre {
    override suspend operator fun invoke(genre: SupportedSpotifyGenres, limit: Int): Result<TracksWithAlbumMetadataListResponse> {
        return try {
            val artist = spotifyService.getTracksForGenre(
                genre = genre,
                limit = 20,
                token = songifySession.requireAccessToken(),
            )
            Result.success(artist)
        } catch (ex: HttpException) {
            Result.failure(ex)
        }
    }
}
