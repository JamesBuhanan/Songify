package com.songify.library.spotify.usecase

import com.songify.library.spotify.response.BrowseCategoriesResponse

interface GetBrowseCategories {
    suspend operator fun invoke(
        locale: String,
        limit: Int = 20,
        offset: Int = 0
    ): Result<BrowseCategoriesResponse>
}
