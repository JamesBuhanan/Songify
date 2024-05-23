package com.songify.library.spotify.usecase

import com.songify.library.spotify.response.EpisodesWithPreviewUrlResponse

interface GetEpisodesForShowWithId {
    suspend operator fun invoke(id: String, offset: Int): Result<EpisodesWithPreviewUrlResponse>
}