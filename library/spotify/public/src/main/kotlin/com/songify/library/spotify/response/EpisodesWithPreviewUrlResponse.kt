package com.songify.library.spotify.response

import com.songify.library.spotify.response.EpisodesWithPreviewUrlResponse.EpisodeMetadataResponseWithPreviewUrl
import com.squareup.moshi.Json

/**
 * A response object that contains a list of [EpisodeMetadataResponseWithPreviewUrl].
 */
data class EpisodesWithPreviewUrlResponse(val items: List<EpisodeMetadataResponseWithPreviewUrl>) {
    /**
     * A response object that contains metadata of a specific episode together
     * with a [previewUrl]. It is essentially the same as [EpisodeMetadataResponse]
     * with a field for [previewUrl].
     */
    data class EpisodeMetadataResponseWithPreviewUrl(
        val id: String,
        @Json(name = "name") val title: String,
        val description: String,
        @Json(name = "html_description") val htmlDescription: String,
        @Json(name = "duration_ms") val durationMillis: Long,
        val images: List<ImageResponse>,
        @Json(name = "release_date") val releaseDate: String,
        @Json(name = "audio_preview_url") val previewUrl: String?
    )
}
