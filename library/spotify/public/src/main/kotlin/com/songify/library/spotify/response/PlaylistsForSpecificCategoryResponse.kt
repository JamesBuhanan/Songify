package com.songify.library.spotify.response

import com.songify.library.spotify.response.PlaylistMetadataResponse.OwnerNameWrapper
import com.squareup.moshi.Json

/**
 * A response object that contains a list of [playlists] that are associated
 * with a particular spotify category.
 */
data class PlaylistsForSpecificCategoryResponse(val playlists: PlaylistsForSpecificCategoryResponseItems) {
    /**
     * A response class that contains a list of [PlaylistMetadataWithImageUrlListResponse].
     */
    data class PlaylistsForSpecificCategoryResponseItems(val items: List<PlaylistMetadataWithImageUrlListResponse>)

    /**
     * A response class that is very similar to [PlaylistMetadataResponse]. This class
     * only differs in the fact that it has [imageUrlList] instead of [PlaylistMetadataResponse.images]
     * since the dimension information will not be provided for [PlaylistsForSpecificCategoryResponse].
     */

    data class PlaylistMetadataWithImageUrlListResponse(
        val id: String,
        val name: String,
        @Json(name = "images") val imageUrlList: List<ImageUrlResponse>,
        @Json(name = "owner") val ownerName: OwnerNameWrapper,
        @Json(name = "tracks") val totalNumberOfTracks: PlaylistMetadataResponse.TotalNumberOfTracksWrapper
    )

    /**
     * A response class that contains a [url] for an image.
     */
    data class ImageUrlResponse(val url: String)
}
