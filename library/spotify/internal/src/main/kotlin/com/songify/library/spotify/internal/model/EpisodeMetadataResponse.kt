package com.songify.library.spotify.internal.model

import com.squareup.moshi.Json

/**
 * A response object that contains metadata of a specific episode.
 */
data class EpisodeMetadataResponse(
    val id: String,
    @Json(name = "name") val title: String,
    val description: String,
    @Json(name = "duration_ms") val durationMillis: Long,
    val images: List<ImageResponse>,
    @Json(name = "release_date") val releaseDate: String
)
