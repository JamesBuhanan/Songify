package com.songify.library.spotify.internal.paging

import com.songify.common.session.SongifySession
import com.songify.library.spotify.internal.SpotifyService
import com.songify.library.spotify.internal.model.toTrack
import com.songify.library.spotify.model.SpotifyModel.Track
import retrofit2.HttpException
import java.io.IOException

class PlaylistTracksPagingSource(
    playlistId: String,
    songifySession: SongifySession,
    spotifyService: SpotifyService
) : SpotifyPagingSource<Track>(
    loadBlock = { limit, offset ->
        try {
            val data = spotifyService.getTracksForPlaylist(
                playlistId = playlistId,
                token = songifySession.requireAccessToken(),
                limit = limit,
                offset = offset
            ).items.map { it.track.toTrack() }

            Result.success(data)
        } catch (httpException: HttpException) {
            Result.failure(httpException)
        } catch (ioException: IOException) {
            Result.failure(ioException)
        }
    }
)