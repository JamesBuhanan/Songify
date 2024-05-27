package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.common.session.SongifySession
import com.songify.library.spotify.SpotifyService
import com.songify.library.spotify.response.NewReleasesResponse
import com.songify.library.spotify.usecase.GetNewReleases
import com.squareup.anvil.annotations.ContributesBinding
import retrofit2.HttpException
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetNewReleasesImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetNewReleases {
    override suspend operator fun invoke(): Result<NewReleasesResponse> {
        return try {
            val newReleases = spotifyService.getNewReleases(songifySession.requireAccessToken())
            Result.success(newReleases)
        } catch (ex: HttpException) {
            Result.failure(ex)
        }
    }
}
