package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.SpotifyService
import com.songify.library.spotify.response.AlbumResponse
import com.songify.library.spotify.response.PlaylistItemsResponse
import com.songify.library.spotify.usecase.GetAlbum
import com.songify.library.spotify.usecase.GetTracksForPlaylist
import com.squareup.anvil.annotations.ContributesBinding
import retrofit2.HttpException
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetTracksForPlaylistImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetTracksForPlaylist {
    override suspend operator fun invoke(
        playlistId: String,
        limit: Int,
        offset: Int
    ): Result<PlaylistItemsResponse> {
        return try {
            val artist = spotifyService.getTracksForPlaylist(
                playlistId = playlistId,
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
//suspend fun getTracksForPlaylist(
//        @Path("playlist_id") playlistId: String,
//        @Header("Authorization") token: String,
//        @Query("limit") limit: Int = 20,
//        @Query("offset") offset: Int = 0
//    ): PlaylistItemsResponse