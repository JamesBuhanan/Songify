package com.songify.library.spotify.usecase

import com.songify.library.spotify.SupportedSpotifyGenres
import com.songify.library.spotify.response.TracksWithAlbumMetadataListResponse

interface GetTracksForGenre {
    suspend operator fun invoke(genre: SupportedSpotifyGenres, limit: Int): Result<TracksWithAlbumMetadataListResponse>
//     @Query("seed_genres") genre: SupportedSpotifyGenres,
//        @Header("Authorization") token: String,
//        @Query("limit") limit: Int = 20
}
