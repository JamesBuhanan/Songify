package com.songify.library.spotify.internal.paging

import com.songify.library.di.AppScope
import com.songify.library.session.SongifySession
import com.songify.library.spotify.internal.SpotifyService
import com.songify.library.spotify.internal.model.toPlaylistCard
import com.songify.library.spotify.model.SpotifyModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@AppScope
class FeaturedPlaylistsPagingSource @Inject constructor(
    songifySession: SongifySession,
    spotifyService: SpotifyService
) : SpotifyPagingSource<SpotifyModel>(
    loadBlock = { limit, offset ->
        try {
            val data = spotifyService.getFeaturedPlaylists(
                token = songifySession.requireAccessToken(),
                limit = limit,
                offset = offset,
            ).playlists.items.map { it.toPlaylistCard() }

            Result.success(data)
        } catch (httpException: HttpException) {
            Result.failure(httpException)
        } catch (ioException: IOException) {
            Result.failure(ioException)
        }
    }
)
