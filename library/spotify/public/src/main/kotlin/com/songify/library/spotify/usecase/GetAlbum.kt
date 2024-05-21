package com.songify.library.spotify.usecase

import com.songify.library.spotify.response.AlbumResponse

interface GetAlbum {
    suspend operator fun invoke(albumId: String): Result<AlbumResponse>
}
