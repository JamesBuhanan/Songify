package com.songify.library.spotify.usecase

import com.songify.library.spotify.response.AlbumResponse
import com.songify.library.spotify.response.PlaylistsForSpecificCategoryResponse

interface GetPlaylistsForCategory {
    suspend operator fun invoke(categoryId: String, limit: Int, offset: Int): Result<PlaylistsForSpecificCategoryResponse>
}

