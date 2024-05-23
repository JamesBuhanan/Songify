package com.songify.library.spotify.usecase

import com.songify.library.spotify.response.FeaturedPlaylistsResponse

interface GetFeaturedPlaylists {
    suspend operator fun invoke(
        locale: String,
        timestamp: String,
        limit: Int = 20,
        offset: Int = 0): Result<FeaturedPlaylistsResponse>
}
