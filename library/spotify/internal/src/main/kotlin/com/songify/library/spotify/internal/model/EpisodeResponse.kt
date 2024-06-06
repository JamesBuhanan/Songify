package com.songify.library.spotify.internal.model

import com.squareup.moshi.Json

/**
 * A response object that contains information about a specific episode.
 */
data class EpisodeResponse(
    val id: String,
    @Json(name = "name") val title: String,
    @Json(name = "images") val episodeImages: List<ImageResponse>,
    val description: String,
    @Json(name = "html_description") val htmlDescription: String,
    @Json(name = "duration_ms") val durationMillis: Long,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "audio_preview_url") val previewUrl: String?,
    val show: EpisodeShowResponse
) {
    data class EpisodeShowResponse(
        val id: String,
        val name: String,
        val images: List<ImageResponse>
    )
}
