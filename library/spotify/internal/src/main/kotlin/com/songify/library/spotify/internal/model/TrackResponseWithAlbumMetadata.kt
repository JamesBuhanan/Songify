package com.songify.library.spotify.internal.model

import com.squareup.moshi.Json

/**
 * A response object that contains information about a specific track
 * together with the metadata of the associated album.
 */
data class TrackResponseWithAlbumMetadata(
    val id: String,
    val name: String,
    @Json(name = "preview_url") val previewUrl: String?,
    @Json(name = "is_playable") val isPlayable: Boolean?,
    val explicit: Boolean,
    @Json(name = "duration_ms") val durationInMillis: Int,
    @Json(name = "album") val albumMetadata: AlbumMetadataResponse
)
