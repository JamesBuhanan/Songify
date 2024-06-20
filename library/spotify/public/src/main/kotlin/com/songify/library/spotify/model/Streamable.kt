package com.songify.library.spotify.model

/**
 * Indicates a class that represents something that can be streamed over
 * a network.
 */
sealed interface Streamable {
    val streamInfo: StreamInfo
}

/**
 * A class that contains contains information about a specific [Streamable].
 * Note that it is possible that a class may contain a null [streamUrl] because
 * the link might not be available for that specific instance.
 * For example, an API might return a list of tracks with a nullable
 * preview url, where the preview url for certain tracks might be null.
 */
data class StreamInfo(
    val streamUrl: String,
    val imageUrl: String,
    val title: String,
    val subtitle: String,
)
