package com.songify.library.spotify.usecase

import com.songify.library.spotify.response.EpisodeResponse

interface GetEpisodeWithId {
    suspend operator fun invoke(id: String): Result<EpisodeResponse>
}