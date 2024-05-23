package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.SpotifyService
import com.songify.library.spotify.response.AlbumResponse
import com.songify.library.spotify.response.ShowResponse
import com.songify.library.spotify.usecase.GetAlbum
import com.songify.library.spotify.usecase.GetShowWithId
import com.squareup.anvil.annotations.ContributesBinding
import retrofit2.HttpException
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetShowWithIdImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetShowWithId {
    override suspend operator fun invoke(id: String): Result<ShowResponse> {
        return try {
            val artist = spotifyService.getShowWithId(
                id = id,
                token = songifySession.requireAccessToken(),
            )
            Result.success(artist)
        } catch (ex: HttpException) {
            Result.failure(ex)
        }
    }
}

// suspend fun getShowWithId(
//        @Path("id") id: String,
//    ): ShowResponse