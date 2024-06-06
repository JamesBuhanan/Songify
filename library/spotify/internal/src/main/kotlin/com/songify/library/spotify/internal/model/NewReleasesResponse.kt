package com.songify.library.spotify.internal.model

/**
 * A response object that contains [albums] that were newly released.
 */
data class NewReleasesResponse(val albums: Albums) {
    /**
     * A response object that contains a list of [AlbumMetadataResponse].
     */
    data class Albums(val items: List<AlbumMetadataResponse>)
}
