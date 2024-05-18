package com.songify.library.spotify.response

import com.squareup.moshi.Json

/**
 * A response class that contains a list of tracks in a particular
 * playlist.
 */
data class PlaylistItemsResponse(val items: List<TrackResponseWithAlbumMetadataWrapper>) {
    data class TrackResponseWithAlbumMetadataWrapper(@Json(name = "track") val track: TrackResponseWithAlbumMetadata)
}

