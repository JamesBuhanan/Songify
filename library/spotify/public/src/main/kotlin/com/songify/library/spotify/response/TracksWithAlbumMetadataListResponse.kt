package com.songify.library.spotify.response

import com.squareup.moshi.Json

/**
 * A response object that contains a list of [TrackResponseWithAlbumMetadata].
 */
data class TracksWithAlbumMetadataListResponse(@Json(name = "tracks") val value: List<TrackResponseWithAlbumMetadata>)