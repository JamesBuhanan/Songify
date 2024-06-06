package com.songify.library.spotify.internal.model

import com.squareup.moshi.Json

/**
 * A response object that contains information about a specific show.
 */
data class ShowResponse(
    val id: String,
    val name: String,
    val publisher: String,
    @Json(name = "html_description") val htmlDescription: String,
    val images: List<ImageResponse>
)
