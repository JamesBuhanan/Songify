package com.songify.library.spotify.internal.model

/**
 * A response object that contains a list of [TrackResponseWithAlbumMetadata].
 */

data class TracksWithAlbumMetadataListResponse(val tracks: List<TrackResponseWithAlbumMetadata>)
