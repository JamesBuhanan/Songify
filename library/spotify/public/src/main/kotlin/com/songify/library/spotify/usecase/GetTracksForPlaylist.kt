package com.songify.library.spotify.usecase

import com.songify.library.spotify.response.AlbumResponse
import com.songify.library.spotify.response.PlaylistItemsResponse

interface GetTracksForPlaylist {
    suspend operator fun invoke(playlistId: String, limit: Int = 20, offset: Int = 0): Result<PlaylistItemsResponse>
}