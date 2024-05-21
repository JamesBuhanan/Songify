package com.songify.library.spotify.usecase

import com.songify.library.spotify.response.TracksWithAlbumMetadataListResponse

interface GetTopTracks {
    suspend operator fun invoke(artistId: String): Result<TracksWithAlbumMetadataListResponse>
}
