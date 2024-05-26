package com.songify.library.spotify.usecase

import com.songify.library.spotify.response.PlaylistsForSpecificCategoryResponse

interface GetPlaylistsForCategory {
    suspend operator fun invoke(
        categoryId: String,
        limit: Int = 20,
        offset: Int = 0,
    ): Result<PlaylistsForSpecificCategoryResponse>
}
