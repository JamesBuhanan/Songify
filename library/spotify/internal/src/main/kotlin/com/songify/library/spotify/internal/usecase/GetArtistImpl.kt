package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.SpotifyService
import com.songify.library.spotify.response.ArtistResponse
import com.songify.library.spotify.response.NewReleasesResponse
import com.songify.library.spotify.usecase.GetArtist
import com.songify.library.spotify.usecase.GetNewReleases
import com.squareup.anvil.annotations.ContributesBinding
import retrofit2.HttpException
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetArtistImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetArtist {
    override suspend operator fun invoke(artistId: String): Result<ArtistResponse> {
        return try {
            val artist = spotifyService.getArtistInfoWithId(
                artistId = artistId,
                token = songifySession.requireAccessToken(),
            )
            Result.success(artist)
        } catch (ex: HttpException) {
            Result.failure(ex)
        }
    }
}
