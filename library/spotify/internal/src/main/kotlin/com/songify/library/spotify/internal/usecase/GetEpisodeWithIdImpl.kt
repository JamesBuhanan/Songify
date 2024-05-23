package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.SpotifyService
import com.songify.library.spotify.response.AlbumResponse
import com.songify.library.spotify.response.EpisodeResponse
import com.songify.library.spotify.usecase.GetAlbum
import com.songify.library.spotify.usecase.GetEpisodeWithId
import com.squareup.anvil.annotations.ContributesBinding
import retrofit2.HttpException
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetEpisodeWithIdImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetEpisodeWithId {
    override suspend operator fun invoke(id: String): Result<EpisodeResponse> {
        return try {
            val artist = spotifyService.getEpisodeWithId(
                id = id,
                token = songifySession.requireAccessToken(),
            )
            Result.success(artist)
        } catch (ex: HttpException) {
            Result.failure(ex)
        }
    }
}