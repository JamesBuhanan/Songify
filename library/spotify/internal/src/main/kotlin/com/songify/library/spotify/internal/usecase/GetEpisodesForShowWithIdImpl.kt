package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.SpotifyService
import com.songify.library.spotify.response.AlbumResponse
import com.songify.library.spotify.response.EpisodesWithPreviewUrlResponse
import com.songify.library.spotify.usecase.GetAlbum
import com.songify.library.spotify.usecase.GetEpisodeWithId
import com.songify.library.spotify.usecase.GetEpisodesForShowWithId
import com.squareup.anvil.annotations.ContributesBinding
import retrofit2.HttpException
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetEpisodesForShowWithIdImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetEpisodesForShowWithId {
    override suspend fun invoke(id: String, offset: Int): Result<EpisodesWithPreviewUrlResponse> {
        return try {
            val artist = spotifyService.getEpisodesForShowWithId(
                id = id,
                token = songifySession.requireAccessToken(),
                limit = 20,
                offset = 0
            )
            Result.success(artist)
        } catch (ex: HttpException) {
            Result.failure(ex)
        }
    }
}
//suspend fun getEpisodesForShowWithId(
//        @Path("id") id: String,
//        @Query("limit") limit: Int = 20,
//        @Query("offset") offset: Int = 0
//    ): EpisodesWithPreviewUrlResponse