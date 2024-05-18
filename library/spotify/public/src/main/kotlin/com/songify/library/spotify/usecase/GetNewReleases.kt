package com.songify.library.spotify.usecase

import com.songify.library.spotify.response.NewReleasesResponse

interface GetNewReleases {
    suspend operator fun invoke(): Result<NewReleasesResponse>
}
