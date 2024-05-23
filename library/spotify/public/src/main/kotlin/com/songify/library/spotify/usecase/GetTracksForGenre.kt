package com.songify.library.spotify.usecase

import com.songify.library.spotify.SupportedSpotifyGenres
import com.songify.library.spotify.response.TracksWithAlbumMetadataListResponse

interface GetTracksForGenre {
    suspend operator fun invoke(genre: SupportedSpotifyGenres, limit: Int): Result<TracksWithAlbumMetadataListResponse>
}
