package com.songify.library.spotify.usecase

import com.songify.library.spotify.response.AlbumResponse
import com.songify.library.spotify.response.ShowResponse

interface GetShowWithId {
    suspend operator fun invoke(id: String): Result<ShowResponse>
}