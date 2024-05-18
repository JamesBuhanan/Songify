package com.songify.library.spotify.usecase

import com.songify.library.spotify.response.ArtistResponse

interface GetArtist {
    suspend operator fun invoke(artistId: String): Result<ArtistResponse>
}
