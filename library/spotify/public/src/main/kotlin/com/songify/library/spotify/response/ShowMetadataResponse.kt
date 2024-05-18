package com.songify.library.spotify.response

/**
 * A response object that contains metadata about a specific show.
 */
data class ShowMetadataResponse(
    val id: String,
    val name: String,
    val publisher: String,
    val images: List<ImageResponse>
)
