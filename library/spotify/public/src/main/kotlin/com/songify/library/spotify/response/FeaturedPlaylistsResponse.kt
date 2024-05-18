package com.songify.library.spotify.response

import com.squareup.moshi.Json

/**
 * A response class that contains an instance of [FeaturedPlaylistsResponse]
 * which will contain a list of featured playlists. This response class also
 * has a property called [playlistsDescription] that represents a common
 * description of all the playlists.
 */
data class FeaturedPlaylistsResponse(
    @Json(name = "message") val playlistsDescription: String,
    val playlists: FeaturedPlaylistItemsResponse
) {
    /**
     * A response class that contains a list of [PlaylistMetadataResponse].
     */
    data class FeaturedPlaylistItemsResponse(val items: List<PlaylistMetadataResponse>)
}
