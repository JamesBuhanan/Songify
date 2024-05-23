package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.SpotifyService
import com.songify.library.spotify.response.AlbumResponse
import com.songify.library.spotify.response.PlaylistsForSpecificCategoryResponse
import com.songify.library.spotify.usecase.GetAlbum
import com.songify.library.spotify.usecase.GetPlaylistsForCategory
import com.squareup.anvil.annotations.ContributesBinding
import retrofit2.HttpException
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetPlaylistsForCategoryImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetPlaylistsForCategory {
    override suspend operator fun invoke(categoryId: String, limit: Int, offset: Int): Result<PlaylistsForSpecificCategoryResponse> {
        return try {
            val artist = spotifyService.getPlaylistsForCategory(
                categoryId = categoryId,
                limit = 20,
                token = songifySession.requireAccessToken(),
                offset = 0
            )
            Result.success(artist)
        } catch (ex: HttpException) {
            Result.failure(ex)
        }
    }
}
