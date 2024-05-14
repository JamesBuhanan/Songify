package com.songify.remote.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.songify.remote.response.TrackResponseWithAlbumMetadata

/**
 * A response object that contains a list of [TrackResponseWithAlbumMetadata].
 */
data class TracksWithAlbumMetadataListResponse(@JsonProperty("tracks") val value: List<TrackResponseWithAlbumMetadata>)