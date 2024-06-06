package com.songify.library.spotify.internal.model

/**
 * A response object the contains information about an image's [height],
 * [width] and [url].
 */
data class ImageResponse(
    val height: Int?,
    val width: Int?,
    val url: String
)
