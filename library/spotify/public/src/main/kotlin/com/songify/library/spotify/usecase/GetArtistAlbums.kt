package com.songify.library.spotify.usecase

import com.songify.library.spotify.response.AlbumsMetadataResponse

interface GetArtistAlbums {
    suspend operator fun invoke(
        artistId: String,
        limit: Int = 20,
        offset: Int = 0,
        includeGroups: String? = null,
    ): Result<AlbumsMetadataResponse>
}
