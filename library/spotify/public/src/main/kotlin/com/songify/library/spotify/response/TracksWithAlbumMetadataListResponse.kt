package com.songify.library.spotify.response

/**
 * A response object that contains a list of [TrackResponseWithAlbumMetadata].
 */

data class TracksWithAlbumMetadataListResponse(val tracks: List<TrackResponseWithAlbumMetadata>)
